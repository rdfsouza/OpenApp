package com.example.openapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.openapp3.DataBases.DataBaseHelper;
import com.example.openapp3.DataBases.DataBaseProfile;
import com.example.openapp3.User.EditSettings;
import com.example.openapp3.User.Settigns;

public class editSettingsHelper extends AppCompatActivity {


    EditText e1, e2, e3, e4, e5, e6,e7;
    Button b1;
    DataBaseHelper db;
    String value,name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_setings_helper);

        Bundle bundle = getIntent().getExtras();
        value = bundle.getString("email");

        Bundle bundle2 = getIntent().getExtras();
        name = bundle2.getString("name");

        TextView displayTextView = (TextView) findViewById(R.id.myTexts);
        displayTextView.setText(name);
        db = new DataBaseHelper(this);

        b1 = (Button) findViewById(R.id.Update);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                e2 = (EditText) findViewById(R.id.NewGender);
                e3 = (EditText) findViewById(R.id.NewAge);
                e4 = (EditText) findViewById(R.id.NewLocation);
                e7 = (EditText) findViewById(R.id.NewAboutMe);


                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                String s5 = e7.getText().toString();


                boolean update = db.Update(value,s2,s3,s4,name,s5);
                if (update==true){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent j = new Intent(editSettingsHelper.this, SettingsHelper.class);
                    j.putExtra("email",value);
                    startActivity(j);

                }else {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();}
            }
        });




    }


}
