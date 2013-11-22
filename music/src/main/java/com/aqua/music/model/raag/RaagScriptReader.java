package com.aqua.music.model.raag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

public class RaagScriptReader {
	public static void main(String[] args) {
		final String fileName = "ahir_bhairav.txt";
		new RaagScriptReader().processFile(fileName);
	}

	public void processFile(String fileName) {
		try {
			Collection<String[]> allResultLines = new RaagScriptParser(Taal.TEENTAL).parseLines(readFile(fileName));
			printResult(allResultLines);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void printResult(Collection<String[]> allResultLines) {
		System.out.println("Results::::::::::::::::::::");
		for (String[] eachLine : allResultLines) {
			System.out.println("\n");
			int count = 0;
			for (String eachString : eachLine) {
				System.out.print(eachString);
				count++;
				if (count % 4 == 0) {
					System.out.print(" | ");
				}
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
