package JAVA_14_CREATIONAL_PATTERNS;

/**
 * ABSTRACT FACTORY PATTERN – UI THEME EXAMPLE
 */
public class Java_3_AbstractFactory {
    public static void main(String[] args) {

        UIFactory lightFactory = FactoryProvider.getFactory("LIGHT");
        Button lightButton = lightFactory.createButton();
        Checkbox lightCheckbox = lightFactory.createCheckbox();

        lightButton.render();
        lightCheckbox.render();

        UIFactory darkFactory = FactoryProvider.getFactory("DARK");
        Button darkButton = darkFactory.createButton();
        Checkbox darkCheckbox = darkFactory.createCheckbox();

        darkButton.render();
        darkCheckbox.render();
    }
}

// ==================== ABSTRACT FACTORY ====================
interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

interface Button { void render(); }
interface Checkbox { void render(); }

// Concrete factories
class LightThemeFactory implements UIFactory {
    public Button createButton() { return new LightButton(); }
    public Checkbox createCheckbox() { return new LightCheckbox(); }
}

class DarkThemeFactory implements UIFactory {
    public Button createButton() { return new DarkButton(); }
    public Checkbox createCheckbox() { return new DarkCheckbox(); }
}

// Concrete products
class LightButton implements Button { public void render() { System.out.println("Light Button rendered"); } }
class DarkButton implements Button { public void render() { System.out.println("Dark Button rendered"); } }
class LightCheckbox implements Checkbox { public void render() { System.out.println("Light Checkbox rendered"); } }
class DarkCheckbox implements Checkbox { public void render() { System.out.println("Dark Checkbox rendered"); } }

// Factory provider
class FactoryProvider {
    public static UIFactory getFactory(String type) {
        switch (type.toUpperCase()) {
            case "LIGHT": return new LightThemeFactory();
            case "DARK": return new DarkThemeFactory();
            default: throw new IllegalArgumentException("Unknown theme");
        }
    }
}

/*
================ ABSTRACT FACTORY – FEATURES & DESIGN =================
WHAT:
- Provides an interface to create families of related objects without specifying concrete classes.
- Example: UI components that adapt to theme.
WHY IT EXISTS:
- Without it, client needs to know concrete classes of multiple products.
- Solves the problem of consistent UI design in enterprise apps.
INTERNAL WORKING:
- Abstract factory defines abstract methods
- Concrete factories implement methods for different product families
- Client interacts only with abstract interfaces
TIME COMPLEXITY: O(1) for object creation
CORE FEATURES:
- Decouples client from concrete classes
- Supports multiple product families
REAL SYSTEM USAGE:
- UI Theme systems, cross-platform GUI apps, widget libraries
*/
