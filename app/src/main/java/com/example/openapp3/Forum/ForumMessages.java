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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.openapp3.Chats.ChatAdapter;
import com.example.openapp3.Chats.ChatsStyle;
import com.example.openapp3.DataBases.DataBaseForum;
import com.example.openapp3.MainActivity;
import com.example.openapp3.Messages.AllMessages;
import com.example.openapp3.R;
import com.example.openapp3.User.Home;
import com.example.openapp3.User.MainJornal;
import com.example.openapp3.User.Preferences;
import com.example.openapp3.User.Settigns;

import java.util.ArrayList;
import java.util.Calendar;

public class ForumMessages extends AppCompatActivity {

    RecyclerView RV;
    RecyclerView.Adapter RVA;
    RecyclerView.LayoutManager RVM;

    String value;
    String Type;
    EditText e1;
    DataBaseForum db;
    String time;
    String Name;

    ArrayList<ChatsStyle> CS = new ArrayList<>();

    ImageButton b1;

    Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_messages);
        db= new DataBaseForum(this);

        Bundle bundle1 = getIntent().getExtras();
        value = bundle1.getString("email");

        Bundle bundle2 = getIntent().getExtras();
        Name = bundle1.getString("Name");

        Bundle bundle = getIntent().getExtras();
        Type = bundle.getString("Type");

        e1 = (EditText) findViewById(R.id.ChatText);
        b1 =(ImageButton) findViewById(R.id.SendChat);


        int a = calendar.get(Calendar.AM_PM);
        if(a == Calendar.AM) {
            time = calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE)+"AM";
        }
        else {
            time = calendar.get(Calendar.HOUR)+ ":"+calendar.get(Calendar.MINUTE)+"PM";
        }

        getMessages();

    }

    public void getMessages(){


        Cursor cursor = db.AllTopic(Type);

        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();

        }else {

            while(cursor.moveToNext()) {

                ArrayList<String> NameU = new ArrayList<>();
                NameU.add(cursor.getString(1));

                ArrayList<String> TimeU = new ArrayList<>();
                TimeU.add(cursor.getString(2));

                ArrayList<String> ChatU = new ArrayList<>();
                ChatU.add(cursor.getString(3));

                ArrayList<String> Type = new ArrayList<>();
                Type.add(cursor.getString(4));

                CS.add(new ChatsStyle(R.drawable.ic_photo, ChatU.get(0), TimeU.get(0), NameU.get(0)));



                RV = findViewById(R.id.recyclerView);
                RV.setHasFixedSize(true);
                RVM = new LinearLayoutManager(this);
                RVA = new ChatAdapter(CS);

                RV.setLayoutManager(RVM);
                RV.setAdapter(RVA);
            }





        }
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String Chat = e1.getText().toString();
                Boolean insert = db.insert(Name, time, Chat, Type);
                Intent i = new Intent(ForumMessages.this, ForumMessages.class);
                i.putExtra("Name", Name);
                i.putExtra("Type", Type);
                i.putExtra("email",value);
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
            Intent i = new Intent(ForumMessages.this, Home.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item2) {
            Intent i = new Intent(ForumMessages.this, AllMessages.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item3) {
            Intent i = new Intent(ForumMessages.this, AllForum.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item4) {
            Intent i = new Intent(ForumMessages.this, MainJornal.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item5) {
            Toast.makeText(getApplicationContext(), "Page In Maintence", Toast.LENGTH_SHORT).show();

        }

        if (id == R.id.item6) {
            Intent i = new Intent(ForumMessages.this, Settigns.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item7) {
            Intent i = new Intent(ForumMessages.this, Preferences.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item8) {
            Intent i = new Intent(ForumMessages.this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
