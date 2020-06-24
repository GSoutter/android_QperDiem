package io.gogz.qperdiem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.gogz.qperdiem.R;
import io.gogz.qperdiem.room_db.Question;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.QuestionViewHolder> {


    private OnQuestionListener mOnQuestionListener;

    class QuestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView questionItemView;
        OnQuestionListener onQuestionListener;

        private QuestionViewHolder(View itemView, OnQuestionListener onQuestionListener){
            super(itemView);
            questionItemView = itemView.findViewById(R.id.textView);
            this.onQuestionListener = onQuestionListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onQuestionListener.onQuestionClick(getAdapterPosition());
        }
    }

    public interface OnQuestionListener{
        void onQuestionClick(int position);
    }

    private final LayoutInflater mInflater;
    private List<Question> mQuestions;

    public QuestionListAdapter(Context context, OnQuestionListener onQuestionListener) {
        mInflater = LayoutInflater.from(context);
        this.mOnQuestionListener = onQuestionListener;
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new QuestionViewHolder(itemView, mOnQuestionListener);
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, int position){
        if(mQuestions != null) {
            Question current = mQuestions.get(position);
            holder.questionItemView.setText(current.text);
        }
    }

    public void setQuestions(List<Question> questions){
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
