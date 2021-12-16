package com.netcracker.utils.impl;

import com.netcracker.utils.List;
import java.util.*;

public class MyArrayList<T> implements List<T> {
    private final int INIT_SIZE = 16;
    private final int CUT_RATE = 4;
    private final double RESIZE_MULTIPLIER = 1.5;
    private Object[] array;
    private int pointer = 0;

    public MyArrayList() {
        array = new Object[INIT_SIZE];
    }

    public MyArrayList(int arrSize) {
        array = new Object[arrSize];
    }

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
     *
     * @param o element to be appended to this list
     */
    @Override
    public void add(T o) {
        if (pointer == array.length - 1) {
            resize((int) (array.length * RESIZE_MULTIPLIER + 1));
        }
        array[pointer++] = o;
    }

    /**
     * Removes the element at the specified position in this list
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index >= pointer) throw new IndexOutOfBoundsException();

        T t = (T) array[index];
        System.arraycopy(array, index + 1, array, index, pointer - index);
        array[pointer--] = null;
        if (array.length > INIT_SIZE && pointer < array.length / CUT_RATE) {
            resize(array.length / 2);
        }

        return t;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     *
     * @param o element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    @Override
    public boolean remove(T o) {
        int index = indexOf(o);
        if (index > -1) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * Returns true if this list contains the specified element.
     *
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
        for (int i = 0; i < INIT_SIZE; i++) {
            array[i] = null;
        }
        pointer = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= pointer) throw new IndexOutOfBoundsException();

        return (T) array[index];
    }

    /**
     * Replaces the element at the specified position in this list with the specified element (optional operation).
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T set(int index, T element) {
        if (index >= pointer) throw new IndexOutOfBoundsException();

        array[index] = element;
        return element;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element
     */
    @Override
    public int indexOf(T o) {
        for (int i = 0; i < pointer; i++) {
            if (o.equals(array[i])) return i;
        }
        return -1;
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element); the runtime type of the returned
     * array is that of the specified array.  If the list fits in the
     * specified array, it is returned therein.  Otherwise, a new array is
     * allocated with the runtime type of the specified array and the size of
     * this list.
     *
     * <p>If the list fits in the specified array with room to spare
     * (i.e., the array has more elements than the list), the element in
     * the array immediately following the end of the collection is set to
     * {@code null}.  (This is useful in determining the length of the
     * list <i>only</i> if the caller knows that the list does not contain
     * any null elements.)
     *
     * @param a the array into which the elements of the list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return an array containing the elements of the list
     * @throws ArrayStoreException if the runtime type of the specified array
     *         is not a supertype of the runtime type of every element in
     *         this list
     * @throws NullPointerException if the specified array is null
     */
    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray(T[] a) {
        if (a.length < pointer)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(array, pointer, a.getClass());
        System.arraycopy(array, 0, a, 0, pointer);
        if (a.length > pointer)
            a[pointer] = null;
        return a;
    }

    /**
     * Resize the capacity of this list, to increase size of list so that it can contain
     * the required number of elements, or decrease so that the list does not take up extra memory
     *
     * @param newLength new capacity of list
     */
    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor < size();
            }

            @Override
            public T next() {
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[cursor++];
            }
        };
    }
}
