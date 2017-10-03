package org.caesarnapleswiki.caesarnapleswiki;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ghoulish on 9/25/2017.
 */

public class  SentenceActivity extends AppCompatActivity implements View.OnClickListener{
    Button button;
    Random random;
    Integer chosen=R.raw.corpus;
    Context context; // before onCreate in MainActivity


    private String randomlySelectWord(ArrayList<String> possibleWords) {
        int randomIndex = random.nextInt(possibleWords.size());
        return possibleWords.get(randomIndex);
    }
    // given any word, randomly choose a word that could come next, using the TextAnalyzer wordsThatCouldComeNext
    // return that sentence as a String


    // given a starting word, generate a random word that could come next, and a random word that could come after that, etc.
    // keep going until you generate punctuation that could end a sentence, as determined by TextAnalyzer isSentenceEndingPunctuation


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate_sentences);
        button = (Button) findViewById(R.id.floatingActionButton5);
        button.setOnClickListener(SentenceActivity.this);
        context = getApplicationContext();  // in onCreate in MainActivity
        InputStream input= getApplicationContext().getResources().openRawResource(chosen);
        WordCounter counter = new WordCounter(input);

    }

    @Override
    public void onClick(View v) {
        if (v == button) {
            String word = new String("Button has been pressed, requesting one sentence.");
            TextView go = (TextView)findViewById(R.id.textView);
            go.setText(generateRandomSentence());
        }
        }


    // given a starting word, generate a random word that could come next, and a random word that could come after that, etc.
    // keep going until you generate punctuation that could end a sentence, as determined by TextAnalyzer isSentenceEndingPunctuation


    // randomly choose a word that starts with a capital letter, and use that as the first word of the sentence
    // then randomly generate the rest of the sentence using generateRandomSentence(String firstWord)




    }





