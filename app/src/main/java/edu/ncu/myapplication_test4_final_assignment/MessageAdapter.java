package edu.ncu.myapplication_test4_final_assignment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{

    private List<Message> messageList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView content;

        public ViewHolder(View view) {
            super(view);
            avatar = (ImageView)view.findViewById(R.id.avatar);
            content = (TextView)view.findViewById(R.id.content);
        }

    }
    public MessageAdapter(List<Message> mList) {
        messageList = mList;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message=messageList.get(position);
        holder.avatar.setImageResource(message.getAvatar());
        holder.content.setText(message.getContent());

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
    // 添加数据
    public void addData(int position, Message message) {
        // 在list中添加数据，并通知条目加入一条
        messageList.add(position, message);
        // 添加动画
        notifyItemInserted(position);
    }

    // 删除数据
    public void removeData(int position) {
        messageList.remove(position);
        // 删除动画

        notifyItemRemoved(position);
    }

    public void moveToPresent(RecyclerView recyclerView) {
        recyclerView.smoothScrollToPosition(messageList.size());
    }

}
