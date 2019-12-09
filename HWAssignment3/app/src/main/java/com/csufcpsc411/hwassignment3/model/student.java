package com.csufcpsc411.hwassignment3.model;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class student extends PersistentObject {

    protected String mFirstName;
    protected String mLastName;
    protected String mCWID;

    protected ArrayList<Courses> mCourses;

    public student(String firstName, String lastName, String CWID) {
        mFirstName = firstName;
        mLastName = lastName;
        mCWID = CWID;
    }

    public student() {}

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setCourses(ArrayList<Courses> courses) {
        mCourses = courses;
    }
    
    @Override
    public void insert(SQLiteDatabase db) {
        ContentValues vals = new ContentValues();
        vals.put("FirstName", mFirstName);
        vals.put("LastName", mLastName);
        vals.put("CWID", mCWID);
        db.insert("Student", null, vals);
        for(int i=0; i < mCourses.size(); i++){
            mCourses.get(i).insert(db);
        }
    }

    @Override
    public void initFrom(SQLiteDatabase db, Cursor cursor) {
        mFirstName = cursor.getString(cursor.getColumnIndex("FirstName"));
        mLastName = cursor.getString(cursor.getColumnIndex("LastName"));
        mCWID = cursor.getString(cursor.getColumnIndex("CWID"));

        mCourses = new ArrayList<Courses>();
        Cursor c = db.query("CourseEnrollment", null, "Student=?", new String[]{mCWID}, null, null, null);
        if(c.getCount() > 0){
            while(c.moveToNext()){
                Courses cObj = new Courses();
                cObj.initFrom(db, c);
                mCourses.add(cObj);
            }
        }
    }
}
