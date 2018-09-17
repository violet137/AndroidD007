package com.com.greenacademy.englishlearning.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.com.greenacademy.englishlearning.Holder.ReceivedMessageHolder;
import com.com.greenacademy.englishlearning.Holder.SentMessageHolder;
import com.com.greenacademy.englishlearning.Holder.UserMessage;
import com.greenacademy.englishlearning.R;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;


    private Context mContext;
    private List<UserMessage> mMessageList;

    public MessageListAdapter(Context mContext, List<UserMessage> mMessageList) {
        this.mContext = mContext;
        this.mMessageList = mMessageList;
    }

    @Override
    public int getItemViewType(int position) {
        UserMessage message = mMessageList.get(position);

        if(message.getSender() != null){
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }else{
            return VIEW_TYPE_MESSAGE_SENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserMessage message = mMessageList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }

    }
    @Override
    public int getItemCount() {
        return mMessageList.size();
    }
}
