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
import android.widget.Toast;

import com.example.openapp3.DataBases.DataBaseMessages;
import com.example.openapp3.MainActivity;
import com.example.openapp3.Messages.AllMessagesAdapter;
import com.example.openapp3.Messages.AllMessagesStyle;
import com.example.openapp3.R;

import java.util.ArrayList;

public class AllMessagesHelper extends AppCompatActivity {


    RecyclerView RV;
    AllMessagesAdapter RVA;
    RecyclerView.LayoutManager RVM;

    String value;
    String eHelper;

    DataBaseMessages db;
    String Name;
    ArrayList<AllMessagesStyle> AM = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_messages_helper);

        db= new DataBaseMessages(this);

        Bundle bundle1 = getIntent().getExtras();
        value = bundle1.getString("email");

        Bundle bundle2 = getIntent().getExtras();
        Name = bundle2.getString("Name");

        getMessages();
    }

    public void click(int position){
        eHelper = AM.get(position).getText3().toString();
    }



    public void getMessages() {
        Toast.makeText(getApplicationContext(), Name, Toast.LENGTH_SHORT).show();
        Cursor cursor = db.AllHelper2(Name);

        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();

        } else {
            while (cursor.moveToNext()) {

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
                AM.add(new AllMessagesStyle(R.drawable.ic_photo, TimeU.get(0), NameU.get(0)));

            }


            RV = findViewById(R.id.recyclerView);
            RV.setHasFixedSize(true);
            RVM = new LinearLayoutManager(this);
            RVA = new AllMessagesAdapter(AM);

            RV.setLayoutManager(RVM);
            RV.setAdapter(RVA);

            RVA.setOnItemClickListener(new AllMessagesAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    click(position);

                    Intent i = new Intent(AllMessagesHelper.this, ChatUserHelper.class);
                    i.putExtra("Name", Name);
                    i.putExtra("email", value);
                    i.putExtra("eHelper", eHelper);
                    startActivity(i);

                }
            });

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
            Intent i = new Intent(AllMessagesHelper.this, SettingsHelper.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item2) {
            Intent i = new Intent(AllMessagesHelper.this, AllMessagesHelper.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }

        if (id == R.id.item3) {
            Intent i = new Intent(AllMessagesHelper.this, AllForumHelper.class);
            i.putExtra("email", value);
            i.putExtra("Name", Name);
            startActivity(i);
        }



        if (id == R.id.item8) {
            Intent i = new Intent(AllMessagesHelper.this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}