package com.byted.camp.todolist;

import android.app.Activity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.byted.camp.todolist.beans.Note;
import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.NoteDataBase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NoteActivity extends AppCompatActivity {

    private static final String TAG="write note";
    private EditText editText;
    private Button addBtn;
    private int newitemid=0;
    private int itemnums;
    private Date date=new Date(System.currentTimeMillis());
    private List<Integer> IDs;
    public NoteDataBase noteDataBase;
    private Spinner spinner;
    private String[] PriorityList={"1","2","3","4"};
    private int Priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(R.string.take_a_note);
        TodoListApplication application=(TodoListApplication)this.getApplication();
        noteDataBase=application.getNoteDataBase();
        IDs=noteDataBase.noteDao().getID();
        itemnums=noteDataBase.noteDao().getNums();
        spinner=(Spinner)findViewById(R.id.spinner_pr);
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,PriorityList);
        spinner.setAdapter(arrayAdapter);
        int i=0;
        while (i<=itemnums)
        {
            if(IDs.contains(newitemid))
            {
                newitemid++;
            }
            else
                break;
            i++;
        }
        editText = findViewById(R.id.edit_text);
        editText.setFocusable(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(editText, 0);
        }

        addBtn = findViewById(R.id.btn_add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence content = editText.getText();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(NoteActivity.this,
                            "No content to add", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean succeed = saveNote2Database(content.toString().trim());
                if (succeed) {
                    Toast.makeText(NoteActivity.this,
                            "Note added", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                } else {
                    Toast.makeText(NoteActivity.this,
                            "Error", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Priority=Integer.parseInt(PriorityList[i]);
                Log.d(TAG,"插入的优先级是"+PriorityList[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private boolean saveNote2Database(String content) {
        // TODO 插入一条新数据，返回是否插入成功
        try{
            Log.d(TAG,"新id是： "+String.valueOf(newitemid));
            Note note=new Note(newitemid);
            note.setDate(date);
            note.setContent(content);
            note.setState(State.TODO);
            note.setPriority(Priority);
            noteDataBase.noteDao().insert(note);
            return true;
        }catch (Exception e)
        {
            Log.d(TAG,e.getMessage());
            return false;
        }
    }
}
