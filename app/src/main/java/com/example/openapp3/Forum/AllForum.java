package com.example.openapp3.Forum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.openapp3.DataBases.DataBaseForum;
import com.example.openapp3.MainActivity;
import com.example.openapp3.Messages.AllMessages;
import com.example.openapp3.R;
import com.example.openapp3.User.Home;
import com.example.openapp3.User.MainJornal;
import com.example.openapp3.User.Preferences;
import com.example.openapp3.User.Settigns;

import java.util.ArrayList;

public class AllForum extends AppCompatActivity {
    RecyclerView RV;
    AllForumAdapter RVA;
    RecyclerView.LayoutManager RVM;

    Button b1;
    String value;
    String Name;
    String Type;


    DataBaseForum db;


    ArrayList<AllForumStyle> AF = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_forum);

        db= new DataBaseForum(this);

        Bundle bundle1 = getIntent().getExtras();
        value = bundle1.getString("email");

        Bundle bundle2 = getIntent().getExtras();
        Name = bundle1.getString("Name");


        b1=(Button)findViewById(R.id.NewForum);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(AllForum.this, NewForum.class);
                i.putExtra("Name", Name);
                i.putExtra("email", value);
                startActivity(i);
            }
        });

        getMessages();
    }

    public void click(int position){
        Type = AF.get(position).getText3().toString();
    }



    public void getMessages() {
        Toast.makeText(getApplicationContext(), Type, Toast.LENGTH_SHORT).show();

        Cursor cursor = db. AllData();

        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();

        } else {

            while (cursor.moveToNext()) {

                ArrayList<String> NameU = new ArrayList<>();
                NameU.add(cursor.getString(1));

                ArrayList<String> TimeU = new ArrayList<>();
                TimeU.add(cursor.getString(2));

                ArrayList<String> ChatU = new ArrayList<>();
                ChatU.add(cursor.getString(3));

                ArrayList<String> Type = new ArrayList<>();
                Type.add(cursor.getString(4));

                AF.add(new AllForumStyle( Type.get(0)));

            }


            RV = findViewById(R.id.recyclerView);
            RV.setHasFixedSize(true);
            RVM = new LinearLayoutManager(this);
            RVA = new AllForumAdapter(AF);

            RV.setLayoutManager(RVM);
            RV.setAdapter(RVA);

            RVA.setOnItemClickListener(new AllForumAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    click(position);

                    Intent i = new Intent(AllForum.this, ForumMessages.class);
                    i.putExtra("Name", Name);
                    i.putExtra("email", value);
                    i.putExtra("Type", Type);
                    startActivity(i);

                }
            });

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
            Intent i = new Intent(AllForum.this, Home.class);
            i.putExtra("email", value);
            startActivity(i);
        }

        if (id == R.id.item2) {
            Intent i = new Intent(AllForum.this, AllMessages.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item3) {
            Intent i = new Intent(AllForum.this, AllForum.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }




        if (id == R.id.item4) {
            Intent i = new Intent(AllForum.this, MainJornal.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item5) {
            Toast.makeText(getApplicationContext(), "Page In Maintence", Toast.LENGTH_SHORT).show();

        }

        if (id == R.id.item6) {
            Intent i = new Intent(AllForum.this, Settigns.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item7) {
            Intent i = new Intent(AllForum.this, Preferences.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item8) {
            Intent i = new Intent(AllForum.this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}