package com.example.administrator.hospital;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class Treatment extends AppCompatActivity {
    private ExpandableListView listView;
    Button button;
    private HospiterDbAdapter dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.treatment);
        Bundle bundle = getIntent().getExtras();
        ArrayList<Tr_content> Data = new ArrayList<Tr_content>();
        final String patient = bundle.getString("patient");
        final String doctor = bundle.getString("doctor");
        Display newDisplay = getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();
        dbHelper = new HospiterDbAdapter(getApplicationContext());
        Cursor cursor = dbHelper.fetchAlltreatment(patient,doctor);
        int s= cursor.getCount();
        cursor.moveToFirst();
        for(int i=0; i<s; i++){
            cursor.moveToPosition(i);
            String patient1= cursor.getString(0);
            String doctor1 = cursor.getString(1);
            String trcontent=cursor.getString(2);
            String date_time=cursor.getString(3);



            Tr_content msg = new Tr_content(patient1,trcontent,date_time);
            Data.add(msg);
        }





        String name=patient;
        String content=doctor;
        String date="aaa";

        listView = (ExpandableListView)findViewById(R.id.mylist);

        Tr_content tr_content = new Tr_content(name,content,date);

        tr_content.child.add("");

        Data.add(tr_content);
        Button button =(Button)findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Addtreatment.class);
                intent.putExtra("patient",patient);
                intent.putExtra("doctor",doctor);




                startActivity(intent);

            }
        });

        TreatmentAdater adapter = new TreatmentAdater(getApplicationContext(),R.layout.treatment_row,R.layout.treatment_child,Data);
        listView.setIndicatorBounds(width-50, width); //이 코드를 지우면 화살표 위치가 바뀐다.
        listView.setAdapter(adapter);


    }
}