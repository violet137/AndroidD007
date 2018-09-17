package com.com.greenacademy.englishlearning.Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.greenacademy.englishlearning.R;


public class SentMessageHolder extends RecyclerView.ViewHolder {
    TextView messageText, timeText;

    public SentMessageHolder(@NonNull View itemView) {
        super(itemView);
        messageText = itemView.findViewById(R.id.text_message_body);
        timeText = itemView.findViewById(R.id.text_message_time);
    }
    public void bind(UserMessage message) {
        messageText.setText(message.getMessage());

        // Format the stored timestamp into a readable String using method.
        timeText.setText(message.getCreatedAt());
    }
}
