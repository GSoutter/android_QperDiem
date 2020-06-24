package io.gogz.qperdiem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.gogz.qperdiem.R;
import io.gogz.qperdiem.room_db.ContextQ;
import io.gogz.qperdiem.room_db.ContextWithQuestions;

public class ContextQOnlyListAdapter extends RecyclerView.Adapter<ContextQOnlyListAdapter.ContextQOnlyViewHolder> {



    class ContextQOnlyViewHolder extends RecyclerView.ViewHolder {
        private final TextView contextQItemView;

        private ContextQOnlyViewHolder(View itemView){
            super(itemView);
            contextQItemView = itemView.findViewById(R.id.textViewRecy);
        }

    }



    private final LayoutInflater mInflater;
    private List<ContextQ> mContextQs;
//    private List<ContextWithQuestions> mContextWithQs;

    public ContextQOnlyListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ContextQOnlyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_question_contexts_edit, parent, false);
        return new ContextQOnlyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContextQOnlyViewHolder holder, int position){
        if(mContextQs != null) {
            ContextQ current = mContextQs.get(position);
            String text = current.name;
            holder.contextQItemView.setText(text);
        }
    }

    public void setContextQs(List<ContextQ> contextQs){
        mContextQs = contextQs;
        notifyDataSetChanged();
    }

//    public void setContextWithQs(List<ContextWithQuestions> contextWithQs){
//        mContextWithQs = contextWithQs;
//        notifyDataSetChanged();
//    }

    @Override
    public int getItemCount(){
        if (mContextQs != null)
            return mContextQs.size();
        else return 0;
    }

}

