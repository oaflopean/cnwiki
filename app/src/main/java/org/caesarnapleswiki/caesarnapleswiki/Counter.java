package org.caesarnapleswiki.caesarnapleswiki;

import java.util.ArrayList;

/**
 * Created by ghoulish on 10/3/2017.
 */

public class Counter  {
    public String generateRandomSentence(String firstWord, Counter counter) {
        String sentence = "";
        String word = firstWord;
        while (!counter.isSentenceEndingPunctuation(word)) {
            sentence += word + " ";
            word = randomlyChooseNextWord(firstWord);
        }
        sentence += word;
        return sentence;}
    public String randomlyChooseNextWord(String prevWord, Counter counter) {
        ArrayList<String> candidateWords = counter.getWordsThatCouldComeNext(prevWord);
        return randomlySelectWord(candidateWords);
    }
    public String generateRandomSentence(Counter counter) {
        String firstWord = randomlySelectWord(counter.getAllWordsThatStartWithACapitalLetter());
        return generateRandomSentence(firstWord);
    }}
