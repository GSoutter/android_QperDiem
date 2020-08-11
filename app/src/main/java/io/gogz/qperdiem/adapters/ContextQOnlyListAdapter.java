package io.gogz.qperdiem.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.gogz.qperdiem.R;
import io.gogz.qperdiem.room_db.ContextQ;
import io.gogz.qperdiem.room_db.QuestionWithContexts;

public class ContextQOnlyListAdapter extends RecyclerView.Adapter<ContextQOnlyListAdapter.ContextQOnlyViewHolder> {


    private static final String TAG = "ContextQOnlyListAdapter";
    private OnContextToggleListener mOnContextToggleListener;

    public ContextQOnlyListAdapter(Context context, OnContextToggleListener onContextListener) {
        mInflater = LayoutInflater.from(context);
        this.mOnContextToggleListener = onContextListener;
    }

    class ContextQOnlyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        private final TextView contextQItemView;
        private final ToggleButton contextQToggle;
        OnContextToggleListener onContextToggleListener;

        private ContextQOnlyViewHolder(View itemView, OnContextToggleListener onContextToggleListener){
            super(itemView);
            contextQItemView = itemView.findViewById(R.id.textViewRecy);
            contextQToggle = itemView.findViewById(R.id.toggleButtonRecy);

            this.onContextToggleListener = onContextToggleListener;
        }

        public void onClick(View view) {
            onContextToggleListener.onContextToggleClick(getAdapterPosition());
        }


    }

    public interface OnContextToggleListener {
        void onContextToggleClick(int position);
    }


    private final LayoutInflater mInflater;
    private List<ContextQ> mContextQs;
    private QuestionWithContexts mQuestionWithContext;


    @Override
    public ContextQOnlyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_question_contexts_edit, parent, false);
        return new ContextQOnlyViewHolder(itemView, mOnContextToggleListener);
    }

    @Override
    public void onBindViewHolder(ContextQOnlyViewHolder holder, int position){
        if(mContextQs != null) {
            ContextQ current = mContextQs.get(position);
            String text = current.name;

            holder.contextQItemView.setText(text);

            if (mQuestionWithContext != null){
                holder.contextQToggle.setChecked(mQuestionWithContext.containsContext(current));

            }
        }
    }

    public void setContextQs(List<ContextQ> contextQs){
        mContextQs = contextQs;
        notifyDataSetChanged();
    }

    public void setQuestionWithContext(QuestionWithContexts contextWithQs){
        Log.d(TAG, "setQuestionWithContext: ");
        mQuestionWithContext = contextWithQs;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        if (mContextQs != null)
            return mContextQs.size();
        else return 0;
    }

}

