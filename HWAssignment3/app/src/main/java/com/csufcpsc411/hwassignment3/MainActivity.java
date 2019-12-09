package com.csufcpsc411.hwassignment3;



import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;

import com.csufcpsc411.hwassignment3.adapter.SummaryLVAdapter;
import com.csufcpsc411.hwassignment3.model.studentDB;
import com.csufcpsc411.hwassignment3.model.Courses;
import com.csufcpsc411.hwassignment3.model.student;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected SummaryLVAdapter SLV;
    protected ListView mSummaryView;
    protected studentDB mStudentDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStudentDB = new studentDB(this);
        mStudentDB.retrieveStudentObjects();

        setContentView(R.layout.listview);
        mSummaryView = findViewById(R.id.list_view_id);
        SLV = new SummaryLVAdapter(mStudentDB);
        mSummaryView.setAdapter(SLV);
    }
//added for part 2

    //edit
    @Override
    protected void onStart() {
        super.onStart();
        mStudentDB.retrieveStudentObjects();
        SLV.notifyDataSetChanged();
    }

    //display menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.summary_screen, menu);
        menu.findItem(R.id.action_add).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    //add screen
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, StudentAddActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

}
