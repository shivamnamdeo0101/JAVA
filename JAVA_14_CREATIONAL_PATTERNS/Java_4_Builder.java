package JAVA_14_CREATIONAL_PATTERNS;

/**
 * BUILDER PATTERN – PIZZA ORDER EXAMPLE
 */
public class Java_4_Builder {
    public static void main(String[] args) {

        Pizza pizza = new Pizza.Builder("Medium")
                .cheese(true)
                .pepperoni(true)
                .bacon(true)
                .build();

        System.out.println(pizza);
    }
}

// ==================== PRODUCT ====================
class Pizza {
    private final String size;
    private final boolean cheese;
    private final boolean pepperoni;
    private final boolean bacon;

    private Pizza(Builder builder) {
        this.size = builder.size;
        this.cheese = builder.cheese;
        this.pepperoni = builder.pepperoni;
        this.bacon = builder.bacon;
    }

    @Override
    public String toString() {
        return "Pizza [size=" + size + ", cheese=" + cheese +
                ", pepperoni=" + pepperoni + ", bacon=" + bacon + "]";
    }

    // ==================== BUILDER ====================
    static class Builder {
        private final String size;
        private boolean cheese = false;
        private boolean pepperoni = false;
        private boolean bacon = false;

        public Builder(String size) { this.size = size; }
        public Builder cheese(boolean value) { cheese = value; return this; }
        public Builder pepperoni(boolean value) { pepperoni = value; return this; }
        public Builder bacon(boolean value) { bacon = value; return this; }

        public Pizza build() { return new Pizza(this); }
    }
}

/*
================ BUILDER – FEATURES & DESIGN =================
WHAT:
- Allows building complex objects step by step.
- Useful for objects with optional parameters.
WHY IT EXISTS:
- Avoids telescoping constructors.
- Provides readable, maintainable code.
INTERNAL WORKING:
- Static inner Builder class holds parameters.
- build() constructs immutable object.
TIME COMPLEXITY: O(1) for creation
CORE FEATURES:
- Fluent interface
- Immutable product
REAL SYSTEM USAGE:
- Config objects, API requests, complex entity creation
*/
