package com.example.openapp3.Helper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.openapp3.R;

public class HelperAboutMe extends AppCompatActivity {


    String AboutMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_about_me);


        Bundle bundle = getIntent().getExtras();
        AboutMe = bundle.getString("AboutMe");


        TextView displayTextView = (TextView) findViewById(R.id.myTexts);
        displayTextView.setText("Hey there this is a little about me drop me a text if you need. " + AboutMe);

    }
}
