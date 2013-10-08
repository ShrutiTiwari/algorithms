package com.shruti.multithreading;

import java.util.Collection;
import java.util.LinkedList;

public class BlockingQueueWithWaitAndNotify<T>
{
    private final int capactity;
    private Collection<T> myData = new LinkedList<T>();

    public BlockingQueueWithWaitAndNotify( int capactity ) {
        this.capactity = capactity;
    }

    synchronized void put( T text ) {
        while( myData.size() == capactity ) {
            try {
                wait();
            } catch( InterruptedException e ) {
                e.printStackTrace();
            }
        }
        myData.add( text );
        notifyAll();
    }

    synchronized T take() {
        while( myData.size() == 0 ) {
            try {
                wait();
            } catch( InterruptedException e ) {
                e.printStackTrace();
            }
        }

        T result = null;
        Collection<T> newData = new LinkedList<T>();
        int i = 0;
        for( T each : myData ) {
            if( i++ == 0 ) {
                result = each;
            } else {
                newData.add( each );
            }
        }
        myData = newData;
        notifyAll();
        return result;

    }
}
