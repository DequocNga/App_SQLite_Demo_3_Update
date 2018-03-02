package com.russia.app_sqlite_demo_3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.russia.app_sqlite_demo_3.R;
import com.russia.app_sqlite_demo_3.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VLADIMIR PUTIN on 3/2/2018.
 */

public class CustomAdapter extends ArrayAdapter<Student> {

    Context context;
    int resource;
    ArrayList<Student> studentArrayList = new ArrayList<>();

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.studentArrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_student, parent, false);
            viewHolder.textViewID = convertView.findViewById(R.id.tv_idSV);
            viewHolder.textViewName = convertView.findViewById(R.id.tv_name);
            viewHolder.textViewPhone = convertView.findViewById(R.id.tv_phone);
            viewHolder.textViewEmail = convertView.findViewById(R.id.tv_email);
            viewHolder.textViewAddress = convertView.findViewById(R.id.tv_address);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Student student = studentArrayList.get(position);
        viewHolder.textViewID.setText(student.getIdStudent() + "");
        viewHolder.textViewName.setText(student.getName());
        viewHolder.textViewPhone.setText(student.getTelephone());
        viewHolder.textViewEmail.setText(student.getEmail());
        viewHolder.textViewAddress.setText(student.getAddress());

        return convertView;
    }

    public class ViewHolder {
        TextView textViewID;
        TextView textViewName;
        TextView textViewPhone;
        TextView textViewEmail;
        TextView textViewAddress;
    }
}
