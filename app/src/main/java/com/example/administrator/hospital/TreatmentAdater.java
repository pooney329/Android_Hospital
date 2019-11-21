package com.example.administrator.hospital;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TreatmentAdater extends BaseExpandableListAdapter {
    private Context context;
    private int groupLayout = 0;
    private int chlidLayout = 0;
    private ArrayList<Tr_content> DataList;
    private LayoutInflater myinf = null;


    public TreatmentAdater(Context context,int groupLay,int chlidLay,ArrayList<Tr_content> DataList){
        this.DataList = DataList;
        this.groupLayout = groupLay;
        this.chlidLayout = chlidLay;
        this.context = context;
        this.myinf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView == null){
            convertView = myinf.inflate(this.groupLayout, parent, false);
        }
        TextView patinent = (TextView) convertView.findViewById(R.id.patient);
        TextView content = (TextView) convertView.findViewById(R.id.content);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        patinent.setText(DataList.get(groupPosition).getName());
        content.setText(DataList.get(groupPosition).getContent());
        date.setText(DataList.get(groupPosition).getDate());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final int gp = groupPosition;
        final int cp = childPosition;
        final String map1;
        if(convertView == null){
            convertView = myinf.inflate(this.chlidLayout, parent, false);
        }
        TextView childName = (TextView)convertView.findViewById(R.id.childName);


//        City map = new City(DataList.get(groupPosition).child.get(childPosition));
//        map1=map.map;
        childName.setText(DataList.get(groupPosition).child.get(childPosition));

        Button button1= convertView.findViewById(R.id.button);
        Button button2= (Button) convertView.findViewById(R.id.button2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent =new Intent(context,Second.class);
//                intent.putExtra("name",DataList.get(gp).child.get(cp));
//                context.startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map1));
//                context.startActivity(intent);

            }
        });

//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(context,Tiket.class);
//                intent.putExtra("name",DataList.get(gp).child.get(cp));
//                context.startActivity(intent);
//            }
//        });



        return convertView;
    }
    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return DataList.get(groupPosition).child.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return DataList.get(groupPosition).child.size();
    }

    @Override
    public Tr_content getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return DataList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return DataList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

}


