package org.caesarnapleswiki.caesarnapleswiki;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import static org.caesarnapleswiki.caesarnapleswiki.randomw.rndMarkov;

/**
 * Created by ghoulish on 9/25/2017.
 */

public class SentenceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView t=new TextView(this);

        setContentView(R.layout.generate_sentences);
        t=(TextView)findViewById(R.id.textView);
        t.setText("Go");
}
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void sentenceG(View view){
        generateSentence();
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void generateSentence() {
        Integer chosen=randomChoice.choose();
        InputStream input= getApplicationContext().getResources().openRawResource(chosen);
        InputStream wiki= getApplicationContext().getResources().openRawResource(R.raw.cnwiki);
        InputStream extra= getApplicationContext().getResources().openRawResource(R.raw.newfile);
        InputStream added = getApplicationContext().getResources().openRawResource(R.raw.corpus);

        String markov=null;
        try {
            TextView t;

            t=(TextView)findViewById(R.id.textView);
            markov = rndMarkov(input, wiki, extra, added);
            t.setText(markov);

    } catch (IOException e) {
            e.printStackTrace();
        }

    }}
