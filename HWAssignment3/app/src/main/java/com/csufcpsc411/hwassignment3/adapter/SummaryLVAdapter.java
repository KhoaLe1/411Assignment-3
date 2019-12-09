package com.csufcpsc411.hwassignment3.adapter;

import android.widget.BaseAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.csufcpsc411.hwassignment3.R;
import com.csufcpsc411.hwassignment3.model.student;
import com.csufcpsc411.hwassignment3.model.studentDB;

public class SummaryLVAdapter extends BaseAdapter {

    studentDB mStudentDB;
    public  SummaryLVAdapter(studentDB sDB){
        mStudentDB = sDB;
    }

    @Override
    public int getCount() {
        return mStudentDB.getStudents().size();
    }

    @Override
    public Object getItem(int position) {
        return mStudentDB.getStudents().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row_view;

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            row_view = inflater.inflate(R.layout.row, parent, false);
        } else row_view = convertView;

        student s = mStudentDB.getStudents().get(position);

        TextView firstNameView = (TextView) row_view.findViewById(R.id.first_name_id);
        TextView lastNameView = (TextView) row_view.findViewById(R.id.last_name_id);
        firstNameView.setText(s.getFirstName());
        lastNameView.setText(s.getLastName());
        row_view.setTag(new Integer(position));

        return row_view;
    }
}
