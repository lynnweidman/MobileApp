package com.example.c196lynnweidman.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196lynnweidman.ENTITY.TermsEntity;
import com.example.c196lynnweidman.R;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    class TermViewHolder extends RecyclerView.ViewHolder{
       // private final TextView termItemView;
        private final TextView listItemView;

        private TermViewHolder(View itemView) {
            super(itemView);
            listItemView=itemView.findViewById(R.id.listItemTextView); //fixme the ID
           // termItemView=itemView.findViewById(R.id.viewcoursename);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int positon= getAdapterPosition();
                    final TermsEntity current= mTerms.get(positon);
                    //Intent intent1= new Intent(context, TermsList.class);
                    Intent intent= new Intent(context, CourseList.class);

                    intent.putExtra("termID", current.getTermID());
                    intent.putExtra("termName", current.getTermName());
                    intent.putExtra("start", current.getStart());
                    intent.putExtra("end", current.getEnd());
                    context.startActivity(intent);
                }
            });
        }
}
    private List<TermsEntity> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;

    public TermAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.activity_terms_list_item,parent,false);
        return new TermViewHolder(itemView);
    }

    //sets the textView with the Term name.
    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if(mTerms!=null){
            TermsEntity current=mTerms.get(position);
            String name=current.getTermName();
            String start=current.getStart();
            holder.listItemView.setText(name + start);

        }
        else{
            holder.listItemView.setText("No Term name");
        }
    }


    public void setTerms(List<TermsEntity> terms) {
        mTerms= terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mTerms !=null) {
            return mTerms.size();
        }
        else {
            return 0;
        }
    }
}
