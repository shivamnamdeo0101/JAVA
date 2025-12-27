package JAVA_16_STRUCTURAL_PATTERNS;

/**
 * DECORATOR PATTERN – COFFEE SHOP EXAMPLE
 */
public class Java_4_Decorator {
    public static void main(String[] args) {

        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println("Price: " + simpleCoffee.getCost());

        Coffee milkCoffee = new MilkDecorator(simpleCoffee);
        System.out.println("Price with milk: " + milkCoffee.getCost());

        Coffee sugarMilkCoffee = new SugarDecorator(milkCoffee);
        System.out.println("Price with milk & sugar: " + sugarMilkCoffee.getCost());
    }
}

// ==================== COMPONENT ====================
interface Coffee {
    double getCost();
}

// ==================== CONCRETE COMPONENT ====================
class SimpleCoffee implements Coffee {
    @Override public double getCost() { return 5.0; }
}

// ==================== DECORATOR ====================
abstract class CoffeeDecorator implements Coffee {
    protected final Coffee decoratedCoffee;
    public CoffeeDecorator(Coffee coffee) { this.decoratedCoffee = coffee; }
}

// ==================== CONCRETE DECORATORS ====================
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) { super(coffee); }
    @Override public double getCost() { return decoratedCoffee.getCost() + 2.0; }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) { super(coffee); }
    @Override public double getCost() { return decoratedCoffee.getCost() + 1.0; }
}

/*
================ DECORATOR PATTERN – FEATURES & DESIGN =================
WHAT:
- Attach additional responsibilities to object dynamically.
- Example: Adding milk or sugar to coffee without modifying base class.
REAL SYSTEM USAGE:
- I/O Streams, Logging, GUI decorations, dynamic feature toggles
*/
