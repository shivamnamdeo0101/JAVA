package JAVA_11_ADVANDED_JAVA;

import java.lang.reflect.*;

/**
 * Topic: Reflection API
 */
public class Java_3_ReflectionAPI {

    static class Demo {
        private String name = "John";

        public void sayHello() {
            System.out.println("Hello, " + name);
        }

        private void secret() {
            System.out.println("Secret method");
        }
    }

    public static void main(String[] args) throws Exception {
        Class<Demo> clazz = Demo.class;

        // =========================
        // Instantiate using reflection
        // =========================
        Constructor<Demo> constructor = clazz.getConstructor();
        Demo obj = constructor.newInstance();

        // =========================
        // Access methods
        // =========================
        Method method = clazz.getMethod("sayHello");
        method.invoke(obj);

        Method secretMethod = clazz.getDeclaredMethod("secret");
        secretMethod.setAccessible(true);
        secretMethod.invoke(obj);

        // =========================
        // Access fields
        // =========================
        Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);
        field.set(obj, "Shivam");
        method.invoke(obj);
    }
}

/*
================================================================================
REFLECTION API – DEEP DIVE
================================================================================

WHAT:
-----
• Allows inspection and manipulation of classes, fields, methods at runtime
• Breaks encapsulation safely
• Enables dynamic instantiation, method invocation, field access

WHY IT EXISTS:
--------------
• Dynamic frameworks (Spring, Hibernate)
• Runtime configuration
• Testing tools (JUnit)
• Serialization & deserialization
• Plugins / modules loaded dynamically

INTERNAL WORKING:
-----------------
• JVM maintains Class object per loaded class
• Methods, fields, constructors accessible via Class API
• Reflection internally uses:
   • MethodAccessor
   • FieldAccessor
   • Unsafe / native calls
• Accessing private members requires setAccessible(true)
• Modifiers checked at runtime
• Performance slower due to:
   • No inlining
   • Native calls
   • Security checks

DEFAULT VALUES:
---------------
• N/A (depends on class loaded)
• No capacity / load factor
• Thread-safe if multiple Class objects, but instance manipulation needs external sync

TIME COMPLEXITY:
----------------
• getMethod / getField → O(n) over declared members
• invoke → native call overhead

CORE FEATURES:
--------------
✔ Runtime inspection
✔ Dynamic invocation
✔ Access private members
✔ Class loading introspection

ENTERPRISE PITFALLS:
-------------------
❌ Overuse → performance hit
❌ Security manager blocks reflection
❌ Bypassing private fields carelessly

REAL SYSTEM USAGE:
-----------------
✔ Spring IoC Bean injection
✔ Hibernate ORM mapping
✔ JUnit / TestNG dynamic tests
✔ Serialization frameworks (Jackson, Gson)

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Can private methods be invoked?
A: Yes, with setAccessible(true).

Q2: Performance impact of reflection?
A: Slower due to no JIT inlining and extra checks.

Q3: Difference between getMethod and getDeclaredMethod?
A: getMethod → public only including inherited, getDeclaredMethod → all declared in class.

INTERVIEW ONE-LINER:
-------------------
"Reflection allows Java programs to inspect and manipulate classes dynamically, crucial for frameworks and runtime behavior."
================================================================================
*/
