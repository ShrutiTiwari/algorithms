package com.octopus.text.search.pair.approach2;


class KeywordOccurance {
	private final long locationInFile;
	private final ValidKeyword keyword;
	private final int length;
	private boolean invalidated;
	private boolean matched;
	private  int matchedMaxIndex=0;
	KeywordOccurance(long locationInFile, ValidKeyword keyword) {
		this.locationInFile = locationInFile;
		this.keyword = keyword;
		this.length = keyword.value.length();
	}

	@Override
	public String toString() {
		return "startIndex[" + locationInFile + "], expression[" + keyword.value + "]";
	}
	boolean invalidated(){
		return invalidated;
	}

	long locationInFile() {
		return locationInFile;
	}

	boolean matched(){
		return matched;
	}

	void update(char currentCharacter) {
		if(!keyword.containsCharacterAtIndex(currentCharacter, matchedMaxIndex +1)){
			invalidated=true;
		}else{
			matchedMaxIndex++;
			if(matchedMaxIndex+1 == length){
				matched=true;
			}
		}
	}
}