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
import com.example.c196lynnweidman.ENTITY.ObjectiveAssessment;
import com.example.c196lynnweidman.R;

import java.util.List;

public class ObjectiveAdapter extends RecyclerView.Adapter<ObjectiveAdapter.ObjectiveViewHolder> {
    class ObjectiveViewHolder extends RecyclerView.ViewHolder{
        private final TextView objectiveItemView;


        private ObjectiveViewHolder(View itemView) {
            super(itemView);
            objectiveItemView=itemView.findViewById(R.id.objectiveTextView); //fixme

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position= getAdapterPosition();
                    final ObjectiveAssessment current= mObjectiveAssessment.get(position);
                    Intent intent= new Intent(context, ObjectiveAssessmentUI.class); //fixme
                    intent.putExtra("objectiveID", current.getObjectiveID());
                    intent.putExtra("objectiveAssessmentName", current.getObjectiveAssessmentName());
                    intent.putExtra("objectiveAssessmentDate", current.getObjectiveAssessmentDate());
                    intent.putExtra("courseID", current.getCourseID());

                    context.startActivity(intent);
                }
            });
        }
    }
    private List<ObjectiveAssessment> mObjectiveAssessment;
    private final Context context;
    private final LayoutInflater mInflater;

    public ObjectiveAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override

    public ObjectiveAdapter.ObjectiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.assesment_list_item,parent,false);
        return new ObjectiveViewHolder(itemView);
    }

    //sets the textView with the Term name.
    @Override
    public void onBindViewHolder(@NonNull ObjectiveAdapter.ObjectiveViewHolder holder, int position) {
        if(mObjectiveAssessment!=null){
            ObjectiveAssessment current=mObjectiveAssessment.get(position);
            int objectiveID = current.getObjectiveID();
            String name=current.getObjectiveAssessmentName();
            String date = current.getObjectiveAssessmentDate();
            int courseID=current.getCourseID();
            //holder.objectiveItemView.setText(objectiveID + name + date + courseID );
            holder.objectiveItemView.setText( name + date);


        }
        else{
            holder.objectiveItemView.setText("No Course name");

        }
    }



    public void setObjectiveAssessment(List<ObjectiveAssessment> objectiveAssessment) {
        mObjectiveAssessment= objectiveAssessment;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mObjectiveAssessment !=null) {
            return mObjectiveAssessment.size();
        }
        else {
            return 0;
        }
    }

}
