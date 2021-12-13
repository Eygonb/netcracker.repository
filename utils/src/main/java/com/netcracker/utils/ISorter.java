package com.netcracker.utils;

import java.util.Comparator;

public interface ISorter<T> {
    void sort(List<T> list, Comparator<T> comparator);
}
