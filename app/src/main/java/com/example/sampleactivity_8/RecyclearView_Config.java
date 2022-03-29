package com.example.sampleactivity_8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclearView_Config {
    private Context mContext;
    private StudentAdaptor mStudentAdaptor;

    public void setConfig(RecyclerView recyclerView, Context context, List<com.example.sampleactivity_8.Student> students, List<String> keys){
        mContext = context;
        mStudentAdaptor = new StudentAdaptor(students, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mStudentAdaptor);
    }

    class StudentItemView extends RecyclerView.ViewHolder{
        private TextView mName;
        private TextView mAge;
        private TextView mCourse;
        private TextView mYear;
        private TextView mID;
        private Button btnDelete;

        private  String key;

        public StudentItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.student_list_item, parent,false));
            mID = (TextView) itemView.findViewById(R.id.ID);
            mName = (TextView) itemView.findViewById(R.id.Name);
            mAge = (TextView) itemView.findViewById(R.id.Age);
            mCourse = (TextView) itemView.findViewById(R.id.Course);
            mYear = (TextView) itemView.findViewById(R.id.Year);
            btnDelete = (Button) itemView.findViewById(R.id.delete);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new com.example.sampleactivity_8.DAOStudent().deleteStudent(key, new com.example.sampleactivity_8.DAOStudent.DataStatus() {
                        @Override
                        public void DataIsLoaded(List<com.example.sampleactivity_8.Student> students, List<String> keys) {
                        }
                        @Override
                        public void DateIsInserted() { }
                        @Override
                        public void DataIsDeleted() { }
                        @Override
                        public void DataIsUpdated() { }
                    });

                }
            });
        }
        public void bind(com.example.sampleactivity_8.Student student, String key){
            mID.setText("ID: "+key);
            mName.setText("Name: "+student.getName());
            mAge.setText("Age: "+student.getAge());
            mCourse.setText("Course: "+student.getCourse());
            mYear.setText("Year: "+student.getYear());
            this.key = key;
        }
    }
    class StudentAdaptor extends RecyclerView.Adapter<StudentItemView>{
        private List<com.example.sampleactivity_8.Student> mStudentList;
        private  List<String> mkeys;

        public StudentAdaptor(List<com.example.sampleactivity_8.Student> mStudentList, List<String> mkeys) {
            this.mStudentList = mStudentList;
            this.mkeys = mkeys;
        }

        public StudentAdaptor() {
            super();
        }


        @NonNull
        @Override
        public StudentItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new StudentItemView(parent);
        }


        @Override
        public void onBindViewHolder(@NonNull StudentItemView holder, int position) {
            holder.bind(mStudentList.get(position),mkeys.get(position));
        }


        @Override
        public int getItemCount() {
            return mStudentList.size();
        }
    }
}
