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
    public TextView tvTitle, tvDesc, tvTitleVs, tvDescVs;
    public ImageView background;
    public Button btnStart;
    public LinearLayout slideHelperBack, slideHelperNext;

    public LessonHolder(View itemView) {
        super(itemView);
        view = itemView;
        tvTitle = view.findViewById(R.id.title);
        tvTitleVs = view.findViewById(R.id.titleTrans);
        tvDesc = view.findViewById(R.id.desc);
        tvDescVs = view.findViewById(R.id.descTrans);
        background = view.findViewById(R.id.image);
        btnStart = view.findViewById(R.id.btnStart);
        slideHelperNext = view.findViewById(R.id.helperNext);
        slideHelperBack = view.findViewById(R.id.helperBack);
    }
}
