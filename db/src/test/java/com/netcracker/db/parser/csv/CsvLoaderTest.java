package com.netcracker.db.parser.csv;

import com.netcracker.db.entity.*;
import com.netcracker.db.repository.impl.ContractRepositoryImpl;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CsvLoaderTest {
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

        ContractRepositoryImpl repository = new ContractRepositoryImpl();
        CsvLoader loader = new CsvLoader();
        loader.loadRepository("testCsvData.csv", repository);

        Assert.assertEquals(digitalContract, repository.getById(1));
        Assert.assertEquals(mobileContract, repository.getById(2));
        Assert.assertEquals(wiredInternetContract, repository.getById(3));
    }
}
