package org.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

enum Person {
    REPUBLIC,
    DEMOCRAT
}

public class UberRideProblem {
    private int democrats = 0;
    private int republics = 0;
    final private Semaphore demWaiting = new Semaphore(0);
    final private Semaphore repWaiting = new Semaphore(0);
    final private CyclicBarrier barrier = new CyclicBarrier(4);
    final private ReentrantLock lock = new ReentrantLock();

    UberRideProblem() {
    }

    ;

    public void seatDemocrat() throws InterruptedException, BrokenBarrierException {
        lock.lock();
        democrats += 1;
        boolean leader = false;
        if (democrats >= 4) {
            demWaiting.release(3);
            democrats -= 4;
            leader = true;
        } else if (democrats > 1 && republics > 1) {
            demWaiting.release();
            repWaiting.release(2);
            democrats -= 2;
            republics -= 2;
            leader = true;
        } else {
            lock.unlock();
            demWaiting.acquire();
        }

        barrier.await();
        if (leader) {
            System.out.printf("Riding, Demo : %d, Republic: %d", democrats, republics);
            lock.unlock();
        }
    }

    public void seatRepublic() throws InterruptedException, BrokenBarrierException {
        lock.lock();
        republics += 1;
        boolean leader = false;
        if (republics >= 4) {
            republics -= 4;
            leader = true;
            repWaiting.release(3);
        } else if (republics > 1 && democrats > 1) {
            leader = true;
            republics -= 2;
            democrats -= 2;
            repWaiting.release();
            demWaiting.release(2);
        } else {
            lock.unlock();
            // So that other threads can release it
            repWaiting.acquire();
        }

        barrier.await();
        if (leader) {
            System.out.printf("Riding, Demo : %d, Republic: %d", democrats, republics);
            lock.unlock();
        }
    }

    public void joinQueue(Person person) {
        try {
            if (person.equals(Person.DEMOCRAT)) {
                seatDemocrat();
            } else {
                seatRepublic();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
