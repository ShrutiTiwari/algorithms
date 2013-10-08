package com.shruti.multithreading;

public class DeadlockGenerator
{
    public static void main(String[] args){
        System.out.println( "starting deadlock simulation." );
        new DeadlockGenerator().simulate();
    }
    
    void simulate() {
        Runnable mainRunnable = new Runnable() {
            @Override
            public void run() {
                ResourceClass1.method1();
            }
        };
        new Thread( mainRunnable, "Deadlock-Main-Thread" ).start();
    }

    static class ResourceClass1
    {
        static synchronized void method1() {
            System.out.println( "inside method1" );
            Thread asyncThread = asyncExeecutor();
            System.out.println( "launching async thread" );
            asyncThread.start();
            System.out.println( "joining for completion of async task" );
            try {
                asyncThread.join();
            } catch( InterruptedException e ) {
                e.printStackTrace();
            }
        }

        static synchronized void method2() {
            System.out.println( "inside method2" );
        }
        private static Thread asyncExeecutor() {
            Runnable myRunnable = new Runnable() {
                @Override
                public void run() {
                    ResourceClass1.method2();
                }
            };
            return new Thread( myRunnable, "Deadlock-Conflicting-Thread" );
        }
    }
}
