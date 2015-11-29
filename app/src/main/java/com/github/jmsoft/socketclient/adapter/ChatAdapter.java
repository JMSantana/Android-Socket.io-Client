package com.github.jmsoft.socketclient.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.github.jmsoft.socketclient.model.Message;

import java.util.List;

import socketclient.lg.com.socketclient.R;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.HistoryViewHolder> {

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
    public void onBindViewHolder(final HistoryViewHolder holder, int position) {
        Message chatMessage = chatMessagesList.get(position);
        if(chatMessage.getType() == Message.TYPE_MESSAGE) {
            holder.tvName.setText(chatMessage.getUsername() + ": " + chatMessage.getMessage());
        } else {
            holder.tvName.setText(chatMessage.getMessage());
        }

        // Else is needed because when the view is recycled, Android needs to know which color will be used
        if(!loggedUser.equals(chatMessage.getUsername())){
            holder.tvName.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.tvName.setBackgroundColor(Color.parseColor("#E1FFC7"));
        }

        // Apply the animation when the view is bound
        setAnimation(holder.cardView, position);
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position >= lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.chat_card, viewGroup, false);

        return new HistoryViewHolder(itemView);
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        protected CardView cardView;
        protected TextView tvName;

        public HistoryViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.card_view);
            tvName =  (TextView) v.findViewById(R.id.tvChat);
        }
    }
}
