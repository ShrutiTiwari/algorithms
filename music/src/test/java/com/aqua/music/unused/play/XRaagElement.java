package com.aqua.music.unused.play;

public class XRaagElement {
	public XRaagElement(String raagName, String recordingLocation) {
		super();
		this.raagName = raagName;
		this.recordingLocation = recordingLocation;
	}

	private String raagName;
	private String recordingLocation;

	String raagName() {
		return raagName;
	}

	String recordingLocation() {
		return recordingLocation;
	}
}
