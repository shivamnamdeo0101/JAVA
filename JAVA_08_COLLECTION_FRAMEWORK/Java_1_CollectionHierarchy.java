package JAVA_08_COLLECTION_FRAMEWORK;
/*
====================== JAVA COLLECTIONS FRAMEWORK ======================

WHAT:
-----
Java Collections Framework (JCF) is a **standardized, reusable,
and extensible architecture** provided by Java to store and
manipulate groups of objects.

Before Java 1.2:
----------------
• Developers used arrays, Vector, Hashtable
• No consistency
• No common interfaces
• Poor scalability and maintenance

Java 1.2 introduced:
--------------------
• Unified interfaces (List, Set, Map, Queue)
• Ready-made implementations
• Algorithms (sort, search, shuffle)

WHY IT EXISTS (DESIGN PROBLEMS SOLVED):
--------------------------------------
1️⃣ Avoid reinventing data structures
2️⃣ Provide optimized, battle-tested implementations
3️⃣ Enforce programming to interfaces
4️⃣ Improve readability & maintainability
5️⃣ Enable polymorphism (switch impl without code change)

HOW IT IS DESIGNED (ARCHITECTURE):
---------------------------------

                Iterable
                    |
               Collection
        ┌───────────┼───────────┐
       List         Set        Queue
        |            |           |
 ArrayList     HashSet        PriorityQueue
 LinkedList    LinkedHashSet  Deque
 Vector        TreeSet
 Stack

Iterable
   ↓
Collection
   ├── List    → Ordered, index-based
   ├── Set     → Unique elements
   └── Queue   → FIFO / priority-based

Map (Separate hierarchy)
-----------------------
• Not a true collection
• Stores key-value pairs
• Needs different access semantics

WHEN TO USE COLLECTIONS:
------------------------
• In-memory data processing
• Caching
• Request/session storage
• Temporary computation
• Business rules execution

WHEN NOT TO USE:
----------------
• Very large datasets → DB / Streaming
• Strict real-time systems → custom DS

REAL-WORLD USAGE:
-----------------
• E-commerce orders
• Banking transactions
• Session management
• Analytics aggregation

INTERVIEW DEEP DIVE:
-------------------
Q: Why Map does NOT extend Collection?
A:
• Collection deals with single elements
• Map deals with key-value pairs
• Operations like add(), contains() don't fit Map semantics

PRINCIPLE:
----------
"Program to interface, not implementation"

=======================================================================
*/
