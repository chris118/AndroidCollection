package com.example.xiaopeng.androiddemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiaopeng.androiddemo.R;

import java.util.List;

/**
 * Created by xiaopeng on 16/7/27.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private LayoutInflater mInflater;
    private List<String> mDatas;

    public MainAdapter(Context context, List<String> datas) {
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MainViewHolder holder = new MainViewHolder(mInflater.inflate(R.layout.repo_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, int position) {
        String fullName = mDatas.get(position);
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

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv;
        public MainViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.holder_name);
        }
    }
}
