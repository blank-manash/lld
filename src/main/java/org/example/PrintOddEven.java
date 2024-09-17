package org.example;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private final int n;
    private int count = 1;
    private final Semaphore zeroSem = new Semaphore(0);
    private final Semaphore oddSem = new Semaphore(0);
    private final Semaphore evenSem = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (count <= n) {
            printNumber.accept(0);
            if (count % 2 == 0) {
                evenSem.release();
            } else {
                oddSem.release();
            }
            zeroSem.acquire();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (count <= n) {
            evenSem.acquire();
            printNumber.accept(count);
            count += 1;
            zeroSem.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (count <= n) {
            oddSem.acquire();
            printNumber.accept(count);
            count += 1;
            zeroSem.release();
        }
    }
}

public class PrintOddEven {
    public static void main(String[] args) {
        int n = 5;  // Set how many times you want to print 0, odd, and even numbers

        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(n);

        // IntConsumer that prints the integer
        IntConsumer printNumber = number -> System.out.print(number);

        // Create and start threads for zero, odd, and even
        Thread zeroThread = new Thread(() -> {
            try {
                zeroEvenOdd.zero(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread oddThread = new Thread(() -> {
            try {
                zeroEvenOdd.odd(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread evenThread = new Thread(() -> {
            try {
                zeroEvenOdd.even(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start the threads
        zeroThread.start();
        oddThread.start();
        evenThread.start();

        // Wait for all threads to finish
        try {
            zeroThread.join();
            oddThread.join();
            evenThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
