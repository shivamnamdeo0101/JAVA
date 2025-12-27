package JAVA_13_BEST_PRACTICES_DESIGN;

import java.util.*;

public class Java_1_EqualsHashCode {
    public static void main(String[] args) {
        Set<Employee> employeeSet = new HashSet<>();
        Employee e1 = new Employee(1, "Alice");
        Employee e2 = new Employee(1, "Alice");

        employeeSet.add(e1);
        employeeSet.add(e2); // prevented duplicate

        System.out.println("Set size: " + employeeSet.size()); // Output: 1
    }
}

class Employee {
    private int id;
    private String name;

    public Employee(int id, String name) { this.id = id; this.name = name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee emp = (Employee) o;
        return id == emp.id && Objects.equals(name, emp.name);
    }

    @Override
    public int hashCode() { return Objects.hash(id, name); }

    @Override
    public String toString() { return "Employee{id=" + id + ", name='" + name + "'}"; }
}

/*
================================================================================
EQUALS & HASHCODE – DEEP DIVE
================================================================================
WHAT:
- Ensures object equality semantics for collections (Set/Map).
- Prevents duplicates and ensures retrieval correctness.

WHY IT EXISTS:
- Default Object equals() is reference-based → problematic in collections.
- hashCode ensures proper bucket placement in HashMap/HashSet.

INTERNAL WORKING:
- HashSet/HashMap compute hashCode() → index → compare equals() on collisions.
- JVM uses Objects.hash() or field values for hashCode.
- equals() used to confirm logical equality.
- HashCode consistency is critical: if obj changes, retrieval fails.

ENTERPRISE PITFALLS:
- Mutable fields in hashCode → breaks collections.
- Failing to override both equals & hashCode → duplicates or missed elements.

REAL USE CASE:
- DTOs in HashSet/HashMap
- Unique entities in microservices caches

INTERVIEW Q&A:
Q1: Why override both equals & hashCode? → For collections correctness.
Q2: What happens if hashCode changes? → Element may become "lost" in Set/Map.
================================================================================
*/
