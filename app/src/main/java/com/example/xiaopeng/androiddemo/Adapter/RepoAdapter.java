package com.example.xiaopeng.androiddemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiaopeng.androiddemo.Activity.RecycleViewActivity;
import com.example.xiaopeng.androiddemo.Bean.RepoEntity;
import com.example.xiaopeng.androiddemo.R;

import java.util.List;

/**
 * Created by xiaopeng on 16/7/20.
 */
public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    private LayoutInflater mInflater;
    private List<RepoEntity> mDatas;

    public RepoAdapter(Context context, List<RepoEntity> datas) {
           this.mDatas = datas;
           mInflater = LayoutInflater.from(context);
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RepoViewHolder holder = new RepoViewHolder(mInflater.inflate(R.layout.repo_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final RepoViewHolder holder, int position) {
        String fullName = mDatas.get(position).getName();
        holder.tv.setText(fullName);


        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(int position) {
        RepoEntity repo = new RepoEntity();
        repo.setName("new repo");
        mDatas.add(position, repo);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    static class RepoViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv;
        public RepoViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.holder_name);
        }
    }
}
