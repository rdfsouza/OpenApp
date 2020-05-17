package com.example.openapp3.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.openapp3.DataBases.DataBaseProfile;
import com.example.openapp3.R;

public class EditSettings extends AppCompatActivity {

    EditText e1, e2, e3, e4, e5, e6,e7;
    Button b1;
    DataBaseProfile db;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_settings);

        Bundle bundle = getIntent().getExtras();
        value = bundle.getString("email");

        db = new DataBaseProfile(this);

        b1 = (Button) findViewById(R.id.Update);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                e1 = (EditText) findViewById(R.id.NewName);
                e2 = (EditText) findViewById(R.id.NewGender);
                e3 = (EditText) findViewById(R.id.NewAge);
                e4 = (EditText) findViewById(R.id.NewLocation);
                e5 = (EditText) findViewById(R.id.NewOcupation);
                e6 = (EditText) findViewById(R.id.NewEducation);
                e7 = (EditText) findViewById(R.id.NewAboutMe);

                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                String s5 = e6.getText().toString();
                String s6 = e5.getText().toString();
                String s7 = e7.getText().toString();


        boolean update = db.Update(value,s1,s2,s3,s4,s5,s6,s7);
                if (update==true){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent j = new Intent(EditSettings.this,Settigns.class);
                    j.putExtra("email",value);
                    startActivity(j);

            }else {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();}
            }
        });




    }

}
