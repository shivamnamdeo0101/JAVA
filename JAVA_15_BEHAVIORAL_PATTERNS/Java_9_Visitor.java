package JAVA_15_BEHAVIORAL_PATTERNS;

/**
 * VISITOR PATTERN – SHOPPING CART EXAMPLE
 */
public class Java_9_Visitor {
    public static void main(String[] args) {
        Item book = new Book(100);
        Item fruit = new Fruit(50);

        ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
        System.out.println("Book cost: " + book.accept(visitor));
        System.out.println("Fruit cost: " + fruit.accept(visitor));
    }
}

// ==================== VISITOR INTERFACE ====================
interface ShoppingCartVisitor {
    int visit(Book book);
    int visit(Fruit fruit);
}

// ==================== ELEMENT INTERFACE ====================
interface Item {
    int accept(ShoppingCartVisitor visitor);
}

// ==================== CONCRETE ELEMENTS ====================
class Book implements Item {
    private final int price;
    public Book(int price) { this.price = price; }
    public int getPrice() { return price; }
    @Override public int accept(ShoppingCartVisitor visitor) { return visitor.visit(this); }
}

class Fruit implements Item {
    private final int price;
    public Fruit(int price) { this.price = price; }
    public int getPrice() { return price; }
    @Override public int accept(ShoppingCartVisitor visitor) { return visitor.visit(this); }
}

// ==================== CONCRETE VISITOR ====================
class ShoppingCartVisitorImpl implements ShoppingCartVisitor {
    @Override public int visit(Book book) { return book.getPrice(); }
    @Override public int visit(Fruit fruit) { return fruit.getPrice(); }
}

/*
================ VISITOR PATTERN – FEATURES & DESIGN =================
WHAT:
- Separates algorithm from objects it operates on.
- Example: Shopping cart calculating costs.
WHY IT EXISTS:
- Add new operations without changing object structure.
REAL SYSTEM USAGE:
- Tax calculation, discount application, audit logging
*/
