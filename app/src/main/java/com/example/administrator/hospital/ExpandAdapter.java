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

public class ExpandAdapter extends BaseExpandableListAdapter {
    private Context context;
    private int groupLayout = 0;
    private int chlidLayout = 0;
    private ArrayList<myGroup> DataList;
    private LayoutInflater myinf = null;
    private String id;
    Button button3;
    public ExpandAdapter(Context context,int groupLay,int chlidLay,ArrayList<myGroup> DataList,String id){
        this.DataList = DataList;
        this.groupLayout = groupLay;
        this.chlidLayout = chlidLay;
        this.id=id;
        this.context = context;
        this.myinf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView == null){
            convertView = myinf.inflate(this.groupLayout, parent, false);
        }
        TextView groupName = (TextView)convertView.findViewById(R.id.denumber);
        TextView groupName1 = (TextView)convertView.findViewById(R.id.dename);
        TextView groupName2 = (TextView)convertView.findViewById(R.id.dedoctor);
        TextView groupName3 = (TextView)convertView.findViewById(R.id.dephone);
        groupName.setText(DataList.get(groupPosition).groupdenumber);
        groupName1.setText(DataList.get(groupPosition).groupdename);
        groupName2.setText(DataList.get(groupPosition).groupdedoctor);
        groupName3.setText(DataList.get(groupPosition).groupdephone);
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




        childName.setText(DataList.get(groupPosition).child.get(childPosition));
        Button button = (Button)convertView.findViewById(R.id.button);

        Button button2= (Button) convertView.findViewById(R.id.button2);
        button3= (Button) convertView.findViewById(R.id.button3);

        if(DataList.get(groupPosition).groupdenumber.equals(id)){
            button3.setText("채팅방입장");
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


            Intent intent = new Intent(context, ChatTable.class);
            intent.putExtra("userId", DataList.get(gp).userId);

            intent.putExtra("ch", "1");
            context.startActivity(intent);
                }
            });
        }


        else {  button3.setText("1대1 대화");
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, OpenChat.class);
                    intent.putExtra("userId", DataList.get(gp).userId);
                    intent.putExtra("groupdenumber", DataList.get(gp).groupdenumber);
                    intent.putExtra("ch", "1");
                    context.startActivity(intent);

                }
            });
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent =new Intent(context,Second.class);
//                intent.putExtra("name",DataList.get(gp).child.get(cp));
                //context.startActivity(intent);
                String phone = DataList.get(gp).groupdephone;
                String changephone= phone.replaceAll("-", "");
                Intent intent =new Intent(Intent.ACTION_DIAL, Uri.parse("tel: "+changephone ));


                context.startActivity(intent);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent =new Intent(context,Reservation.class);
                    intent.putExtra("groupdenumber",DataList.get(gp).groupdenumber);
                    intent.putExtra("groupdename",DataList.get(gp).groupdename);
                    intent.putExtra("groupdedoctor",DataList.get(gp).groupdedoctor);
                    intent.putExtra("groupdephone",DataList.get(gp).groupdephone);
                    intent.putExtra("userId",DataList.get(gp).userId);
                    context.startActivity(intent);

            }
        });




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
    public myGroup getGroup(int groupPosition) {
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


