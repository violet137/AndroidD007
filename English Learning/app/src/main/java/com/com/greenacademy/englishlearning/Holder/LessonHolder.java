package com.com.greenacademy.englishlearning.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.greenacademy.englishlearning.R;

public class LessonHolder extends RecyclerView.ViewHolder {

    View view;
    public TextView tvTitle, tvDesc;
    public ImageView background;

    public LessonHolder(View itemView) {
        super(itemView);
        view = itemView;
        tvTitle = view.findViewById(R.id.title);

        tvDesc = view.findViewById(R.id.desc);

        background = view.findViewById(R.id.image);

    }
}
