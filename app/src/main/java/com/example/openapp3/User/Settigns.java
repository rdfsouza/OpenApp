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

import com.example.openapp3.DataBases.DataBaseProfile;
import com.example.openapp3.MainActivity;
import com.example.openapp3.R;

public class Settigns extends AppCompatActivity {


    DataBaseProfile db;
    Button b1;
    String value;

    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settigns);

        Bundle bundle = getIntent().getExtras();
        value = bundle.getString("email");



        db = new DataBaseProfile(this);
        Cursor cursor = db.AllData(value);
        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        }else {
            while(cursor.moveToNext()){

                Name = cursor.getString(5);
                String Age = cursor.getString(4);
                String Genre = cursor.getString(3);
                String AcountType = cursor.getString(2);
                String Education = cursor.getString(7);
                String Ocupation = cursor.getString(8);
                String Location = cursor.getString(9);
                String AboutMe = cursor.getString(6);


                TextView displayTextView = (TextView) findViewById(R.id.myTexts);
                TextView displayTextView1 = (TextView) findViewById(R.id.Age);
                TextView displayTextView2 = (TextView) findViewById(R.id.NewGender);
                TextView displayTextView3 = (TextView) findViewById(R.id.AcountUser);
                TextView displayTextView4 = (TextView) findViewById(R.id.Jornal1);
                TextView displayTextView5 = (TextView) findViewById(R.id.Date);
                TextView displayTextView6 = (TextView) findViewById(R.id.Location);
                TextView displayTextView7 = (TextView) findViewById(R.id.AboutMe);


                displayTextView.setText(Name);
                displayTextView1.setText("Gender: " + Genre);
                displayTextView2.setText("Age: " + Age);
                displayTextView3.setText("Account Type: " + AcountType);
                displayTextView4.setText("Occupation: " + Ocupation);
                displayTextView5.setText("Education: " + Education);
                displayTextView6.setText("Location: " + Location);
                displayTextView7.setText("About Me: " + AboutMe);
            }

        }

        b1 = (Button)findViewById(R.id.AddRecord);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Settigns.this,EditSettings.class);
                i.putExtra("email", value);
                i.putExtra("name", Name);
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
            Intent i = new Intent(Settigns.this, Home.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }


        if (id == R.id.item2) {
            Intent i = new Intent(Settigns.this, Home.class);
            i.putExtra("email", value);
            startActivity(i);
        }
        if (id == R.id.item3) {
            Intent i = new Intent(Settigns.this, Home.class);
            i.putExtra("email", value);
            startActivity(i);
        }


        if (id == R.id.item4) {
            Intent i = new Intent(Settigns.this, MainJornal.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item5) {
            Toast.makeText(getApplicationContext(), "Page In Maintenance", Toast.LENGTH_SHORT).show();

        }

        if (id == R.id.item6) {
            Toast.makeText(getApplicationContext(), "Already On this page", Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.item7) {
            Intent i = new Intent(Settigns.this, Preferences.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item8) {
            Intent i = new Intent(Settigns.this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
