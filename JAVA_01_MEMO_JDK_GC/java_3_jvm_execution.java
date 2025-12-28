package JAVA_01_MEMO_JDK_GC;
// ============================================================================
// JVM MEMORY ARCHITECTURE + JIT (UPDATED ARROW DIAGRAM + BULLET EXPLANATION)
// Java 8+  | Interview + Real-world ready
// ============================================================================

/*
                         ┌──────────────────────────┐
                         │        OS  RAM           │
                         └─────────────▲────────────┘
                                       │
                          JVM PROCESS  │
┌──────────────────────────────────────┼─────────────────────────────────────┐
│                                      │                                     │
│   ┌──────────────────────────────┐   │   ┌──────────────────────────────┐ │
│   │        METASPACE              │◄──┘   │        CODE CACHE             │ │
│   │  (Native Memory)              │       │  (Native Memory)              │ │
│   │-------------------------------│       │-------------------------------│ │
│   │ • Class metadata              │       │ • JIT compiled native code     │ │
│   │ • Method & field info         │       │ • CPU-specific instructions    │ │
│   │ • Runtime constant pool       │       │ • Executed directly by CPU     │ │
│   │ • Static variables (class)    │       │                               │ │
│   │                               │       │ Managed by: JIT Compiler       │ │
│   │ Managed by: JVM               │       │ Filled at: Runtime only        │ │
│   │ Freed when: ClassLoader GC    │       └──────────────────────────────┘ │
│   └──────────────────────────────┘                                     ▲   │
│                                                                         │   │
│                                                                         │   │
│   ┌─────────────────────────────────────────────────────────────────┐   │
│   │                          HEAP MEMORY                            │◄───┘
│   │                  (GC MANAGED — SHARED)                           │
│   │------------------------------------------------------------------│
│   │                                                                  │
│   │   Young Generation                                               │
│   │   ┌──────────────┐        ┌──────────────┐                       │
│   │   │   Eden       │ ───▶   │  Survivor    │                       │
│   │   │ (new objects)│        │  S0 / S1     │                       │
│   │   └──────────────┘        └──────────────┘                       │
│   │            │                                                       │
│   │            ▼                                                       │
│   │   ┌──────────────────────────────┐                                │
│   │   │        Old Generation        │                                │
│   │   │   (Long-lived objects)       │                                │
│   │   └──────────────────────────────┘                                │
│   │                                                                  │
│   │   String Pool (inside Heap)                                       │
│   │                                                                  │
│   │ Managed by: Garbage Collector                                     │
│   └─────────────────────────────────────────────────────────────────┘
│                                                                         ▲
│                                                                         │
│   ┌─────────────────────────────────────────────────────────────────┐   │
│   │                         STACK MEMORY                            │   │
│   │                     (PER THREAD — NO GC)                         │   │
│   │------------------------------------------------------------------│   │
│   │                                                                  │   │
│   │   Thread-1 Stack        Thread-2 Stack        Thread-N Stack      │   │
│   │   ┌─────────────┐      ┌─────────────┐      ┌─────────────┐     │   │
│   │   │ main()      │      │ main()      │      │ main()      │     │   │
│   │   │ service()  │      │ controller()│      │ worker()    │     │   │
│   │   │ helper()   │      │ dao()        │      │ process()  │     │   │
│   │   └─────────────┘      └─────────────┘      └─────────────┘     │   │
│   │                                                                  │   │
│   │ • Local primitive variables                                      │   │
│   │ • Object references ────────────────▶ Heap Objects               │───┘
│   │ • Method call frames                                             │
│   │                                                                  │   │
│   │ Managed by: JVM                                                  │   │
│   │ Error: StackOverflowError                                        │   │
│   └─────────────────────────────────────────────────────────────────┘
│
└─────────────────────────────────────────────────────────────────────────────┘
*/

// ============================================================================
// DETAILED BULLET EXPLANATION (EACH COMPONENT)
// ============================================================================

/*
========================
1️⃣ HEAP MEMORY
========================
• Stores ALL objects and arrays
• Shared among all threads
• Divided into Young + Old Generation
• Eden: new objects allocated
• Survivor: objects that survived Minor GC
• Old Gen: long-lived objects
• String Pool also lives here (Java 7+)
• Managed fully by Garbage Collector
• GC runs ONLY on Heap
• Error:
  → OutOfMemoryError: Java heap space


========================
2️⃣ STACK MEMORY
========================
• One stack per thread
• Stores method call frames
• Each frame contains:
  - Local primitive variables
  - Object references (pointing to heap)
  - Return address
• LIFO structure
• Automatically created & destroyed
• GC NEVER runs here
• Error:
  → StackOverflowError (deep recursion / infinite calls)


========================
3️⃣ METASPACE (Java 8+)
========================
• Stores class-level metadata
• Includes:
  - Class structure
  - Method & field info
  - Runtime constant pool
  - Static variables (class)
• NOT part of Heap
• Uses native OS memory
• Grows dynamically
• Memory freed only when ClassLoader unloads
• Replaced PermGen
• Error:
  → OutOfMemoryError: Metaspace


========================
4️⃣ CODE CACHE (VERY IMPORTANT)
========================
• Stores JIT compiled native code
• Native code = CPU-specific machine instructions
• Filled ONLY at runtime
• Used directly by CPU (no interpretation)
• Improves performance massively
• Managed by JVM JIT Compiler
• If full:
  → Performance drops
  → Rare "CodeCache full" warning


========================
5️⃣ JIT (JUST-IN-TIME COMPILER)
========================
• Does NOT run during `javac`
• Runs ONLY during runtime (`java ClassName`)
• Works like this:
   - Bytecode starts in Interpreter
   - JVM detects HOT methods/loops
   - JIT compiles them into native code
   - Stores native code in Code Cache
   - Future executions skip interpreter
• Uses runtime profiling for optimization
• Optimizations include:
   - Method inlining
   - Loop unrolling
   - Dead code elimination
   - Escape analysis

COMMAND FLOW:
-------------
javac Shivam.java   → Compile time (NO JIT)
java Shivam         → Runtime (JIT ACTIVE)

JIT PROOF:
----------
java -Xint Shivam   → Interpreter only (slow)
java Shivam         → JIT enabled (fast)


========================
6️⃣ ERROR MAPPING (INTERVIEW FAVORITE)
========================
• Heap Full        → OutOfMemoryError: Java heap space
• Stack Full       → StackOverflowError
• Metaspace Full   → OutOfMemoryError: Metaspace
• Direct Memory    → OutOfMemoryError: Direct buffer memory


========================
7️⃣ ONE-LINER (PERFECT INTERVIEW)
========================
"Heap stores objects, Stack stores calls,
 Metaspace stores classes,
 Code Cache stores JIT compiled native code."
*/



// ============================================================================
// END — This is COMPLETE JVM + JIT explanation in ONE WINDOW
// ============================================================================
