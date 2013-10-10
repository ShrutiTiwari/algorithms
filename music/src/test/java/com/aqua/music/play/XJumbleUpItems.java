package com.aqua.music.play;

import java.io.File;
import java.util.HashSet;
import java.util.Set;


public class XJumbleUpItems
{
	static String sourceDirectory= "c:/raag-recognition/";
	static Set<XRaagElement> jumbledupRecordings= new HashSet<XRaagElement>();
	
	
	public static void main( String[] args ) {
		File sourceDir= new File( sourceDirectory );
		jumbledupRecordings.addAll( loadRaagElementsFrom( sourceDir ));
		
		for (XRaagElement each:jumbledupRecordings){
			System.out.println(each.recordingLocation() );
			System.out.println("This was from [" +each.raagName()+"]");
		}
	}


	private static Set<XRaagElement> loadRaagElementsFrom( File sourceDir ) {
		Set<XRaagElement> jumbledRecordings= new HashSet<XRaagElement>();
		for(File eachItem:sourceDir.listFiles()){
			String raagName=eachItem.getName();
			for(File eachRecordng: eachItem.listFiles()){
				jumbledRecordings.add( new XRaagElement(raagName, eachRecordng.getName()) );	
			}
		}
		return jumbledRecordings;
	}
}
