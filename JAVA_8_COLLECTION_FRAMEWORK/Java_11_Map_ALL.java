package JAVA_8_COLLECTION_FRAMEWORK;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Topic: Map Implementations (HashMap, LinkedHashMap, TreeMap, Hashtable, ConcurrentHashMap)
 */
public class Java_11_Map_ALL {

    public static void main(String[] args) {

        // =========================
        // HASHMAP
        // =========================
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("A", 1);
        hashMap.put("B", 2);
        hashMap.get("A");
        hashMap.remove("B");
        hashMap.containsKey("A");
        hashMap.containsValue(1);

        // =========================
        // LINKEDHASHMAP
        // =========================
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("X", 10);
        linkedMap.put("Y", 20);

        // =========================
        // TREEMAP
        // =========================
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("B", 20);
        treeMap.put("A", 10);
        treeMap.put("C", 30);

        // =========================
        // HASHTABLE (LEGACY)
        // =========================
        Map<String, Integer> hashtable = new Hashtable<>();
        hashtable.put("C", 30);
        hashtable.put("D", 40);
        // hashtable.put(null, 1); // ❌ NullPointerException

        // =========================
        // CONCURRENTHASHMAP (THREAD-SAFE)
        // =========================
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("D", 40);
        concurrentMap.putIfAbsent("E", 50);
        concurrentMap.computeIfPresent("D", (k, v) -> v + 10);

        // =========================
        // BULK OPERATIONS
        // =========================
        hashMap.putAll(Map.of("X", 100, "Y", 200));
        hashMap.keySet();
        hashMap.values();
        hashMap.entrySet();

        // =========================
        // ITERATION (STANDARD & SAFE)
        // =========================
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
        }

        // =========================
        // UTILITY
        // =========================
        hashMap.size();
        hashMap.isEmpty();
        hashMap.clear();
    }
}

/*
================================================================================
MAP – ENTERPRISE & JVM LEVEL DEEP DIVE
================================================================================

WHAT:
-----
Map is a key-value data structure.
It represents an ASSOCIATION, not a pure collection of elements.
Keys are UNIQUE, values can be duplicated.

WHY IT EXISTS:
--------------
Before Map:
• Developers used parallel lists
• Manual index synchronization
• High bug probability

Java introduced Map to:
• Provide O(1) / O(logN) lookup
• Enforce key uniqueness
• Model real-world relationships (id → object)

WHY IT MATTERS:
---------------
• Almost every backend system depends on Map
• Caching, auth, config, metadata, routing

--------------------------------------------------------------------------------
INTERNAL WORKING (JVM LEVEL)
--------------------------------------------------------------------------------

HASHMAP:
--------
Internal Structure:
• Node<K,V>[] table
• Each bucket stores a linked list or tree

Important fields:
• table
• size
• threshold
• loadFactor
• modCount

put():
• hash(key) → index
• Insert Node
• Collision → linked list / tree
• Treeify if chain length > 8

get():
• hash(key)
• Traverse bucket
• equals() match

remove():
• Find node
• Unlink
• modCount++

Iteration:
• entrySet iterator
• Fail-fast using modCount

LINKEDHASHMAP:
--------------
• HashMap + Doubly Linked List
• Maintains insertion or access order
• Used for LRU caches

TREEMAP:
--------
• Red-Black Tree
• Sorted keys
• O(logN) operations

HASHTABLE:
----------
• Synchronized at method level
• No null key/value
• Legacy, slow

CONCURRENTHASHMAP:
------------------
Java 7:
• Segment-based locking

Java 8+:
• CAS + synchronized bins
• Lock only affected bucket
• High throughput

--------------------------------------------------------------------------------
DEFAULT VALUES:
--------------------------------------------------------------------------------
HashMap:
• Initial Capacity: 16
• Load Factor: 0.75

ConcurrentHashMap:
• Initial Capacity: 16
• No null key/value

--------------------------------------------------------------------------------
TIME COMPLEXITY:
--------------------------------------------------------------------------------
HashMap:
• get / put → O(1) avg, O(n) worst

TreeMap:
• get / put → O(logN)

ConcurrentHashMap:
• get → O(1) (lock-free)
• put → O(1) avg

--------------------------------------------------------------------------------
WHY MAP IS NOT A COLLECTION:
--------------------------------------------------------------------------------
• Collection = group of elements
• Map = key-value association
• Different abstraction

--------------------------------------------------------------------------------
ENTERPRISE PITFALLS:
--------------------------------------------------------------------------------
❌ Using mutable objects as keys
❌ Bad hashCode() implementations
❌ Using Hashtable in new code
❌ Assuming ConcurrentHashMap locks whole map
❌ Iterating HashMap during modification

--------------------------------------------------------------------------------
REAL SYSTEM USAGE:
--------------------------------------------------------------------------------
• Caching layers (Redis fallback)
• Configuration services
• Authentication tokens
• Feature flags
• Request routing

--------------------------------------------------------------------------------
INTERVIEW ONE-LINER:
--------------------------------------------------------------------------------
"A Map models real-world key-value associations and is the backbone of
caching, configuration, and fast lookup systems in Java."

================================================================================
*/
