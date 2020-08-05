package io.github.RulLu16.alertApp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
public class keywordPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword_page);
    }

    public void onClick_Crawl(View v){
        String message="";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        EditText inputKeyword=findViewById(R.id.loginKeyword);
        String keyword=inputKeyword.getText().toString();

        Intent intent1=getIntent();

        String id=intent1.getStringExtra("inputId");
        String password=intent1.getStringExtra("inputPassword");

        crawlPage c=new crawlPage();

        c.setID(id);
        c.setPassword(password);
        c.setKeyword(keyword);


        builder.setTitle("후..").setMessage(id+" "+password+" "+keyword);
        AlertDialog alertDialog1 = builder.create();
        alertDialog1.show();

        try{
            message=c.crawl();
            builder.setTitle("알림").setMessage(message);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }catch(Exception e){
            builder.setTitle("Error!").setMessage("잘못된 로그인 정보이거나 키워드입니다!");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}