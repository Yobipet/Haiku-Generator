/*
Class: WordGenerator
Author: Samdine Murray
Created: 3/23/2023
Modified: 5/1/2023

Purpose: Generate a random word from a list based on a certain specified attribute.

Attributes: -sortedWords: ArrayList<GenericWord>

Methods:	+searchWord(int, String): GenericWord
			+searchSyllables(String): int
			+requestWord(): GenericWord
			+getWordFromDatabase(String): GenericWord

*/

import java.util.ArrayList;
import java.util.Scanner;

public class WordGenerator {

	// ATTRIBUTES
	private ArrayList<GenericWord> sortedWords = new ArrayList<>();

	// CONSTRUCTOR
	public WordGenerator() {
		HaikuIO hIO = new HaikuIO();
		sortedWords = hIO.getSortedWords();
	}

	// METHODS
	public GenericWord searchWord(int prefSyllables, String prefType) {
		ArrayList<GenericWord> choiceWords = new ArrayList<>();
		if (prefSyllables <= 4 && prefSyllables > 0) {
			for (int i = 0; i < sortedWords.size(); i++) {
				if (prefType.equalsIgnoreCase(sortedWords.get(i).getPartOfSpeech()) && prefSyllables == sortedWords.get(i).getSyllables()) {
					choiceWords.add(sortedWords.get(i));
				}
			}
		}
		if (choiceWords.size() != 0) {
			return choiceWords.get((int)Math.round(Math.random()*(choiceWords.size()-1)));
		} else {
			return new OtherWord("null",0,"null",false);
		}
	}

	public int searchSyllables(String word) {
		int wordSyllables = 0;
		for (int i = 0; i < sortedWords.size(); i++) {
			if (sortedWords.get(i).getWord().equalsIgnoreCase(word)) {
				wordSyllables = sortedWords.get(i).getSyllables();
			}
		}
		return wordSyllables;
	}

	public GenericWord requestWord() {
		int wordSyllables = 0;
		boolean errorChecked = false;
		do {
			errorChecked = true;
			try {
				Scanner in = new Scanner(System.in);
				System.out.println("How many syllables should the word be?");
				wordSyllables = in.nextInt();
			} catch(Exception e) {
				System.out.println("Invalid input.." + e);
				errorChecked = false;
			}
		}
		while (!errorChecked);
		String wordPOS = "";
		do {
			Scanner in = new Scanner(System.in);
			System.out.println("What part of speech?");
			wordPOS = in.nextLine();
			if (!wordPOS.equalsIgnoreCase("adjective") && !wordPOS.equalsIgnoreCase("noun") && !wordPOS.equalsIgnoreCase("verb") && !wordPOS.equalsIgnoreCase("preposition")) {
				errorChecked = false;
				System.out.println("Invalid input.");
			}
			else {
				errorChecked = true;
			}
		}
		while (!errorChecked);
		return searchWord(wordSyllables,wordPOS);
	}

	public GenericWord getWordFromDatabase(String word) {
		GenericWord newWord = new GenericWord("null",0,"null",false);
		for (int i = 0; i < sortedWords.size(); i++) {
			if (word.equalsIgnoreCase(sortedWords.get(i).getWord())) {
				newWord = sortedWords.get(i);
			}
		}
		if (newWord.getPartOfSpeech().equalsIgnoreCase("article")) {
			return new ArticleWord(newWord.getWord(), newWord.getSyllables(), newWord.getPartOfSpeech(), newWord.getPlural());
		} else if (newWord.getPartOfSpeech().equalsIgnoreCase("noun")) {
			return new NounWord(newWord.getWord(), newWord.getSyllables(), newWord.getPartOfSpeech(), newWord.getPlural());
		} else {
			return new OtherWord(newWord.getWord(), newWord.getSyllables(), newWord.getPartOfSpeech(), newWord.getPlural());
		}
	}
}