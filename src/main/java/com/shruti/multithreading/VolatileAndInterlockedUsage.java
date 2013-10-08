package com.shruti.multithreading;

import java.util.LinkedList;

public class VolatileAndInterlockedUsage
{
    public static void main( String[] args ) {
        int intialCounterValue = 10000000;

        System.out.println("Executing CounterKeeperWithThreadSaftyByAtomicData");
        executeFor( new CounterKeeper.CounterKeeperWithThreadSaftyByAtomicData( intialCounterValue ) );
        
        System.out.println("Executing CounterKeeperWithThreadSaftyByLock");
        executeFor( new CounterKeeper.CounterKeeperWithThreadSaftyByLock( intialCounterValue ) );
        
        System.out.println("Executing CounterKeeperWithThreadSaftyByLock");
        executeFor( new CounterKeeper.CounterKeeperWithoutThreadSafty( intialCounterValue ) );
    }

    private static void executeFor( CounterKeeper candidate ) {
        int updateC = 10000;
        int numberOfThreads = 10;

        int discrepancyFound = 0;
        for( int i = 0; i < 100; i++ ) {
            int diff = new ManipulatingThreadsFactory( candidate, updateC, numberOfThreads ).manipulateState() ;
            if( diff != 0 ) {
                System.out.println( "("+(i+1)+"). After [" + updateC + "]updates, counter-diff =" + diff );
                discrepancyFound++;
            }
        }
        if( discrepancyFound != 0 ) {
            System.out.println( "discrepancyFound[" + discrepancyFound + "]" );
        } else {
            System.out.println( "All updates were correct" );
        }
    }

    static class ManipulatingThreadsFactory
    {
        private final CounterKeeper counterKeeper;
        private final int numberOfThreads;
        private final int updateCount;

        ManipulatingThreadsFactory( CounterKeeper counterKeeper, int updateCount, int numberOfThreads ) {
            this.updateCount = updateCount;
            this.counterKeeper = counterKeeper;
            this.numberOfThreads=numberOfThreads;
        }

        int manipulateState() {
            int initialVal = counterKeeper.get();
            LinkedList<Thread> threads= new LinkedList<Thread>();
            for(int i=0;i<numberOfThreads;i++){
                Thread incThread = createIncrementThread(i+1);
                Thread decThread = createDecrementThread(i+1);
                incThread.start();
                decThread.start();
                threads.add( incThread );
                threads.add( decThread );
            }
            
            for(Thread each : threads){
                try {
                    each.join();
                } catch( InterruptedException e ) {
                    e.printStackTrace();
                }
            }
            
            int finalVal = counterKeeper.get();
            return finalVal - initialVal;
        }

        private Thread createDecrementThread(int threadNumber) {
            return new Thread("Decrement-Thread-"+threadNumber) {
                @Override
                public void run() {
                    for( int i = 0; i < updateCount; i++ ) {
                        counterKeeper.decrement();
                    }
                }
            };
        }

        private Thread createIncrementThread(int threadNumber) {
            return new Thread("Increment-Thread-"+threadNumber) {
                @Override
                public void run() {
                    for( int i = 0; i < updateCount; i++ ) {
                        counterKeeper.increment();
                    }
                }
            };
        }
    }
}
