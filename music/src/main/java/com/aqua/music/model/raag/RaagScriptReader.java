package com.aqua.music.model.raag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.aqua.music.api.AudioPlayerSettings;
import com.aqua.music.model.core.ClassicalNote;
import com.aqua.music.model.core.DynamicFrequency;

/**
 * @author "Shruti Tiwari"
 *
 */
public class RaagScriptReader {
	public static void main(String[] args) {
		final String fileName = "ahir_bhairav.txt";
		new RaagScriptReader().processFile(fileName);
	}

	public void processFile(String fileName) {
		try {
			Collection<String[]> allResultLines = new RaagScriptParser(Taal.TEENTAL).parseLines(readFile(fileName));
			Collection<DynamicFrequency> allFrequencies = printResult(allResultLines);
			AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER.play(allFrequencies);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Collection<DynamicFrequency> printResult(Collection<String[]> allResultLines) {
		System.out.println("Results::::::::::::::::::::");

		Collection<DynamicFrequency> allFreqencies = new ArrayList<DynamicFrequency>();

		for (String[] eachLine : allResultLines) {
			MusicalPhrase musicPhrase = new MusicalPhrase();

			System.out.println("\n");
			// int count = 0;

			String lastFreq = null;
			int countFreq = 1;
			System.out.print("processing line:::  " + Arrays.asList(eachLine) + "\n");
			for (String eachItem : eachLine) {
				// System.out.print("processing string:::  "+eachString);
				if (eachItem == null || eachItem.equals("")) {
					continue;
				} else {
					if (eachItem.equals("-")) {
						countFreq++;
					} else if (eachItem.contains(",")) {
						addLastNote(musicPhrase, lastFreq, countFreq);
						lastFreq = null;
						countFreq = 0;

						String[] split = eachItem.split(",");
						musicPhrase.couple(ClassicalNote.valueOf(split[0]), ClassicalNote.valueOf(split[1]));
						System.out.println("adding couple note:::  " + split[0] + split[1]);
					} else {
						addLastNote(musicPhrase, lastFreq, countFreq);
						lastFreq = eachItem;
						countFreq = 1;
					}
				}
			}
			addLastNote(musicPhrase, lastFreq, countFreq);
			System.out.println(musicPhrase.printLine());
			allFreqencies.addAll(musicPhrase.frequencies());
		}

		return allFreqencies;
	}

	private void addLastNote(MusicalPhrase musicPhrase, String lastFreq, int countFreq) {
		if (lastFreq != null) {
			ClassicalNote lastNote = ClassicalNote.valueOf(lastFreq);
			if (countFreq == 1) {
				musicPhrase.n(lastNote);
				System.out.println("adding normal note:" + lastNote);
			} else {
				musicPhrase.e(lastNote, countFreq);
				System.out.println("adding extended note:" + lastNote + ":: " + countFreq);
			}
		}
	}

	private Collection<String> readFile(String fileName) throws IOException {
		Collection<String> allLines = new ArrayList<String>();
		InputStream i = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		System.out.println(i);
		BufferedReader fr = new BufferedReader(new InputStreamReader(i));

		String line = null;
		while ((line = fr.readLine()) != null) {
			allLines.add(line);
		}
		return allLines;
	}

}
