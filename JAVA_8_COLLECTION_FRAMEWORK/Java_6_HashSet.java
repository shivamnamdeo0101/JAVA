package JAVA_8_COLLECTION_FRAMEWORK;
import java.util.*;

public class Java_6_HashSet{
    public static void main(String[] args) {

        Set<String> set = new HashSet<>();

        set.add("A");
        set.add("B");
        set.add("A"); // ignored

        set.contains("B");
        set.remove("A");

        set.addAll(Set.of("X", "Y"));
        set.removeAll(Set.of("X"));
        set.retainAll(Set.of("Y"));

        set.size();
        set.isEmpty();
    }
}

// /*
// =============================== HASHSET – FEATURES, CONTRACT & INTERNAL WORKING ===============================

// WHAT:
// -----
// HashSet is an **unordered collection** that:
// • Stores UNIQUE elements
// • Provides FAST lookup
// • Does NOT maintain insertion order
// • Does NOT allow index-based access

// It implements:
// ---------------
// • Set interface
// • Backed internally by HashMap

// -------------------------------------------------------------------------------------------------------------
// WHY HASHSET EXISTS:
// -------------------
// Problems with List:
// ❌ Duplicate data
// ❌ Slow contains() → O(n)

// HashSet solves:
// ✔ Automatic deduplication
// ✔ O(1) average lookup
// ✔ Clean membership checks

// -------------------------------------------------------------------------------------------------------------
// INTERNAL WORKING (VERY IMPORTANT):
// ---------------------------------

// 1️⃣ BACKING DATA STRUCTURE:
// --------------------------
// HashSet internally uses **HashMap**:

// private transient HashMap<E, Object> map;

// • Element → stored as KEY
// • Value → dummy static Object (PRESENT)

// Example:
// --------
// set.add("A");

// Internally:
// -----------
// map.put("A", PRESENT);

// This is WHY:
// • HashSet has no get()
// • HashSet focuses only on existence

// 2️⃣ ADD OPERATION:
// ------------------
// add(E e):
// • Calculate hashCode()
// • Compute bucket index
// • Check bucket:
//    → If key exists (equals true) → IGNORE
//    → Else → INSERT

// Time Complexity:
// ----------------
// Average: O(1)
// Worst   : O(n) (hash collision)

// 3️⃣ DUPLICATE HANDLING:
// ----------------------
// When you do:
// --------------
// set.add("A");
// set.add("A");

// Flow:
// -----
// • Same hashCode
// • equals() returns true
// • HashMap replaces value (same key)
// • HashSet ignores duplicate

// ✔ NO EXCEPTION
// ✔ NO DUPLICATION

// 4️⃣ CONTAINS():
// ---------------
// contains("B"):
// • Compute hashCode
// • Jump directly to bucket
// • Compare using equals()

// This is WHY HashSet is FAST.

// 5️⃣ REMOVE():
// -------------
// remove("A"):
// • Locate bucket via hash
// • Remove node if equals() matches

// -------------------------------------------------------------------------------------------------------------
// CRITICAL CONTRACT (INTERVIEW GOLD):
// ----------------------------------

// hashCode() + equals() MUST FOLLOW RULES:
// ----------------------------------------
// 1. If equals() is true → hashCode MUST be same
// 2. If hashCode same → equals MAY be false
// 3. hashCode MUST NOT change while object is in Set

// ❌ Violating this breaks HashSet behavior.

// -------------------------------------------------------------------------------------------------------------
// WHY HASHSET HAS NO get():
// ------------------------
// • No ordering
// • No index
// • Optimized ONLY for membership
// • Conceptually represents mathematical SET

// INTERVIEW ANSWER:
// -----------------
// "HashSet is not about retrieving data by position, but about checking presence."

// -------------------------------------------------------------------------------------------------------------
// FEATURE DESIGN & REAL USE CASES:
// --------------------------------

// contains():
// ------------
// ✔ Permission checks
// ✔ Feature toggles
// ✔ Session validation

// retainAll():
// ------------
// ✔ Role filtering
// ✔ Access control intersections
// ✔ Common elements between systems

// addAll():
// ---------
// ✔ Bulk imports
// ✔ Batch sync
// ✔ Merging datasets

// removeAll():
// ------------
// ✔ Blacklist filtering
// ✔ Data cleanup

// -------------------------------------------------------------------------------------------------------------
// WHY HASHSET IS FAST:
// --------------------
// ✔ Hashing instead of traversal
// ✔ No sorting
// ✔ No index shifting
// ✔ Direct bucket access

// BUT:
// ----
// ❌ Speed depends on GOOD hashCode()

// -------------------------------------------------------------------------------------------------------------
// WHEN HASHSET FAILS (COMMON PRODUCTION BUGS):
// -------------------------------------------

// 1️⃣ POOR hashCode():
// -------------------
// • Too many collisions
// • Degrades to O(n)

// 2️⃣ MUTABLE KEYS:
// -----------------
// ❌ If object fields change after insertion
// ❌ hashCode changes → element becomes unreachable

// 3️⃣ CUSTOM OBJECT WITHOUT equals():
// -----------------------------------
// • Duplicate objects get added
// • Set contract breaks

// -------------------------------------------------------------------------------------------------------------
// MEMORY & JVM NOTES:
// -------------------
// • HashSet consumes MORE memory than ArrayList
// • HashMap buckets + Node objects
// • Treeification happens when collisions exceed threshold (Java 8+)

// -------------------------------------------------------------------------------------------------------------
// THREAD SAFETY:
// --------------
// ❌ NOT thread-safe
// ✔ Use Collections.synchronizedSet()
// ✔ Or CopyOnWriteArraySet (read-heavy)

// -------------------------------------------------------------------------------------
