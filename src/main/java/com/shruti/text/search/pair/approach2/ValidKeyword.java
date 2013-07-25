package com.shruti.text.search.pair.approach2;

class ValidKeyword {
	final char startChar;
	final String value;

	/**
	 * TODO: Increase robustness on whitespaces
	 * 
	 * @param mainWord
	 * @param pairWord
	 */
	ValidKeyword(String mainWord, String pairWord) {
		this.value = mainWord + " " + pairWord;
		this.startChar = mainWord.charAt(0);
	}

	boolean containsCharacterAtIndex(char c, int index) {
		return index < value.length() && c == value.charAt(index);
	}
}