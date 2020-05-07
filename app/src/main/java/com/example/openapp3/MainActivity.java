package com.example.openapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.openapp3.DataBases.DataBaseHelper;
import com.example.openapp3.DataBases.DataBaseProfile;
import com.example.openapp3.Helper.HomeHelper;
import com.example.openapp3.Helper.SettingsHelper;
import com.example.openapp3.User.Home;

public class MainActivity extends AppCompatActivity {

    DataBaseProfile db;
    DataBaseHelper dbh;
    EditText e1,e2;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataBaseProfile(this);
        dbh = new DataBaseHelper(this);

        e1 = (EditText)findViewById(R.id.Email);
        e2 = (EditText)findViewById(R.id.Password);
        b1 = (Button)findViewById(R.id.Login);
        b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String Email = e1.getText().toString();
            String Password = e2.getText().toString();

            Boolean checkemailPass = db.emailPass(Email,Password);
            Boolean checkemailPass2 = dbh.emailPass(Email,Password);
            if (checkemailPass==true){

                Intent j = new Intent(MainActivity.this, Home.class);
                j.putExtra("email",Email);
                startActivity(j);

            }if(checkemailPass2==true){

                Intent j = new Intent(MainActivity.this, SettingsHelper.class);
                j.putExtra("email",Email);
                startActivity(j);

            }else {
               // Toast.makeText(getApplicationContext(), "Wrong Email or Password", Toast.LENGTH_SHORT).show();
            }


        }
    });


        b2 = (Button)findViewById(R.id.Register);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,Register.class);
                startActivity(i);
            }
        });





        // viewFlipper.setFlipInterval(200);
        // viewFlipper.setAutoStart(true);



    }
}
