package com.example.administrator.hospital;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HospiterDbAdapter {
    User note;
    public static final String KEY_ROWID = "_id";
    public static final String ID = "id";
    public static final String PWD = "pwd";

    private static final String TAG = "Hospiter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private  Cursor mCursor;


    private static final String DATABASE_NAME = "Hospiter";
    private static final String User = "user";
    private static final String Department = "department";
    private static final String Reservation = "reservation";
    private static final String Message = "msg";
    private static final String TreatmentContent = "treatmentcontent";

    private static final int DATABASE_VERSION = 3;

    private final Context mCtx;

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + User +"(id varchar(20) not null, pwd varchar(20) not null, name varchar(20) not null, phone varchar(20) not null, primary key(id)) ";

    private static final String DATABASE_CREATE2 = "CREATE Table if not exists " + Department + "(_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "denumber varchar(20) not null , dename varchar(20) not null , dedoctor varchar(20) not null, dephone varchar(20) not null)";

    private static final String DATABASE_CREATE3 = "CREATE Table if not exists " + Reservation + "(data_id DATETIME not null, patient varchar(20) not null, doctor varchar(20) not null,name varchar(20) not null, primary key(doctor,data_id))";
    private static final String DATABASE_CREATE4 = "CREATE Table if not exists " + Message + "(sender varchar(20) not null, msg varchar(20) not null, reciver varchar(20) ,date_time DATETIME  DEFAULT CURRENT_TIMESTAMP)";
    private static final String DATABASE_CREATE5 = "CREATE Table if not exists " + TreatmentContent + "(patient varchar(20) not null, doctor varchar(20) not null,trcontent varchar(50), date_time DATETIME  DEFAULT CURRENT_TIMESTAMP, primary key(date_time))";




    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE);

            db.execSQL(DATABASE_CREATE2);
            db.execSQL(DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE3);
            db.execSQL(DATABASE_CREATE4);
            db.execSQL(DATABASE_CREATE5);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old- data");
            db.execSQL("DROP TABLE IF EXISTS " + User);
            db.execSQL("DROP TABLE IF EXISTS " + Department);

            onCreate(db);
        }
    }

    public HospiterDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public HospiterDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);



        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createUser(String id, String pwd, String name, String phone) {

        ContentValues initialValues = new ContentValues();
        initialValues.put("id", id);
        initialValues.put("pwd", pwd);
        initialValues.put("name", name);
        initialValues.put("phone", phone);


        return mDb.insert(User, null, initialValues);
    }

    public long createDepartMent(String denumber, String dename, String dedoctor, String dephone) {

        ContentValues initialValues = new ContentValues();
        initialValues.put("denumber", denumber);
        initialValues.put("dename", dename);
        initialValues.put("dedoctor", dedoctor);
        initialValues.put("dephone", dephone);



        return mDb.insert(Department, null, initialValues);
    }

    public long insertTreatment(String patient, String doctor, String content) {
        mDbHelper = new DatabaseHelper(mCtx);



        mDb = mDbHelper.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("patient", patient);
        initialValues.put("doctor", doctor);
        initialValues.put("trcontent", content);




        return mDb.insert(TreatmentContent, null, initialValues);
    }





    public long creatReservation(String date, String patient, String doctor , String name) {
        mDbHelper = new DatabaseHelper(mCtx);



        mDb = mDbHelper.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("data_id", date);
        initialValues.put("patient", patient);
        initialValues.put("doctor", doctor);
        initialValues.put("name", name);


        return mDb.insert(Reservation, null, initialValues);
    }

    public long insertmsg(String sender, String msg,String reciver) {
        mDbHelper = new DatabaseHelper(mCtx);



        mDb = mDbHelper.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("sender", sender);
        initialValues.put("msg", msg);
        initialValues.put("reciver",reciver);




        return mDb.insert(Message, null, initialValues);
    }




