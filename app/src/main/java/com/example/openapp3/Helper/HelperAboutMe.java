package com.example.openapp3.Helper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.openapp3.Chats.ChatUser;
import com.example.openapp3.Forum.AllForum;
import com.example.openapp3.MainActivity;
import com.example.openapp3.Messages.AllMessages;
import com.example.openapp3.R;
import com.example.openapp3.User.Home;
import com.example.openapp3.User.MainJornal;
import com.example.openapp3.User.Preferences;
import com.example.openapp3.User.Settigns;

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

        TextView displayTextView1 = (TextView) findViewById(R.id.myTextsName);
        displayTextView1.setText(eHelper);



        TextView displayTextView = (TextView) findViewById(R.id.myTexts);
        displayTextView.setText("Hey there, here is a little bit about myself, drop me a message if you need to talk: \n" + AboutMe);



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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sandwich, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.item1) {
            Intent i = new Intent(HelperAboutMe.this, Home.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item2) {
            Intent i = new Intent(HelperAboutMe.this, AllMessages.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item3) {
            Intent i = new Intent(HelperAboutMe.this, AllForum.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item4) {
            Intent i = new Intent(HelperAboutMe.this, MainJornal.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item5) {
            Toast.makeText(getApplicationContext(), "Page In Maintence", Toast.LENGTH_SHORT).show();

        }

        if (id == R.id.item6) {
            Intent i = new Intent(HelperAboutMe.this, Settigns.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item7) {
            Intent i = new Intent(HelperAboutMe.this, Preferences.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item8) {
            Intent i = new Intent(HelperAboutMe.this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


}
