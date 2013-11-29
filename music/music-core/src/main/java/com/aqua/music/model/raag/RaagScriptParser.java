package com.aqua.music.model.raag;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author "Shruti Tiwari"
 *
 */
public class RaagScriptParser {

	private final int toalBeats;
	private final int beatsPerDivision;

	RaagScriptParser(Taal taal) {
		this.toalBeats = taal.totalBeats();
		this.beatsPerDivision = taal.beatsSequence()[0];
	}

	Collection<String[]> parseLines(Collection<String> allLines) {
		Collection<String[]> allResultLines = new ArrayList<String[]>();
		try {
			for (String each : allLines) {
				String[] resultArray = line();
				String[] div = each.split("\\|");

				int counter = 0;
				for (String eachDivision : div) {
					if (eachDivision.isEmpty()) {
						counter = counter + beatsPerDivision;
						continue;
					} else {
						String[] beatsData = eachDivision.split(" ");
						counter = counter + (beatsPerDivision - beatsData.length);

						for (String eachBeat : beatsData) {
							String beatData = eachBeat.trim();
							if (beatData.equals("")) {
								continue;
							}
							// extended logic
							char firstChar = beatData.charAt(0);
							if (firstChar == '-') {
								resultArray[counter++] = "" + firstChar;
							} else if (Character.isLetter(firstChar)) {
								StringBuffer lastFrequency = new StringBuffer(firstChar);
								lastFrequency.append(firstChar);
								for (int j = 1; j < beatData.length(); j++) {
									char currentChar = beatData.charAt(j);
									if (currentChar == '1' || currentChar == '3' || currentChar == '_') {
										lastFrequency.append(currentChar);
									} else {
										lastFrequency.append("," + currentChar);
									}
								}
								resultArray[counter++] = lastFrequency.toString();
							}
						}
					}
				}
				allResultLines.add(resultArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allResultLines;
	}

	private String[] line() {
		return new String[toalBeats];
	}
}
