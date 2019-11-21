package com.example.administrator.hospital;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChatTable extends AppCompatActivity {
    private ArrayList<ChatTable_a> chatTable_as = new ArrayList<>(); // 데이터집합
    private RecyclerView recyclerView;
    private ChatTableAdater adapter;
    Context context;
    String  id;
    private HospiterDbAdapter dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chattable);
        Bundle bundle =getIntent().getExtras();
        id = bundle.getString("userId");
        context=this;
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new ChatTableAdater(chatTable_as,context,id);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        dbHelper = new HospiterDbAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);

        prepareMovieData();





    }

    private void prepareMovieData() {
        chatTable_as.clear();
        Cursor cursor = dbHelper.sendUser(id);
        Toast.makeText(getApplicationContext(),id, Toast.LENGTH_SHORT).show();

        int c = cursor.getCount();
        cursor.moveToFirst();
        for(int i=0; i<c; i++){
            cursor.moveToPosition(i);
            String sender=cursor.getString(0);

            ChatTable_a ct = new ChatTable_a(sender);
            chatTable_as.add(ct);

        }
        recyclerView.scrollToPosition(chatTable_as.size()-1);



        adapter.notifyDataSetChanged();  // 중요~~어뎁터한테 데이터집합의 변화를 알려준다.
    }
}
