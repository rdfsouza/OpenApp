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

import com.example.openapp3.DataBases.DataBaseProfile;
import com.example.openapp3.Forum.AllForum;
import com.example.openapp3.MainActivity;
import com.example.openapp3.Messages.AllMessages;
import com.example.openapp3.R;

public class EditSettings extends AppCompatActivity {

    EditText  e2, e3, e4, e5, e6,e7;
    Button b1;
    DataBaseProfile db;
    String value,Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_settings);

        Bundle bundle = getIntent().getExtras();
        value = bundle.getString("email");
        Bundle bundle2 = getIntent().getExtras();
        Name = bundle2.getString("name");

        db = new DataBaseProfile(this);

        TextView displayTextView = (TextView) findViewById(R.id.myTexts);
        displayTextView.setText(Name);


        b1 = (Button) findViewById(R.id.Update);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                e2 = (EditText) findViewById(R.id.NewGender);
                e3 = (EditText) findViewById(R.id.NewAge);
                e4 = (EditText) findViewById(R.id.NewLocation);
                e5 = (EditText) findViewById(R.id.NewOcupation);
                e6 = (EditText) findViewById(R.id.NewEducation);
                e7 = (EditText) findViewById(R.id.NewAboutMe);


                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                String s5 = e6.getText().toString();
                String s6 = e5.getText().toString();
                String s7 = e7.getText().toString();


        boolean update = db.Update(value,Name,s2,s3,s4,s5,s6,s7);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sandwich, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.item1) {
            Intent i = new Intent(EditSettings.this, Home.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item2) {
            Intent i = new Intent(EditSettings.this, AllMessages.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item3) {
            Intent i = new Intent(EditSettings.this, AllForum.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item4) {
            Intent i = new Intent(EditSettings.this, MainJornal.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item5) {
            Toast.makeText(getApplicationContext(), "Page In Maintence", Toast.LENGTH_SHORT).show();

        }

        if (id == R.id.item6) {
            Intent i = new Intent(EditSettings.this, Settigns.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item7) {
            Intent i = new Intent(EditSettings.this, Preferences.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item8) {
            Intent i = new Intent(EditSettings.this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
