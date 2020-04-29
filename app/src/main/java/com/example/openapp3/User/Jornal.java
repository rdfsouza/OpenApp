package com.example.openapp3.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.openapp3.DataBases.DataBaseJornal;
import com.example.openapp3.R;

public class Jornal extends AppCompatActivity {

    EditText e1, e2;
    Button b1;
    DataBaseJornal db;
    String value;
    String Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jornal);


        Bundle bundle = getIntent().getExtras();
        value = bundle.getString("email");
        Name = bundle.getString("name");

        db = new DataBaseJornal(this);

        b1 = (Button) findViewById(R.id.NewJornal);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                e1 = (EditText) findViewById(R.id.NJornal);
                e2 = (EditText) findViewById(R.id.NDate);

                TextView displayTextView2 = (TextView) findViewById(R.id.myTexts);
                displayTextView2.setText("Name:" + Name);


                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();

                boolean update = db.Update(value,s1,s2,Name);
                if (update==true){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent j = new Intent(Jornal.this,MainJornal.class);
                    j.putExtra("email",value);
                    startActivity(j);

                }else {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();}
            }
        });




    }

}