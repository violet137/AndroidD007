package com.com.greenacademy.englishlearning.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.englishlearning.R;


public class ReceivedMessageHolder extends RecyclerView.ViewHolder {
    TextView messageText, timeText, nameText;
    ImageView profileImage;

    public ReceivedMessageHolder(View itemView) {
        super(itemView);
        messageText = itemView.findViewById(R.id.text_message_body);
        timeText = itemView.findViewById(R.id.text_message_time);
        nameText =  itemView.findViewById(R.id.text_message_name);
        profileImage = itemView.findViewById(R.id.image_message_profile);
    }

    public void bind(UserMessage message) {
        messageText.setText(message.getMessage());

        timeText.setText(message.getCreatedAt());
        nameText.setText(message.getSender().getNickname());
        profileImage.setImageResource(R.drawable.circle);
    }
}
