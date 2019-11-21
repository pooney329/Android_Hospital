package com.example.administrator.hospital;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Addtreatment extends AppCompatActivity {
    private HospiterDbAdapter dbHelper;
    String patient;
    String doctor;
    String content2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtreatment);
        Bundle bundle = getIntent().getExtras();
         patient= bundle.getString("patient");
         doctor= bundle.getString("doctor");

        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText(patient);






        dbHelper = new HospiterDbAdapter(getApplicationContext());
        Button button = (Button) findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText text = (EditText)findViewById(R.id.editText2);
                content2=text.getText().toString();
                Toast.makeText(getApplicationContext(),content2,Toast.LENGTH_LONG).show();
                dbHelper.insertTreatment(patient,doctor,content2);
                Intent intent = new Intent(getApplication(),Treatment.class);
                intent.putExtra("patient",patient);
                intent.putExtra("doctor",doctor);
                startActivity(intent);
            }
        });






    }
}
