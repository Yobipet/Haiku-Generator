/*
Class: ArticleWord
Author: Samdine Murray
Created: 4/30/2023
Modified: 5/1/2023

Purpose: Class to define the semantic behavior of articles, such as "the", "a", or "an."

Attributes:

Methods:    +nextWordBehavior(GenericWord): String

*/

public class ArticleWord extends GenericWord {

    // CONSTRUCTOR
    public ArticleWord(String word, int syllables, String partOfSpeech, boolean isPlural) {
        super(word, syllables, partOfSpeech, isPlural);
    }

    // METHODS
    @Override
    public String nextWordBehavior(GenericWord nextWord) {
        char firstCharacter = Character.toLowerCase(nextWord.getWord().charAt(0));
        if (nextWord.getPlural()) {
            setWord("the");
        }
        else if (firstCharacter == 'a' || firstCharacter == 'e' || firstCharacter == 'i' || firstCharacter == 'o' || firstCharacter == 'u') {
            if (getWord().equalsIgnoreCase("a")) {
                setWord("an");
            }
        }
        return getWord();
    }
}
