package com.byted.camp.todolist.beans;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class TypeConverterUtils {
    @TypeConverter
    public String dateToTimestamp(Date date){
        if(date != null)return date.toString();
        return null;
    }

    @TypeConverter
    public Date fromTimeStamp(String timeStamp){
        if(timeStamp == null)return null;
        return new Date(timeStamp);
    }

}
