package com.octopus.multithreading;


public class ProducerConsumerWithWaitNotify
{
    private final Runnable producer;
    private final Runnable consumer;
    private volatile String message=null;
    private volatile boolean empty=true;

    synchronized String getMessage(){
        if(empty){
            try {
                wait();
            } catch( InterruptedException e ) {
                e.printStackTrace();
            }
        }
        empty=true;
        notifyAll();
        System.out.println( " ===> Consumed message[" + message + "]" );
        return message;
    }
    
    synchronized void setMessage(String newMessage){
        if(!empty){
            try {
                wait();
            } catch( InterruptedException e ) {
                e.printStackTrace();
            }
        }
        this.message=newMessage;
        empty=false;
        System.out.println( " **** ====> Produced message[" + message + "]" );
        notifyAll();
    }
    
    public static void main( String[] args ) {
        new ProducerConsumerWithWaitNotify().simulate();
    }

    ProducerConsumerWithWaitNotify() {
        int numberOfMessages = 50;
        producer = new ProducerThread( numberOfMessages, this);
        consumer = new ConsumerThread( numberOfMessages, this);
    }

    void simulate() {
        new Thread( producer ).start();
        new Thread( consumer ).start();
    }

    static class ProducerThread implements Runnable
    {
        private final int numberOfMessages;
        private ProducerConsumerWithWaitNotify container;

        public ProducerThread( int numberOfMessages, ProducerConsumerWithWaitNotify container) {
            this.numberOfMessages = numberOfMessages;
            this.container=container;
        }

        @Override
        public void run() {
            System.out.println( "ProducerThread Started" );
            for( int i = 0; i < numberOfMessages; i++ ) {
                String newMessage = "message" + i;
                container.setMessage( newMessage );
            }
            System.out.println( "ProducerThread Fininshed" );
        }
    }

    static class ConsumerThread implements Runnable
    {
        private int numberOfMessages;
        private int count;
        private ProducerConsumerWithWaitNotify container;

        public ConsumerThread( int numberOfMessages, ProducerConsumerWithWaitNotify container) {
            this.numberOfMessages = numberOfMessages;
            this.container=container;
        }

        @Override
        public void run() {
            System.out.println( "ConsumerThread Started" );
            try {
                while( count < numberOfMessages ) {
                    container.getMessage();
                    count++;
                }
            } catch( Exception ex ) {
                ex.printStackTrace();
            }
            System.out.println( "ConsumerThread Finished" );
        }
    }

}
