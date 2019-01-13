package com.example.junyishi.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView myTaskList;
    public RecyclerView.Adapter myAdapter;
    public RecyclerView.LayoutManager myLayoutManager;

    private SQLiteDatabase db;
    private dbTaskHelper dbHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTaskList = findViewById(R.id.taskList);
        myLayoutManager = new LinearLayoutManager(this);
        myTaskList.setHasFixedSize(true);
        myTaskList.setLayoutManager(myLayoutManager);
        myTaskList.addItemDecoration(new ItemDividerDecoration(this, R.drawable.divider));

        dbHelper = new dbTaskHelper(this);





//        List<String> mDatas = new ArrayList<String>();
//        for (int i = 'A'; i < 'z'; i++)
//        {
//            mDatas.add("" + (char) i);
//        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTaskList();
        Log.e("MainActivity" ,"Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("MainActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("MainActivity","onStop");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addTask:
                Intent addIntent = new Intent(this, AddTask.class);
                startActivity(addIntent);


            case R.id.setting:
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }

    private void updateTaskList() {

        List<String> tasks = updateList();

            myAdapter = new TaskAdapter(tasks);
            myTaskList.setAdapter(myAdapter);



    }

    private List<String> updateList() {
        List<String> list = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(dbTaskContract.taskEntry.TABLE_NAME,
                new String[]{dbTaskContract.taskEntry._ID, dbTaskContract.taskEntry.COLUMN_NAME_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(dbTaskContract.taskEntry.COLUMN_NAME_TITLE);
            list.add(cursor.getString(idx));
        }
        cursor.close();
        db.close();
        return list;
    }

}
