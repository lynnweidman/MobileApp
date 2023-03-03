package com.example.c196lynnweidman.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196lynnweidman.ENTITY.CoursesEntity;
import com.example.c196lynnweidman.ENTITY.TermsEntity;
import com.example.c196lynnweidman.R;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{

    class CourseViewHolder extends RecyclerView.ViewHolder{
        private final TextView courseItemView;


        private CourseViewHolder(View itemView) {
            super(itemView);
            courseItemView=itemView.findViewById(R.id.viewcoursename);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position= getAdapterPosition();
                    final CoursesEntity current= mCourse.get(position);
                    Intent intent= new Intent(context, CourseDetails.class);
                    intent.putExtra("courseID", current.getCourseID());
                    intent.putExtra("courseName", current.getCourseName());
                    intent.putExtra("start", current.getStart());
                    intent.putExtra("end", current.getEnd());
                    intent.putExtra("status", current.getStatus());
                    intent.putExtra("instructorName", current.getInstructorName());
                    intent.putExtra("instructorPhone", current.getInstructorPhone());
                    intent.putExtra("instructorEmail", current.getInstructorEmail());
                    intent.putExtra("termID", current.getTermID());
                    intent.putExtra("note", current.getNote());

                    context.startActivity(intent);
                }
            });
        }
    }
    private List<CoursesEntity> mCourse;
    private final Context context;
    private final LayoutInflater mInflater;

    public CourseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override

    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.course_list_item,parent,false);
        return new CourseAdapter.CourseViewHolder(itemView); //fixme maybe take out CourseAdapter.
    }

    //sets the textView with the Term name.
    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if(mCourse!=null){
            CoursesEntity current=mCourse.get(position);
            String name=current.getCourseName();
            int termID=current.getTermID();
            holder.courseItemView.setText(name );


        }
        else{
            holder.courseItemView.setText("No Course name");

        }
    }


    public void setCourse(List<CoursesEntity> courses) {
        mCourse= courses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mCourse !=null) {
            return mCourse.size();
        }
        else {
            return 0;
        }
    }

}
