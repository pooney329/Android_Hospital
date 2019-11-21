package com.example.administrator.hospital;



import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class MypageAdapter extends BaseExpandableListAdapter {
    private Context context;
    private int groupLayout = 0;
    private int chlidLayout = 0;
    private ArrayList<MyPage> myPages;
    private LayoutInflater myinf = null;
    private HospiterDbAdapter dbHelper;
    String id;

    public MypageAdapter(Context context,int groupLay,int chlidLay,ArrayList<MyPage> myPages,String id){
        this.myPages = myPages;
        this.groupLayout = groupLay;
        this.chlidLayout = chlidLay;
        this.context = context;
        this.id=id;
        this.myinf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView == null){
            convertView = myinf.inflate(this.groupLayout, parent, false);
        }
        TextView groupName = (TextView)convertView.findViewById(R.id.dedoctor);
        TextView groupName1 = (TextView)convertView.findViewById(R.id.dename);
        TextView groupName2 = (TextView)convertView.findViewById(R.id.date);

        groupName.setText(myPages.get(groupPosition).dedocter);
        groupName1.setText(myPages.get(groupPosition).dename);
        groupName2.setText(myPages.get(groupPosition).date);

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




        childName.setText(myPages.get(groupPosition).child.get(childPosition));
        Button button = (Button)convertView.findViewById(R.id.button);
        Button button1= convertView.findViewById(R.id.button);
        Button button2= (Button) convertView.findViewById(R.id.button2);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHelper = new HospiterDbAdapter(context);

                Cursor cursor = dbHelper.phonenumber(myPages.get(gp).doctornumber);

                 String phone = cursor.getString(0);
                 Toast.makeText(context,phone,Toast.LENGTH_LONG).show();
                String changephone= phone.replaceAll("-", "");
                Intent intent =new Intent(Intent.ACTION_DIAL, Uri.parse("tel: "+changephone ));


                context.startActivity(intent);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper = new HospiterDbAdapter(context);

                dbHelper.deleteReservation(id,myPages.get(gp).date,myPages.get(gp).doctornumber);
                notifyDataSetChanged();
//                Intent intent = new Intent(context,Content.class);
//                intent.putExtra("id",myPages.get(gp).id);
//                context.startActivity(intent);


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
        return myPages.get(groupPosition).child.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return myPages.get(groupPosition).child.size();
    }

    @Override
    public MyPage getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return myPages.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return myPages.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

}


