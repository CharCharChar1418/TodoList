package com.example.junyishi.todolist;

import java.net.PortUnreachableException;

public class Task {

    public String taskName;
    public String inputTime;

    public Task (String name, String time) {
        this.taskName = name;
        this.inputTime = time;
    }
}
