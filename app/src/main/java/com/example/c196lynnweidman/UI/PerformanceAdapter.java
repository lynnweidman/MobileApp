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
import com.example.c196lynnweidman.ENTITY.PerformanceAssessment;
import com.example.c196lynnweidman.R;

import java.util.List;

public class PerformanceAdapter extends RecyclerView.Adapter<PerformanceAdapter.PerformanceViewHolder> {
    class PerformanceViewHolder extends RecyclerView.ViewHolder{
        private final TextView performanceItemView;


        private PerformanceViewHolder(View itemView) {
            super(itemView);
            performanceItemView=itemView.findViewById(R.id.performanceTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position= getAdapterPosition();
                    final PerformanceAssessment current= mPerformanceAssessment.get(position);
                    Intent intent= new Intent(context, PerformanceAssessmentUI.class); //fixme
                    intent.putExtra("performanceID", current.getPerformanceID());
                    intent.putExtra("performanceAssessmentName", current.getPerformanceAssessmentName());
                    intent.putExtra("performanceAssessmentDate", current.getPerformanceAssessmentDate());
                    intent.putExtra("courseID", current.getCourseID());

                    context.startActivity(intent);
                }
            });
        }
    }
    private List<PerformanceAssessment> mPerformanceAssessment;
    private final Context context;
    private final LayoutInflater mInflater;

    public PerformanceAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override

    public PerformanceAdapter.PerformanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.assesment_list_item,parent,false);
        return new PerformanceViewHolder(itemView);
    }

    //sets the textView with the Term name.
    @Override
    public void onBindViewHolder(@NonNull PerformanceViewHolder holder, int position) {
        if(mPerformanceAssessment!=null){
            PerformanceAssessment current=mPerformanceAssessment.get(position);
            int performanceID = current.getPerformanceID();
            String name=current.getPerformanceAssessmentName();
            String date = current.getPerformanceAssessmentDate();
            int courseID=current.getCourseID();
           // holder.performanceItemView.setText(performanceID + name + date + courseID);
            holder.performanceItemView.setText(name + date);


        }
        else{
            holder.performanceItemView.setText("No Course name");

        }
    }

    public void setPerformanceAssessment(List<PerformanceAssessment> performanceAssessment) {
        mPerformanceAssessment= performanceAssessment;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mPerformanceAssessment.size();
        }
    }


