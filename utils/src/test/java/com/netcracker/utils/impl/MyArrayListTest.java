package com.netcracker.utils.impl;

import com.netcracker.utils.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class MyArrayListTest {
    private final int[] expectedValues = {1, 2, 3, 4, 5};
    private List<Integer> list;

    @Before
    public void setUp() {
        list = new MyArrayList<>();

        for (int expectedValue : expectedValues) {
            list.add(expectedValue);
        }
    }

    @Test
    public void addAndGetTest() {
        for (int i = 0; i < expectedValues.length; i++) {
            Assert.assertEquals(expectedValues[i], (int) list.get(i));
        }
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.get(100));
    }

    @Test
    public void addAllTest() {
        List<Integer> newList = new MyArrayList<>();

        newList.addAll(list);

        Assert.assertEquals(list.toArray(new Integer[0]), newList.toArray(new Integer[0]));
    }

    @Test
    public void sizeTest() {
        Assert.assertEquals(expectedValues.length, list.size());
    }


    @Test
    public void clearAndIsEmptyTest() {
        List<Integer> list = new MyArrayList<>();
        Assert.assertTrue(list.isEmpty());

        for (int expectedValue : expectedValues) {
            list.add(expectedValue);
        }
        Assert.assertFalse(list.isEmpty());

        list.clear();
        Assert.assertTrue(list.isEmpty());
    }


    @Test
    public void indexOfTest() {
        for (int i = 0; i < expectedValues.length; i++) {
            Assert.assertEquals(i, list.indexOf(expectedValues[i]));
        }

        Assert.assertEquals(-1, list.indexOf(100));
    }

    @Test
    public void resizeTest() {
        List<Integer> list = new MyArrayList<>();
        int size = 10000;
        Integer[] values = new Integer[size];

        for (int i = 0; i < size; i++) {
            values[i] = new Random().nextInt();
            list.add(values[i]);
        }

        for (int i = 0; i < values.length; i++) {
            Assert.assertEquals(values[i], list.get(i));
        }

        int resizeValue = 100;
        for (int i = resizeValue; i < values.length; i++) {
            list.remove(resizeValue);
        }

        for (int i = 0; i < resizeValue; i++) {
            Assert.assertEquals(values[i], list.get(i));
        }

        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.get(size));
    }

    @Test
    public void containsTest() {
        for (int expectedValue : expectedValues) {
            Assert.assertTrue(list.contains(expectedValue));
        }
        Assert.assertFalse(list.contains(10));
        Assert.assertFalse(list.contains(20));
        Assert.assertFalse(list.contains(100));
    }

    @Test
    public void removeTest() {
        for (int i = 0; i < expectedValues.length - 1; i++) {
            list.remove(0);
        }
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(expectedValues[expectedValues.length - 1], (int) list.get(0));
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));

        list.clear();

        for (int expectedValue : expectedValues) {
            list.add(expectedValue);
        }

        for (int i = 0; i < expectedValues.length - 1; i++) {
            list.remove((Integer) expectedValues[i]);
        }
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(expectedValues[expectedValues.length - 1], (int) list.get(0));
        Assert.assertFalse(list.remove((Integer) 100));
    }

    @Test
    public void setTest() {
        for (int i = 0; i < expectedValues.length; i++) {
            list.set(i, expectedValues[i] + 1);
        }

        for (int i = 0; i < expectedValues.length; i++) {
            Assert.assertEquals(expectedValues[i] + 1, (int) list.get(i));
        }
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.set(100, 100));
    }

}
