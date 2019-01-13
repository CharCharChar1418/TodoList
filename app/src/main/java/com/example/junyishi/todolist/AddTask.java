package com.example.junyishi.todolist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AddTask extends AppCompatActivity {

    public EditText editText;
    public dbTaskHelper myDbHelper;
    public SQLiteDatabase db;

    private FirebaseDatabase myDatabase;
    private DatabaseReference myRef = myDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        editText = findViewById(R.id.editTask);
        myDbHelper = new dbTaskHelper(this);
        db = myDbHelper.getReadableDatabase();


    }

    public void addButtonClicked(View v) {

        String newtask = editText.getText().toString();
        ContentValues values = new ContentValues();
        values.put(dbTaskContract.taskEntry.COLUMN_NAME_TITLE, newtask);
        db.insertWithOnConflict(dbTaskContract.taskEntry.TABLE_NAME,
                                null,
                                values,
                                SQLiteDatabase.CONFLICT_REPLACE);

        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyy h:mm a");
        String dataString = sdf.format(date);

        Task task = new Task(newtask, dataString);
        myRef.child("Tasks").setValue(task);
//        myRef = database.getInstance().getReference().child("Task");
//
//        DatabaseReference newTask = myRef.push();
//        newTask.child("name").setValue(name);
//        newTask.child("time").setValue(dataString);

    }


}
