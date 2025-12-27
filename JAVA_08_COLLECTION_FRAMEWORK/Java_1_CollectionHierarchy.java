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


// | Collection               | Interface         | Use Case / When to Use                                             | Why / Advantages                                             | Key Notes / Performance                                   | Real-World Example                                   |
// | ------------------------ | ----------------- | ------------------------------------------------------------------ | ------------------------------------------------------------ | --------------------------------------------------------- | ---------------------------------------------------- |
// | **ArrayList**            | List              | When you need **fast random access** and iteration.                | Backed by array → O(1) access, O(n) insertion in middle.     | Resize automatically; slower at insert/remove in middle.  | Storing search results, list of users.               |
// | **LinkedList**           | List, Deque       | When you need **frequent insertions/deletions** in middle of list. | Doubly linked list → O(1) insertion/removal at ends.         | Slower random access → O(n).                              | Implementing queue, undo history.                    |
// | **Vector**               | List              | Legacy code; synchronized ArrayList alternative.                   | Thread-safe.                                                 | Slower due to synchronization overhead.                   | Rare in modern code; legacy apps.                    |
// | **Stack**                | Vector            | LIFO operations.                                                   | Provides push/pop/top operations.                            | Legacy; use Deque instead (`ArrayDeque`).                 | Undo functionality, parsing expressions.             |
// | **ArrayDeque**           | Deque             | When you need **stack or queue** operations efficiently.           | Faster than Stack/LinkedList; no capacity restriction.       | Amortized O(1) for add/remove at both ends.               | Job scheduling, BFS traversal.                       |
// | **PriorityQueue**        | Queue             | **Heap-based priority queue**.                                     | Elements are ordered by priority.                            | No null elements; O(log n) insertion/removal.             | Task scheduling, Dijkstra’s algorithm.               |
// | **HashSet**              | Set               | **Unique elements, no order** needed.                              | Hashing → O(1) add, remove, contains.                        | Not thread-safe; no ordering guarantees.                  | Store unique user IDs, tags.                         |
// | **LinkedHashSet**        | Set               | Maintain **insertion order** with uniqueness.                      | Hash table + linked list → predictable iteration order.      | Slightly slower than HashSet due to linked list overhead. | Caching, maintaining ordered set of items.           |
// | **TreeSet**              | Set, NavigableSet | **Sorted set** of unique elements.                                 | Implements **Red-Black tree** → sorted elements.             | O(log n) insertion/removal/search.                        | Auto-sorted leaderboard, range queries.              |
// | **HashMap**              | Map               | **Key-value store**, fast access by key.                           | O(1) lookup with good hash function.                         | Not thread-safe; allows null key/value.                   | Caching, dictionary, configuration properties.       |
// | **LinkedHashMap**        | Map               | Maintain **insertion order** or **access order** (LRU).            | Hash table + linked list → predictable iteration.            | Slightly slower than HashMap.                             | LRU caches, predictable JSON order.                  |
// | **TreeMap**              | Map, NavigableMap | Sorted key-value mapping.                                          | Red-Black tree → sorted by key.                              | O(log n) insertion/removal/search.                        | Sorted records, interval queries.                    |
// | **Hashtable**            | Map               | Legacy, synchronized key-value store.                              | Thread-safe.                                                 | Slower due to synchronization; no null key/value.         | Rarely used; legacy systems.                         |
// | **ConcurrentHashMap**    | Map               | Thread-safe, high-concurrency key-value store.                     | Segment-based locking → efficient for multi-threaded access. | Cannot lock entire map; safe concurrent operations.       | Caching, shared state in multi-threaded apps.        |
// | **CopyOnWriteArrayList** | List              | Thread-safe **read-heavy list**, occasional writes.                | On write → copy array; reads O(1).                           | High write cost; excellent for concurrent reads.          | Event listeners, configuration lists.                |
// | **CopyOnWriteArraySet**  | Set               | Thread-safe, read-heavy unique elements.                           | Uses CopyOnWriteArrayList internally.                        | High write cost; excellent for read-heavy sets.           | Listener sets, immutable collections in concurrency. |





// | Collection               | Interface         | ✅ When to Use                                       | ❌ When to Avoid                                    | Real-World Example                           |
// | ------------------------ | ----------------- | --------------------------------------------------- | -------------------------------------------------- | -------------------------------------------- |
// | **ArrayList**            | List              | ✅ Fast random access, frequent iteration            | ❌ Frequent insert/delete in middle                 | Storing search results, user lists           |
// | **LinkedList**           | List, Deque       | ✅ Frequent insertions/deletions, implementing queue | ❌ Random access, indexing                          | Undo history, job queue                      |
// | **Vector**               | List              | ✅ Legacy synchronized list needed                   | ❌ Modern code (use ArrayList + synchronized block) | Legacy enterprise apps                       |
// | **Stack**                | Vector            | ✅ Simple LIFO operations                            | ❌ Modern code (use ArrayDeque)                     | Undo/Redo stack                              |
// | **ArrayDeque**           | Deque             | ✅ Efficient stack/queue                             | ❌ Need thread-safety                               | Job scheduling, BFS traversal                |
// | **PriorityQueue**        | Queue             | ✅ Priority-based ordering                           | ❌ Null elements, non-priority tasks                | Task scheduler, Dijkstra’s algo              |
// | **HashSet**              | Set               | ✅ Unique elements, fast lookup                      | ❌ Need order                                       | Unique user IDs, tags                        |
// | **LinkedHashSet**        | Set               | ✅ Maintain insertion order, unique elements         | ❌ Sorting needed                                   | Caching, ordered unique items                |
// | **TreeSet**              | Set, NavigableSet | ✅ Sorted unique elements                            | ❌ No ordering requirement                          | Leaderboard, range queries                   |
// | **HashMap**              | Map               | ✅ Key-value store, fast access                      | ❌ Need order                                       | Caching, dictionary                          |
// | **LinkedHashMap**        | Map               | ✅ Maintain insertion/access order                   | ❌ Not needing order                                | LRU cache, JSON output                       |
// | **TreeMap**              | Map, NavigableMap | ✅ Sorted key-value pairs                            | ❌ Unsorted access only                             | Sorted records, interval queries             |
// | **Hashtable**            | Map               | ✅ Legacy synchronized map                           | ❌ Modern apps (use ConcurrentHashMap)              | Legacy enterprise apps                       |
// | **ConcurrentHashMap**    | Map               | ✅ Thread-safe, multi-threaded access                | ❌ Small single-threaded apps (overhead)            | Caching, shared state in concurrent apps     |
// | **CopyOnWriteArrayList** | List              | ✅ Read-heavy, occasional writes, thread-safe        | ❌ Frequent writes                                  | Event listeners, configuration lists         |
// | **CopyOnWriteArraySet**  | Set               | ✅ Thread-safe, read-heavy unique elements           | ❌ Frequent writes                                  | Listener sets, immutable sets in concurrency |
