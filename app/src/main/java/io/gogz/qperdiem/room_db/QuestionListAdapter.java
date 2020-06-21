package io.gogz.qperdiem.room_db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.gogz.qperdiem.R;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.QuestionViewHolder> {

    class QuestionViewHolder extends RecyclerView.ViewHolder {
        private final TextView questionItemView;

        private QuestionViewHolder(View itemView){
            super(itemView);
            questionItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<QuestionWithRatings> mQuestions;

    public QuestionListAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new QuestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, int position){
        if(mQuestions != null) {
            QuestionWithRatings current = mQuestions.get(position);
            holder.questionItemView.setText(current.question.text);
        }
    }

    public void setQuestions(List<QuestionWithRatings> questions){
        mQuestions = questions;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        if (mQuestions != null)
            return mQuestions.size();
        else return 0;
    }

}
