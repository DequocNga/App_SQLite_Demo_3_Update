package com.russia.app_sqlite_demo_3.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.russia.app_sqlite_demo_3.R;
import com.russia.app_sqlite_demo_3.adapter.CustomAdapter;
import com.russia.app_sqlite_demo_3.database.DBManager;
import com.russia.app_sqlite_demo_3.model.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextID;
    EditText editTextName;
    EditText editTextPhone;
    EditText editTextEmail;
    EditText editTextAddress;
    Button buttonSave;
    Button buttonUpdate;
    ListView listViewShow;
    DBManager dbManager = new DBManager(this);
    CustomAdapter customAdapter;
    ArrayList<Student> studentArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();
        /*load data from database*/
        studentArrayList = dbManager.getAllStudent();
        setAdapter();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = createStudent();
                if (student != null) {
                    dbManager.addStudent(student);
                }
                updateListStudent();
                setAdapter();
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setIdStudent(Integer.parseInt(String.valueOf(editTextID.getText())));
                student.setName(editTextName.getText().toString());
                student.setTelephone(editTextPhone.getText().toString());
                student.setEmail(editTextEmail.getText().toString());
                student.setAddress(editTextAddress.getText().toString());
                int result = dbManager.updateStudent(student);
                if (result > 0) {
                    updateListStudent();
                }
                buttonSave.setEnabled(true);
                buttonUpdate.setEnabled(false);
            }
        });
        listViewShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = studentArrayList.get(i);
                editTextID.setText(student.getIdStudent() + "");
                editTextName.setText(student.getName());
                editTextPhone.setText(student.getTelephone());
                editTextEmail.setText(student.getEmail());
                editTextAddress.setText(student.getAddress());
                buttonSave.setEnabled(false);
                buttonUpdate.setEnabled(true);
            }
        });
        listViewShow.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = studentArrayList.get(i);
                int result = dbManager.deleteStudent(student.getIdStudent());
                if(result > 0){
                    Toast.makeText(MainActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Delete fail", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    private void updateListStudent() {
        studentArrayList.clear();
        studentArrayList.addAll(dbManager.getAllStudent());
        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
        }
        editTextName.setText("");
        editTextPhone.setText("");
        editTextEmail.setText("");
        editTextAddress.setText("");
    }

    private Student createStudent() {
        String name = editTextName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        Student student = new Student(name, phone, email, address);
        return student;
    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.item_list_student, studentArrayList);
            listViewShow.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            /*vi bo dem bat dau tu vi tri thu 0 nen ta se phai giam vi tri di 1 don vi, moi dung vi tri
            * dang chon hien tai tren man hinh*/
            listViewShow.setSelection(customAdapter.getCount() - 1);
        }
    }

    private void connectView() {
        editTextID = findViewById(R.id.edt_id);
        editTextName = findViewById(R.id.edt_name);
        editTextPhone = findViewById(R.id.edt_telephone);
        editTextEmail = findViewById(R.id.edt_email);
        editTextAddress = findViewById(R.id.edt_address);
        buttonSave = findViewById(R.id.btn_save);
        buttonUpdate = findViewById(R.id.btn_update);
        listViewShow = findViewById(R.id.lv_showList);
    }
}
