package com.octopus.coursera.algo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestInversions
{
	private static final String filename = "C:\\Users\\shruti.tiwari\\Desktop\\IntegerArray.txt";

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main( String[] args ) throws FileNotFoundException {
		List<Integer> listI = readFile();
		int count=listI.size();
		//printList( listI);
		int firstElement = 54044;
		int lastElement = 91901;
		if(listI.size()!=count || listI.get(0)!=firstElement || listI.get(count-1)!=lastElement){
			throw new AssertionError("data is not read properly");
		}
		System.out.println( "Successfully read [" + count + "] records." );
		
		countInversionsInList(listI);
	}

/*	private static void printList( List<Integer> listI ) {
		int count=listI.size();
		System.out.println( "file length[" + count + "]" );
		for( int i = 0; i < 10; i++ ) {
			System.out.println( "number at index[" + i + "] =" + listI.get( i ) );
		}
		for( int i = 1; i < 10; i++ ) {
			int index = count-i;
			System.out.println( "number at index[" + index + "] =" + listI.get( index ) );
		}
	}*/

	private static void countInversionsInList( List<Integer> listI ) {
		
	}

	private static List<Integer> readFile() throws FileNotFoundException {
		List<Integer> result = new ArrayList<Integer>();
		BufferedReader reader = new BufferedReader( new InputStreamReader( new FileInputStream( filename ) ) );
		try {
			String data = reader.readLine();
			while( data != null ) {
				result.add( Integer.parseInt( data ) );
				data = reader.readLine();
			}
		} catch( IOException e ) {
			e.printStackTrace();
		}
		return result;
	}
}
