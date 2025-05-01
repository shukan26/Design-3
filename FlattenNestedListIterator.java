// Time;  O(1) amortized per next() and hasNext() call across all elements, totaling O(n) for n integers in the nested structure.
// Space: O(n) in the worst case, where n is the total number of nested elements due to stack usage.
// Leetcode Problem: https://leetcode.com/problems/flatten-nested-list-iterator/

/**
 * Flattens a nested list of integers using a lazy evaluation iterator pattern.
 * Maintains a stack to simulate depth-first traversal while preserving order.
 * Supports hasNext() and next() in O(1) amortized time per operation.
 */

import java.util.*;


public class FlattenNestedListIterator {

    /**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    Deque<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new ArrayDeque(nestedList);
    }

    @Override
    public Integer next() {
        makeStackTopInteger();
        return stack.removeFirst().getInteger();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public void makeStackTopInteger() {
        while(!stack.isEmpty() && !stack.peekFirst().isInteger()) {
            List<NestedInteger> list = stack.removeFirst().getList();
            for(int i = list.size()-1; i >= 0 ; i--) {
                stack.addFirst(list.get(i));
            }
        }
    }
}
    
}
