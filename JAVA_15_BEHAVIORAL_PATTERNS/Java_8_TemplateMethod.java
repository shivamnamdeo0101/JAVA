package JAVA_15_BEHAVIORAL_PATTERNS;

/**
 * TEMPLATE METHOD PATTERN – COFFEE BREWING EXAMPLE
 */
public class Java_8_TemplateMethod {
    public static void main(String[] args) {

        Coffee coffee = new Coffee();
        Tea tea = new Tea();

        System.out.println("Making coffee:");
        coffee.prepareBeverage();

        System.out.println("\nMaking tea:");
        tea.prepareBeverage();
    }
}

// ==================== TEMPLATE ====================
abstract class Beverage {
    // Template method
    public final void prepareBeverage() {
        boilWater();
        brew();
        pour();
        addCondiments();
    }

    abstract void brew();
    abstract void addCondiments();

    void boilWater() { System.out.println("Boiling water"); }
    void pour() { System.out.println("Pouring into cup"); }
}

// ==================== CONCRETE CLASSES ====================
class Coffee extends Beverage {
    @Override void brew() { System.out.println("Brewing coffee"); }
    @Override void addCondiments() { System.out.println("Adding sugar and milk"); }
}

class Tea extends Beverage {
    @Override void brew() { System.out.println("Steeping tea"); }
    @Override void addCondiments() { System.out.println("Adding lemon"); }
}

/*
================ TEMPLATE METHOD PATTERN – FEATURES & DESIGN =================
WHAT:
- Defines skeleton of algorithm in superclass but lets subclasses override steps.
- Example: Coffee and Tea brewing steps.
WHY IT EXISTS:
- Reuse common behavior while allowing customization.
REAL SYSTEM USAGE:
- Report generation, workflow templates, UI wizards
*/
