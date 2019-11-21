package com.example.administrator.hospital;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class TabFragment2 extends Fragment {
    String userId;
    private HospiterDbAdapter dbHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_2, container, false);
        userId = getArguments().getString("id");
//        Toast.makeText(getContext(), userId, Toast.LENGTH_LONG).show();

        dbHelper = new HospiterDbAdapter(getActivity());
        Cursor cursor = dbHelper.fetchAllDepartment();
        ImageButton button = (ImageButton) view.findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(getContext(),OpenChat.class);
                intent.putExtra("id",userId);
                intent.putExtra("ch","2");

                startActivity(intent);
            }
        });

        return  view;
    }
}

