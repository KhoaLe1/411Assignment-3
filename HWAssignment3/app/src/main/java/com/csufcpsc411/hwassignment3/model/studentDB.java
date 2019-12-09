package com.csufcpsc411.hwassignment3.model;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;

import java.io.File;
import java.util.ArrayList;

public class studentDB {
    protected ArrayList<student> mStudents;

    protected SQLiteDatabase mSQLiteDatabase;

    public studentDB(Context context) {
        File dbFile = context.getDatabasePath("student.db");
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);

        mSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Student (FirstName Text, LastName Text, CWID Text)");
        mSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS CourseEnrollment (CourseID Text, Grade Text, Student Text)");

    }

    public ArrayList<student> getStudents() {
        return mStudents;
    }
    
    public void addStudent(student student){
        student.insert(mSQLiteDatabase);
    }

    public ArrayList<student> retrieveStudentObjects(){
        mStudents = new ArrayList<student>();
        Cursor c = mSQLiteDatabase.query("Student", null, null, null, null, null, null);
        if (c.getCount() > 0){
            while(c.moveToNext()){
                student sObj = new student();
                sObj.initFrom(mSQLiteDatabase, c);
                mStudents.add(sObj);
            }
        }
        return mStudents;
    }

}
