package com.byted.camp.todolist.db;
import android.arch.persistence.room.TypeConverter;

import com.byted.camp.todolist.beans.State;

public class TypeChange {
    @TypeConverter
    public Boolean EnumtoBool(State state){
        if(state.intValue==0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @TypeConverter
    public State BooltoEnum(Boolean a)
    {
        if(a)
        {
            return State.DONE;
        }
        else
            return State.TODO;
    }
}
