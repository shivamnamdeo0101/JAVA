package JAVA_11_ADVANDED_JAVA;

import java.lang.annotation.*;

/**
 * Topic: Meta-Annotations
 */
public class Java_2_MetaAnnotations {

    // Meta-Annotation Example
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.ANNOTATION_TYPE)
    @Documented
    public @interface MyMetaAnnotation {}

    @MyMetaAnnotation
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface MethodAnnotation {}

    static class Demo {
        @MethodAnnotation
        public void demoMethod() {
            System.out.println("Demo Method with MetaAnnotation");
        }
    }

    public static void main(String[] args) {
        System.out.println("Meta-annotations mark other annotations.");
    }
}

/*
================================================================================
META-ANNOTATIONS – DEEP DIVE
================================================================================

WHAT:
-----
• Annotations that apply to other annotations
• Examples: @Retention, @Target, @Inherited, @Documented
• Provide rules about how annotations behave

WHY IT EXISTS:
--------------
• Standardize annotation behavior
• Ensure annotations are retained properly
• Provide compile-time enforcement for annotation usage

INTERNAL WORKING:
-----------------
• JVM stores meta-annotations in class file attributes
• Checked during compilation or runtime reflection
• Influences processing of the annotation they annotate

CORE FEATURES:
--------------
✔ Control retention (SOURCE/CLASS/RUNTIME)
✔ Define target (METHOD/CLASS/FIELD)
✔ Enable inheritance with @Inherited
✔ Allow documentation with @Documented

ENTERPRISE PITFALLS:
-------------------
❌ Misunderstanding target → compile errors
❌ Forgetting retention → annotation not accessible at runtime

REAL SYSTEM USAGE:
-----------------
✔ Frameworks enforce rules (Spring, JUnit)
✔ Custom validation annotations
✔ API documentation generators

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: What is @Inherited?
A: Makes annotation inherited by subclasses.

Q2: Can meta-annotations be applied to classes?
A: No, only to other annotations (@Target(ANNOTATION_TYPE)).

INTERVIEW ONE-LINER:
-------------------
"Meta-annotations define the behavior and scope of custom or built-in annotations."
================================================================================
*/
