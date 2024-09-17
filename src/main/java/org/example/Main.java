package org.example;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

enum Gender {
    NONE,
    MALE,
    FEMALE
}

class Bathroom {

    private int maleCount = 0;
    private int femaleCount = 0;
    private final Lock lock = new ReentrantLock();
    private final Semaphore space;
    private final Condition bathroomFree = lock.newCondition();

    Bathroom(int capacity) {
        space = new Semaphore(capacity);
    }

    private void maleUseBathroom(Runnable use) throws InterruptedException {
        // Acquire bathroom space first
        space.acquire();

        // Critical section for gender checking
        lock.lock();
        try {
            while (femaleCount > 0) {
                bathroomFree.await(); // Wait until females exit
            }
            maleCount++;  // Increment male count
        } finally {
            lock.unlock();
        }

        // Perform the bathroom usage outside of the lock
        use.run();  // Simulate using the bathroom

        // After bathroom usage, lock again to update state
        lock.lock();
        try {
            maleCount--;  // Decrement male count
            if (maleCount == 0) {
                bathroomFree.signalAll(); // Notify waiting females when no males are left
            }
        } finally {
            lock.unlock();
        }

        // Release the space semaphore after use
        space.release();
    }

    private void femaleUseBathroom(Runnable use) throws InterruptedException {
        // Acquire bathroom space first
        space.acquire();

        // Critical section for gender checking
        lock.lock();
        try {
            while (maleCount > 0) {
                bathroomFree.await(); // Wait until males exit
            }
            femaleCount++;  // Increment female count
        } finally {
            lock.unlock();
        }

        // Perform the bathroom usage outside of the lock
        use.run();  // Simulate using the bathroom

        // After bathroom usage, lock again to update state
        lock.lock();
        try {
            femaleCount--;  // Decrement female count
            if (femaleCount == 0) {
                bathroomFree.signalAll(); // Notify waiting males when no females are left
            }
        } finally {
            lock.unlock();
        }

        // Release the space semaphore after use
        space.release();
    }

    public void useBathroom(Gender gender, Runnable use) throws InterruptedException {
        if (gender == Gender.MALE) {
            maleUseBathroom(use);
        } else {
            femaleUseBathroom(use);
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bathroom bathroom = new Bathroom(3);  // Bathroom with capacity 3

        Runnable maleTask = () -> {
            try {
                bathroom.useBathroom(Gender.MALE, () -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + " Male Starting");
                        Thread.sleep(5000);
                        System.out.println(Thread.currentThread().getName() + " Male Exits");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable femaleTask = () -> {
            try {
                bathroom.useBathroom(Gender.FEMALE, () -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + " Female Starting");
                        Thread.sleep(5000);
                        System.out.println(Thread.currentThread().getName() + " Female Ends");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        // Simulate multiple males and females trying to use the bathroom
        int n = 5;
        for (int i = 0; i < n; i++) {
            new Thread(maleTask).start();
            new Thread(femaleTask).start();
        }
    }
}