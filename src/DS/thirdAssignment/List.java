package DS.thirdAssignment;

import java.util.Arrays;

public class List {
    private int[] array;
    private int occupied1; //Stores size of the first stack in pair.
    private int occupied2; //Stores size of the second stack in pair.

    /**
     * Constructs a list.
     */
    public List() {
        array = new int[4];
        occupied1 = 0;
        occupied2 = 0;
    }

    /**
     * Returns the String representation of the list.
     * @param stackId which stack in pair, the current stack is. First or Second.
     * @return the String representation of the list.
     */
    public String toString(int stackId) {
        if (stackId == 1) {
            return getString(stackId, occupied1);
        } else {
            return getString(stackId, occupied2);
        }
    }

    //Builds up a string from the list.
    private String getString(int stackId, int occupied1) {
        StringBuilder str = new StringBuilder();

        str.append('[');
        for (int i = 0; i < occupied1; i++) {
            str.append(get(i, stackId));
            if (i != occupied1 - 1) {
                str.append(" ,");
            }
        }
        str.append(']');

        return str.toString();
    }

    /**
     * Returns the size of the list. But the size will be differ based on the stacks number.
     * Because every list is shared among two stacks.
     * @param stackId which stack in pair, the current stack is. First or Second.
     * @return the size of the list.
     */
    public int size(int stackId) {
        if (stackId == 1) {
            return occupied1;
        } else return occupied2;
    }

    /**
     * Returns the specific element from the list.
     * @param index index of the element to be returned.
     * @param stackId which stack in pair, the current stack is. First or Second.
     * @return the element in the given index.
     */
    public int get(int index, int stackId) {
        if (stackId == 1) {
            return array[2 * index];
        } else return array[2 * index + 1];
    }

    /**
     * Sets a position of the list to a value.
     * @param index the position to set some value to.
     * @param val the value to be set.
     * @return the List.
     */
    public List set(int index, int val) {
        array[index] = val;

        return this;
    }

    //For assuring that the array always has some space for adding a new element
    private void assureCapacity(int stackId) {
        if (stackId == 1) {
            if (!(2 * occupied1 < array.length)) {
                array = Arrays.copyOf(array, 2 * array.length);
            }
        } else {
            if (!((2 * occupied2 + 1) < array.length)) {
                array = Arrays.copyOf(array, 2 * array.length);
            }
        }
    }

    //When the 3/4 of the array is empty, we shrink it by making a new array with 1/2 size.
    private void shrinkArray() {
        if((occupied1 + occupied2) == array.length / 4) {
            array = Arrays.copyOf(array, array.length / 2);
        }
    }

    /**
     * Inserts a value to the list in the given index.
     * @param index the index of the position to insert the value.
     * @param val the value to be inserted.
     * @param stackId which stack in pair, the current stack is. First or Second.
     * @return the List.
     */
    public List insert(int index, int val, int stackId) {
        assureCapacity(stackId);

        if (stackId == 1) {
            set(2 * occupied1, val);
            occupied1++;
        } else {
            set(2 * occupied2 + 1, val);
            occupied2++;
        }

        return this;
    }

    /**
     * Adds a value to the end of the list.
     * @param val the value to be added.
     * @param stackId which stack in pair, the current stack is. First or Second.
     * @return the List.
     */
    public List pushBack(int val, int stackId) {
        if (stackId == 1) {
            return insert(occupied1, val, stackId);
        } else return insert(occupied2, val, stackId);
    }

    /**
     * Removes an element of the list stored in the given position.
     * @param index the index of the element to be removed.
     * @param stackId which stack in pair, the current stack is. First or Second.
     * @return the removed value.
     */
    public int remove(int index, int stackId) {
        int val = get(index, stackId);

        if (stackId == 1) {
            occupied1--;
        } else {
            occupied2--;
        }

        shrinkArray();

        return val;
    }

    /**
     * Removes the last element of the list.
     * @param stackId which stack in pair, the current stack is. First or Second.
     * @return the removed value.
     */
    public int removeBack(int stackId) {
        if (stackId == 1) {
            return remove(occupied1 - 1, stackId);
        } else return remove(occupied2 - 1, stackId);
    }
}