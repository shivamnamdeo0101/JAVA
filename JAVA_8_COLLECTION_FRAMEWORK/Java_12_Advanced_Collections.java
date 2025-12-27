package JAVA_8_COLLECTION_FRAMEWORK;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Topic: Advanced Collection Concepts
 * Covers:
 * - HashMap Internal Working
 * - Load Factor & Capacity
 * - Comparable vs Comparator
 * - Fail-fast vs Fail-safe
 * - Collections vs Collection
 * - Immutable Collections
 */
public class Java_12_Advanced_Collections {

    public static void main(String[] args) {

        // =========================
        // HASHMAP – INTERNAL WORKING
        // =========================
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("A", 10);
        hashMap.put("B", 20);
        hashMap.put("C", 30);
        hashMap.get("B");
        hashMap.remove("C");

        // =========================
        // LOAD FACTOR & CAPACITY DEMO
        // =========================
        // Default: capacity=16, loadFactor=0.75
        Map<String, String> mapLF = new HashMap<>(4, 0.75f);
        mapLF.put("K1","V1");
        mapLF.put("K2","V2");
        mapLF.put("K3","V3"); // triggers resize if size>capacity*loadFactor

        // =========================
        // COMPARABLE VS COMPARATOR
        // =========================
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", 25));
        employees.add(new Employee("Bob", 20));
        employees.add(new Employee("Charlie", 30));

        // Comparable – natural ordering (by age)
        Collections.sort(employees);

        // Comparator – custom ordering (by name)
        employees.sort(Comparator.comparing(Employee::getName));

        // =========================
        // FAIL-FAST VS FAIL-SAFE
        // =========================
        List<String> failFastList = new ArrayList<>(List.of("X","Y","Z"));
        try {
            for(String s : failFastList) {
                failFastList.add("W"); // ConcurrentModificationException
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Fail-Fast caught: " + e);
        }

        List<String> failSafeList = new CopyOnWriteArrayList<>(List.of("X","Y","Z"));
        for(String s : failSafeList) {
            failSafeList.add("W"); // Safe, no exception
        }

        // =========================
        // COLLECTIONS VS COLLECTION
        // =========================
        Collection<String> collection = new ArrayList<>();
        collection.add("A");
        collection.add("B");
        Collections.sort((List<String>) collection); // Collections utility works on Collection subtypes

        // =========================
        // IMMUTABLE COLLECTIONS
        // =========================
        List<String> immutableList = List.of("One", "Two", "Three");
        try {
            immutableList.add("Four"); // UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Immutable collection exception: " + e);
        }
    }

    static class Employee implements Comparable<Employee> {
        private String name;
        private int age;
        public Employee(String name, int age) {
            this.name = name; this.age = age;
        }
        public String getName() { return name; }
        public int getAge() { return age; }
        @Override
        public int compareTo(Employee o) { return this.age - o.age; }
        @Override
        public String toString() { return name + "-" + age; }
    }
}

/*
================================================================================
ADVANCED COLLECTION CONCEPTS – ENTERPRISE & JVM LEVEL DEEP DIVE
================================================================================

1️⃣ HASHMAP INTERNAL WORKING
---------------------------
• Data Structure: Node<K,V>[] table (array of buckets)
• Node stores key, value, hash, next
• Operations:
   - put(): hash → index → insert → treeify if collisions>8
   - get(): hash → index → traverse linked list / tree → equals match
   - remove(): find node → unlink → modCount++
• Fail-Fast Iterator: detects structural changes via modCount

2️⃣ LOAD FACTOR & CAPACITY
-------------------------
• Default capacity: 16, load factor: 0.75
• Resize triggers when size >= capacity * loadFactor
• Growth strategy: newCapacity = oldCapacity * 2
• Purpose: balance memory vs performance

3️⃣ COMPARABLE VS COMPARATOR
----------------------------
• Comparable: natural ordering inside class (single logic)
• Comparator: external multiple sorting strategies (name, salary, etc.)
• Usage: Collections.sort(), TreeSet, TreeMap
• Interview tip: always explain why one is preferred (no modification vs multiple strategies)

4️⃣ FAIL-FAST VS FAIL-SAFE
--------------------------
• Fail-Fast: ArrayList, HashMap → throws ConcurrentModificationException on modification during iteration
• Fail-Safe: CopyOnWriteArrayList, ConcurrentHashMap → works on clone/copy → safe in multithreaded code
• Important in enterprise systems with concurrent access

5️⃣ COLLECTIONS VS COLLECTION
-----------------------------
• Collection: interface, represents group of elements (List, Set, Queue)
• Collections: utility class with static methods (sort, reverse, shuffle)
• Collections methods operate on Collection subtypes
• Common interview trap: Collection.sort() does NOT exist; must cast to List

6️⃣ IMMUTABLE COLLECTIONS
-------------------------
• Java 9+ List.of(), Set.of(), Map.of()
• Characteristics: thread-safe, read-only, throws UnsupportedOperationException on modification
• Use cases: constants, config data, feature flags
• Enterprise tip: prevents accidental modification, ensures predictable behavior

================================================================================
ENTERPRISE PITFALLS
-------------------
❌ Using mutable keys in HashMap
❌ Assuming fail-fast prevents all concurrency issues
❌ Modifying immutable collections accidentally
❌ Misunderstanding load factor → performance issues
❌ Using raw types instead of generics

REAL SYSTEM USAGE
-----------------
✔ Caching (HashMap, ConcurrentHashMap)
✔ Task queues (Deque, Queue)
✔ Read-only configs (Immutable Collections)
✔ Sorting and ranking (Comparable/Comparator)
✔ UI tables, REST API response transformations

INTERVIEW QUESTIONS (WITH ANSWERS)
----------------------------------
Q1: Difference between Comparable and Comparator?
A: Comparable = natural, one way; Comparator = multiple strategies externally.

Q2: Why Fail-Fast iterator exists?
A: Detect concurrent modification bugs early, avoids silent data corruption.

Q3: Difference between Collection and Collections?
A: Collection = interface, Collections = utility class for operations.

Q4: How does HashMap resizing work?
A: When size >= capacity*loadFactor, new array is created, entries rehashed.

Q5: Why immutable collections are preferred in enterprise?
A: Thread-safe, predictable, prevents accidental modification of constants/configs.

INTERVIEW ONE-LINER
-------------------
"Advanced collections provide predictable, high-performance, thread-safe,
and maintainable data structures critical for real-world enterprise applications."
================================================================================
*/
