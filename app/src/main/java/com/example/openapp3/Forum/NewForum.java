package com.example.openapp3.Forum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.openapp3.DataBases.DataBaseForum;
import com.example.openapp3.R;

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
                String s3 = "Forum Created To talk about " + e1.getText().toString();
                String s4 = e1.getText().toString();


                if (s4.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields is empty", Toast.LENGTH_SHORT).show();
                } else {

                        Boolean insert = db.insert(s1, s2, s3, s4);

                        Toast.makeText(getApplicationContext(), "Registrated Sucessfully", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(NewForum.this, AllForum.class);
                    i.putExtra("Name", Name);
                    i.putExtra("email", value);
                    startActivity(i);

                }
            }
        });


    }
}
