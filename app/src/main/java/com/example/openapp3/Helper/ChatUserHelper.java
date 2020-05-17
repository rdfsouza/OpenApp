package com.example.openapp3.Helper;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.openapp3.Chats.ChatAdapter;
import com.example.openapp3.Chats.ChatsStyle;
import com.example.openapp3.DataBases.DataBaseMessages;
import com.example.openapp3.MainActivity;
import com.example.openapp3.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ChatUserHelper extends AppCompatActivity {
    RecyclerView RV;
    RecyclerView.Adapter RVA;
    RecyclerView.LayoutManager RVM;


    String value;
    String eHelper;
    EditText e1;
    DataBaseMessages db;
    String time;
    String Name;

    ArrayList<ChatsStyle> CS = new ArrayList<>();

    ImageButton b1;

    Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_user_helper);
        db= new DataBaseMessages(this);

        Bundle bundle1 = getIntent().getExtras();
        value = bundle1.getString("email");

        Bundle bundle2 = getIntent().getExtras();
        Name = bundle1.getString("Name");

        Bundle bundle = getIntent().getExtras();
        eHelper = bundle.getString("eHelper");

        e1 = (EditText) findViewById(R.id.ChatText);
        b1 =(ImageButton) findViewById(R.id.SendChat);


        TextView displayTextView = (TextView) findViewById(R.id.ForumPage);
        displayTextView.setText(eHelper);


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


        Cursor cursor = db.AllMessages(Name,eHelper);

        if (cursor.getCount()==0){


        }else {

            while(cursor.moveToNext()) {

                ArrayList<String> NameU = new ArrayList<>();
                NameU.add(cursor.getString(1));

                ArrayList<String> NameH = new ArrayList<>();
                NameH.add(cursor.getString(3));

                ArrayList<String> TimeU = new ArrayList<>();
                TimeU.add(cursor.getString(2));

                ArrayList<String> TimeH = new ArrayList<>();
                TimeH.add(cursor.getString(4));

                ArrayList<String> ChatU = new ArrayList<>();
                ChatU.add(cursor.getString(5));

                ArrayList<String> ChatH = new ArrayList<>();
                ChatH.add(cursor.getString(6));

                ArrayList<String> Type = new ArrayList<>();
                Type.add(cursor.getString(7));



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
                Boolean insert = db.insert(Name, time, eHelper, "", Chat, "","USER");
                Intent i = new Intent(ChatUserHelper.this, ChatUserHelper.class);
                i.putExtra("Name", Name);
                i.putExtra("eHelper", eHelper);
                i.putExtra("email",value);
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
            Intent i = new Intent(ChatUserHelper.this, SettingsHelper.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item2) {
            Intent i = new Intent(ChatUserHelper.this, AllMessagesHelper.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item3) {
            Intent i = new Intent(ChatUserHelper.this, AllForumHelper.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }


        if (id == R.id.item8) {
            Intent i = new Intent(ChatUserHelper.this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}