package org.caesarnapleswiki.caesarnapleswiki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void sentenceLayout(View view){
        launchSentence();
    }
    public void paragraphLayout(View view){
        launchParagraph();
    }
    public void customLayout(View view){
        launchCustom();
    }
    public void historyLayout(View view){
        launchHistory();
    }

    private void launchSentence() {

        Intent intent = new Intent(this, SentenceActivity.class);
        startActivity(intent);
            }
    private void launchParagraph() {

        Intent intent = new Intent(this, ParagraphActivity.class);
        startActivity(intent);
            }

    private void launchCustom() {

        Intent intent = new Intent(this, CustomActivity.class);
        startActivity(intent);
    }
    private void launchHistory() {

        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}
