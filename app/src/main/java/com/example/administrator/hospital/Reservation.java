package com.example.administrator.hospital;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Reservation extends AppCompatActivity {

    private HospiterDbAdapter dbHelper;
    Chronometer chronometer;
    Button b1, b2;
    RadioButton r1,r2;
    CalendarView calendar;
    TimePicker time;
    TextView tv1;
    String date = " ";
    String denumber;
    String userid;
    String name2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);
        Bundle bundle = getIntent().getExtras();
        denumber =bundle.getString("groupdenumber");
        name2 =bundle.getString("groupdename");


        userid =bundle.getString("userId");

        dbHelper = new HospiterDbAdapter(getApplicationContext());

        b2 = (Button)findViewById(R.id.button2);
        r1 = (RadioButton)findViewById(R.id.radioButton);
        r2 = (RadioButton)findViewById(R.id.radioButton2);
        calendar = (CalendarView)findViewById(R.id.calendarView2);
        time = (TimePicker)findViewById(R.id.timePicker);
        tv1 = (TextView)findViewById(R.id.textView);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String times = Integer.toString(time.getHour()) + "시 " + time.getMinute()+ "분 ";


                String date2 = date+" "+Integer.toString(time.getHour())+":"+time.getMinute();
                tv1.setText(date2);


                int i = dbHelper.reservationcheck(denumber,date2);
                if(i==1) {
                    Toast.makeText(getApplicationContext(),"시간이 다찼습니다",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"예약 완료",Toast.LENGTH_LONG).show();
                    dbHelper.creatReservation(date2, userid, denumber,name2);

                    Intent intent = new Intent(getApplicationContext(),Content.class);
                    intent.putExtra("id",userid);

                    startActivity(intent);
                }

            }
        });
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = year + "-" + (month+1) + "-" + dayOfMonth;

            }
        });
        r1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (r1.isChecked()){
                    calendar.setVisibility(View.VISIBLE);
                    time.setVisibility(View.INVISIBLE);
                }
            }
        });
        r2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (r2.isChecked()){
                    calendar.setVisibility(View.INVISIBLE);
                    time.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}