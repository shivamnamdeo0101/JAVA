package JAVA_9_MULTITHREADING_CONCURRENCY;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class Java_9_ForkJoinPool {

    static class SumTask extends RecursiveTask<Integer> {
        private final int[] arr;
        private final int start, end;

        public SumTask(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= 2) { // threshold
                int sum = 0;
                for (int i = start; i < end; i++) sum += arr[i];
                return sum;
            }
            int mid = (start + end) / 2;
            SumTask left = new SumTask(arr, start, mid);
            SumTask right = new SumTask(arr, mid, end);
            left.fork();
            return right.compute() + left.join();
        }
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5};
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(data, 0, data.length);
        System.out.println("Sum: " + pool.invoke(task));
    }
}

/*
================================================================================
FORKJOINPOOL – DEEP DIVE
================================================================================

WHAT:
-----
• Specialized executor for **divide-and-conquer** tasks
• Splits tasks recursively, joins results

WHY IT EXISTS:
--------------
• Efficient parallelism for CPU-bound tasks
• Leverages **work-stealing algorithm**
• Avoids manual thread splitting

INTERNAL WORKING:
-----------------
• ForkJoinPool maintains worker threads
• Tasks split recursively (RecursiveTask / RecursiveAction)
• idle threads steal from busy threads
• join() waits for completion
• fork() schedules subtasks

DEFAULT VALUES:
---------------
• Parallelism = number of cores
• Work queue = Deque per worker
• ForkJoinWorkerThread is daemon

TIME COMPLEXITY:
----------------
• O(n) total, but parallelism reduces wall-clock
• Overhead depends on task granularity

CORE FEATURES:
--------------
• Automatic task splitting
• Work-stealing
• Lightweight threads
• RecursiveTask / RecursiveAction support

ENTERPRISE PITFALLS:
-------------------
❌ Small tasks → overhead > benefit
❌ Shared mutable state → race conditions
❌ Blocking I/O → reduces parallelism

REAL SYSTEM USAGE:
-----------------
✔ Parallel array summation
✔ Matrix operations
✔ Image processing
✔ CPU-bound algorithms

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference RecursiveTask vs RecursiveAction?
A: Task returns result, Action does not.

Q2: What is work-stealing?
A: Idle thread steals tasks from busy threads to balance load.

INTERVIEW ONE-LINER:
-------------------
"ForkJoinPool enables efficient parallel execution by splitting tasks and stealing work dynamically."
================================================================================
*/
