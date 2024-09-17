package org.example;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

import java.util.EnumMap;
enum State {
    FIZZ,
    BUZZ,
    FB,
    NUM
};


class FizzBuzz {
    private int n;
    private int counter = 1;
    private final EnumMap<State, Semaphore> sem = new EnumMap<>(State.class);
    private final EnumMap<State, Integer> lastInt = new EnumMap<>(State.class);

    public FizzBuzz(int n) {
        this.n = n;
        sem.put(State.FIZZ, new Semaphore(0));
        sem.put(State.BUZZ, new Semaphore(0));
        sem.put(State.FB, new Semaphore(0));
        sem.put(State.NUM, new Semaphore(1));

        for(int i = 1; i <= n; ++i) {
            State now = getState(i);
            lastInt.put(now, i);
        }
    }

    private State getState(int x) {
        if (x % 3 == 0 && x % 5 == 0) {
            return State.FB;
        }
        if (x % 3 == 0) {
            return State.FIZZ;
        }
        if (x % 5 == 0) {
            return State.BUZZ;
        }
        return State.NUM;
    }

    private void execute(State state, Runnable task) throws InterruptedException {
        System.out.println("Currenty at: " + state.name());
        while(counter <= lastInt.getOrDefault(state, n)) {
            sem.get(state).acquire();
            task.run();
            counter += 1;
            State nextState = getState(counter);
            sem.get(nextState).release();
        }
    }


    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        execute(State.FIZZ, printFizz);
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        execute(State.BUZZ, printBuzz);
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        execute(State.FB, printFizzBuzz);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        execute(State.NUM, () -> { printNumber.accept(counter); });
    }
}

public class TestFizzBuzz {
    public static void main(String[] args) {
        int n = 5;  // The upper limit for the FizzBuzz sequence

        FizzBuzz fizzBuzz = new FizzBuzz(n);

        Runnable printFizz = () -> System.out.print("fizz ");
        Runnable printBuzz = () -> System.out.print("buzz ");
        Runnable printFizzBuzz = () -> System.out.print("fizzbuzz ");

        // IntConsumer for numbers
        IntConsumer printNumber = number -> System.out.print(number + " ");

        // Create and start the threads
        Thread fizzThread = new Thread(() -> {
            try {
                fizzBuzz.fizz(printFizz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread buzzThread = new Thread(() -> {
            try {
                fizzBuzz.buzz(printBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread fizzBuzzThread = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(printFizzBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread numberThread = new Thread(() -> {
            try {
                fizzBuzz.number(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start the threads
        fizzThread.start();
        buzzThread.start();
        fizzBuzzThread.start();
        numberThread.start();

        // Wait for all threads to finish
        try {
            fizzThread.join();
            buzzThread.join();
            fizzBuzzThread.join();
            numberThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}