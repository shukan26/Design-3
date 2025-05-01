//Time Complexity: O(1) for both get and put operations
//Space Complexity: O(n) where n is the capacity of the cache
//Leetcode Problem: https://leetcode.com/problems/lru-cache/

/**
 * Implements an LRU (Least Recently Used) Cache with O(1) time complexity for get and put operations.
 * Uses a HashMap for quick key access and a doubly linked list to track usage order.
 * Automatically evicts the least recently used item when capacity is exceeded.
 */

import java.util.*;


public class LRUCache {

    class DLinkedListNode {
        int key;
        int val;
        DLinkedListNode next;
        DLinkedListNode prev;
    }

    public void removeNode(DLinkedListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void moveToHead(DLinkedListNode node) {
        removeNode(node);
        addNode(node);
    }

    public void addNode(DLinkedListNode node) {
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;

    }

    public DLinkedListNode popTail() {
        DLinkedListNode removedNode = tail.prev;
        removeNode(removedNode);
        return removedNode;

    }

    DLinkedListNode head = new DLinkedListNode();
    DLinkedListNode tail = new DLinkedListNode();
    int capacity;
    int size;
    Map<Integer, DLinkedListNode> hmap;

    public LRUCache(int capacity) {
        hmap = new HashMap<>();
        size = 0;
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedListNode node = hmap.get(key);
        if (node != null) {
            moveToHead(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        DLinkedListNode node = hmap.get(key);

        if (node == null) {
            DLinkedListNode nodeToInsert = new DLinkedListNode();
            nodeToInsert.key = key;
            nodeToInsert.val = value;

            hmap.put(key, nodeToInsert);
            addNode(nodeToInsert);
            size++;

            if (size > capacity) {
                DLinkedListNode deletedNode = popTail();
                hmap.remove(deletedNode.key);
                size--;
            }
        } else {
            node.val = value;
            moveToHead(node);

        }
    }
}
