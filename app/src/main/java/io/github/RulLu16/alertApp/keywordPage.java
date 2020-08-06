package io.github.RulLu16.alertApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class keywordPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword_page);
    }

    public void onClick_Crawl(View v){
        //AlertDialog.Builder builder = new AlertDialog.Builder(this);
        EditText inputKeyword=findViewById(R.id.loginKeyword);
        String keyword=inputKeyword.getText().toString();

        Intent intent1=getIntent();

        String id=intent1.getStringExtra("input id");
        String password=intent1.getStringExtra("input password");

        Intent intent2 = new Intent(getApplicationContext(), crawlPage.class);
        intent2.putExtra("input id", id);
        intent2.putExtra("input password", password);
        intent2.putExtra("input keyword", keyword);

        startActivity(intent2);
    }

}