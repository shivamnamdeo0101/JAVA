package JAVA_11_ADVANDED_JAVA;

import java.lang.annotation.*;
import java.lang.reflect.*;

/**
 * Topic: Annotations
 */
public class Java_1_Annotations {

    // Custom Annotation
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD})
    public @interface MyAnnotation {
        String value() default "Default";
        int version() default 1;
    }

    @MyAnnotation(value = "ClassAnnotation", version = 2)
    static class DemoClass {
        @MyAnnotation("MethodAnnotation")
        public void demoMethod() {
            System.out.println("Demo Method");
        }
    }

    public static void main(String[] args) throws Exception {
        DemoClass obj = new DemoClass();
        obj.demoMethod();

        // =========================
        // Reflection to read annotation
        // =========================
        Class<?> clazz = DemoClass.class;
        if(clazz.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation = clazz.getAnnotation(MyAnnotation.class);
            System.out.println("Class Annotation: " + annotation.value() + ", Version: " + annotation.version());
        }

        Method method = clazz.getMethod("demoMethod");
        if(method.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
            System.out.println("Method Annotation: " + annotation.value());
        }
    }
}

/*
================================================================================
ANNOTATIONS – DEEP DIVE
================================================================================

WHAT:
-----
• Metadata added to classes, methods, fields
• Does not affect program logic directly
• Represents declarative information for tools, frameworks, or runtime

WHY IT EXISTS:
--------------
• Remove hard-coded configuration (XML)
• Enable compile-time checks (Override, Deprecated)
• Facilitate frameworks (Spring, JPA)
• Runtime introspection via Reflection

INTERNAL WORKING:
-----------------
• Stored in class file in a special attributes section
• RetentionPolicy defines lifecycle:
   • SOURCE → discarded at compile time
   • CLASS → in class file, not runtime
   • RUNTIME → available via reflection
• JVM reads annotation metadata at runtime when requested

CORE FEATURES:
--------------
✔ Compile-time & runtime metadata
✔ Supports default values
✔ Can target classes, methods, fields, etc.
✔ Can be nested and repeatable

ENTERPRISE PITFALLS:
-------------------
❌ Overusing runtime annotations for performance-heavy tasks
❌ Misunderstanding retention → annotation not accessible
❌ Creating custom annotations without documentation

REAL SYSTEM USAGE:
-----------------
✔ Spring Bean configuration (@Component, @Autowired)
✔ JPA Entities mapping (@Entity, @Column)
✔ Testing frameworks (@Test, @Before)
✔ Logging / auditing metadata

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference between SOURCE, CLASS, RUNTIME retention?
A: SOURCE → discarded at compile-time, CLASS → in .class file, RUNTIME → accessible via reflection.

Q2: Can annotations be inherited?
A: Only if @Inherited meta-annotation is used.

Q3: Are annotations objects?
A: Not regular objects; JVM creates proxy objects when accessed via reflection.

INTERVIEW ONE-LINER:
-------------------
"Annotations provide declarative metadata enabling frameworks and runtime introspection without altering program logic."
================================================================================
*/
