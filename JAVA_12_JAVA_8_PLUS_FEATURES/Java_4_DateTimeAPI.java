package JAVA_12_JAVA_8_PLUS_FEATURES;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Topic: Date & Time API (java.time)
 */
public class Java_4_DateTimeAPI {
    public static void main(String[] args) {

        // =========================
        // LocalDate, LocalTime, LocalDateTime
        // =========================
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();

        System.out.println(date);
        System.out.println(time);
        System.out.println(dateTime);

        // =========================
        // ZonedDateTime & TimeZone
        // =========================
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println(zdt);

        // =========================
        // Formatting
        // =========================
        String formatted = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        System.out.println(formatted);

        // =========================
        // Parsing
        // =========================
        LocalDate parsedDate = LocalDate.parse("2025-12-27", DateTimeFormatter.ISO_DATE);
        System.out.println(parsedDate);
    }
}

/*
================================================================================
DATE & TIME API – DEEP DIVE
================================================================================

WHAT:
-----
• java.time package for date-time handling
• Immutable, thread-safe objects
• Solves old java.util.Date & Calendar issues

WHY IT EXISTS:
--------------
• Old Date API mutable & error-prone
• Calendar is verbose
• Time-zone handling difficult
• Needed better API for modern apps

INTERNAL WORKING:
-----------------
• Classes are immutable
• Internally uses epoch seconds/nanos
• Zone rules stored in ZoneRulesProvider
• Operations return new instances, no mutation

CORE FEATURES:
--------------
✔ Immutable
✔ Thread-safe
✔ Fluent API (plus/minus)
✔ Time-zone aware
✔ Formatting/parsing support

ENTERPRISE PITFALLS:
-------------------
❌ Using legacy Date/Calendar
❌ Assuming LocalDateTime contains time zone
❌ Parsing with wrong format

REAL SYSTEM USAGE:
-----------------
✔ Scheduling tasks
✔ Logging timestamps
✔ Financial applications
✔ APIs & reports

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference between LocalDateTime and ZonedDateTime?
A: ZonedDateTime contains zone info, LocalDateTime doesn’t

Q2: Is java.time API thread-safe?
A: Yes, immutable design

INTERVIEW ONE-LINER:
-------------------
"Java 8 Date-Time API provides immutable, thread-safe, fluent date-time handling with zone support."
================================================================================
*/
