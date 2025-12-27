package JAVA_01_MEMO_JDK_GC;
// ================================================================================
// JAVA MEMORY & JVM — PART 3
// TOPIC: Object Lifecycle + Garbage Collection + STW
// ================================================================================

// new Object()
//     |
//     v
// TLAB (Thread Local Allocation Buffer)
//     |
//     v
// Eden Space (Young Gen)
//     |
//     | Eden FULL?
//     | YES
//     v

// Minor GC  (STOP-THE-WORLD)
// --------------------------
// • All application threads paused
// • GC threads run

// GC Roots
// --------
// • Stack references
// • Static fields
// • JNI references

// Reachability Analysis
// ---------------------
// • Reachable   → ALIVE
// • Unreachable → DEAD

// Movement
// --------
// • Dead → deleted
// • Alive → Survivor (age++)

//     |
//     | Age threshold OR Survivor full?
//     v
// Old Generation
//     |
//     | Old Gen FULL?
//     v

// Major / Full GC (STOP-THE-WORLD)
// --------------------------------
// • Young + Old Gen scanned
// • Long pause
// • Optional compaction

// ================================================================================
// STOP-THE-WORLD (STW)
// ================================================================================
// • Application threads paused
// • Required for memory safety
// • Duration depends on GC type

// ONE-LINER:
// "STW pauses application threads so GC can safely clean memory."
// ================================================================================
