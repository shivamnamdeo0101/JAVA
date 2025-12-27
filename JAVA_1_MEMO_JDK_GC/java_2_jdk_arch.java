package JAVA_1_MEMO_JDK_GC;
// ================================================================================
// JAVA MEMORY & JVM — PART 1
// TOPIC: JVM Architecture + JDK vs JRE vs JVM + ClassLoader
// Author: Shivam
// ================================================================================

// /*
// HIGH-LEVEL FLOW
// ---------------
// Java is NOT executed directly by OS.
// OS runs JVM, JVM runs Java code.
// */

// Shivam.java
//     |
//     | javac              // Compiler (JDK)
//     |                    // Converts source code → BYTECODE
//     v
// Shivam.class
//     |
//     | java               // Launcher
//     |                    // Starts JVM process
//     v

// ┌────────────────────────────────────────────────────────┐
// │                       JDK                              │
// │--------------------------------------------------------│
// │ • Used for DEVELOPMENT                                 │
// │ • Contains build + debug tools                         │
// │                                                        │
// │ Tools:                                                 │
// │ • javac   → compiler                                  │
// │ • java    → launcher                                  │
// │ • jar     → packaging                                 │
// │ • javadoc → docs                                      │
// │ • jdb     → debugger                                  │
// │                                                        │
// │   ┌──────────────────────────────────────────────┐    │
// │   │                    JRE                       │    │
// │   │----------------------------------------------│    │
// │   │ • Used for RUNNING Java apps  util, lang                │    │
// │   │ • Contains runtime libraries                  │    │
// │   │                                              │    │
// │   │   ┌──────────────────────────────────────┐   │    │
// │   │   │                 JVM                  │   │    │
// │   │   │--------------------------------------│   │    │
// │   │   │ • Loads classes                      │   │    │
// │   │   │ • Verifies bytecode                  │   │    │
// │   │   │ • Manages memory                     │   │    │
// │   │   │ • Executes bytecode                  │   │    │
// │   │   │ • Runs Garbage Collector             │   │    │
// │   │   └──────────────────────────────────────┘   │    │
// │   └──────────────────────────────────────────────┘    │
// └────────────────────────────────────────────────────────┘

// ================================================================================
// CLASSLOADER SUBSYSTEM
// ================================================================================

// /*
// ClassLoader loads .class files into JVM memory
// */

// ClassLoader Flow
// ----------------
// Bootstrap ClassLoader
//     → Loads core Java classes
//     → java.lang.Object, String, System
//     → Implemented in native code (C/C++)

// Extension / Platform ClassLoader
//     → Loads standard libraries
//     → java.sql, java.xml, logging

// Application ClassLoader
//     → Loads user-defined classes
//     → From classpath / JAR

// ================================================================================
// KEY INTERVIEW FACTS
// ================================================================================
// • JDK = JRE + Dev Tools
// • JRE = JVM + Libraries
// • JVM is platform dependent
// • Bytecode is platform independent
// • ClassLoader follows parent delegation

// ONE-LINER:
// "ClassLoaders load classes, JVM executes them, OS hosts the JVM."
// ================================================================================
