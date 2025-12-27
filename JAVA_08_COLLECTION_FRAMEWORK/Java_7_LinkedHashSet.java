package JAVA_08_COLLECTION_FRAMEWORK;

import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.Set;

public class Java_7_LinkedHashSet {
    public static void main(String[] args) {

        Set<String> set = new LinkedHashSet<>();

        // ================= CORE OPERATIONS =================
        set.add("A");
        set.add("B");
        set.add("C");
        set.add("A");          // ignored

        set.contains("B");
        set.remove("A");

        set.size();
        set.isEmpty();

        // ================= BULK OPERATIONS =================
        set.addAll(Set.of("X", "Y"));
        set.removeAll(Set.of("X"));
        set.retainAll(Set.of("B", "Y"));

        // ================= ITERATION =================
        for (String s : set) {}

        Iterator<String> it = set.iterator();
        while (it.hasNext()) it.next();

        // ================= UTILITY =================
        set.clear();
    }
}

/*
========================= LINKEDHASHSET – FEATURES, DESIGN & INTERNAL WORKING =========================

WHAT:
-----
LinkedHashSet is a **hash-based Set** that maintains
**insertion order** while ensuring uniqueness.

Abstraction:
------------
"Unique elements with predictable iteration order"

WHY IT EXISTS:
--------------
HashSet problems:
❌ No ordering guarantee
❌ Unpredictable iteration (bad for UI / APIs)

LinkedHashSet solves:
✔ Deduplication
✔ Deterministic order
✔ Better user-facing behavior

-----------------------------------------------------------------------------------------------
INTERNAL WORKING (JVM LEVEL – VERY IMPORTANT)
-----------------------------------------------------------------------------------------------

1️⃣ INTERNAL DATA STRUCTURE:
----------------------------
LinkedHashSet is backed by:
    LinkedHashMap<E, Object>

LinkedHashMap internals:
------------------------
• Hash table (Node[])
• Doubly linked list connecting entries

Each node contains:
-------------------
Node {
  hash, key, value, next,
  before, after
}

2️⃣ ADD OPERATION:
------------------
• Compute hash
• Find bucket
• Check equals()
• If unique → insert
• Link node at end of linked list

Time:
-----
O(1) average

3️⃣ REMOVE OPERATION:
---------------------
• Locate bucket
• Remove hash node
• Update linked list pointers

4️⃣ ITERATION:
--------------
• Traverses linked list
• Preserves insertion order
• Fail-fast via modCount

-----------------------------------------------------------------------------------------------
DEFAULT VALUES:
---------------
• Initial capacity = 16
• Load factor = 0.75
• Ordering = insertion order
• Thread safety = ❌ Not thread-safe

-----------------------------------------------------------------------------------------------
TIME COMPLEXITY:
----------------
add      → O(1) avg
remove   → O(1) avg
contains → O(1) avg
iterate  → O(n)

Worst case → O(n) (hash collision)

-----------------------------------------------------------------------------------------------
CORE FEATURES:
--------------
✔ Maintains insertion order
✔ No duplicates
✔ Allows single null
❌ Not sorted
❌ Not thread-safe

-----------------------------------------------------------------------------------------------
WHY EACH IMPORTANT METHOD EXISTS (DESIGN INTENT):
-----------------------------------------------
add()        → ordered deduplication
contains()  → permission / feature checks
retainAll() → role-based filtering
addAll()    → batch imports (CSV, DB)
remove()    → revoke access / cleanup

-----------------------------------------------------------------------------------------------
WHY CERTAIN METHODS ARE MISSING:
--------------------------------
• No get() → Set has no index
• No random access → order ≠ position

-----------------------------------------------------------------------------------------------
ENTERPRISE PITFALLS:
-------------------
❌ Assuming it is sorted
❌ Using mutable objects as keys
❌ Ignoring hashCode/equals contract
❌ Using it in concurrent code

-----------------------------------------------------------------------------------------------
REAL SYSTEM USAGE:
-----------------
✔ UI dropdowns without duplicates
✔ Ordered API responses
✔ Feature flags
✔ Deduplicated logs

-----------------------------------------------------------------------------------------------
INTERVIEW QUESTIONS (WITH ANSWERS):
----------------------------------
Q1: Difference between HashSet and LinkedHashSet?
A : LinkedHashSet maintains insertion order using a linked list.

Q2: Performance impact vs HashSet?
A : Slight memory overhead, similar time complexity.

Q3: Why LinkedHashSet over TreeSet?
A : Faster, no sorting cost, predictable order.

Q4: How ordering is preserved?
A : Doubly linked list inside LinkedHashMap.

-----------------------------------------------------------------------------------------------
INTERVIEW ONE-LINER:
-------------------
"LinkedHashSet provides HashSet performance with predictable iteration order."

===============================================================================================

*/
