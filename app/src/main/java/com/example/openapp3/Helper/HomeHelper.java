package com.example.openapp3.Helper;

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

import com.example.openapp3.AllForumHelper;
import com.example.openapp3.AllMessagesHelper;
import com.example.openapp3.DataBases.DataBaseHelper;
import com.example.openapp3.DataBases.DataBaseProfile;
import com.example.openapp3.Forum.AllForum;
import com.example.openapp3.MainActivity;
import com.example.openapp3.Messages.AllMessages;
import com.example.openapp3.R;
import com.example.openapp3.SettingsHelper;
import com.example.openapp3.User.Home;
import com.example.openapp3.User.MainJornal;
import com.example.openapp3.User.Preferences;
import com.example.openapp3.User.Settigns;

public class HomeHelper extends AppCompatActivity {

    Button b1;
    String value,Name;

    DataBaseHelper dbH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_helper);

        Bundle bundle = getIntent().getExtras();
       value = bundle.getString("email");


        b1 = (Button)findViewById(R.id.MesHelper);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent j = new Intent(HomeHelper.this, ChatHelper.class);
                j.putExtra("email",value);
                j.putExtra("Name",Name);
                startActivity(j);
            }
        });
        GetName();
    }


    public void GetName(){

        dbH = new DataBaseHelper(this);
        Cursor cursor = dbH.AllData2(value);
        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        }else {
            while(cursor.moveToNext()){
                Name = cursor.getString(5).toString();
                TextView displayTextView = (TextView) findViewById(R.id.myTexts);
                displayTextView.setText("Welcome " + Name);

            }
        }
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
            Intent i = new Intent(HomeHelper.this, HomeHelper.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item2) {
            Intent i = new Intent(HomeHelper.this, AllMessagesHelper.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item3) {
            Intent i = new Intent(HomeHelper.this, AllForumHelper.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item6) {
            Intent i = new Intent(HomeHelper.this, SettingsHelper.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }


        if (id == R.id.item8) {
            Intent i = new Intent(HomeHelper.this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}

