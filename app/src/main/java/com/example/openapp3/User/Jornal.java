package com.example.openapp3.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.openapp3.DataBases.DataBaseJornal;
import com.example.openapp3.Forum.AllForum;
import com.example.openapp3.MainActivity;
import com.example.openapp3.Messages.AllMessages;
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
                displayTextView2.setText(Name);


                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();

                boolean update = db.Update(value,s1,s2,Name);
                if (update==true){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent j = new Intent(Jornal.this,MainJornal.class);
                    j.putExtra("name", Name);
                    j.putExtra("email", value);
                    startActivity(j);

                }else {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();}
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
            Intent i = new Intent(Jornal.this, Home.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item2) {
            Intent i = new Intent(Jornal.this, AllMessages.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item3) {
            Intent i = new Intent(Jornal.this, AllForum.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item4) {
            Intent i = new Intent(Jornal.this, MainJornal.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item5) {
            Toast.makeText(getApplicationContext(), "Page In Maintence", Toast.LENGTH_SHORT).show();

        }

        if (id == R.id.item6) {
            Intent i = new Intent(Jornal.this, Settigns.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item7) {
            Intent i = new Intent(Jornal.this, Preferences.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item8) {
            Intent i = new Intent(Jornal.this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }



}