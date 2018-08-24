package com.com.greenacademy.englishlearning.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.com.greenacademy.englishlearning.Activity.ListenSkillActivity;
import com.com.greenacademy.englishlearning.Adapter.TopicAdapter;
import com.com.greenacademy.englishlearning.Interface.ItemClickTopic;
import com.greenacademy.englishlearning.R;

public class MainBackgroundFragment extends Fragment implements ItemClickTopic {
    TextView tvPoint;
    ImageView btnMenuChattingBot;
    ConstraintLayout btnStartChattingBot;
    FrameLayout frameTopic;
    RecyclerView recyclerViewTopic;
    Boolean choosedTopic;
    ImageView imgChooseTopic;
    int[] imgResTopicID;
    String[] tvResTopic;
    FragmentManager fragmentManager;
    PositionClickTopic positionClickTopic;
    int iPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_background, container, false);
        tvPoint = view.findViewById(R.id.tv_point_chatting_bot);
        btnStartChattingBot = view.findViewById(R.id.btn_start_chatting_bot);
        btnMenuChattingBot = view.findViewById(R.id.btn_menu_chatting_bot);
        frameTopic = view.findViewById(R.id.frame_topic);
        recyclerViewTopic = view.findViewById(R.id.recycleview_topic);
        recyclerViewTopic.setLayoutManager(new GridLayoutManager(getContext(), 3));

        imgChooseTopic = view.findViewById(R.id.img_choose_topic);
        choosedTopic = false;
        fragmentManager = getActivity().getSupportFragmentManager();
        btnMenuChattingBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTopic();
                frameTopic.setVisibility(View.VISIBLE);
            }
        });
        btnStartChattingBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positionClickTopic.positionTopic(iPosition, choosedTopic);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        positionClickTopic = (PositionClickTopic) getActivity();
    }


    public void getTopic() {
        tvResTopic = getResources().getStringArray(R.array.arr_name_topic);
        final TypedArray typedArray = getResources().obtainTypedArray(R.array.arr_image_topic);
        imgResTopicID = new int[tvResTopic.length];
        for (int i = 0; i < tvResTopic.length; i++) {
            imgResTopicID[i] = typedArray.getResourceId(i, 0);
        }
        typedArray.recycle();
        recyclerViewTopic.setAdapter(new TopicAdapter(getContext(), imgResTopicID, tvResTopic, this));
    }

    @Override
    public void onItemClick(View view, int position) {
//        iPosition = position;
//        choosedTopic = true;
//        imgChooseTopic.setImageResource(imgResTopicID[position]);
//        frameTopic.setVisibility(View.INVISIBLE);

        switch (position) {
            case 5:
                Intent intent = new Intent(getActivity(), ListenSkillActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }

    public interface PositionClickTopic {
        void positionTopic(int position, boolean isChoosedTopic);
    }
}
