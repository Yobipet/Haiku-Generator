/*
Class: ProgramController
Author: Samdine Murray
Created: 4/19/2023
Modified: 5/1/2023

Purpose: Controls the program. Takes all user input, contains methods to create haikus, and coordinates all other classes for more concise usage in the HGMain class.

Attributes: -haiku: String

Methods:	+userHaikuLine(int): void
			+randomHaiku(): void
			+outputHaiku(): void

*/

import java.util.Scanner;
import java.util.ArrayList;
public class ProgramController {

	// ATTRIBUTES
	private String haiku;

	// CONSTRUCTOR
	public ProgramController() {
		haiku = "";
	}

	// METHODS
	public void userHaikuLine(int lineSyllables) {
		Scanner in = new Scanner(System.in);
		PhraseBuilder pb = new PhraseBuilder();
		WordGenerator wg = new WordGenerator();
		HaikuIO hIO = new HaikuIO();
		ArrayList<GenericWord> haikuLine = new ArrayList<>();
		while(lineSyllables > 0) {
			System.out.println("What word next?");
			String nextWord = in.nextLine();
			if (nextWord.equalsIgnoreCase("Give word")) {
				GenericWord requestedWord = wg.requestWord();
				if ((lineSyllables - requestedWord.getSyllables()) >= 0) {
					if (requestedWord.getWord().equalsIgnoreCase("null")) {
						System.out.println("There is no such word in the database. Please try again.");
					} else {
						OtherWord requestedWordClasstype = new OtherWord(requestedWord.getWord(), requestedWord.getSyllables(), requestedWord.getPartOfSpeech(), requestedWord.getPlural());
						haikuLine.add(requestedWordClasstype);
						lineSyllables = lineSyllables - requestedWordClasstype.getSyllables();
						System.out.println("Line so far: " + pb.sequenceLine(haikuLine));
					}
				}
				else {
					System.out.println("Sorry, that word has too many syllables for this line. Try again.");
				}
			} else {
				int wordSyllables = wg.searchSyllables(nextWord);
				if (wordSyllables == 0) {
					wordSyllables = hIO.readNewWord(nextWord);
				}
				if ((lineSyllables - wordSyllables) < 0) {
					System.out.println("Sorry, that word has too many syllables for this line. Try again.");
				} else {
					WordGenerator wg2 = new WordGenerator();
					GenericWord nextWordClasstype = wg2.getWordFromDatabase(nextWord);
					if (nextWordClasstype.getWord().equalsIgnoreCase("null")) {
						nextWordClasstype.setWord(nextWord);
					}
					haikuLine.add(nextWordClasstype);
					lineSyllables = lineSyllables - wordSyllables;
					System.out.println("Line so far: " + pb.sequenceLine(haikuLine).toLowerCase());
				}
			}
		}
		System.out.println("\nLine complete!\n");
		haiku = haiku + pb.sequenceLine(haikuLine) + "\n";
	}

	public void randomHaiku() {
		PhraseBuilder pb = new PhraseBuilder();
		pb.setSyllablesNeeded(5);
		haiku = haiku + pb.buildLine().toLowerCase() + "\n";
		pb.setSyllablesNeeded(7);
		haiku = haiku + pb.buildLine().toLowerCase() + "\n";
		pb.setSyllablesNeeded(5);
		haiku = haiku + pb.buildLine().toLowerCase() + "\n";
		System.out.println(haiku);
	}

	public void outputHaiku() {
		Scanner in = new Scanner(System.in);
		HaikuIO hIO = new HaikuIO();
		System.out.println("What would you like to name the file where the haiku is printed?");
		String fileName = in.nextLine();
		hIO.writeHaiku(fileName, getHaiku());
	}

	// SETTERS & GETTERS
	public String getHaiku() {
		return haiku;
	}

}