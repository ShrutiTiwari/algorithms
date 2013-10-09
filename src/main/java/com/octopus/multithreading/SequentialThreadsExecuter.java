package com.octopus.multithreading;

public class SequentialThreadsExecuter
{
    private Thread[] poolOfThreads;

    SequentialThreadsExecuter( int count ) {
        this.poolOfThreads = createThreadPool( count );
    }

    public static void main( String[] args ) {
        new SequentialThreadsExecuter( 10 ).executeJoinAfterSimultaneousStart();
    }

    private static Thread[] createThreadPool( int count ) {
        Thread[] poolOfThreads = new Thread[count];
        for( int i = 0; i < count; i++ ) {
            poolOfThreads[i] = new CustomizedPrinterThread( i + 1 );
        }
        return poolOfThreads;
    }

    void executeJoinAfterSimultaneousStart() {
        for( Thread each : poolOfThreads ) {
            each.start();
        }
        for( Thread each : poolOfThreads ) {
            joinThread( each );
        }
        System.out.println( "End" );
    }

    void executeWithSimultaneousStartAndJoin() {
        for( Thread each : poolOfThreads ) {
            each.start();
            joinThread( each );
        }
        System.out.println( "End" );
    }

    private void joinThread( Thread each ) {
        try {
            each.join();
        } catch( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    private static class CustomizedPrinterThread extends Thread
    {
        private int index;

        CustomizedPrinterThread( int index ) {
            this.index = index;
        }

        @Override
        public void run() {
            try {
                Thread.sleep( 1000 );
            } catch( InterruptedException e ) {}
            System.out.println( "I am thread[" + index + "] " );
        }
    }

}
