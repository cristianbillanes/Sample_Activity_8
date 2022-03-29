package com.example.sampleactivity_8;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DAOStudent {
    private FirebaseDatabase mDataBase;
    private DatabaseReference mReferenceStudent;
    private List<com.example.sampleactivity_8.Student> students = new ArrayList<>();
    public interface DataStatus{
        void DataIsLoaded(List<com.example.sampleactivity_8.Student> students, List<String> keys);
        void DateIsInserted();
        void DataIsDeleted();
        void DataIsUpdated();
    }
    public DAOStudent()
    {
        mDataBase = FirebaseDatabase.getInstance();
        mReferenceStudent = mDataBase.getReference("Student");
    }
    public void readStudent(final DataStatus dataStatus){
        mReferenceStudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 students.clear();
                 List<String> keys = new ArrayList<>();
                 for(DataSnapshot keyNods: snapshot.getChildren()){
                    keys.add(keyNods.getKey());
                    com.example.sampleactivity_8.Student student = keyNods.getValue(com.example.sampleactivity_8.Student.class);
                    students.add(student);
                 }
                 dataStatus.DataIsLoaded(students,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void addstudent(String idname,com.example.sampleactivity_8.Student student, final DataStatus dataStatus){
        String key = idname;
        mReferenceStudent.child(key).setValue(student)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.DateIsInserted();
                    }
                });
    }
    public void updateStudent(String key, com.example.sampleactivity_8.Student student, final DataStatus dataStatus){
        mReferenceStudent.child(key).setValue(student).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dataStatus.DataIsUpdated();
            }
        });
    }
    public void deleteStudent(String key,final DataStatus dataStatus){
        mReferenceStudent.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }

}
