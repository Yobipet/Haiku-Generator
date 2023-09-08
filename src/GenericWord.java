/*
Class: GenericWord
Author: Samdine Murray
Created: 3/23/2023
Modified: 5/1/2023

Purpose: Generic class to define the attributes of every word.

Attributes: -word: String
			-syllables: int
			-partOfSpeech: String
			-isPlural: boolean

Methods:	+nextWordBehavior(GenericWord): String
			+previousWordBehavior(GenericWord): String

*/

public class GenericWord {
	
	// ATTRIBUTES
	private String word;
	private int syllables;
	private String partOfSpeech;
	private boolean isPlural;
	
	// CONSTRUCTOR
	public GenericWord(String word, int syllables, String partOfSpeech, boolean isPlural) {
		this.word = word;
		this.syllables = syllables;
		this.partOfSpeech = partOfSpeech;
		this.isPlural = isPlural;
	}

	// METHODS
	public String nextWordBehavior(GenericWord nextWord) {
		return word;
	}
	public String previousWordBehavior(GenericWord prevWord) {
		return word;
	}

	// SETTERS & GETTERS
	public void setWord(String word) {
		this.word = word;
	}
	public String getWord() {
		return word;
	}
	public void setSyllables(int syllables) {
		this.syllables = syllables;
	}
	public int getSyllables() {
		return syllables;
	}
	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}
	public String getPartOfSpeech() {
		return partOfSpeech;
	}
	public void setPlural(boolean isPlural) {
		this.isPlural = isPlural;
	}
	public boolean getPlural() {
		return isPlural;
	}
}