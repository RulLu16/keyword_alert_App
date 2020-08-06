package io.github.RulLu16.alertApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class crawlPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crawl_page);
        executeCrawl startCrawl=new executeCrawl();
        startCrawl.execute();
    }

    private class executeCrawl extends AsyncTask<Void, Void, Void>{
        String str="아직 글이 없어요!";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Intent intent2=getIntent();
            String id=intent2.getStringExtra("input id");
            String password=intent2.getStringExtra("input password");
            String keyword=intent2.getStringExtra("input keyword");

            try{
                String url="http://www.ssodam.com/";

                //System.out.println(ID+" "+password+" "+keyword);
                Connection.Response initial= Jsoup.connect(url)
                        .method(Connection.Method.GET)
                        .execute();
                Document key=initial.parse();
                String csrf=initial.cookies().get("csrftoken");

                Connection.Response login=Jsoup.connect("http://www.ssodam.com/auth")
                        .cookies(initial.cookies())
                        .data("id",id, "password", password,"auto","false","csrfmiddlewaretoken",csrf)
                        .method(Connection.Method.POST)
                        .timeout(5000)
                        .execute();

                Document doc=Jsoup.connect("http://www.ssodam.com/board/11/1")
                        .cookies(login.cookies())
                        .timeout(3000000).get();

                Elements element = doc.select("div.post-element.table-post.visible-xs");

                for(Element el : element.select("td.title.title-align")) {
                    if(el.text().contains(keyword)==true){
                        str="새로운 글이 올라왔어요!\n"+el.text();
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            TextView result=findViewById(R.id.crawlingResult);
            result.setText(str);
        }
    }
}