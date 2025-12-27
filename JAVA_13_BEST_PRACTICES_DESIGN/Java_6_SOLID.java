package JAVA_13_BEST_PRACTICES_DESIGN;
import java.util.List;

/**
 * ENTERPRISE-READY SOLID PRINCIPLES IN JAVA
 * 
 * Demonstrates:
 * - SRP (Single Responsibility)
 * - OCP (Open-Closed)
 * - LSP (Liskov Substitution)
 * - ISP (Interface Segregation)
 * - DIP (Dependency Inversion) with Weather API example
 */
public class Java_6_SOLID {
    public static void main(String[] args) {

        // =========================
        // 1️⃣ SRP: Single Responsibility Principle
        // =========================
        Report report = new Report("Q4 Financials");
        ReportPrinter printer = new ReportPrinter();
        printer.printReport(report);

        // =========================
        // 2️⃣ OCP: Open-Closed Principle
        // =========================
        List<Shape> shapes = List.of(new Circle(5), new Rectangle(4, 6));
        ShapeCalculator calculator = new ShapeCalculator();
        System.out.println("Total Area: " + calculator.calculateTotalArea(shapes));

        // =========================
        // 3️⃣ LSP: Liskov Substitution Principle
        // =========================
        FlyingBird sparrow = new Sparrow();
        sparrow.fly();

        // =========================
        // 4️⃣ ISP: Interface Segregation Principle
        // =========================
        DevOpsEngineer devOps = new DevOpsEngineer();
        devOps.deploy(); // Only implements what is necessary

        // =========================
        // 5️⃣ DIP: Dependency Inversion Principle
        // =========================
        WeatherProvider weatherAPI = new OpenWeatherAPI(); // Abstraction used
        WeatherService weatherService = new WeatherService(weatherAPI);

        System.out.println("Weather in Delhi: " + weatherService.getWeather("Delhi"));

        // Swap easily to another provider
        weatherService.setWeatherProvider(new WeatherStackAPI());
        System.out.println("Weather in Mumbai: " + weatherService.getWeather("Mumbai"));
    }
}

// =============================================================================
// S - SINGLE RESPONSIBILITY PRINCIPLE (SRP)
// =============================================================================
class Report {
    private String content;
    public Report(String content) { this.content = content; }
    public String getContent() { return content; }
}

class ReportPrinter {
    public void printReport(Report report) {
        System.out.println("Outputting Report: " + report.getContent());
    }
}

// =============================================================================
// O - OPEN-CLOSED PRINCIPLE (OCP)
// =============================================================================
interface Shape { double area(); }

class Circle implements Shape {
    private double radius;
    public Circle(double radius) { this.radius = radius; }
    @Override public double area() { return Math.PI * radius * radius; }
}

class Rectangle implements Shape {
    private double length, width;
    public Rectangle(double length, double width) { this.length = length; this.width = width; }
    @Override public double area() { return length * width; }
}

class ShapeCalculator {
    public double calculateTotalArea(List<Shape> shapes) {
        return shapes.stream().mapToDouble(Shape::area).sum();
    }
}

// =============================================================================
// L - LISKOV SUBSTITUTION PRINCIPLE (LSP)
// =============================================================================
abstract class Bird { }

abstract class FlyingBird extends Bird {
    public abstract void fly();
}

class Sparrow extends FlyingBird {
    @Override
    public void fly() { System.out.println("Sparrow flying high."); }
}

class Penguin extends Bird { }

// =============================================================================
// I - INTERFACE SEGREGATION PRINCIPLE (ISP)
// =============================================================================
interface Codeable { void code(); }
interface Testable { void test(); }
interface Deployable { void deploy(); }

class FullStackDeveloper implements Codeable, Testable, Deployable {
    @Override public void code() { System.out.println("Writing Java..."); }
    @Override public void test() { System.out.println("Writing JUnit tests..."); }
    @Override public void deploy() { System.out.println("Deploying to AWS..."); }
}

class DevOpsEngineer implements Deployable {
    @Override public void deploy() { System.out.println("Managing CI/CD Pipelines..."); }
}

// =============================================================================
// D - DEPENDENCY INVERSION PRINCIPLE (DIP) - Weather API Example
// =============================================================================
interface WeatherProvider {
    String getWeather(String city);
}

// Concrete implementation #1
class OpenWeatherAPI implements WeatherProvider {
    @Override
    public String getWeather(String city) {
        // Imagine API call here
        return "Sunny (OpenWeatherAPI) in " + city;
    }
}

// Concrete implementation #2
class WeatherStackAPI implements WeatherProvider {
    @Override
    public String getWeather(String city) {
        // Another API call
        return "Cloudy (WeatherStackAPI) in " + city;
    }
}

// High-level module depends on abstraction
class WeatherService {
    private WeatherProvider weatherProvider;

    public WeatherService(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    public void setWeatherProvider(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    public String getWeather(String city) {
        return weatherProvider.getWeather(city);
    }
}

/*
================================================================================
DETAILED SOLID COMMENTARY - ENTERPRISE & INTERVIEW FOCUS
================================================================================

WHAT:
-----
- SOLID principles guide enterprise-level OOP design.
- Abstraction and separation of concerns are key.
- DIP example now uses Weather API for real-world analogy.

WHY IT EXISTS:
--------------
- Prevents tightly-coupled code.
- Allows high-level modules to be independent of low-level API changes.
- Critical in microservices, REST clients, and cloud integration.

INTERNAL WORKING / DESIGN INSIGHTS:
-----------------------------------
- DIP: High-level module (WeatherService) depends on WeatherProvider interface.
- Easy to swap API without modifying WeatherService.
- Constructor injection and setter injection used.

DEFAULT VALUES:
---------------
- No specific capacity; design principle, not data structure.

TIME COMPLEXITY:
----------------
- Depends on underlying implementation (API call latency).

CORE FEATURES:
--------------
✔ Decoupled code
✔ Testable (mock APIs)
✔ Extensible (add new API providers)
✔ Swap providers without changing high-level logic

REAL ENTERPRISE USAGE:
---------------------
- SRP: Report generation microservice
- OCP: Payment or discount strategies
- LSP: Behavioral contracts (flying vs non-flying entities)
- ISP: Developer teams implementing only relevant methods
- DIP: Service depends on abstractions (API clients, DB, cloud storage)

ENTERPRISE PITFALLS:
--------------------
❌ Violating LSP by forcing a subclass to implement unsupported behavior
❌ Hard-coding API provider in high-level module
❌ Overloading classes with multiple responsibilities

INTERVIEW QUESTIONS:
-------------------
Q1: How does DIP improve maintainability?
A: High-level code depends on interface, not implementation → easier API/provider swap.

Q2: Why use setter injection for WeatherService?
A: Allows runtime swapping of providers without changing high-level logic.

Q3: How is LSP applied here?
A: Sparrow extends FlyingBird, Penguin extends Bird → no fly() violations.

Q4: Why is SRP important in microservices?
A: Each service does one thing → easier to test, scale, deploy.

INTERVIEW ONE-LINER:
-------------------
"DIP decouples high-level modules from specific implementations, enabling adaptable, maintainable systems."

================================================================================
*/
