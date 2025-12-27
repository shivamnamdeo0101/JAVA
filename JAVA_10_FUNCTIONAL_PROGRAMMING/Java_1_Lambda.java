package JAVA_10_FUNCTIONAL_PROGRAMMING;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * TOPIC: Lambda Expressions & Functional Interfaces
 * * CONCEPT: 
 * - Lambdas allow you to pass "behavior" as a parameter.
 * - They replace bulky Anonymous Inner Classes.
 * - They require a "Functional Interface" (Interface with exactly ONE abstract method).
 */
public class Java_1_Lambda {

    public static void main(String[] args) {

        // ============================================================
        // 1. PREDICATE (The Filter) - Input: T, Output: boolean
        // UseCase: Validations, Filtering, Conditional checking
        // ============================================================
        Predicate<Integer> isAdult = (age) -> age >= 18;
        Predicate<String> isNotEmpty = (str) -> str != null && !str.isEmpty();
        
        System.out.println("Can vote? " + isAdult.test(20)); // true


        // ============================================================
        // 2. FUNCTION (The Mapper) - Input: T, Output: R
        // UseCase: Converting data (e.g., Entity to DTO, String to Integer)
        // ============================================================
        Function<String, Integer> stringLength = (s) -> s.length();
        Function<Integer, Integer> multiplyByTen = (n) -> n * 10;
        
        // Chaining: first find length, then multiply by 10
        Integer result = stringLength.andThen(multiplyByTen).apply("Gemini");
        System.out.println("Transformed Result: " + result);


        // ============================================================
        // 3. CONSUMER (The Executor) - Input: T, Output: void
        // UseCase: Printing, Logging, Saving to Database
        // ============================================================
        Consumer<String> logger = (msg) -> System.out.println("[LOG]: " + msg);
        Consumer<String> databaseSaver = (data) -> { /* logic to save */ };
        
        logger.accept("Lambda started executing...");


        // ============================================================
        // 4. SUPPLIER (The Producer) - Input: None, Output: T
        // UseCase: Generating IDs, Factory methods, Lazy Initialization
        // ============================================================
        Supplier<Double> randomGenerator = () -> Math.random();
        Supplier<String> dbConfig = () -> "jdbc:mysql://localhost:3306/db";
        
        System.out.println("New ID: " + randomGenerator.get());


        // ============================================================
        // 5. METHOD REFERENCES (::) - Shorthand for Lambdas
        // UseCase: When a lambda just calls an existing method.
        // ============================================================
        List<String> names = Arrays.asList("Shivam", "Amit", "Ravi");
        
        // Lambda: names.forEach(s -> System.out.println(s));
        // Method Reference (Shorter):
        names.forEach(System.out::println); 


        // ============================================================
        // 6. REAL WORLD ENTERPRISE SCENARIO
        // Filtering a list of products and getting their names
        // ============================================================
        List<Product> products = Arrays.asList(
            new Product("Laptop", 1200),
            new Product("Phone", 800),
            new Product("Mouse", 20)
        );

        List<String> expensiveProducts = products.stream()
            .filter(p -> p.getPrice() > 500)      // Predicate
            .map(Product::getName)               // Function (Method Ref)
            .collect(Collectors.toList());

        System.out.println("Premium Items: " + expensiveProducts);
    }

    static class Product {
        String name; double price;
        Product(String n, double p) { name = n; price = p; }
        public String getName() { return name; }
        public double getPrice() { return price; }
    }
}

/*
================================================================================
INTERVIEW DEEP DIVE: WHY LAMBDAS?
================================================================================

1. WHAT IS "EFFECTIVELY FINAL"?
-------------------------------
Local variables used inside a lambda must not be modified after they are defined.
If you try to change 'x' after using it in a lambda, the code won't compile.

2. LAMBDA VS ANONYMOUS INNER CLASS
-----------------------------------
- Lambda: No new .class file is generated. Uses 'invokedynamic' (faster/lighter).
- Anonymous Class: Creates a separate .class file (e.g., Main$1.class).
- 'this' in Lambda: Refers to the outer class.
- 'this' in Anonymous: Refers to the anonymous class itself.

3. PERFORMANCE BENEFIT
----------------------
Lambdas are lazily linked by the JVM, meaning they don't consume memory until 
they are actually called.

4. REAL WORLD ANALOGY (The Pizza Shop)
--------------------------------------
- Predicate: The person at the door checking if you have a ticket (Yes/No).
- Function: The chef who takes raw dough and turns it into Pizza (Input -> Output).
- Consumer: The customer who eats the pizza (Takes input, produces nothing).
- Supplier: The oven that gives out hot pizzas (Produces without taking input).

================================================================================
*/