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
import android.widget.ViewFlipper;

import com.example.openapp3.Forum.AllForum;
import com.example.openapp3.Messages.AllMessages;
import com.example.openapp3.DataBases.DataBaseHelper;
import com.example.openapp3.DataBases.DataBasePreference;
import com.example.openapp3.DataBases.DataBaseProfile;
import com.example.openapp3.Helper.HelperAboutMe;
import com.example.openapp3.MainActivity;
import com.example.openapp3.R;

import java.util.ArrayList;


public class Home extends AppCompatActivity implements View.OnClickListener{

    DataBaseProfile db;
    DataBasePreference dbP;
    DataBaseHelper dbH;

    ArrayList<String> data;
    ArrayList<String> eHelper;

    String value,Name, PrefG,PrefA,PrefL,HelpAboutMe,HelpAboutMe1,eHelper1,eHelper2;

    int myClickCount;

    ViewFlipper viewFlipper;
    Button yes,no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle bundle = getIntent().getExtras();
        value = bundle.getString("email");

        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);


        GetName();

    }

        public void GetName(){

            db = new DataBaseProfile(this);
            Cursor cursor = db.AllData(value);
            if (cursor.getCount()==0){
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }else {
                while(cursor.moveToNext()){
                    Name = cursor.getString(5).toString();
                    TextView displayTextView = (TextView) findViewById(R.id.myTexts);
                    displayTextView.setText("Welcome " + Name);
                    GetPref();
                }
            }
        }


        public void GetPref(){

            dbP = new DataBasePreference(this);
            Cursor cursor1 = dbP.AllData(value);
            if (cursor1.getCount()==0){
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }else {
                while(cursor1.moveToNext()){

                    PrefG = cursor1.getString(2).toString();
                    PrefA = cursor1.getString(3).toString();
                    PrefL = cursor1.getString(4).toString();


                }getHelpers();
            }
        }


public void getHelpers(){

    dbH = new DataBaseHelper(this);
    data = new ArrayList<>();
    eHelper = new ArrayList<>();

    Cursor cursor2 = dbH.AllData(PrefG,PrefA,PrefL);

    if (cursor2.getCount()==0){
        Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();

    }else {

        while(cursor2.moveToNext()) {
            eHelper.add(cursor2.getString(4));
            data.add(cursor2.getString(6));

        }
         HelpAboutMe = data.get(0);
         eHelper1 = eHelper.get(0);
         HelpAboutMe1 = data.get(1);
         eHelper2 = eHelper.get(1);


        TextView displayTextView3 = (TextView) findViewById(R.id.Page1);
        displayTextView3.setText(HelpAboutMe);
        TextView displayTextView4 = (TextView) findViewById(R.id.Page2);
        displayTextView4.setText(HelpAboutMe1);

        TextView displayTextView5 = (TextView) findViewById(R.id.Page3);
        displayTextView5.setText("Sorry No More People at the moment");

    }

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
            Intent i = new Intent(Home.this, Home.class);
            i.putExtra("email", value);
            startActivity(i);
        }

        if (id == R.id.item2) {
            Intent i = new Intent(Home.this, AllMessages.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item3) {
            Intent i = new Intent(Home.this, AllForum.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }




        if (id == R.id.item4) {
            Intent i = new Intent(Home.this, MainJornal.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item5) {
            Toast.makeText(getApplicationContext(), "Page In Maintence", Toast.LENGTH_SHORT).show();

        }

        if (id == R.id.item6) {
            Intent i = new Intent(Home.this, Settigns.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item7) {
            Intent i = new Intent(Home.this, Preferences.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item8) {
            Intent i = new Intent(Home.this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void countClick(){
        myClickCount++;
    }

    @Override
    public void onClick(View v) {
        if (v == yes) {

            if( myClickCount==0) {
                String SendAbout = HelpAboutMe;
                Intent i = new Intent(Home.this, HelperAboutMe.class);
                i.putExtra("AboutMe", SendAbout);
                i.putExtra("email", value);
                i.putExtra("Name", Name);
                i.putExtra("eHelper", eHelper1);
                startActivity(i);

            } else {
                String SendAbout1 = HelpAboutMe1;
                Intent i = new Intent(Home.this, HelperAboutMe.class);
                i.putExtra("AboutMe", SendAbout1);
                i.putExtra("Name", Name);
                i.putExtra("eHelper", eHelper2);
                startActivity(i);
            }

        } else if (v == no) {
            countClick();
            viewFlipper.showNext();

        }
    }

}