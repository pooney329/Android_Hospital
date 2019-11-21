package com.example.administrator.hospital;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TabFragment4 extends Fragment {
    private ArrayList<MyPage> myPages = new ArrayList<>(); // 데이터집합
    private RecyclerView recyclerView;
    private ExpandableListView listView;
    private ExpandableListView listView2;
    Button button;
    private HospiterDbAdapter dbHelper;
    private SimpleCursorAdapter dataAdapter;
    MypageAdapter adapter;
    Context context;
    String userId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mypage, container, false);
        userId = getArguments().getString("id");


        dbHelper = new HospiterDbAdapter(getActivity());
        Cursor cursor = dbHelper.fetchAllMypage(userId);


        Display newDisplay = getActivity().getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();

        ArrayList<MyPage> myPages = new ArrayList<MyPage>();

        listView = (ExpandableListView)view.findViewById(R.id.mylist);
        listView2 = (ExpandableListView)view.findViewById(R.id.my);




        int s = cursor.getCount();
        MyPage temp;
        cursor.moveToFirst();
        TextView textView = (TextView)view.findViewById(R.id.textView7);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
         adapter = new MypageAdapter(getActivity(),R.layout.mypage_row,R.layout.mypage_child,myPages,userId);
        listView.setIndicatorBounds(width-50, width); //이 코드를 지우면 화살표 위치가 바뀐다.
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        for(int i =0; i<s; i++) {
            cursor.moveToPosition(i);
            String doctornumber = cursor.getString(0);
            String dedocter = cursor.getString(1);
            String dename = cursor.getString(2);
            String date = cursor.getString(3);
            temp = new MyPage(doctornumber,dedocter,dename,date);
            temp.child.add("");
            myPages.add(temp);
            adapter.notifyDataSetChanged();
        }









        ArrayList<Tr_content> tr_contents = new ArrayList<Tr_content>();

        listView2 = (ExpandableListView)view.findViewById(R.id.my);
        Cursor cursor2 = dbHelper.fetchAlltreatment(userId,"no");
        int c= cursor2.getCount();
        Tr_content Trc;
        cursor2.moveToFirst();



        for(int i =0; i<c; i++) {
            cursor2.moveToPosition(i);
            String patient1= cursor2.getString(0);
            String doctor1 = cursor2.getString(1);
            String trcontent=cursor2.getString(2);
            String date_time=cursor2.getString(3);



            Tr_content msg = new Tr_content(doctor1,trcontent,date_time);
            tr_contents.add(msg);
        }

        TreatmentAdater adapter = new TreatmentAdater(getContext(),R.layout.treatment_row,R.layout.treatment_child,tr_contents);
        listView2.setIndicatorBounds(width-50, width); //이 코드를 지우면 화살표 위치가 바뀐다.
        listView2.setAdapter(adapter);


        listView2.setIndicatorBounds(width-50, width); //이 코드를 지우면 화살표 위치가 바뀐다.
        listView2.setAdapter(adapter);
        adapter.notifyDataSetChanged();






        return  view;
    }
    private void refresh () {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();


    }


}





