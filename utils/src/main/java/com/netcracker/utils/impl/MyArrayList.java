package com.netcracker.utils.impl;

import com.netcracker.utils.List;

public class MyArrayList<T> implements List<T> {
    private final int INIT_SIZE = 16;
    private final int CUT_RATE = 4;
    private final double RESIZE_MULTIPLIER = 1.5;
    private Object[] array = new Object[INIT_SIZE];
    private int pointer = 0;

    /**
     * @return the number of elements in this list.
     */
    @Override
    public int size() {
        return pointer;
    }

    /**
     * @return true if this list contains no elements.
     */
    @Override
    public boolean isEmpty() {
        return pointer == 0;
    }

    /**
     * Appends the specified element to the end of this list.
     * @param o element to be appended to this list
     */
    @Override
    public void add(T o) {
        if (pointer == array.length - 1) {
            resize((int) (array.length * RESIZE_MULTIPLIER));
        }
        array[pointer++] = o;
    }

    /**
     * Removes the element at the specified position in this list
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     */
    @Override
    public T remove(int index) {
        if(index < 0) return null;

        T t = (T) array[index];
        if (pointer - index >= 0)
            System.arraycopy(array, index + 1, array, index, pointer - index);
        array[pointer--] = null;
        if (array.length > INIT_SIZE && pointer < array.length / CUT_RATE) {
            resize(array.length / 2);
        }
        
        return t;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * @param o element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    @Override
    public boolean remove(T o) {
        int index = indexOf(o);
        if(index > -1) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * Returns true if this list contains the specified element.
     * @param o element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    @Override
    public boolean contains(T o) {
        for (Object t : array) {
            if (o.equals(t)) return true;
        }
        return false;
    }

    /**
     * Removes all of the elements from this list (optional operation).
     */
    @Override
    public void clear() {
        resize(INIT_SIZE);
        for(int i = 0; i < INIT_SIZE; i++) {
            array[i] = null;
        }
        pointer = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    @Override
    public T get(int index) {
        return (T) array[index];
    }

    /**
     * Replaces the element at the specified position in this list with the specified element (optional operation).
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     */
    @Override
    public T set(int index, T element) {
        array[index] = element;
        return element;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element
     */
    @Override
    public int indexOf(T o) {
        for(int i = 0; i < pointer; i++) {
            if(o.equals(array[i])) return i;
        }
        return -1;
    }

    /**
     * Resize the capacity of this list, to increase size of list so that it can contain
     * the required number of elements, or decrease so that the list does not take up extra memory
     * @param newLength new capacity of list
     */
    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }
}
