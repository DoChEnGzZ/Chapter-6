package com.byted.camp.todolist.beans;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.byted.camp.todolist.db.TypeChange;

import java.util.Date;



/**
 * Created on 2019/1/23.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
@TypeConverters(value = {TypeChange.class,TypeConverterUtils.class})
@Entity(tableName = "notes")
public class Note {
    @PrimaryKey
    public final long id;
    /**
     * 新增的priority属性应该是1~4之间的某个数，等级越高越优先。
     * 展示效果上，4级为红色 3级为黄色 2级为蓝色
     */
    @ColumnInfo(name = "priority")
    private int priority;
    @ColumnInfo(name = "date")
    private Date date;
    @ColumnInfo(name = "state")
    private State state;
    @ColumnInfo(name = "content")

    private String content;
    public Note(long id) {
        this.id = id;
    }

    /**
     *
     * @param priority
     * 新增了priority属性
     */
    public void setPriority(int priority){this.priority=priority;}
    public int getPriority(){return priority;}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
