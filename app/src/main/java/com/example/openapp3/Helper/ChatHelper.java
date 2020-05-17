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
import android.widget.Toast;

import com.example.openapp3.Chats.ChatAdapter;
import com.example.openapp3.Chats.ChatsStyle;
import com.example.openapp3.DataBases.DataBaseMessages;
import com.example.openapp3.MainActivity;
import com.example.openapp3.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ChatHelper extends AppCompatActivity {

    RecyclerView RV;
    RecyclerView.Adapter RVA;
    RecyclerView.LayoutManager RVM;


    String value;
    String eHelper;
    EditText e1;
    DataBaseMessages db;
    String time;
    String Name,NameUser;





    ArrayList<ChatsStyle> CS = new ArrayList<>();


    ImageButton b1;

    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_user);

        db= new DataBaseMessages(this);

        Bundle bundle1 = getIntent().getExtras();
        value = bundle1.getString("email");

        Bundle bundle2 = getIntent().getExtras();
        Name = bundle1.getString("Name");

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


        Cursor cursor = db.AllHelper(Name);

        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();

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

                NameUser = NameU.get(0);

                if(Type.get(0)=="User") {
                    CS.add(new ChatsStyle(R.drawable.ic_photo1, ChatU.get(0), TimeU.get(0), NameU.get(0)));
                }else{
                    CS.add(new ChatsStyle(R.drawable.ic_photo, ChatU.get(0), TimeU.get(0), NameU.get(0)));
                }


                b1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        String Chat = e1.getText().toString();
                        Boolean insert = db.insert(Name, time, NameUser, "", Chat, "","Helper");
                        Intent i = new Intent(ChatHelper.this, ChatHelper.class);
                        i.putExtra("Name", Name);
                        i.putExtra("eHelper", eHelper);
                        i.putExtra("email",value);
                        startActivity(i);

                    }
                });

                RV = findViewById(R.id.recyclerView);
                RV.setHasFixedSize(true);
                RVM = new LinearLayoutManager(this);
                RVA = new ChatAdapter(CS);

                RV.setLayoutManager(RVM);
                RV.setAdapter(RVA);
            }





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
            Intent i = new Intent(ChatHelper.this, MainActivity.class);
            i.putExtra("email", value);
            startActivity(i);
        }


        if (id == R.id.item2) {
            Intent i = new Intent(ChatHelper.this, MainActivity.class);
            i.putExtra("email", value);
            startActivity(i);
        }
        if (id == R.id.item3) {
            Intent i = new Intent(ChatHelper.this, MainActivity.class);
            i.putExtra("email", value);
            startActivity(i);
        }


        if (id == R.id.item4) {
            Intent i = new Intent(ChatHelper.this, MainActivity.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item5) {
            Toast.makeText(getApplicationContext(), "Page In Maintence", Toast.LENGTH_SHORT).show();

        }

        if (id == R.id.item6) {
            Intent i = new Intent(ChatHelper.this, MainActivity.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item7) {
            Intent i = new Intent(ChatHelper.this, MainActivity.class);
            i.putExtra("email", value);
            i.putExtra("name", Name);
            startActivity(i);
        }

        if (id == R.id.item8) {
            Intent i = new Intent(ChatHelper.this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}