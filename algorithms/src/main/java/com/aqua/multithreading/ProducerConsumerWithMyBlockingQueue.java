package com.aqua.multithreading;


public class ProducerConsumerWithMyBlockingQueue
{
    private final Runnable producer;
    private final Runnable consumer;
    private final BlockingQueueWithWaitAndNotify<String> referenceQueue;
    private final int referenceQueueSize = 5;

    public static void main( String[] args ) {
        new ProducerConsumerWithMyBlockingQueue().simulate();
    }

    ProducerConsumerWithMyBlockingQueue() {
        referenceQueue = new BlockingQueueWithWaitAndNotify<String>( referenceQueueSize );
        int numberOfMessages = 50;
        producer = new ProducerThread( numberOfMessages, referenceQueue );
        consumer = new ConsumerThread( numberOfMessages, referenceQueue );
    }

    void simulate() {
        new Thread( producer ).start();
        new Thread( consumer ).start();
    }

    static class ProducerThread implements Runnable
    {
        private final int numberOfMessages;
        private final BlockingQueueWithWaitAndNotify<String> referenceQueue;

        public ProducerThread( int numberOfMessages, BlockingQueueWithWaitAndNotify<String> referenceQueue ) {
            this.referenceQueue = referenceQueue;
            this.numberOfMessages = numberOfMessages;
        }

        @Override
        public void run() {
            System.out.println( "ProducerThread Started" );
            for( int i = 0; i < numberOfMessages; i++ ) {
                broadcast( "message" + i );
            }
            System.out.println( "ProducerThread Fininshed" );
        }

        private void broadcast( String message ) {
            try {
                referenceQueue.put( message );
                System.out.println( " **** ====> Produced message[" + message + "]" );
            } catch( Exception e ) {
                e.printStackTrace();
            }
        }
    }

    static class ConsumerThread implements Runnable
    {
        private final BlockingQueueWithWaitAndNotify<String> referenceQueue;
        private int numberOfMessages;
        private int count;

        public ConsumerThread( int numberOfMessages, BlockingQueueWithWaitAndNotify<String> referenceQueue ) {
            this.numberOfMessages = numberOfMessages;
            this.referenceQueue = referenceQueue;
        }

        @Override
        public void run() {
            System.out.println( "ConsumerThread Started" );
            try {
                while( count < numberOfMessages ) {
                    printRecievedMessage( referenceQueue.take() );
                }
            } catch( Exception ex ) {
                ex.printStackTrace();
            }
            System.out.println( "ConsumerThread Finished" );
        }

        private void printRecievedMessage( String message ) {
            count++;
            System.out.println( " ===> Consumed message[" + message + "]" );
        }
    }

}
