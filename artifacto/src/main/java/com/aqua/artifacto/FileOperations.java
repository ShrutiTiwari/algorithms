package com.aqua.artifacto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

public class FileOperations
{
	File targetFile;

	public FileOperations( File targetFile ) {
		this.targetFile = targetFile;
	}

	public static String collateInputStreamAsSting(InputStream sourceInputStream) throws IOException {
		StringDataCollater stringDataCollater = new StringDataCollater();
		FileOperations.collateContentsOfStreamWith( sourceInputStream, stringDataCollater );
		String fileData = stringDataCollater.data();
		return fileData;
	}
	
	public static void collateContentsOfStreamWith( InputStream inputStream, DataCollater strategy ) throws IOException {
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ) );
		String s = null;
		while( (s = bufferedReader.readLine()) != null ) {
			strategy.collectData( s );
		}
	}

	
	public void  writeToDisk(String data) throws IOException
	{
		FileWriter fstream = new FileWriter( targetFile );
		BufferedWriter out = new BufferedWriter( fstream );
		out.write( data );
		out.flush();
		out.close();
	}
	
	public void  writeToDisk(InputStream fileAsStream) throws IOException
	{
		FileOperations.StringDataCollater stringDataCollater = new FileOperations.StringDataCollater();
		FileOperations.collateContentsOfStreamWith( fileAsStream, stringDataCollater );
		writeToDisk( stringDataCollater.data());
	}		

	
	public void collateContentsOfStreamWith( DataCollater strategy ) throws IOException {
		collateContentsOfStreamWith( new FileInputStream( targetFile ), strategy );
	}

	public void deleteFile( ) {
		if( targetFile.exists() ) {
			targetFile.delete();
		}
	}

	public void writeToDiskInAppendingMode( String data ) throws IOException {
		if( !targetFile.exists() ) {
			targetFile.createNewFile();
		}
		writeToDisk( data, true );
	}

	public void writeToDisk( String data, boolean mode ) throws IOException {
		FileWriter fstream = new FileWriter( targetFile, mode );
		BufferedWriter out = new BufferedWriter( fstream );
		out.write( data );
		out.flush();
		out.close();
	}

	public static class BatchDiskWrite
	{

		private final int batchSize;
		private final File writeLocation;

		private StringBuilder dataForDisk = new StringBuilder();
		private int dataCounter = 0;

		public BatchDiskWrite( int batchSize, File writeLocation ) {
			this.batchSize = batchSize;
			this.writeLocation = writeLocation;
		}

		public void appendData( String data ) throws IOException {
			dataForDisk.append( data );
			dataCounter++;
			if( dataCounter == batchSize ) {
				new FileOperations( writeLocation ).writeToDiskInAppendingMode( dataForDisk.toString() );
				dataForDisk = new StringBuilder();
				dataCounter = 0;
			}
		}

		public void flushAll() throws IOException {
			new FileOperations( writeLocation ).writeToDiskInAppendingMode( dataForDisk.toString() );
		}
	}

	public interface DataCollater
	{
		static DataCollater SIMPLE = new DataCollater() {
			public void collectData( String s ) {
				System.out.println( s );
			}
		};

		void collectData( String s );
	}

	public static class ListDataCollater implements DataCollater
	{
		private final Collection<String> data = new ArrayList<String>();

		public void collectData( String s ) {
			if( s != null ) {
				data.add( s );
			}
		}

		public Collection<String> data() {
			return data;
		}
	}

	public static class StringDataCollater implements DataCollater
	{
		private final StringBuilder data = new StringBuilder();

		public void collectData( String s ) {
			if( s != null ) {
				data.append( "\n" + s );
			}
		}

		public String data() {
			return data.toString();
		}
	}

}
