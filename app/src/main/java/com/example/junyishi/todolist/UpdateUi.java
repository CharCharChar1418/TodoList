package com.example.junyishi.todolist;

import android.support.v7.widget.RecyclerView;

import java.util.List;

public class UpdateUi {

    public RecyclerView myRecyclerView;
    public RecyclerView.Adapter myAdapter;

    public UpdateUi(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        this.myRecyclerView = recyclerView;
        this.myAdapter = adapter;
    }

    public void update(List<String> tasks) {
        if (myAdapter == null) {
            myAdapter = new TaskAdapter(tasks);
            myRecyclerView.setAdapter(myAdapter);
        } else {
            myAdapter.notifyDataSetChanged();
        }
    }
}
