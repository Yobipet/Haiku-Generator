/*
Class: NounWord
Author: Samdine Murray
Created: 4/30/2023
Modified: 5/1/2023

Purpose: Class to define the semantic behavior of nouns.

Attributes:

Methods:    +nextWordBehavior(GenericWord): String

*/

public class NounWord extends GenericWord {
    public NounWord(String word, int syllables, String partOfSpeech, boolean isPlural) {
        super(word, syllables, partOfSpeech, isPlural);
    }

    @Override
    public String nextWordBehavior(GenericWord nextWord) {
        if (nextWord.getPlural() && nextWord.getPartOfSpeech().equalsIgnoreCase("verb") && !getPlural()) {
            setWord(getWord() + "s");
            setPlural(true);
        }
        return getWord();
    }
}