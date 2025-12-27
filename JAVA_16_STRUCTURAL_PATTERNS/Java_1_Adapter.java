package JAVA_16_STRUCTURAL_PATTERNS;

/**
 * ADAPTER PATTERN – WEATHER API ADAPTER EXAMPLE
 */
public class Java_1_Adapter {
    public static void main(String[] args) {

        // Client expects Temperature in Celsius
        TemperatureService clientService = new WeatherAdapter();

        System.out.println("Current temperature in Celsius: " + clientService.getTemperature());
    }
}

// ==================== TARGET ====================
interface TemperatureService {
    double getTemperature(); // Returns temperature in Celsius
}

// ==================== ADAPTEE ====================
class FahrenheitWeatherAPI {
    public double getTemperatureFahrenheit() {
        return 86; // Example: API returns Fahrenheit
    }
}

// ==================== ADAPTER ====================
class WeatherAdapter implements TemperatureService {
    private final FahrenheitWeatherAPI api = new FahrenheitWeatherAPI();

    @Override
    public double getTemperature() {
        double tempF = api.getTemperatureFahrenheit();
        return (tempF - 32) * 5 / 9; // Convert to Celsius
    }
}

/*
================ ADAPTER PATTERN – FEATURES & DESIGN =================
WHAT:
- Converts interface of one class to another expected by client.
- Example: Weather API in Fahrenheit to Celsius.
REAL SYSTEM USAGE:
- Integrating legacy APIs, payment gateways, file format converters
*/
