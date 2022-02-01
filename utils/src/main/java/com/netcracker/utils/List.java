package com.netcracker.utils;

public interface List<T> extends Iterable<T> {

    /**
     * @return the number of elements in this list.
     */
    int size();

    /**
     * @return true if this list contains no elements.
     */
    boolean isEmpty();

    /**
     * Appends the specified element to the end of this list.
     * @param o element to be appended to this list
     */
    void add(T o);

    /**
     * Removes the element at the specified position in this list
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    T remove(int index);

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * @param o element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    boolean remove(T o);

    /**
     * Returns true if this list contains the specified element.
     * @param o element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    boolean contains(T o);

    /**
     * Removes all of the elements from this list (optional operation).
     */
    void clear();

    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    T get(int index);

    /**
     * Replaces the element at the specified position in this list with the specified element (optional operation).
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    T set(int index, T element);

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element
     */
    int indexOf(T o);


    /**
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.</p>
     *
     * <p>!!! "a" must be "new T[0]"</p>
     *
     * @return an array containing all of the elements in this list in
     *         proper sequence
     */
    T[] toArray(T[] a);
}
