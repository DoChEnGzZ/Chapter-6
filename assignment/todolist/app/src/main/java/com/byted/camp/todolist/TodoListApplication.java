package com.byted.camp.todolist;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.byted.camp.todolist.db.NoteDataBase;
import com.facebook.stetho.Stetho;


/**
 * @author wangzhongshan
 * @date 2020-04-19.
 */
public class TodoListApplication extends Application {

    public NoteDataBase noteDataBase;
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        noteDataBase= Room.databaseBuilder(getApplicationContext(),NoteDataBase.class,"Note.db")
                .allowMainThreadQueries()
                .build();
        noteDataBase.close();
    }

    public NoteDataBase getNoteDataBase()
    {
        return noteDataBase;
    }
}
