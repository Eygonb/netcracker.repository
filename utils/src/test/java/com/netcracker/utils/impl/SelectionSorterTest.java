package com.netcracker.utils.impl;

import com.netcracker.db.entity.*;
import com.netcracker.utils.ISorter;
import com.netcracker.utils.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class SelectionSorterTest {
    private static Client client;
    private static List<Contract> expectedValues = new MyArrayList<>();
    private static ISorter<Contract> sorter = new SelectionSorter<>();

    @BeforeClass
    public static void fillExpectedValues() {
        client = new Client(0, "TEST_FN", new Date(), SexEnum.MALE,
                "TEST_PASSPORT_DATA");
        expectedValues.add(new DigitalTVContract(1, new Date(), new Date(), "1", client,
                new String[]{"1"}));
        expectedValues.add(new MobileContract(2, new Date(), new Date(), "2", client,
                500, 50, 50));
        expectedValues.add(new WiredInternetContract(3, new Date(), new Date(), "3", client,
                100));
    }

    @Test
    public void sortTestFirstComp() {
        Comparator<Contract> comp = Comparator.comparingInt(Contract::getId);

        Contract[] arr = expectedValues.toArray(new Contract[0]);

        Arrays.sort(arr, comp);

        sorter.sort(expectedValues, comp);
        Assert.assertArrayEquals(arr, expectedValues.toArray(new Contract[0]));
    }

    @Test
    public void sortTestSecondComp() {
        Comparator<Contract> comp = Comparator.comparing(Contract::getContractStartDate);

        Contract[] arr = expectedValues.toArray(new Contract[0]);

        Arrays.sort(arr, comp);

        sorter.sort(expectedValues, comp);
        Assert.assertArrayEquals(arr, expectedValues.toArray(new Contract[0]));
    }
}
