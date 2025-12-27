package JAVA_09_MULTITHREADING_CONCURRENCY;

/**
 * ATM System: Understanding Thread States with Inter-thread Communication
 */
public class Java_1_ThreadLifecycle_ATM_System {
    static int balance = 1000; // Shared Resource

    public static void main(String[] args) throws InterruptedException {
        Object atmMachine = new Object();

        // Thread 1: Husband wants to Withdraw
        Thread husband = new Thread(() -> { // State: NEW (जब ऑब्जेक्ट बना)
            synchronized (atmMachine) { // State: RUNNABLE (जब CPU मिला)
                System.out.println("Husband: Withdrawal के लिए मशीन लॉक की।");
                
                while (balance < 5000) {
                    System.out.println("Husband: बैलेंस कम है (" + balance + "), इंतज़ार कर रहा हूँ...");
                    try {
                        // State transition: RUNNABLE -> WAITING
                        // यहाँ Husband ताला (lock) छोड़ देता है और Wait Set में चला जाता है
                        atmMachine.wait(); 
                        
                        // जब Wife notify करेगी, तब Husband यहाँ से आगे बढ़ेगा
                        // पर पहले उसे BLOCKED स्टेट में ताले (lock) का इंतज़ार करना होगा
                    } catch (InterruptedException e) { e.printStackTrace(); }
                }
                
                balance -= 5000;
                System.out.println("Husband: पैसे मिल गए! बचा हुआ बैलेंस: " + balance);
            } 
            // State: TERMINATED (काम खत्म होने पर)
        }, "Husband-Thread");

        // Thread 2: Wife wants to Deposit
        Thread wife = new Thread(() -> {
            // State: BLOCKED (अगर Husband ने ताला पकड़ा हुआ है)
            synchronized (atmMachine) { 
                // State: RUNNABLE (जब Husband ने wait() करके ताला छोड़ा)
                System.out.println("Wife: मशीन मिल गई। पैसे जमा कर रही हूँ...");
                balance += 6000;
                System.out.println("Wife: पैसे जमा हो गए। बैलेंस: " + balance);
                
                // Husband को जगाया: Husband अब WAITING से निकलकर BLOCKED स्टेट में जाएगा
                atmMachine.notify(); 
                
                System.out.println("Wife: मैंने Notify कर दिया है, रसीद ले रही हूँ...");
                try { 
                    // State: TIMED_WAITING (Wife सो रही है, ताला अभी भी उसी के पास है)
                    Thread.sleep(2000); 
                } catch (InterruptedException e) {} 
                
            } // यहाँ Wife ने ताला छोड़ा। अब Husband (जो BLOCKED था) RUNNABLE बनेगा।
        }, "Wife-Thread");

        // --- Execution & State Checking ---

        System.out.println("Husband Initial State: " + husband.getState()); // NEW

        husband.start(); // Husband starts -> RUNNABLE
        Thread.sleep(500); 
        System.out.println("Husband after wait(): " + husband.getState()); // WAITING (लूप के अंदर wait की वजह से)

        wife.start(); // Wife starts -> BLOCKED (क्योकि Husband ने lock पकड़ा था) या सीधे RUNNABLE (अगर Husband wait में चला गया)
        Thread.sleep(500); 

        // जब Wife sleep(2000) कर रही है और notify() कर चुकी है
        System.out.println("Husband state while Wife holds lock: " + husband.getState()); 
        // Logic: Husband 'WAITING' से हट चुका है, लेकिन उसे लॉक नहीं मिला, इसलिए वो 'BLOCKED' दिखेगा

        husband.join(); // Main thread इंतज़ार कर रहा है Husband के खत्म होने का
        wife.join();
        
        System.out.println("Husband Final State: " + husband.getState()); // TERMINATED
        System.out.println("Final Balance: " + balance);
    }
}



// बिलकुल! शुरू से अंत तक **Thread Lifecycle** और **Inter-thread Communication** (wait/notify) का यह सबसे सरल और सटीक ओवरव्यू है। 
// इसे आप अपनी "नोटबुक" में इस तरह सेव कर सकते हैं:

// ---

// ### 1. थ्रेड की 6 अवस्थाएं (The 6 States)

// | स्टेट | आसान भाषा में मतलब | कब होती है? |
// | --- | --- | --- |
// | **NEW** | थ्रेड का जन्म | जब `new Thread()` किया, पर `start()` नहीं। |
// | **RUNNABLE** | काम के लिए तैयार | `start()` के बाद; CPU मिलने का इंतज़ार या काम चालू। |
// | **BLOCKED** | ताला (Lock) नहीं मिला | जब `synchronized` ब्लॉक में घुसना है पर Lock किसी और के पास है। |
// | **WAITING** | खुद ताला छोड़ दिया | जब `wait()` कॉल किया। अब किसी के `notify()` का इंतज़ार है। |
// | **TIMED_WAITING** | समय के साथ इंतज़ार | `Thread.sleep(ms)` या `wait(ms)` के दौरान। |
// | **TERMINATED** | काम खत्म | जब `run()` मेथड पूरी तरह खत्म हो गई। |

// ---

// ### 2. Full Journey: Husband-Wife ATM Scenario

// इस प्रोसेस को स्टेप्स में देखें कि थ्रेड्स आपस में कैसे बात करते हैं:

// **Step 1: NEW & START**

// * पति (`t1`) और पत्नी (`t2`) के थ्रेड्स बने (**NEW**)।
// * `husband.start()` हुआ, वह ATM पहुँचा और उसे लॉक मिल गया (**RUNNABLE**)।

// **Step 2: WAITING (The Voluntary Exit)**

