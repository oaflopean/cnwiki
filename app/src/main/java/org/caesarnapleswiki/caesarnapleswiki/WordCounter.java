package org.caesarnapleswiki.caesarnapleswiki;

/**
 * Created by ghoulish on 9/25/2017.
 */


        import java.io.InputStream;
        import java.util.ArrayList;
        import java.util.Scanner;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.HashMap;

        import static org.caesarnapleswiki.caesarnapleswiki.R.raw.corpus;

public class WordCounter {
    private HashMap<String, Integer> wordCounts;
    private HashMap<String, ArrayList<String>> wordsThatFollow;



    public WordCounter(InputStream corpus) {
        wordCounts = new HashMap<String, Integer>();
            Scanner fileScanner = new Scanner(corpus);
            countWords(fileScanner);

    }

    private void countWords(Scanner scanner) {
        String prevWord = "";
        while (scanner.hasNext()) {
            String word = scanner.next();
            int oldWordCount = getWordCounts(word);
            wordCounts.put(word, oldWordCount + 1);

            if (!prevWord.equals("")) {
                addWordThatFollows(prevWord, word);
            }
            prevWord = word;
        }
    }

    private void addWordThatFollows(String prevWord, String nextWord) {
        ArrayList existingWords = getWordsThatCouldComeNext(prevWord);
        existingWords.add(nextWord);
        wordsThatFollow.put(prevWord, existingWords);
    }

    public int getWordCounts(String word) {
        if (wordCounts.containsKey(word)) {
            return wordCounts.get(word);
        }
        return 0;
    }

    public void printCounts() {
        for (String key : wordCounts.keySet()) {
            int count = wordCounts.get(key);
            System.out.println(key + ": " + count);
        }
    }

    public boolean startsWithCapitalLetter(String word) {
        char firstLetter = word.charAt(0);
        return (firstLetter >= 'A' && firstLetter <= 'Z');
    }

    public boolean isSentenceEndingPunctuation(String word) {
        return (word.equals(".") || word.equals("?") || word.equals("!"));
    }

    public String getMostCommonWord() {
        int highestCount = -1;
        String wordWithHighestCount = null;
        for (String word : wordCounts.keySet()) {
            if (wordCounts.get(word) > highestCount) {
                wordWithHighestCount = word;
                highestCount = wordCounts.get(word);
            }
        }
        return wordWithHighestCount;
    }

    public int getCountOfMostCommonWord() {
        return getWordCounts(getMostCommonWord());
    }

    public ArrayList<String> getAllWordsThatStartWithACapitalLetter() {
        return null;
    }

    public ArrayList<String> getWordsThatCouldComeNext(String prevWord) {
        return null;
    }

    public int numberOfSentences() {
        return 0;
    }


}