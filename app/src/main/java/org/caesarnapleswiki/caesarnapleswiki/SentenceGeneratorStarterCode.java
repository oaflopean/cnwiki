package org.caesarnapleswiki.caesarnapleswiki;

/**
 * Created by ghoulish on 9/25/2017.
 */
import java.util.ArrayList;
import java.util.Random;

public class SentenceGeneratorStarterCode {
    WordCounter counter;
    Random random;

    public SentenceGeneratorStarterCode(String filename) {
        counter = new WordCounter(filename);
        this.random = new Random();
    }

    public SentenceGeneratorStarterCode(String filename, Random random) {
        counter = new WordCounter(filename);
        this.random = random;
    }

    // given any word, randomly choose a word that could come next, using the TextAnalyzer wordsThatCouldComeNext
    // return that sentence as a String



    private String randomlySelectWord(ArrayList<String> possibleWords) {
        int randomIndex = random.nextInt(possibleWords.size());
        return possibleWords.get(randomIndex);
    }


}
