package com.example.openapp3.Helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.openapp3.DataBases.DataBaseHelper;
import com.example.openapp3.DataBases.DataBaseProfile;
import com.example.openapp3.MainActivity;
import com.example.openapp3.R;
import com.example.openapp3.User.Home;

public class HomeHelper extends AppCompatActivity {

    Button b1;
    String Value,Name;

    DataBaseHelper dbH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_helper);

        Bundle bundle = getIntent().getExtras();
       Value = bundle.getString("email");


        b1 = (Button)findViewById(R.id.MesHelper);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent j = new Intent(HomeHelper.this, ChatHelper.class);
                j.putExtra("email",Value);
                j.putExtra("Name",Name);
                startActivity(j);
            }
        });
        GetName();
    }


    public void GetName(){

        dbH = new DataBaseHelper(this);
        Cursor cursor = dbH.AllData2(Value);
        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        }else {
            while(cursor.moveToNext()){
                Name = cursor.getString(4).toString();
                TextView displayTextView = (TextView) findViewById(R.id.myTexts);
                displayTextView.setText("Welcome " + Name);

            }
        }
    }}
