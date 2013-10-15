package com.aqua.music.items;

public class Random
{
	public static void main( String[] args ) {
		new Random().swap( "here is string", 1, 2);
	}
	
	
	public static void swap( String swapArray, int location1, int location2 ) {
		char char1 = swapArray.charAt( location1 );
		char char2 = swapArray.charAt( location2 );

		StringBuilder swapArrayBuilder = new StringBuilder( swapArray );
		swapArrayBuilder.setCharAt( location1, char2 );
		swapArrayBuilder.setCharAt( location2, char1 );
		
		System.out.println(swapArrayBuilder.toString());
	}
	
	public static int parse(String s, int start, int end) {
	    long result = 0;
	    boolean foundMinus = false;

	    while (start < end) {
	        char ch = s.charAt(start);
	        if (ch == ' ')
	            /* ok */;
	        else if (ch == '-') {
	            if (foundMinus)
	                throw new NumberFormatException();
	            foundMinus = true;
	        } else if (ch < '0' || ch > '9')
	            throw new NumberFormatException();
	        else
	            break;
	        ++start;
	    }

	    if (start == end)
	        throw new NumberFormatException();

	    while (start < end) {
	        char ch = s.charAt(start);
	        if (ch < '0' || ch > '9')
	            break;
	        result = result * 10 + (int) ch - (int) '0';
	        ++start;
	    }

	    while (start < end) {
	        char ch = s.charAt(start);
	        if (ch != ' ')
	            throw new NumberFormatException();
	        ++start;
	    }
	    if (foundMinus)
	        result *= -1;
	    if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE)
	        throw new NumberFormatException();
	    return (int) result;
	}
}
