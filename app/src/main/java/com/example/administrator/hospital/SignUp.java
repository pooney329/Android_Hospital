package com.example.administrator.hospital;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    AutoCompleteTextView id;
    EditText password;
    EditText name;
    EditText phone;
    private HospiterDbAdapter dbHelper;
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        context = this;
        id = (AutoCompleteTextView) findViewById(R.id.id);
        password = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.username);
        phone = (EditText) findViewById(R.id.phonenumber);
        dbHelper = new HospiterDbAdapter(context);
        dbHelper.open(); // 테이블생성하고 DB를 open.

        getIntent();

        Button signup = (Button) findViewById(R.id.sign_up_button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id1 = id.getText().toString();
                String pwd = password.getText().toString();
                String name1 = name.getText().toString();
                String phone1 = phone.getText().toString();
                Log.d("a",id1);
                Log.d("b",pwd);
                Log.d("c",name1);
                Log.d("d",phone1);
                if(id1.getBytes().length <= 0 || pwd.getBytes().length <=0 || name1.getBytes().length<=0 || phone1.getBytes().length<=0){


                    Toast.makeText(getApplicationContext(),"회원정보를 모두 입력해주세요",Toast.LENGTH_LONG).show();
                }

                else {
                    boolean bool = dbHelper.insertUser(id1, pwd, name1, phone1);


                    if (bool) {
                        Intent intent = new Intent();

                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "중복된 아이디가 존재", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });


    }
}
