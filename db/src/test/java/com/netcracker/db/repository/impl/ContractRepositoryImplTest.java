package com.netcracker.db.repository.impl;

import com.netcracker.db.entity.*;
import com.netcracker.utils.List;
import com.netcracker.utils.impl.MyArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

public class ContractRepositoryImplTest {
    private ContractRepositoryImpl contractRepository;
    private static Client client;
    private static List<Contract> expectedValues = new MyArrayList<>();

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

    @Before
    public void setUp() {
        contractRepository = new ContractRepositoryImpl();
        for (int i = 0; i < expectedValues.size(); i++) {
            contractRepository.save(expectedValues.get(i));
        }
    }

    @Test
    public void saveAndGetByIdTest() {
        for (int i = 0; i < expectedValues.size(); i++) {
            Assert.assertEquals(expectedValues.get(i), contractRepository.getById(i + 1));
        }
        Contract testContract = new DigitalTVContract(3, new Date(), new Date(), "1", client,
                new String[]{"1"});
        contractRepository.save(testContract);
        Assert.assertEquals(testContract, contractRepository.getById(3));
        Assert.assertNull(contractRepository.getById(4));
    }

    @Test
    public void removeByIdTest() {
        Assert.assertTrue(contractRepository.removeById(3));
        Assert.assertNull(contractRepository.getById(3));
        Assert.assertFalse(contractRepository.removeById(3));
    }

    @Test
    public void saveAllTest() {
        List<Contract> testValue = new MyArrayList<>();
        testValue.add(new MobileContract(4, new Date(), new Date(), "4", client,
                500, 50, 50));
        testValue.add(new WiredInternetContract(5, new Date(), new Date(), "5", client,
                100));

        contractRepository.saveAll(testValue);
        for (int i = expectedValues.size(); i < expectedValues.size() + testValue.size(); i++) {
            Assert.assertEquals(testValue.get(i - expectedValues.size()), contractRepository.getById(i + 1));
        }
    }
}
