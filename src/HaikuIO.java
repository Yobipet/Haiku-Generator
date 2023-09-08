/*
Class: HaikuIO
Author: Samdine Murray
Created: 4/19/2023
Modified: 5/1/2023

Purpose: In charge of most file input and file output events for the program (Importing/exporting haikus, editing word list).

Attributes: -wordList: File
			-sortedWords: ArrayList<GenericWord>

Methods:	+readWords(): void
			+readNewWord(String): int
			+writeWordList(): void
			+writeHaiku(String, String): void

*/

import javax.print.attribute.standard.MediaSize;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class HaikuIO {
	
	// ATTRIBUTES
	private File wordList = new File("WordList.csv");
	private ArrayList<GenericWord> sortedWords = new ArrayList<>();
	
	// CONSTRUCTOR
	public HaikuIO() {
		readWords();
	}
	
	// METHODS
	public void readWords() {
		try {
			FileReader fr = new FileReader(wordList);
			BufferedReader br = new BufferedReader(fr);
			String row;
			row = br.readLine();
			String[] cols = row.split(",");
			while ((row = br.readLine()) != null) {
				String[] columns = row.split(",", 4);
				String word = columns[1];
				int syllables = Integer.parseInt(columns[2]);
				String partOfSpeech = columns[0];
				boolean isPlural = Boolean.parseBoolean(columns[3]);
				sortedWords.add(new OtherWord(word, syllables, partOfSpeech, isPlural));
			}
			br.close();
			fr.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int readNewWord(String word) {
		int wordSyllables = 0;
		boolean errorChecked = false;
		do {
			errorChecked = true;
			try {
				Scanner in = new Scanner(System.in);
				System.out.println("How many syllables is that word?");
				wordSyllables = in.nextInt();
			}
			catch(Exception e) {
				System.out.println("Invalid input.." + e);
				errorChecked = false;
			}
		}
		while (!errorChecked);
		Scanner in = new Scanner(System.in);
		System.out.println("What part of speech is that word? (noun, adjective, verb, preposition, possessive)");
		String wordPOS = in.nextLine();
		System.out.println("Is this word plural, or to be used in a plural context?");
		String pluralYN = in.nextLine();
		boolean isPlural;
		isPlural = !pluralYN.equalsIgnoreCase("n") && !pluralYN.equalsIgnoreCase("no");
		if (wordPOS.equalsIgnoreCase("adjective") || wordPOS.equalsIgnoreCase("noun") || wordPOS.equalsIgnoreCase("verb") || wordPOS.equalsIgnoreCase("preposition") || wordPOS.equalsIgnoreCase("possessive")) {
			sortedWords.add(new OtherWord(word,wordSyllables,wordPOS,isPlural));
		}
		writeWordList();
		return wordSyllables;
	}
	public void writeWordList() {
		try {
			FileWriter fw = new FileWriter(wordList);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Part of speech,Word,Syllable Count,Plural?");
			bw.newLine();
			for (int i = 0; i < sortedWords.size(); i++) {
				String thisLine = sortedWords.get(i).getPartOfSpeech() + "," + sortedWords.get(i).getWord() + "," + sortedWords.get(i).getSyllables() + "," + sortedWords.get(i).getPlural();
				bw.write(thisLine);
				bw.newLine();
			}
			bw.close();
			fw.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void writeHaiku(String fileName, String haiku) {
		String trueFileName = fileName + ".txt";
		File haikuFile = new File(trueFileName);
		try {
			FileWriter fw = new FileWriter(haikuFile);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(haiku);
			bw.close();
			fw.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// SETTERS & GETTERS
	public ArrayList<GenericWord> getSortedWords() {
		return sortedWords;
	}
	public void setSortedWords(ArrayList<GenericWord> sortedWords) {
		this.sortedWords = sortedWords;
	}
	
}