//    public User getNote(String id) {
//        // get readable database as we are not inserting anything
////        SQLiteDatabase db = this.getReadableDatabase();
//
//        //select * from note where id= ....
//
//        String selectQuery = "select * from "+ SQLITE_TABLE+ " where id = '" +id+"'";
//        //cursor 검색된레코드를가리키는 포인터역할
//        SQLiteDatabase db = mDbHelper.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//
//        if (cursor != null) {
//            cursor.moveToFirst();
//            User note = new User(
//                    cursor.getString(cursor.getColumnIndex(ID)),
//                    cursor.getString(cursor.getColumnIndex(PWD))
//
//
//            );
//        }
//        else{
//
//
//        }
//        // prepare note object
//
//
//        // close the db connection
//        cursor.close();
//
//        return note;
//    }

    public User Login(String id,String pwd) {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        try {
            String selectQuery = "select * from "+ User+ " where id = '" +id+"'"+  "and pwd =" + "'" + pwd+"'";
            int i = 0;
            Cursor c = null;
            c = db.rawQuery(selectQuery, null);
            c.moveToFirst();
            i = c.getCount();
            c.close();
            return new User(id,pwd,i);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return new User(id,pwd,0);
    }

    public int Idcheck(String id) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        try {
            String selectQuery = "select * from "+ User+ " where id =" +"'" +id+"'";
            int i = 0;
            Cursor c = null;
            c = db.rawQuery(selectQuery, null);
            c.moveToFirst();
            i = c.getCount();
            c.close();
            return  i;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    public int reservationcheck(String doctor,String date) {
        mDbHelper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        try {
            String s ="select * from reservation where doctor=" +"'"+doctor+"'"+ "and data_id ="+"'"+date+"'";
            int i = 0;
            Cursor c = null;
            c = db.rawQuery(s, null);
            c.moveToFirst();
            i = c.getCount();
            c.close();
            return  i;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return 0;





    }


    public boolean deleteAllDepartment() {

        int doneDelete = 0;
        doneDelete = mDb.delete(Department, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }

//    public boolean deleteAllCountries() {
//
//        int doneDelete = 0;
//        doneDelete = mDb.delete(SQLITE_TABLE, null , null);
//        Log.w(TAG, Integer.toString(doneDelete));
//        return doneDelete > 0;
//
//    }
//
//    public Cursor fetchCountriesByName(String inputText) throws SQLException {
//        Log.w(TAG, inputText);
//        Cursor mCursor = null;
//        if (inputText == null  ||  inputText.length () == 0)  {
//            mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
//                            KEY_CODE, KEY_NAME, KEY_CONTINENT, KEY_REGION, KEY_FALG},
//                    null, null, null, null, null);
//
//        }
//        else {
//            mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ROWID,
//                            KEY_CODE, KEY_NAME, KEY_CONTINENT, KEY_REGION, KEY_FALG},
//                    KEY_NAME + " like '%" + inputText + "%'", null,
//                    null, null, null, null);
//        }
//        if (mCursor != null) {
//            mCursor.moveToFirst();
//        }
//        return mCursor;
//
//    }
//


    public boolean insertUser(String id, String pwd,String name , String phone) {
     int nu=Idcheck(id);
     if(nu==0){
         createUser(id,pwd,name,phone);
         return  true;

     }
     else{

         return  false;

     }




    }
    public Cursor fetchAllDepartment() {
        mDbHelper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor mCursor = db.rawQuery("select * from department",null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor fetchAlltreatment(String patient,String doctor) {
        mDbHelper = new DatabaseHelper(mCtx);
        Cursor mCursor;
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        if(doctor.equals("no")){
            mCursor = db.rawQuery("select * from treatmentcontent where patient="+"'"+patient+"'order by date_time ",null);
        }
        else{
            mCursor = db.rawQuery("select * from treatmentcontent where patient="+"'"+patient+"' and doctor="+"'"+doctor+"' order by date_time ",null);
        }

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor sendUser(String id) {
        mDbHelper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

            mCursor = db.rawQuery("select distinct sender from msg where reciver='"+id+"' order by date_time  ", null);




        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor fetchAllMsg(String reciver,String id) {
        mDbHelper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        if(reciver.equals("no")) {
            mCursor = db.rawQuery("select * from msg where reciver='no' order by date_time  ", null);
        }
        else{
            mCursor = db.rawQuery("select * from msg where (reciver="+"'"+reciver+"'"+"and sender="+"'"+id+"'"+") or (reciver="+"'"+id+"'and sender='"+reciver+"') order by date_time " , null);
        }


        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


    public Cursor phonenumber(String doctor) {
        mDbHelper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor mCursor = db.rawQuery("select dephone from department where denumber="+"'"+doctor+"'",null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }



    public Cursor fetchAllMypage(String id) {
        mDbHelper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor mCursor = db.rawQuery("select reservation.doctor, department.dedoctor,department.dename,reservation.data_id from reservation,department where reservation.doctor=department.denumber and reservation.patient='"+id+"'" ,null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public void insertDepartment() {

        createDepartMent("A13","이비인후과","장필순","010-5555-1111");
        createDepartMent("A14","흉부외과","이민호","010-5523-1222");
        createDepartMent("A15","신경외과","이리리","010-3234-1254");
        createDepartMent("A16","소아외과","이효리","010-3234-1252");



    }

    public void deleteReservation(String id, String date, String doctor) {

        mDbHelper = new DatabaseHelper(mCtx);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Log.d("asda","asdasd");
        //db.rawQuery("delete from reservation  where doctor="+"'"+doctor+"'"+"and data_id =" + "'"+date+"'"+"and patient ="+"'"+id+"'" ,null);
        db.execSQL("delete from reservation  where doctor="+"'"+doctor+"'"+"and data_id =" + "'"+date+"'"+"and patient ="+"'"+id+"'");





    }




}


