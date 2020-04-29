package com.example.openapp3.Helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.openapp3.Chats.ChatUser;
import com.example.openapp3.R;

public class HelperAboutMe extends AppCompatActivity {


    String AboutMe;
    Button b1;
    String value;
    String eHelper;
    String Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_about_me);

        Bundle bundle1 = getIntent().getExtras();
        value = bundle1.getString("email");

        Bundle bundle = getIntent().getExtras();
        AboutMe = bundle.getString("AboutMe");

        Bundle bundle3 = getIntent().getExtras();
        eHelper= bundle.getString("eHelper");

        Bundle bundle4 = getIntent().getExtras();
        Name= bundle.getString("Name");




        TextView displayTextView = (TextView) findViewById(R.id.myTexts);
        displayTextView.setText("Hey there this is a little about me drop me a text if you need. " + AboutMe);

        b1 = findViewById(R.id.SayHello);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HelperAboutMe.this, ChatUser.class);
                i.putExtra("email", value);
                i.putExtra("eHelper", eHelper);
                i.putExtra("Name", Name);
                startActivity(i);
            }
        });

    }
}
