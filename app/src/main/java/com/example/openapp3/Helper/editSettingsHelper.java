package com.example.openapp3.Helper;

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

import com.example.openapp3.DataBases.DataBaseHelper;
import com.example.openapp3.MainActivity;
import com.example.openapp3.R;

public class editSettingsHelper extends AppCompatActivity {


    EditText e1, e2, e3, e4, e5, e6,e7;
    Button b1;
    DataBaseHelper db;
    String value,Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_setings_helper);

        Bundle bundle = getIntent().getExtras();
        value = bundle.getString("email");

        Bundle bundle2 = getIntent().getExtras();
        Name = bundle2.getString("name");

        TextView displayTextView = (TextView) findViewById(R.id.myTexts);
        displayTextView.setText(Name);
        db = new DataBaseHelper(this);

        b1 = (Button) findViewById(R.id.Update);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                e2 = (EditText) findViewById(R.id.NewGender);
                e3 = (EditText) findViewById(R.id.NewAge);
                e4 = (EditText) findViewById(R.id.NewLocation);
                e7 = (EditText) findViewById(R.id.NewAboutMe);
                e1 =(EditText) findViewById(R.id.NewOcupation);;
                e5 =(EditText) findViewById(R.id.NewEducation);;


                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                String s5 = e7.getText().toString();
                String s6 = e1.getText().toString();
                String s7 = e5.getText().toString();


                boolean update = db.Update(value,s2,s3,s4,Name,s5,s6,s7);
                if (update==true){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent j = new Intent(editSettingsHelper.this, SettingsHelper.class);
                    j.putExtra("email",value);
                    startActivity(j);

                }else {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();}
            }
        });




    } @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.helper, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.item1) {
            Intent i = new Intent(editSettingsHelper.this, SettingsHelper.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item2) {
            Intent i = new Intent(editSettingsHelper.this, AllMessagesHelper.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item3) {
            Intent i = new Intent(editSettingsHelper.this, AllForumHelper.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }


        if (id == R.id.item8) {
            Intent i = new Intent(editSettingsHelper.this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


}
