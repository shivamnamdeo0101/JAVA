package JAVA_13_BEST_PRACTICES_DESIGN;

public class Java_2_ToString {
    public static void main(String[] args) {
        Employee emp = new Employee(1, "Bob");
        System.out.println(emp); // Output: Employee{id=1, name='Bob'}
    }
}

/*
================================================================================
TOSTRING – DEEP DIVE
================================================================================
WHAT:
- Provides human-readable object representation for logging/debugging.

WHY IT EXISTS:
- Default Object.toString() → class@hashcode (not readable)
- Useful for monitoring and debugging enterprise apps.

INTERNAL WORKING:
- JVM calls emp.toString() → prints meaningful fields.
- Can be overridden to include relevant fields only.

ENTERPRISE PITFALLS:
- Forgetting to override → logs not meaningful.
- Sensitive fields (passwords) included accidentally → security risk.

REAL USE CASE:
- Logs, API responses, monitoring dashboards

INTERVIEW Q&A:
Q1: Why override toString()? → For readability, maintainability, logging.
Q2: Should sensitive info be included? → No, use custom formatting or masking.
================================================================================
*/
