package com.octopus.multithreading;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public interface CounterKeeper
{
    void increment();

    void decrement();

    int get();

    class CounterKeeperWithoutThreadSafty implements CounterKeeper
    {
        volatile int counter;

        public CounterKeeperWithoutThreadSafty( int counter ) {
            this.counter = counter;
        }

        public void increment() {
            counter++;
        }

        public void decrement() {
            counter--;
        }

        @Override
        public int get() {
            return counter;
        }
    }

    class CounterKeeperWithThreadSaftyByAtomicData implements CounterKeeper
    {
        volatile AtomicInteger counter;

        public CounterKeeperWithThreadSaftyByAtomicData( int counter ) {
            this.counter = new AtomicInteger( counter );
        }

        public void increment() {
            counter.incrementAndGet();
        }

        public void decrement() {
            counter.decrementAndGet();
        }

        @Override
        public int get() {
            return counter.get();
        }
    }

    class CounterKeeperWithThreadSaftyByLock implements CounterKeeper
    {
        volatile int counter;
        private final Lock lock;

        public CounterKeeperWithThreadSaftyByLock( int counter ) {
            this.counter = counter;
            this.lock = new ReentrantLock();
        }

        public void increment() {
            try {
                lock.lock();
                counter++;
            } finally {
                lock.unlock();
            }
        }

        public void decrement() {
            try {
                lock.lock();
                counter--;
            } finally {
                lock.unlock();
            }
        }

        @Override
        public int get() {
            return counter;
        }
    }
}
