package ununused.com.aqua.music.print;

import java.util.LinkedHashMap;
import java.util.Map;

public class RaagProperties {
	private static final String KEY_NAME = "RAAG_NAME";
	private static final String KEY_THAAT = "THAAT";
	public static final String KEY_TIME = "TIME";
	private static final String KEY_JAATI = "JAATI";
	private static final String KEY_VADI = "VADI";
	private static final String KEY_SAMVADI = "SAMVADI";
	private static final String KEY_AAROHI = "AAROHI";
	private static final String KEY_AVROHI = "AVROHI";

	private final static String SEP = "|";
	private final static String propertiesHeader = header();
	private Map<String, String> myProperties = new LinkedHashMap<String, String>();

	RaagProperties(String raagName, VadiSamvadi keyNotes, Time time, WriteableThaat thaat, String jaati) {
		myProperties.put(KEY_NAME, raagName);
		myProperties.put(KEY_THAAT, thaat.name());
		myProperties.put(KEY_TIME, time.name());
		myProperties.put(KEY_JAATI, jaati);
		myProperties.put(KEY_VADI, keyNotes.vadi());
		myProperties.put(KEY_SAMVADI, keyNotes.samvadi());
	}

	public static String padRight(String s) {
		return String.format("%-15s", s);
	}

	static String header() {
		StringBuilder output = new StringBuilder();
		output.append(String.format("%-6s", SEP + "S.No."));
		append(output, KEY_NAME);
		append(output, KEY_THAAT);
		append(output, KEY_TIME);
		append(output, KEY_JAATI);
		append(output, KEY_VADI);
		append(output, KEY_SAMVADI);
		append(output, "");
		return output.toString();
	}

	private static void append(StringBuilder output, String value) {
		String outputStr = SEP + value;
		outputStr = padRight(outputStr);
		output.append(outputStr);
	}

	public WriteableThaat thaat() {
		return WriteableThaat.valueOf(myProperties.get(KEY_THAAT));
	}

	public Time time() {
		return Time.valueOf(myProperties.get(KEY_TIME));
	}

	String name() {
		return myProperties.get(KEY_NAME);
	}

	String properties() {
		StringBuilder output = new StringBuilder();
		for (String each : myProperties.values()) {
			append(output, each);
		}
		append(output, "");
		return output.toString();
	}

	interface RaagProp {
	}

	public enum WriteableThaat implements RaagProp {
		Bhairav,
		Bhairavi,
		Bilawal,
		Kafi,
		Khamaj,
		Marva,
		Purvi,
		Kalyan,
		Asavari,
		Todi;
	}

	public enum Time implements RaagProp {
		Morning,
		Evening,
		Afternoon;
	}
}
