/*
Class: HGMain
Author: Samdine Murray
Created: 4/19/2023
Modified: 5/1/2023

Purpose: The main class of the program. Compiles and runs the code.

Attributes: 

Methods:	+main(String[]): void
			+runProgram(): void

*/

import java.util.Scanner;
public class HGMain {
	
	// MAIN
	public static void main(String[] args) {
		HGMain run = new HGMain();
		run.runProgram();
	}

	// METHODS
	public void runProgram() {
		Scanner in = new Scanner(System.in);
		ProgramController pc = new ProgramController();
		System.out.println("Would you like a random haiku, or would you like to create your own?\n1) Random\n2) User-Generated");
		int haikuType = in.nextInt();
		if (haikuType == 1) {
			System.out.println("Random haiku:\n");
			pc.randomHaiku();
		}
		else {
			System.out.println("Entering Haiku Creator. If you would like to generate a random word, please type \"Give word\".");
			System.out.println("This line has: 5 syllables");
			pc.userHaikuLine(5);
			System.out.println("This line has: 7 syllables");
			pc.userHaikuLine(7);
			System.out.println("This line has: 5 syllables");
			pc.userHaikuLine(5);
			System.out.println("\nFinal haiku:\n" + pc.getHaiku().toLowerCase());
		}
		System.out.println("Would you like to print this haiku to a document? (y/n)");
		in.nextLine();
		String printYN = in.nextLine();
		if (printYN.equalsIgnoreCase("yes") || printYN.equalsIgnoreCase("y")) {
			pc.outputHaiku();
		}
	}
}