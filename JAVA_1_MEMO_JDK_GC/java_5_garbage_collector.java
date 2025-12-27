package JAVA_1_MEMO_JDK_GC;
// ================================================================================
// JAVA MEMORY & JVM — PART 4
// TOPIC: GC Algorithms, Types, Memory Leaks, Errors
// ================================================================================

// GC ALGORITHMS
// -------------
// • Mark → Identify live objects
// • Sweep → Remove dead objects
// • Compact → Remove fragmentation
// • Copying → Move live objects

// ================================================================================
// GC TYPES
// ================================================================================

// Serial GC
// ---------
// • Single thread
// • Small apps
// • Long pauses

// Parallel GC
// -----------
// • Multi-threaded
// • High throughput
// • Batch systems

// G1 GC (DEFAULT)
// ---------------
// • Region-based heap
// • Predictable pauses
// • Balanced performance

// ZGC
// ---
// • Concurrent
// • Ultra-low latency
// • Very large heaps

// Shenandoah
// ----------
// • Concurrent compaction
// • Minimal pause time

// ================================================================================
// MEMORY LEAK
// ================================================================================
// • Objects are still referenced
// • GC cannot free them
// • Heap slowly fills

// Example:
// --------
// while(true) {
//    list.add(new Object());
// }

// Memory Leak = Cause
// OOM Error   = Result

// ================================================================================
// ERRORS
// ================================================================================

// OutOfMemoryError
// ----------------
// • Heap full
// • Metaspace full
// • Native memory exhausted

// StackOverflowError
// ------------------
// • Deep recursion
// • Too many method calls

// ================================================================================
// FINAL INTERVIEW ONE-LINER
// ================================================================================
// "GC frees unreachable heap objects; memory leaks keep objects reachable, causing OOM."

// ================================================================================
// END — JAVA MEMORY & JVM (MASTER LEVEL)
// ================================================================================
