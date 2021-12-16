package com.netcracker.utils.impl;

import com.netcracker.utils.ISorter;
import com.netcracker.utils.List;

import java.util.Comparator;

public class SelectionSorter<T> implements ISorter<T> {
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        for (int min = 0; min < list.size()-1; min++) {
            int least = min;
            for (int j = min + 1; j < list.size(); j++) {
                if (comparator.compare(list.get(j), list.get(least)) < 0) {
                    least = j;
                }
            }
            swap(list, min, least);
        }
    }

    private void swap(List<T> list, int firstIndex, int secondIndex) {
        T temp = list.get(firstIndex);
        list.set(firstIndex, list.get(secondIndex));
        list.set(secondIndex, temp);
    }
}
