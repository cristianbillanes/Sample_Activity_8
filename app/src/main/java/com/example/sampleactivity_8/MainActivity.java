package com.example.sampleactivity_8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private RecyclerView mRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycleView = (RecyclerView) findViewById(R.id.StudentRecord);
        Grade_View();

    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Grade_View();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }
    private void Grade_View(){
        new DAOStudent().readStudent(new DAOStudent.DataStatus() {
            @Override
            public void DataIsLoaded(List<Student> students, List<String> keys) {
                new RecyclearView_Config().setConfig(mRecycleView,MainActivity.this,students, keys);
            }
            @Override
            public void DateIsInserted() { }

            @Override
            public void DataIsUpdated() { }

            @Override
            public void DataIsDeleted() { }
        });
    }
}