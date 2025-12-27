package JAVA_15_BEHAVIORAL_PATTERNS;

import java.util.ArrayList;
import java.util.List;

/**
 * OBSERVER PATTERN – WEATHER STATION EXAMPLE
 */
public class Java_2_Observer {
    public static void main(String[] args) {

        WeatherStation station = new WeatherStation();
        WeatherDisplay display1 = new WeatherDisplay("Display1");
        WeatherDisplay display2 = new WeatherDisplay("Display2");

        station.addObserver(display1);
        station.addObserver(display2);

        station.setTemperature(30);
        station.setTemperature(32);
    }
}

// ==================== SUBJECT ====================
interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

// ==================== OBSERVER ====================
interface Observer {
    void update(int temperature);
}

// ==================== CONCRETE SUBJECT ====================
class WeatherStation implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private int temperature;

    @Override
    public void addObserver(Observer o) { observers.add(o); }
    @Override
    public void removeObserver(Observer o) { observers.remove(o); }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) { o.update(temperature); }
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        notifyObservers();
    }
}

// ==================== CONCRETE OBSERVER ====================
class WeatherDisplay implements Observer {
    private final String name;
    public WeatherDisplay(String name) { this.name = name; }
    @Override
    public void update(int temperature) {
        System.out.println(name + " updated temperature: " + temperature);
    }
}

/*
================ OBSERVER PATTERN – FEATURES & DESIGN =================
WHAT:
- Defines a one-to-many dependency between objects.
- Example: Weather station updates multiple displays.
WHY IT EXISTS:
- Decouples publisher and subscriber.
- Supports dynamic runtime subscription.
REAL SYSTEM USAGE:
- UI data binding, event handling, notification systems, stock tickers
*/