// * पति ने देखा बैलेंस कम है। उसने `lock.wait()` किया।
// * **यहाँ जादू हुआ:** पति ने ATM का ताला (Lock) छोड़ दिया और "इंतज़ार रूम" में चला गया।
// * **State:** Husband = **WAITING**।

// **Step 3: RUNNABLE & BLOCKED**

// * जैसे ही पति ने लॉक छोड़ा, पत्नी को लॉक मिल गया। वह पैसे जमा करने लगी।
// * अगर उसी समय कोई तीसरा थ्रेड आता, तो वह **BLOCKED** रहता क्योंकि ताला पत्नी के पास है।
// * **State:** Wife = **RUNNABLE**।

// **Step 4: NOTIFY & BLOCKED (The Wake-up Call)**

// * पत्नी ने पैसे जमा किए और `lock.notify()` किया।
// * **बड़ी बात:** पति जाग गया, लेकिन वह सीधे ATM में नहीं घुस सकता क्योंकि पत्नी अभी अंदर है (वह रसीद निकाल रही है)।
// * **State:** Husband = **BLOCKED** (अब वह इंतज़ार कर रहा है कि पत्नी कब बाहर निकलेगी)।

// **Step 5: RE-ACQUIRING LOCK**

// * पत्नी का काम खत्म हुआ, उसने `synchronized` ब्लॉक छोड़ा।
// * अब पति को दोबारा लॉक मिला, वह **RUNNABLE** हुआ और पैसे निकालकर बाहर आया।

// **Step 6: TERMINATED**

// * दोनों का काम खत्म। **State:** **TERMINATED**।

// ---

// ### 3. गोल्डन रूल्स (इन्हें कभी न भूलें)

// 1. **wait() vs sleep():**
// * `wait()` ताला (Lock) **छोड़ देता है**।
// * `sleep()` ताला **पकड़ कर सोता है** (अगर उसने ताला लिया हुआ है तो)।


// 2. **Lock Ownership:** `wait()`, `notify()`, और `notifyAll()` सिर्फ `synchronized` ब्लॉक के अंदर ही काम करते हैं।
//  इसके बाहर चलाने पर `IllegalMonitorStateException` आता है।
// 3. **Why Blocked after Notify?** जगाने (notify) का मतलब काम शुरू करना नहीं है, बल्कि "ताले की लाइन" में दोबारा लगना है।

// ---

// ### 4. इंटरव्यू के लिए प्रो-टिप (Interview One-liner)

// > "Java में थ्रेड्स के बीच तालमेल `wait()` और `notify()` से होता है। `wait()` थ्रेड को **WAITING** स्टेट में डालता है और लॉक रिलीज़ करता है,
//  जबकि `notify()` थ्रेड को वापस **BLOCKED** स्टेट में लाता है ताकि वह लॉक फिर से पा सके और अपना काम **RUNNABLE** होकर पूरा कर सके।"

// ---

// **अगला कदम:** क्या आप **"Deadlock"** के बारे में जानना चाहेंगे? यह वह स्थिति है जहाँ दो थ्रेड्स एक-दूसरे के ताले का इंतज़ार करते रह जाते हैं और पूरा सिस्टम जाम हो जाता है।

// Enterprise applications में हम हमेशा notifyAll() इस्तेमाल करने की सलाह देते हैं। notify() से 'Lost Notification' की समस्या हो सकती है, जहाँ कुछ थ्रेड्स कभी जाग ही नहीं पाते।
//  notifyAll() सुनिश्चित करता है कि हर थ्रेड को अपनी कंडीशन चेक करने का मौका मिले।"




/*
================================================================================
THREAD LIFECYCLE – DEEP DIVE
================================================================================

WHAT:
-----
Thread lifecycle defines the **states a thread goes through** from creation to termination.
States: NEW → RUNNABLE → RUNNING → BLOCKED / WAITING → TIMED_WAITING → TERMINATED

WHY IT EXISTS:
--------------
• Threads allow concurrent execution.
• Lifecycle helps JVM & developer manage scheduling, resources, and synchronization.
• Provides hooks (start, sleep, join, wait) for controlling execution flow.

INTERNAL WORKING:
-----------------
• JVM Thread object wraps OS-level native thread.
• Thread states tracked internally via 'Thread.State' enum.
• modCount & stack frame per thread.
• Thread scheduler decides RUNNABLE → RUNNING.
• OS-level context switching moves thread in CPU.

TIME COMPLEXITY / PERFORMANCE:
-----------------------------
• State transitions are constant-time operations.
• Context switch overhead is OS-dependent.
• Frequent blocking can reduce CPU efficiency.

CORE FEATURES:
--------------
• Lifecycle management
• Thread-safe execution control
• Hooks: start(), sleep(), join(), interrupt()

ENTERPRISE PITFALLS:
-------------------
❌ Ignoring TERMINATED threads (memory leak)
❌ Modifying shared state without synchronization
❌ Misusing join() → deadlocks

REAL SYSTEM USAGE:
-----------------
✔ Job scheduling
✔ Background tasks
✔ Thread pools (Executor)
✔ Async processing in microservices

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: What are thread states in Java?
A: NEW, RUNNABLE, RUNNING, BLOCKED, WAITING, TIMED_WAITING, TERMINATED.

Q2: Can a terminated thread be restarted?
A: No, start() throws IllegalThreadStateException.

Q3: Difference between RUNNABLE and RUNNING?
A: RUNNABLE is ready to run; RUNNING is actively on CPU.

INTERVIEW ONE-LINER:
-------------------
"Thread lifecycle allows precise control over thread execution and resource management in concurrent systems."
================================================================================
*/
