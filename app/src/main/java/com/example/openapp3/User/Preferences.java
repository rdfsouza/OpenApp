package com.example.openapp3.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.openapp3.DataBases.DataBasePreference;
import com.example.openapp3.MainActivity;
import com.example.openapp3.R;

public class Preferences extends AppCompatActivity {

    DataBasePreference db;
    Button b1;
    String value;
    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        Bundle bundle = getIntent().getExtras();
        value = bundle.getString("email");
        Name = bundle.getString("name");

        db = new DataBasePreference(this);




        Cursor cursor = db.AllData(value);
        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            Boolean insert = db.insert(value, Name, " ", " ", " ","");
        }else {
            while(cursor.moveToNext()){

                String Gender = cursor.getString(2);
                String Age = cursor.getString(3);
                String Location = cursor.getString(4);
                String Interest = cursor.getString(5);



                TextView displayTextView = (TextView) findViewById(R.id.GenrePref);
                TextView displayTextView1 = (TextView) findViewById(R.id.AgePref);
                TextView displayTextView2 = (TextView) findViewById(R.id.LocationPref);
                TextView displayTextView3 = (TextView) findViewById(R.id.InterestPref);



                displayTextView.setText("Gender: " + Gender);
                displayTextView1.setText("Age: " + Age);
                displayTextView2.setText("Location: " + Location);
                displayTextView3.setText("Interest: " + Interest);




        }

        }

        TextView displayTextView4 = (TextView) findViewById(R.id.myTexts);
        displayTextView4.setText("Name: " + Name);

        b1 = (Button)findViewById(R.id.UpdatePref);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Preferences.this,EditPreferences.class);
                i.putExtra("name", Name);
                i.putExtra("email",value);
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
            Intent i = new Intent(Preferences.this, Home.class);
            i.putExtra("email", value);
            startActivity(i);
        }


        if (id == R.id.item2) {
            Intent i = new Intent(Preferences.this, Home.class);
            i.putExtra("email", value);
            startActivity(i);
        }
        if (id == R.id.item3) {
            Intent i = new Intent(Preferences.this, Home.class);
            i.putExtra("email", value);
            startActivity(i);
        }


        if (id == R.id.item4) {
            Intent i = new Intent(Preferences.this, MainJornal.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item5) {
            Toast.makeText(getApplicationContext(), "Page In Maintence", Toast.LENGTH_SHORT).show();

        }

        if (id == R.id.item6) {
            Intent i = new Intent(Preferences.this, Settigns.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item7) {
            Toast.makeText(getApplicationContext(), "Already On this page", Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.item8) {
            Intent i = new Intent(Preferences.this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }



}
