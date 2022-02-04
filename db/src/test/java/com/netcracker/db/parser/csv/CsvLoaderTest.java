package com.netcracker.db.parser.csv;

import com.netcracker.db.entity.*;
import com.netcracker.db.repository.impl.ContractRepositoryImpl;
import com.netcracker.db.validator.model.ValidationEnum;
import com.netcracker.db.validator.model.ValidationResult;
import com.netcracker.utils.ISorter;
import com.netcracker.utils.impl.SelectionSorter;
import org.di.Application;
import org.di.ApplicationContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class CsvLoaderTest {
    private static ApplicationContext app;
    private static CsvLoader loader;
    ContractRepositoryImpl repository;

    @Before
    public void init() {
        app = Application.run(new HashMap<>(Map.of(ISorter.class, SelectionSorter.class)));

        loader = app.getObject(CsvLoader.class);
        repository = new ContractRepositoryImpl();
    }

    @Test
    public void loadTest() throws ParseException {
        SimpleDateFormat dateTimeParser = new SimpleDateFormat("HH:mm dd.MM.yyyy");

        Client client = new Client(1, "TEST_FN",
                dateTimeParser.parse("00:00 01.01.1970"), SexEnum.MALE,
                "TEST_PASSPORT_DATA");
        Contract digitalContract = new DigitalTVContract(
                1,
                dateTimeParser.parse("00:00 01.01.2000"),
                dateTimeParser.parse("00:00 01.01.2001"),
                "1",
                client,
                new String[]{"1"});
        Contract mobileContract = new MobileContract(2,
                dateTimeParser.parse("00:00 01.01.2000"),
                dateTimeParser.parse("00:00 01.01.2001"),
                "2",
                client,
                500, 50, 50);
        Contract wiredInternetContract = new WiredInternetContract(3,
                dateTimeParser.parse("00:00 01.01.2000"),
                dateTimeParser.parse("00:00 01.01.2001"),
                "3",
                client,
                100);

        loader.loadRepository("testCsvData.csv", repository);

        Assert.assertEquals(digitalContract, repository.getById(1));
        Assert.assertEquals(mobileContract, repository.getById(2));
        Assert.assertEquals(wiredInternetContract, repository.getById(3));
    }

    @Test
    public void loadBadTest() {
        ValidationResult validationResult = loader.loadRepository("badTestCsvData.csv", repository);

        Assert.assertEquals(validationResult.getResult(), ValidationEnum.ERR);

        String[] errors = {
                "Contract owner full name is empty",
                "Contract owner birth date is invalid",
                "Contract channel package is empty",
                "Contract start date is after contract end date",
                "Contract owner sex is null",
                "Contract owner passport data is empty",
                "Contract minutes, sms and traffic count is invalid",
                "Contract number is empty"
        };

        Assert.assertEquals(errors, validationResult.getErrors().toArray(new String[0]));
    }
}
