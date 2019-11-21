package com.example.administrator.hospital;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class OpenChat extends AppCompatActivity {
    private ArrayList<Message>msgs = new ArrayList<>();
    private RecyclerView recyclerView;
    private OpenChatAdater openChatAdater;
    private EditText chatInput;
    private HospiterDbAdapter dbHelper;
    String reciver;
    String id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("ch").equals("1")){
            id = bundle.getString("userId");
            reciver=bundle.getString("groupdenumber");
        }
        else if(bundle.getString("ch").equals("3")){
            id = bundle.getString("sender");
            reciver=bundle.getString("reciver");
        }
        else{
            id = bundle.getString("id");
            reciver="no";

        }

        Toast.makeText(getApplicationContext(),id+reciver,Toast.LENGTH_LONG).show();





        super.onCreate(savedInstanceState);
        setContentView(R.layout.openchating);
        dbHelper = new HospiterDbAdapter(getApplicationContext());
        chatInput = findViewById(R.id.chat_input);
        // 입력창에 엔터키를 입력했을 때 동작하는 것
        chatInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String text= chatInput.getText().toString();
                if(text.equals("")) {
                    prepareMovieData();
                }
                else{
                    dbHelper.insertmsg(id,text,reciver);
                    chatInput.setText("");

                    prepareMovieData();
                }


                return false;  // 키 입력 이벤트가 더 이상 전달안되도록
            }
        });



        recyclerView =findViewById(R.id.chat_message);//
        openChatAdater = new OpenChatAdater(msgs,id); //데이터셋과 어뎁터를 연결했다고 보면된다 데이터가 없어도
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());  //여러 다양한 형태를 배치하기위해서 매니저가있어야한다. 그래서 리니어매니저를 사용하면 기본이 수직이니깐 수직적으로 배치한다
        recyclerView.setLayoutManager(layoutManager);  //아이템을 수직으로 배치할것이다.
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //아이템에 클릭, 스코롤시 애니메이션 효과를 줄수있는데 디폴트로 사용하겠다는거다
        recyclerView.setAdapter(openChatAdater);  //리사이클뷰는 껍데기 그것을 채워주는것이 어뎁터이다. 그럴려면 데이터가 필요하니깐 어뎁터에 무비스를 넣어준것이다.

        prepareMovieData();







    }
    private void prepareMovieData() {
        msgs.clear();
        Cursor cursor = dbHelper.fetchAllMsg(reciver,id);


        int c = cursor.getCount();
        cursor.moveToFirst();
        for(int i=0; i<c; i++){
            cursor.moveToPosition(i);
            String sender=cursor.getString(0);
            String text = cursor.getString(1);
            String date=cursor.getString(2);



            Message msg = new Message(sender,text,date);
            msgs.add(msg);

        }
        recyclerView.scrollToPosition(msgs.size()-1);



        openChatAdater.notifyDataSetChanged();  // 중요~~어뎁터한테 데이터집합의 변화를 알려준다.
    }
}
