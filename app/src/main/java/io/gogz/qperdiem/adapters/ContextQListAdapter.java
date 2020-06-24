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

public class ContextQListAdapter extends RecyclerView.Adapter<ContextQListAdapter.ContextQViewHolder> {


    private OnContextQListener mOnContextQListener;

    class ContextQViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView contextQItemView;
        OnContextQListener mOnContextQListener;

        private ContextQViewHolder(View itemView, OnContextQListener mOnContextQListener){
            super(itemView);
            contextQItemView = itemView.findViewById(R.id.textView);
            this.mOnContextQListener = mOnContextQListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onContextQListener.onConextQClick(getAdapterPosition());
        }
    }

    public interface OnContextQListener{
        void onContextQClick(int position);
    }

    private final LayoutInflater mInflater;
    private List<ContextQ> mContextQs;
    private List<ContextWithQuestions> mContextWithQs;

    public ContextQListAdapter(Context context, OnContextQListener onContextQListener) {
        mInflater = LayoutInflater.from(context);
        this.mOnContextQListener = onContextQListener;
    }

    @Override
    public ContextQViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ContextQViewHolder(itemView, mOnContextQListener);
    }

    @Override
    public void onBindViewHolder(ContextQViewHolder holder, int position){
        if(mContextQs != null) {
            ContextQ current = mContextQs.get(position);
            holder.contextQItemView.setText(current.name);
        }
    }

    public void setContextQs(List<ContextQ> contextQs){
        mContextQs = contextQs;
        notifyDataSetChanged();
    }
    public void setContextWithQs(List<ContextWithQuestions> contextWithQs){
        mContextWithQs = contextWithQs;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        if (mContextQs != null)
            return mContextQs.size();
        else return 0;
    }

}
