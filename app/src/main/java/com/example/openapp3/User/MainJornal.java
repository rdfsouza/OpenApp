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

import com.example.openapp3.DataBases.DataBaseJornal;
import com.example.openapp3.Forum.AllForum;
import com.example.openapp3.MainActivity;
import com.example.openapp3.Messages.AllMessages;
import com.example.openapp3.R;

public class MainJornal extends AppCompatActivity {


    DataBaseJornal db;
    Button b1;
    String value;
    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_jornal);

        Bundle bundle = getIntent().getExtras();
        value = bundle.getString("email");
        Name = bundle.getString("name");

        TextView displayTextView2 = (TextView) findViewById(R.id.myTexts);
        displayTextView2.setText("Name: " + Name);

        db = new DataBaseJornal(this);

        Cursor cursor = db.AllData(value);
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();

            Boolean insert = db.insert(value, " ", " ", Name);
        } else {
            while (cursor.moveToNext()) {

                String Jornal = cursor.getString(1);
                String date = cursor.getString(2);


                TextView displayTextView = (TextView) findViewById(R.id.Jornal1);
                TextView displayTextView1 = (TextView) findViewById(R.id.Date);

                displayTextView.setText("Last Jornal: " + Jornal);
                displayTextView1.setText("Date: " + date);


            }

        }

        b1 = (Button) findViewById(R.id.AddRecord);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainJornal.this, Jornal.class);
                i.putExtra("name", Name);
                i.putExtra("email", value);
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
            Intent i = new Intent(MainJornal.this, Home.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }


        if (id == R.id.item2) {
            Intent i = new Intent(MainJornal.this, AllMessages.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }
        if (id == R.id.item3) {
            Intent i = new Intent(MainJornal.this, AllForum.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }


        if (id == R.id.item4) {
            Intent i = new Intent(MainJornal.this, MainJornal.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item5) {
            Toast.makeText(getApplicationContext(), "Page In Maintence", Toast.LENGTH_SHORT).show();

        }

        if (id == R.id.item6) {
            Intent i = new Intent(MainJornal.this, Settigns.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item7) {
            Intent i = new Intent(MainJornal.this, Preferences.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item8) {
            Intent i = new Intent(MainJornal.this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}