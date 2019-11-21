package com.example.administrator.hospital;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TabFragment extends Fragment {
    private ArrayList<DepartMent> departMents = new ArrayList<>(); // 데이터집합
    private RecyclerView recyclerView;
    private ExpandableListView listView;
    Button button;
    private HospiterDbAdapter dbHelper;
    private SimpleCursorAdapter dataAdapter;
    Context context;
    String userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);
        userId = getArguments().getString("id");
//        Toast.makeText(getContext(), userId, Toast.LENGTH_LONG).show();

        dbHelper = new HospiterDbAdapter(getActivity());
        Cursor cursor = dbHelper.fetchAllDepartment();

        Display newDisplay = getActivity().getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();

        ArrayList<myGroup> DataList = new ArrayList<myGroup>();

        listView = (ExpandableListView)view.findViewById(R.id.mylist);

        int s = cursor.getCount();
        myGroup temp;
        cursor.moveToFirst();
        for(int i =0; i<s; i++) {
            cursor.moveToPosition(i);
            String denumber = cursor.getString(1);
            String dename = cursor.getString(2);
            String dedoctor = cursor.getString(3);
            String dephone = cursor.getString(4);
            temp = new myGroup(denumber, dename, dedoctor, dephone,userId);
            temp.child.add("");
            DataList.add(temp);

        }



//        myGroup temp = new myGroup("g52","aa","asdas","asdasd");
//        temp.child.add("");
//
//        DataList.add(temp);
//        temp = new myGroup("g52","aa","asdas","asdasd");
//        temp.child.add("");
//
//
//        DataList.add(temp);
//        temp = new myGroup("g52","aa","asdas","asdasd");
//        temp.child.add("");




        ExpandAdapter adapter = new ExpandAdapter(getActivity(),R.layout.tab_fragment1_row,R.layout.child_row,DataList,userId);
        listView.setIndicatorBounds(width-50, width); //이 코드를 지우면 화살표 위치가 바뀐다.
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);


         return  view;
   }


}





