package com.example.openapp3.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.openapp3.DataBases.DataBaseProfile;
import com.example.openapp3.MainActivity;
import com.example.openapp3.R;

public class RegisterUser extends AppCompatActivity {

   DataBaseProfile db;

    EditText e1, e2, e3, e4, e5, e6;
    Button b1, b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        db= new DataBaseProfile(this);


        e1 = (EditText) findViewById(R.id.Email);
        e2 = (EditText) findViewById(R.id.Pass);
        e3 = (EditText) findViewById(R.id.Cpass);
        e4 = (EditText) findViewById(R.id.NewGender);
        e5 = (EditText) findViewById(R.id.Age);
        e6 = (EditText) findViewById(R.id.Name);

        b2 = (Button) findViewById(R.id.Login);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(RegisterUser.this, MainActivity.class);
                startActivity(i);
            }
        });

        b1 = (Button) findViewById(R.id.Register);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String User = "USER";
                String s4 = e4.getText().toString();
                String s5 = e5.getText().toString();
                String s6 = e6.getText().toString();
                String s7 = "About Me: ";
                String s8 = "";
                String s9 = "";
                String s10 = "";

                if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("") || s6.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (s2.equals(s3)) {

                        Boolean insert = db.insert(s1, s2, User, s4, s5, s6, s7,s8,s9,s10);
                        Toast.makeText(getApplicationContext(), "Registrated Sucessfully", Toast.LENGTH_SHORT).show();
                        Boolean checkemailPass = db.emailPass(s1,s2);

                        if (checkemailPass==true){

                            Intent j = new Intent(RegisterUser.this, Home.class);
                            j.putExtra("email",s1);
                            startActivity(j);

                        }

                    } else {

                        Toast.makeText(getApplicationContext(), "Password dont match", Toast.LENGTH_SHORT).show();


                    }
                }
            }
        });


    }
}