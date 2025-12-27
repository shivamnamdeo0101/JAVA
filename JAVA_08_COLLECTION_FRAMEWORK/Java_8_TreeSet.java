package JAVA_08_COLLECTION_FRAMEWORK;

import java.util.*;

/**
 * Senior Java Architect Level Demonstration
 * Topic: TreeSet
 */
public class Java_8_TreeSet {

    public static void main(String[] args) {

        // =========================
        // CREATION
        // =========================
        TreeSet<Integer> treeSet = new TreeSet<>();

        // =========================
        // CORE OPERATIONS
        // =========================
        treeSet.add(40);
        treeSet.add(10);
        treeSet.add(30);
        treeSet.add(20);
        treeSet.add(20); // duplicate ignored

        for (Integer val : treeSet) {
            System.out.print(val + " ");
        }

        System.out.println("--------------------------------");

        treeSet.remove(30);
        treeSet.contains(10);

        // =========================
        // NAVIGABLE / SORTED OPERATIONS
        // =========================
        treeSet.first();
        treeSet.last();
        treeSet.lower(20);   // < 20
        treeSet.floor(20);   // <= 20
        treeSet.ceiling(15); // >= 15
        treeSet.higher(20);  // > 20

        // =========================
        // RANGE OPERATIONS
        // =========================
        treeSet.subSet(10, 40);
        treeSet.headSet(20);
        treeSet.tailSet(20);

        // =========================
        // BULK OPERATIONS
        // =========================
        treeSet.addAll(Set.of(50, 60, 70));
        treeSet.removeAll(Set.of(60));
        treeSet.retainAll(Set.of(10, 20, 40, 50));

        // =========================
        // ITERATION
        // =========================
        for (Integer val : treeSet) {
            System.out.print(val + " ");
        }

        Iterator<Integer> it = treeSet.iterator();
        while (it.hasNext()) {
            it.next();
        }

        // =========================
        // UTILITY METHODS
        // =========================
        treeSet.size();
        treeSet.isEmpty();
        treeSet.clear();
    }
}

/*
================================================================================
TREESET – ENTERPRISE & JVM LEVEL DEEP DIVE
================================================================================

WHAT:
-----
TreeSet is a SortedSet implementation backed by a Red-Black Tree.
It stores UNIQUE elements in SORTED order (natural or custom comparator).
It represents the abstraction of an ordered mathematical set.

Problem it solves:
• Fast sorted lookup
• Range queries
• Ordered traversal without manual sorting

WHY IT EXISTS:
--------------
Before TreeSet:
• HashSet had no ordering
• Sorting required external utilities

Java designers introduced TreeSet to:
• Maintain sorted uniqueness
• Support range-based operations
• Enable log(N) ordered access

Why it matters:
• Ranking systems
• Priority-based rules
• Time-based data processing

INTERNAL WORKING (JVM LEVEL – VERY IMPORTANT):
---------------------------------------------
Internal Data Structure:
• Red-Black Tree (Self-balancing BST)

Actual implementation:
• TreeSet internally uses TreeMap
• TreeSet<E> wraps TreeMap<E, Object>
• Dummy value = PRESENT

Key internal fields:
• TreeMap.root
• TreeMap.Entry (key, value, left, right, parent, color)
• Comparator<? super E> comparator
• modCount

STEP-BY-STEP:

add(element):
• TreeSet calls TreeMap.put(element, PRESENT)
• TreeMap navigates tree using compareTo / comparator
• If key exists → ignored
• New node inserted
• Red-Black tree rebalanced (rotations + recoloring)
• modCount++

get(element):
• Uses tree traversal (O log N)
• Compare left/right until found

remove(element):
• TreeMap.remove(key)
• Node deleted
• Tree rebalanced to maintain red-black rules
• modCount++

Iteration:
• In-order traversal of tree
• Iterator holds expectedModCount
• Structural change → ConcurrentModificationException

Memory behavior:
• Each element stored as TreeMap.Entry object
• Higher memory than HashSet
• Extra parent + color pointers

DEFAULT VALUES:
---------------
Default capacity:
• NO concept of capacity (tree-based)

Load factor:
• NOT APPLICABLE

Growth strategy:
• Tree grows node-by-node

Thread safety:
• NOT thread-safe
• Use Collections.synchronizedSortedSet or ConcurrentSkipListSet

TIME COMPLEXITY:
----------------
Operation     Best   Avg   Worst
--------------------------------
add           O(logN) O(logN) O(logN)
remove        O(logN) O(logN) O(logN)
contains      O(logN) O(logN) O(logN)
iteration     O(N)

CORE FEATURES:
--------------
Ordering        → Sorted (Natural / Comparator)
Duplicates      → NOT allowed
Null handling   → ❌ Null not allowed (NPE)
Thread safety   → ❌ Not thread-safe
Performance     → Slower than HashSet, predictable ordering

WHY EACH IMPORTANT METHOD EXISTS (DESIGN INTENT):
-------------------------------------------------
add():
• Insert ordered data (ranking, scores)

contains():
• Permission checks
• Feature toggles

addAll():
• Batch import (DB sync, API ingestion)

retainAll():
• Role-based filtering
• Access control lists

remove():
• Cleanup expired data

WHY CERTAIN METHODS ARE MISSING OR PROTECTED:
---------------------------------------------
Why no get() in Set:
• Set models existence, not position

Why removeRange() is protected:
• Internal optimization
• Prevent misuse in public API

Why Map is not a Collection:
• Map represents key-value association
• Not pure elements

ENTERPRISE PITFALLS:
--------------------
• Using TreeSet for high-volume inserts (slow)
• Forgetting comparator consistency with equals
• Inserting mutable objects
• Assuming thread safety
• Expecting HashSet-like performance

REAL SYSTEM USAGE:
------------------
Backend:
• Leaderboards
• Scheduled tasks
• Time-based events

UI:
• Sorted dropdowns
• Filtered search results

Microservices:
• Rule engines
• Priority queues
• Event ordering

INTERVIEW QUESTIONS (WITH ANSWERS):
----------------------------------
Q1: Why TreeSet is slower than HashSet?
→ TreeSet maintains ordering using Red-Black Tree, causing O(logN) operations.

Q2: Why null not allowed in TreeSet?
→ Comparison with null breaks ordering logic.

Q3: TreeSet vs PriorityQueue?
→ TreeSet enforces uniqueness; PriorityQueue allows duplicates.

Q4: How TreeSet maintains balance?
→ Red-Black tree rotations + recoloring.

Q5: Why TreeSet uses TreeMap internally?
→ Code reuse + consistent Map-Set behavior.

INTERVIEW ONE-LINER:
-------------------
"TreeSet is a sorted, unique collection backed by a Red-Black Tree, optimized for ordered access and range queries at log(N) cost."

================================================================================
*/
