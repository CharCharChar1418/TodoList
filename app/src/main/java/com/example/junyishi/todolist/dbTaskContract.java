package com.example.junyishi.todolist;

import android.provider.BaseColumns;

public final class dbTaskContract {

    private dbTaskContract(){ }

    public static class taskEntry implements BaseColumns {
        public static final String TABLE_NAME = "Tasks";
        public static final String COLUMN_NAME_TITLE = "Title";
    }

}
