package JAVA_08_COLLECTION_FRAMEWORK;

import java.util.*;

/**
 * Topic: Deque
 */
public class Java_10_Deque {

    public static void main(String[] args) {

        Deque<Integer> deque = new ArrayDeque<>();

        // =========================
        // CORE OPERATIONS
        // =========================
        deque.addFirst(10);
        deque.addLast(20);

        deque.offerFirst(5);
        deque.offerLast(25);

        deque.peekFirst();
        deque.peekLast();

        deque.pollFirst();
        deque.pollLast();

        deque.push(30);
        deque.pop();

        // =========================
        // BULK OPERATIONS
        // =========================
        deque.addAll(List.of(40, 50));
        deque.removeAll(List.of(50));
        deque.retainAll(List.of(40));

        // =========================
        // ITERATION
        // =========================
        for (Integer i : deque) {
            System.out.println(i);
        }

        // =========================
        // UTILITY
        // =========================
        deque.size();
        deque.clear();
    }
}

/*
================================================================================
DEQUE – ENTERPRISE & JVM LEVEL DEEP DIVE
================================================================================

WHAT:
-----
Deque = Double Ended Queue.
Supports insertion and removal from both ends.

Problem it solves:
• Stack + Queue hybrid
• Undo/redo
• Sliding window

WHY IT EXISTS:
--------------
Before Deque:
• Separate Stack & Queue APIs
• Code duplication

Java designers introduced Deque to:
• Unify LIFO + FIFO
• Replace legacy Stack

INTERNAL WORKING:
-----------------
ArrayDeque implementation:
• Circular array
• head & tail index
• power-of-two sizing

addFirst():
• Decrement head
• Insert element

addLast():
• Insert at tail
• Increment tail

Resizing:
• Array doubled
• Elements copied circularly

DEFAULT VALUES:
---------------
Initial capacity: 16
Load factor: ❌ N/A
Thread safety: ❌ No

TIME COMPLEXITY:
----------------
add/remove → O(1)
peek       → O(1)
search     → O(n)

CORE FEATURES:
--------------
Ordering      → Maintained
Duplicates    → Allowed
Null handling → ❌ Not allowed
Thread safety → No

ENTERPRISE PITFALLS:
--------------------
• Using Stack instead of Deque
• Null insert assumptions
• Thread misuse

REAL SYSTEM USAGE:
------------------
• Undo/redo stacks
• Browser history
• Sliding window analytics

INTERVIEW ONE-LINER:
-------------------
"Deque is a modern replacement for Stack and Queue, optimized via circular arrays."

================================================================================
*/
