package com.netcracker.utils.impl;

import com.netcracker.utils.ISorter;
import com.netcracker.utils.List;

import java.util.Comparator;

public class BubbleSorter<T> implements ISorter<T> {
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        for (int i = list.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (comparator.compare(list.get(i), list.get(i + 1)) > 0)
                    swap(list, i, i + 1);
            }
        }
    }

    private void swap(List<T> list, int firstIndex, int secondIndex) {
        T temp = list.get(firstIndex);
        list.set(firstIndex, list.get(secondIndex));
        list.set(secondIndex, temp);
    }
}
