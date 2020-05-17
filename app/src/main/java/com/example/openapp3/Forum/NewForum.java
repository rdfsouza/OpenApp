package com.example.openapp3.Forum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.openapp3.DataBases.DataBaseForum;
import com.example.openapp3.MainActivity;
import com.example.openapp3.Messages.AllMessages;
import com.example.openapp3.R;
import com.example.openapp3.User.Home;
import com.example.openapp3.User.MainJornal;
import com.example.openapp3.User.Preferences;
import com.example.openapp3.User.Settigns;

import java.util.Date;

public class NewForum extends AppCompatActivity {


    EditText e1;
    Button b1;
    String value;
    String Name;
    Date date = new Date();
    String day;
    DataBaseForum db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_forum);

        Bundle bundle1 = getIntent().getExtras();
        value = bundle1.getString("email");

        Bundle bundle2 = getIntent().getExtras();
        Name = bundle1.getString("Name");

        db= new DataBaseForum(this);

        day = date.toString();


        e1 = (EditText) findViewById(R.id.Topic);

        b1 = (Button) findViewById(R.id.New_Topic);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = "";
                String s2 = "Created  "+day;
                String s3 = "Forum About " + e1.getText().toString();
                String s4 = e1.getText().toString();


                if (s4.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields is empty", Toast.LENGTH_SHORT).show();
                } else {

                        Boolean insert = db.insert(s1, s2, s3, s4);

                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(NewForum.this, AllForum.class);
                    i.putExtra("Name", Name);
                    i.putExtra("email", value);
                    startActivity(i);

                }
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
            Intent i = new Intent(NewForum.this, Home.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item2) {
            Intent i = new Intent(NewForum.this, AllMessages.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item3) {
            Intent i = new Intent(NewForum.this, AllForum.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item4) {
            Intent i = new Intent(NewForum.this, MainJornal.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item5) {
            Toast.makeText(getApplicationContext(), "Page In Maintence", Toast.LENGTH_SHORT).show();

        }

        if (id == R.id.item6) {
            Intent i = new Intent(NewForum.this, Settigns.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item7) {
            Intent i = new Intent(NewForum.this, Preferences.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item8) {
            Intent i = new Intent(NewForum.this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
