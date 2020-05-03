package com.example.openapp3;

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

import com.example.openapp3.DataBases.DataBaseHelper;
import com.example.openapp3.Forum.AllForum;
import com.example.openapp3.Helper.HomeHelper;
import com.example.openapp3.Messages.AllMessages;
import com.example.openapp3.User.EditSettings;
import com.example.openapp3.User.Home;
import com.example.openapp3.User.MainJornal;
import com.example.openapp3.User.Preferences;


public class SettingsHelper extends AppCompatActivity {


    DataBaseHelper db;
    Button b1;
    String value;

    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_helper);

        Bundle bundle = getIntent().getExtras();
        value = bundle.getString("email");


        db = new DataBaseHelper(this);

        Cursor cursor = db.AllData2(value);
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {

                Name = cursor.getString(5);
                String Age = cursor.getString(3);
                String Genre = cursor.getString(2);
                String Location = cursor.getString(4);
                String AboutMe = cursor.getString(6);


                TextView displayTextView = (TextView) findViewById(R.id.myTexts);
                TextView displayTextView1 = (TextView) findViewById(R.id.Age);
                TextView displayTextView2 = (TextView) findViewById(R.id.NewGender);
                TextView displayTextView6 = (TextView) findViewById(R.id.Location);
                TextView displayTextView7 = (TextView) findViewById(R.id.AboutMe);


                displayTextView.setText("Name: " + Name);
                displayTextView1.setText("Genre: " + Genre);
                displayTextView2.setText("Age: " + Age);
                displayTextView6.setText("Location: " + Location);
                displayTextView7.setText("AboutMe: " + AboutMe);
            }

        }

        b1 = (Button) findViewById(R.id.AddRecord);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SettingsHelper.this, editSettingsHelper.class);
                i.putExtra("email", value);
                i.putExtra("name", Name);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.helper, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.item1) {
            Intent i = new Intent(SettingsHelper.this, HomeHelper.class);
            i.putExtra("email", value);
            startActivity(i);
        }

        if (id == R.id.item2) {
            Intent i = new Intent(SettingsHelper.this, AllMessagesHelper.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item3) {
            Intent i = new Intent(SettingsHelper.this, AllForumHelper.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item6) {
            Intent i = new Intent(SettingsHelper.this, SettingsHelper.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }


        if (id == R.id.item8) {
            Intent i = new Intent(SettingsHelper.this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
