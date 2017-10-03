package org.caesarnapleswiki.caesarnapleswiki;

import android.content.res.Resources;
import android.os.Build;
import android.provider.MediaStore;
import android.renderscript.ScriptGroup;
import android.support.annotation.RequiresApi;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import static java.lang.System.in;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by ghoulish on 3/14/2017.
 */


public class randomw {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String rndWord(InputStream input) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(input, writer, UTF_8);
        String contents = writer.toString().replace("\n", " ");
        Random rnd = new Random();
        String[] token = contents.split(" ");
        String word = "";
        while (word.length() < 4) {
            word = token[rnd.nextInt(token.length)].replace(",", "").replace(".", "".replace("\"", "").replace(" ", ""));
        }
        return word;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String rndSentence(InputStream input) throws IOException {
        Random rnd = new Random();
        StringWriter writer = new StringWriter();
        IOUtils.copy(input, writer, UTF_8);
        String contents = writer.toString().replace("\n", "");
        String[] token = contents.split(" ");
        Vector<String> sentence = new Vector<String>();
        //ArrayList<String> startWords=new ArrayList<String>();
        //startWords.add(token[0]);
        //for (int p=0; p<token.length-1;p++){
        //    if (token[p].contains(".")) {
        //        startWords.add(token[p+1]);}
        // }
        //    String first = startWords.get(rnd.nextInt(startWords.size()));
        ArrayList<Integer> firstNum = new ArrayList<Integer>();
        for (int p = 0; p < token.length - 1; p++) {
            if (token[p].contains(".")) {
                firstNum.add(p + 1);
            }
        }
        Integer ranNum = firstNum.get(rnd.nextInt(firstNum.size()));
        for (int i = ranNum; i < token.length; i++) {
            if (token[i].contains(".") != true) {
                sentence.add(token[i]);
            } else {
                sentence.add(token[i]);
                break;
            }
        }
        return sentence.toString().replace("[", "").replace("]", "").replace(",", "");
    }
    //        Vector<String> phrase=new Vector<String>();
//        for (int i=0;i<token.length;i++) {
//            if (token[i].contains(".")) {
//                for (int p = i + 1; p < token.length; p++) {
//                    if (token[p].contains(".") != true) {
//
//                        phrase.add(token[p]);
//                    } else {
//                       break;
//
//                    }
//                }
//            }
//        }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String rndMarkov(InputStream input, InputStream wiki, InputStream extra, InputStream added) throws IOException{
        StringWriter writer1 = new StringWriter();
        StringWriter writer2= new StringWriter();
        StringWriter writer3= new StringWriter();
        StringWriter writer4= new StringWriter();


        IOUtils.copy(input, writer1, UTF_8);
        IOUtils.copy(wiki, writer2, UTF_8);
        IOUtils.copy(extra, writer3, UTF_8);
        IOUtils.copy(added, writer4, UTF_8);

        String contents1 = writer1.toString().replace("\n", "");
        String contents2 = writer2.toString().replace("\n","");
        String contents3 = writer3.toString().replace("\n","");
        String contents4 = writer4.toString().replace("\n","");


        String everything=contents1 + contents2 +contents3 + contents4;
        try {
            return addWords(everything);
        }
        catch (NullPointerException e){
            return "";
        }
    }




    /*
     * Add words
     */
    public static String addWords(String phrase) {

        Random rnd = new Random();
	/*
	 * Main constructor
	 */
        Hashtable<String, Vector<String>> markovChain = new Hashtable<String, Vector<String>>();

        // Create the first two entries (k:_start, k:_end)
        markovChain.put("_start", new Vector<String>());
        markovChain.put("_end", new Vector<String>());

        // put each word into an array
        String[] words = phrase.split(" ");

        // Loop through each word, check if it's already added
        // if its added, then get the suffix vector and add the word
        // if it hasn't been added then add the word to the list
        // if its the first or last word then select the _start / _end key

        for (int i = 0; i < words.length; i++) {

            // Add the start and end words to their own
            if (i == 0) {
                Vector<String> startWords = markovChain.get("_start");
                startWords.add(words[i]);

                Vector<String> suffix = markovChain.get(words[i]);
                if (suffix == null) {
                    suffix = new Vector<String>();
                    suffix.add(words[i + 1]);
                    markovChain.put(words[i], suffix);
                }

            } else if (i == words.length - 1) {
                Vector<String> endWords = markovChain.get("_end");
                endWords.add(words[i]);

            } else {
                Vector<String> suffix = markovChain.get(words[i]);
                if (suffix == null) {
                    suffix = new Vector<String>();
                    suffix.add(words[i + 1]);
                    markovChain.put(words[i], suffix);
                } else {
                    suffix.add(words[i + 1]);
                    markovChain.put(words[i], suffix);
                }
            }
        }
        try{
    return generateSentence(markovChain);}
        catch (NullPointerException e){
            return "";
    }}
    public static String generateSentence(Hashtable<String, Vector<String>> markovChain ){
        Random rnd = new Random();

        // Vector to hold the phrase
        Vector<String> newPhrase = new Vector<String>();

        // String for the next word
        String nextWord = "";

        // Select the first word
        Vector<String> startWords = markovChain.get("_start");
        int startWordsLen = startWords.size();
        nextWord = startWords.get(rnd.nextInt(startWordsLen));
        newPhrase.add(nextWord);
        if (nextWord.length()!=0){
        // Keep looping through the words until we've reached the end
        try{
            while (nextWord.charAt(nextWord.length()-1) != '.') {
                Vector<String> wordSelection = markovChain.get(nextWord);
                int wordSelectionLen = wordSelection.size();
                nextWord = wordSelection.get(rnd.nextInt(wordSelectionLen));
                newPhrase.add(nextWord);
            }
        }
        catch (StringIndexOutOfBoundsException e){
            generateSentence(markovChain);}
        }
        else{
            generateSentence(markovChain);
        }

        return newPhrase.toString().replace("[", "").replace("]", "").replace(",", "");}


}







