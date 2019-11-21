package com.example.administrator.hospital;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView id;
    EditText password;
    private HospiterDbAdapter dbHelper;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntent();
        setContentView(R.layout.activity_login);
        context = this;
        id = (AutoCompleteTextView) findViewById(R.id.id);
        password = (EditText) findViewById(R.id.password);
        dbHelper = new HospiterDbAdapter(context);
        dbHelper.open(); // 테이블생성하고 DB를 open.
        dbHelper.deleteAllDepartment();
          dbHelper.insertDepartment();
        Button button = (Button) findViewById(R.id.sign_in_button);
        Button button2 = (Button) findViewById(R.id.sign_up_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getid = id.getText().toString();
                String getpwd = password.getText().toString();



                User user = dbHelper.Login(getid,getpwd);
                 int check=user.getCheck();
                 if(check==1) {
                     Intent intent =new Intent(MainActivity.this,Content.class);
                     intent.putExtra("id",getid);
                     startActivity(intent);
                 }
                 else{
                     Toast.makeText(getApplicationContext(), "아이디와 비밀번호가 틀렸습니다", Toast.LENGTH_LONG).show();
                 }



            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,SignUp.class);
                startActivityForResult(intent, 100);


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
// MainActivity 에서 요청할 때 보낸 요청 코드 (3000)
                case 100:
                    Toast.makeText(getApplicationContext(),"회원가입완료",Toast.LENGTH_LONG).show();
                    break;

            }


        }
    }
}