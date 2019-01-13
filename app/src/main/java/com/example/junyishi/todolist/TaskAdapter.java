package com.example.junyishi.todolist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<String> myDataset;

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TaskViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.taskTitle);
        }
    }

    public TaskAdapter (List<String> dataset) {
        myDataset = dataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create a new view
//        TextView tv =  (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.tasks_to_do, parent, false);
//        TaskViewHolder vh = new TaskViewHolder(tv);
        TaskViewHolder holder = new TaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tasks_to_do, parent, false));
        return holder;
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder (TaskViewHolder viewHolder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        viewHolder.textView.setText(myDataset.get(position));
    }

    //Return the size of the database
    @Override
    public int getItemCount() {
        return myDataset.size();
    }
}
