package io.github.RulLu16.alertApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick_login(View v){
        EditText inputId=findViewById(R.id.loginId);
        EditText inputPassword=findViewById(R.id.loginPassword);

        String id=inputId.getText().toString();
        String password=inputPassword.getText().toString();

        Intent intent1 = new Intent(getApplicationContext(), keywordPage.class);
        intent1.putExtra("input id", id);
        intent1.putExtra("input password", password);

        startActivity(intent1);
    }
}