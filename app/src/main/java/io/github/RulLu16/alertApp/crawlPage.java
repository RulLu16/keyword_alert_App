package io.github.RulLu16.alertApp;

import android.app.AlertDialog;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class crawlPage {
    String ID;
    String password;
    String keyword;

    public crawlPage() {
    }

    public void setID(String Id){
        this.ID=Id;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setKeyword(String keyword){
        this.keyword=keyword;
    }

    public String crawl() throws Exception{
        String url="http://www.ssodam.com/";

        System.out.println(ID+" "+password+" "+keyword);
        Connection.Response initial= Jsoup.connect(url)
                .method(Connection.Method.GET)
                .execute();
        Document key=initial.parse();
        String csrf=initial.cookies().get("csrftoken");
        //System.out.println(csrf);

        Connection.Response login=Jsoup.connect("http://www.ssodam.com/auth")
                .cookies(initial.cookies())
                .data("id",ID, "password", password,"auto","false","csrfmiddlewaretoken",csrf)
                .method(Connection.Method.POST)
                .timeout(5000)
                .execute();

        Document doc=Jsoup.connect("http://www.ssodam.com/board/11/1")
                .cookies(login.cookies())
                .timeout(3000000).get();
        //System.out.println(doc);

        Elements element = doc.select("div.post-element.table-post.visible-xs");

        for(Element el : element.select("td.title.title-align")) {
            if(el.text().contains(keyword)==true){
                return "새로운 글이 올라왔어요!";
            }
        }

        return "아직 새로운 글이 없어요!";
    }
}
