package com.example.latticeapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbData {
        public static final String KEY_NAME = "person_name";
        public static final String KEY_ADDRESS = "person_address";
        public static final String KEY_LOCATION = "person_location";
        public static final String KEY_PASSWORD = "person_password";
        public static final String KEY_EMAIL = "person_email";
        public static final String KEY_CELL = "person_number";
        private final String DATABASE_NAME = "detailsDB";
        private final String DATABASE_TABLE = "dataTable";
        private final int DATABASE_VERSION = 1;
        private DBHelper ourHelper;
        private final Context ourContext;
        private SQLiteDatabase ourDataBase;


        public DbData(Context context) {
            this.ourContext = context;
        }

        public class DBHelper extends SQLiteOpenHelper {
            @Override
            public void onCreate(SQLiteDatabase db) {
                String SqlCode = " CREATE TABLE " + DATABASE_TABLE + " (" +
                        KEY_NAME + " TEXT NOT NULL, " + KEY_CELL + " TEXT NOT NULL,"+ KEY_ADDRESS +" TEXT NOT NULL,"+
                        KEY_LOCATION + " TEXT NOT NULL,"+ KEY_PASSWORD +" TEXT NOT NULL,"+ KEY_EMAIL+" TEXT NOT NULL);";
                db.execSQL(SqlCode);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            }

            public DBHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }

        }
        public  DbData open() throws SQLException {
            ourHelper = new DBHelper(ourContext);
            ourDataBase = ourHelper.getWritableDatabase();
            return this;
        }
        public void  close(){
            ourHelper.close();
        }
        public long createEntry( String name , String number,String email,String address, String password,String location){
            ContentValues cv = new ContentValues();
            cv.put(KEY_NAME,name);
            cv.put(KEY_CELL,number);
            cv.put(KEY_EMAIL,email);
            cv.put(KEY_ADDRESS,address);
            cv.put(KEY_PASSWORD,password);
            cv.put(KEY_LOCATION,location);
            return ourDataBase.insert(DATABASE_TABLE,null,cv);
        }
       ArrayList<PersonData> getData(){
           String [] columns = new String[]{KEY_NAME,KEY_CELL,KEY_EMAIL,KEY_ADDRESS,KEY_PASSWORD,KEY_LOCATION};
           ArrayList<PersonData> storeContacts = new ArrayList<>();
           Cursor c = ourDataBase.query(DATABASE_TABLE, columns,null,null,null,null,null);
           if (c.moveToFirst()) {
               do {
                   String name = c.getString(0);
                   String phone = c.getString(1);
                   String email = c.getString(2);
                   String address = c.getString(3);
                   String password = c.getString(4);
                   String location = c.getString(5);

                   storeContacts.add(new PersonData(name, phone,email,address,password,location));
               }
               while (c.moveToNext());
           }
           c.close();
           return storeContacts;
        }


}
