package JAVA_16_STRUCTURAL_PATTERNS;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Flyweight Pattern: CoffeeFlavor example
 * Demonstrates memory-efficient sharing of CoffeeFlavor objects.
 * 
 * 2️⃣ Flyweight Pattern Concept

Shared object = holds intrinsic state (unchanging)
External data = passed each time (extrinsic state)
 */

// ======================= FLYWEIGHT =======================
class CoffeeFlavor {
    private final String name; // Intrinsic state (shared)

    public CoffeeFlavor(String name) {
        this.name = name;
    }

    // Extrinsic state (tableNumber) passed externally
    public void serve(int tableNumber) {
        System.out.println("Serving " + name + " to table " + tableNumber);
    }
}

// ======================= FLYWEIGHT FACTORY =======================
class CoffeeFactory {
    // Thread-safe cache for shared CoffeeFlavor objects
    private static final Map<String, CoffeeFlavor> flavors = new ConcurrentHashMap<>();

    public static CoffeeFlavor getFlavor(String name) {
        // computeIfAbsent ensures atomic creation
        return flavors.computeIfAbsent(name, CoffeeFlavor::new);
    }

    public static int getTotalFlavors() {
        return flavors.size();
    }
}

// ======================= CLIENT =======================
public class Java_7_Flyweight {
    public static void main(String[] args) {
        String[] orders = {"Cappuccino", "Latte", "Cappuccino", "Latte", "Latte"};

        for (int i = 0; i < orders.length; i++) {
            CoffeeFlavor flavor = CoffeeFactory.getFlavor(orders[i]);
            flavor.serve(i + 1); // Extrinsic state: table number
        }

        System.out.println("-----------------------------------------");
        System.out.println("Total Flavor Objects Created: " + CoffeeFactory.getTotalFlavors());
    }
}

/*
======================== FLYWEIGHT PATTERN – COFFEE EXAMPLE ========================

WHAT:
-----
- Flyweight pattern shares immutable CoffeeFlavor objects to reduce memory.
- Intrinsic state: flavor name.
- Extrinsic state: table number, passed externally.


WHY IT EXISTS:
--------------
- Without sharing, each order would create a new CoffeeFlavor object.
- Flyweight separates intrinsic (shared) and extrinsic (unique per usage) states.

INTERNAL WORKING:
-----------------
- CoffeeFactory maintains a ConcurrentHashMap cache.
- computeIfAbsent ensures atomic, thread-safe creation.
- Client passes table number externally, reusing CoffeeFlavor objects.

ENTERPRISE USE CASES:
--------------------
- Coffee shop management systems
- POS order tracking
- Any system with repeated objects (fonts, glyphs, vehicles, icons)

KEY BENEFITS:
-------------
- Memory efficiency
- Thread-safe object sharing
- Clear separation of intrinsic and extrinsic states

INTERVIEW TIP:
--------------
"Flyweight reduces memory by reusing immutable objects and externalizing unique state."

=========================================================================
*/
