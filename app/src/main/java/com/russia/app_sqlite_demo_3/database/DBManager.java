package com.russia.app_sqlite_demo_3.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.russia.app_sqlite_demo_3.model.Student;

import java.util.ArrayList;

/**
 * Created by VLADIMIR PUTIN on 3/1/2018.
 */

public class DBManager extends SQLiteOpenHelper {
    private final String TAG = "DBManager";
    private static final String DATABASE_NAME = "students_manager";
    private static final String TABLE_NAME = "students";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "address";
    private static int VERSION = 1;
    private Context context;
    private String SQLQuery = "create table " + TABLE_NAME + "(" +
            ID + " integer primary key, " + NAME + " text, " + PHONE + " text, " +
            EMAIL + " text, " + ADDRESS + " text)";

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
        Log.d(TAG, "DBManager:");
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
        Log.d(TAG, "onCreate:");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpgrade:");
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, student.getName());
        values.put(PHONE, student.getTelephone());
        values.put(EMAIL, student.getEmail());
        values.put(ADDRESS, student.getAddress());

        db.insert(TABLE_NAME, null, values);
        db.close();
        Log.d(TAG, "addStudent Successfully");
    }

    public ArrayList<Student> getAllStudent() {
        ArrayList<Student> studentArrayList = new ArrayList<>();
        String selectQuery = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setIdStudent(cursor.getInt(0));
                student.setTelephone(cursor.getString(1));
                student.setEmail(cursor.getString(2));
                student.setAddress(cursor.getString(3));
                studentArrayList.add(student);
            } while (cursor.moveToNext());
        }
        db.close();
        return studentArrayList;
    }

    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, student.getName());
        contentValues.put(PHONE, student.getTelephone());
        contentValues.put(EMAIL, student.getEmail());
        contentValues.put(ADDRESS, student.getAddress());
        return db.update(TABLE_NAME, contentValues, ID + "=?", new String[]{String.valueOf(student.getIdStudent())});
    }

    public int deleteStudent(Student student) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(student.getIdStudent())});
    }
}
