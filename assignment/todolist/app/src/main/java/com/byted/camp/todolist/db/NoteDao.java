package com.byted.camp.todolist.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.byted.camp.todolist.beans.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT* FROM notes")
    List<Note> getAll();

    @Query("SELECT* FROM notes WHERE id == :nid")
    Note getNote(long nid);

    @Query("SELECT COUNT(*) FROM notes")
    int getNums();

    @Query("SELECT id From notes")
    List<Integer> getID();

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Insert
    void insert(Note note);
}
