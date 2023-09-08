/*
Class: VerbWord
Author: Samdine Murray
Created: 4/30/2023
Modified: 5/1/2023

Purpose: Class to define the semantic behavior of verbs.

Attributes:

Methods:    +previousWordBehavior(GenericWord): String

*/

public class VerbWord extends GenericWord{
    public VerbWord(String word, int syllables, String partOfSpeech, boolean isPlural) {
        super(word, syllables, partOfSpeech,isPlural);
    }

    @Override
    public String previousWordBehavior(GenericWord prevWord) {
        if (prevWord.getPartOfSpeech().equalsIgnoreCase("pronoun") && getPlural()) {
            setWord(getWord() + "s");
            setPlural(false);
        }
        else if (prevWord.getPartOfSpeech().equalsIgnoreCase("noun") && prevWord.getPlural() && !getPlural()) {
            setWord(getWord().substring(0, (getWord().length()-2)) + "" + getWord().substring(getWord().length()));
        }
        return getWord();
    }
}