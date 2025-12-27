package JAVA_08_COLLECTION_FRAMEWORK;

import java.util.*;

/**
 * Topic: Queue
 */
public class Java_9_Queue {

    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>();

        // =========================
        // CORE OPERATIONS
        // =========================
        queue.add(10);
        queue.offer(20);

        queue.peek();
        queue.element();

        queue.poll();
        queue.remove();

        // =========================
        // BULK OPERATIONS
        // =========================
        queue.addAll(List.of(30, 40, 50));
        queue.removeAll(List.of(40));
        queue.retainAll(List.of(30, 50));

        // =========================
        // ITERATION
        // =========================
        for (Integer i : queue) {
            System.out.println(i);
        }

        Iterator<Integer> it = queue.iterator();
        while (it.hasNext()) {
            it.next();
        }

        // =========================
        // UTILITY METHODS
        // =========================
        queue.size();
        queue.isEmpty();
        queue.clear();
    }
}

/*
================================================================================
QUEUE – ENTERPRISE & JVM LEVEL DEEP DIVE
================================================================================

WHAT:
-----
Queue is a FIFO (First-In-First-Out) data structure abstraction.
It models real-world waiting lines.

Problem it solves:
• Ordered processing
• Task sequencing
• Fair scheduling

WHY IT EXISTS:
--------------
Before Queue:
• Developers misused Lists
• Manual index handling

Java designers introduced Queue to:
• Separate access vs processing
• Prevent random access misuse

Why it matters:
• Messaging systems
• Request pipelines
• Async processing

INTERNAL WORKING (JVM LEVEL):
-----------------------------
Queue is an INTERFACE.
Common implementation: LinkedList

Internal structure (LinkedList):
• Doubly linked nodes
• Node { item, prev, next }
• head, tail pointers
• modCount

add():
• Adds node at tail
• Updates tail pointer

poll():
• Removes head node
• Updates head pointer

peek():
• Returns head without removal

Iteration:
• Cursor walks node-by-node
• Fail-fast via modCount

Memory behavior:
• Each element = Node object
• More memory than ArrayList

DEFAULT VALUES:
---------------
Capacity: ❌ Unlimited
Load factor: ❌ N/A
Thread safety: ❌ Not thread-safe

TIME COMPLEXITY:
----------------
add       → O(1)
poll      → O(1)
peek      → O(1)
search    → O(n)

CORE FEATURES:
--------------
Ordering      → FIFO
Duplicates    → Allowed
Null handling → Allowed
Thread safety → No
Performance   → Fast insert/remove

WHY IMPORTANT METHODS EXIST:
-----------------------------
offer():
• Avoid exception-based flow

poll():
• Safe dequeue in async systems

addAll():
• Batch job ingestion

retainAll():
• Filtering allowed jobs

WHY METHODS ARE MISSING:
------------------------
No index access:
• Queue enforces discipline

ENTERPRISE PITFALLS:
--------------------
• Using Queue where random access needed
• Assuming thread safety
• Blocking operations confusion

REAL SYSTEM USAGE:
------------------
• Message brokers
• Request queues
• Order processing

INTERVIEW ONE-LINER:
-------------------
"Queue models FIFO processing and is the backbone of async and message-driven systems."

================================================================================
*/
