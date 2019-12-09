package com.csufcpsc411.hwassignment3.model;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;

public class Courses extends PersistentObject {
    protected String mCourseID;
    protected String mGrade;
    protected String mStudent;

    public Courses(String courseID, String grade, String student) {
        mCourseID = courseID;
        mGrade = grade;
        mStudent = student;
    }

    public Courses() {}

    public String getCourseID() {
        return mCourseID;
    }

    public void setCourseID(String courseID) {
        mCourseID = courseID;
    }

    public String getGrade() {
        return mGrade;
    }

    public void setGrade(String grade) {
        mGrade = grade;
    }

    public String getStudent() {
        return mStudent;
    }

    public void setStudent(String student) {
        mStudent = student;
    }

    @Override
    public void insert(SQLiteDatabase db) {
        ContentValues value = new ContentValues();
        value.put("CourseID", mCourseID);
        value.put("Grade", mGrade);
        value.put("Student", mStudent);
        db.insert("CourseEnrollment", null, value);
    }

    @Override
    public void initFrom(SQLiteDatabase db, Cursor cursor) {
        mCourseID = cursor.getString(cursor.getColumnIndex("CourseID"));
        mGrade = cursor.getString(cursor.getColumnIndex("Grade"));
        mStudent = cursor.getString(cursor.getColumnIndex("Student"));
    }
}
