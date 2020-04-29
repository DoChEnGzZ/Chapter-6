package com.byted.camp.todolist.db;

import android.animation.TypeConverter;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;


import com.byted.camp.todolist.beans.Note;
@Database(entities = {Note.class},version = 1,exportSchema = false)
public abstract class NoteDataBase extends RoomDatabase {
    private static final String DB_Name="Note.db";

    public abstract NoteDao noteDao();
}
