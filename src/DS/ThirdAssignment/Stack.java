package DS.ThirdAssignment;

/**
 * This data structure is designed in a way, that each pair of the instanced stacks, share a single list
 * among them. So, for 10 stacks, we have 5 lists.
 */
public class Stack {
    /**
     * numOfStacks, stores the number of stacks that have been instanced using this Stack class.
     */
    private static int numOfStacks = 0;

    /**
     * sharedList, stores the reference of the list, that is going to be shared among 2 stacks.
     */
    private static List sharedList;

    /**
     * list, stores the reference of the list of the stack.
     */
    private final List list;

    /**
     * stackId, stores an id for the stack to be able to recognize the pair stacks.
     */
    private final int stackId;

    /**
     * Constructs a stack.
     */
    public Stack() {
        numOfStacks++;

        //If we have odd numbers of stacks, it means this is the first stack in the pair and needs a new list.
        //If we have even numbers of stacks, it means this stack doesn't need a new list and it's going to share
        //a list with the previous odd stack.
        if (isOdd(numOfStacks)) {
            list = new List();
            sharedList = list;
            stackId = 1;
        } else {
            list = sharedList;
            stackId = 2;
        }
    }

    /**
     * Returns the size of the stack.
     * @return size of the stack.
     */
    public int size() {
        return list.size(stackId);
    }

    /**
     * Removes the top element of the stack.
     * @return the stack.
     */
    public Stack pop() {
        list.removeBack(stackId);
        return this;
    }

    /**
     * Adds a value to the top of the stack.
     * @param value the value to be added.
     * @return the stack.
     */
    public Stack push(int value) {
        list.pushBack(value, stackId);
        return this;
    }

    /**
     * Returns the top element of the stack.
     * @return the top element of the stack.
     */
    public int peek() {
        return list.get(this.size() - 1, stackId);
    }

    /**
     * @return String representation of the stack.
     */
    public String toString() {
        return list.toString(stackId);
    }

    //Helper method to see if a number is odd or not.
    private boolean isOdd(int number) {
        return !(number % 2 == 0);
    }
}