package com.github.jmsoft.socketclient.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.jmsoft.socketclient.model.Message;

import java.util.List;

import socketclient.lg.com.socketclient.R;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<Message> chatMessagesList;
    private String loggedUser;
    private int lastPosition = -1;
    private Context context;

    public ChatAdapter(List<Message> historyList, String loggedUser, Context context) {
        this.chatMessagesList = historyList;
        this.loggedUser = loggedUser;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return chatMessagesList.size();
    }

    @Override
    public void onBindViewHolder(final ChatViewHolder holder, int position) {
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.chat_card, viewGroup, false);

        return new ChatViewHolder(itemView);
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        protected CardView cardView;
        protected TextView tvName;

        public ChatViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.card_view);
            tvName =  (TextView) v.findViewById(R.id.tvChat);
        }
    }
}
