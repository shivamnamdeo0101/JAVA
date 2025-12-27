package JAVA_16_STRUCTURAL_PATTERNS;

/**
 * Proxy Pattern: Control Access, Lazy Initialization, Logging, Security
 */
interface Image {
    void display();
}

// ======================= REAL SUBJECT =======================
class RealImage implements Image {
    private final String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk(); // Simulate expensive operation
    }

    private void loadFromDisk() {
        System.out.println("Loading image from disk: " + filename);
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

// ======================= PROXY =======================
class ImageProxy implements Image {
    private final String filename;
    private RealImage realImage;

    public ImageProxy(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        // Lazy initialization: load RealImage only when needed
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        System.out.println("[Proxy] Before displaying: " + filename);
        realImage.display();
        System.out.println("[Proxy] After displaying: " + filename);
    }
}

// ======================= CLIENT =======================
public class Java_6_Proxy {
    public static void main(String[] args) {
        Image image1 = new ImageProxy("photo1.jpg");
        Image image2 = new ImageProxy("photo2.jpg");

        // Images are not loaded until display() is called
        image1.display();
        System.out.println("-----------------------------------");
        image2.display();
        System.out.println("-----------------------------------");

        // Second call uses already loaded RealImage (no disk load)
        image1.display();
    }
}

/*
======================== PROXY PATTERN – DEEP DIVE ========================

WHAT:
-----
Proxy provides a surrogate or placeholder for another object to control access.
It solves:
• Lazy loading
• Access control
• Logging / auditing
• Network optimization

WHY IT EXISTS:
--------------
Before Proxy:
• Clients had to manage expensive resources manually
• No uniform access to sensitive objects
• High coupling

INTERNAL WORKING:
-----------------
- RealSubject: The actual object (expensive to create/load)
- Proxy: Holds reference to RealSubject, controls access
- Lazy init: RealSubject instantiated only when needed
- Thread-safety: Use synchronization or atomic reference if shared

DEFAULT BEHAVIOR:
----------------
- Thread-safety not guaranteed unless explicitly handled
- Lazy initialization optional
- Extra logic (logging/security) can be injected in Proxy

TIME COMPLEXITY:
----------------
- Proxy call: O(1)
- Real object creation (first call): expensive (depends on real initialization)

CORE FEATURES:
--------------
- Controls access to object
- Can add extra responsibilities
- Supports lazy loading
- Transparent to client (same interface)

ENTERPRISE USE CASES:
--------------------
- Image / Video streaming
- Remote service calls (RMI, web services)
- Security checks before accessing resources
- Caching expensive computations

INTERVIEW QUESTIONS:
-------------------
Q1: Difference between Proxy and Decorator?
A : Proxy controls access; Decorator adds behavior.

Q2: How does lazy loading work in Proxy?
A : Real object created only when first request occurs.

Q3: Can Proxy be thread-safe?
A : Yes, by synchronizing access to RealSubject or using volatile.

Q4: Why use Proxy instead of directly accessing object?
A : Encapsulation, lazy init, logging, access control.

INTERVIEW ONE-LINER:
-------------------
"Proxy acts as a controlled gateway to real objects, enabling lazy loading and additional responsibilities."

=========================================================================
*/
