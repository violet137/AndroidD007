package com.com.greenacademy.englishlearning.Fragment;


import android.content.ClipData;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.greenacademy.englishlearning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowChooseWordFragment extends Fragment {
    LinearLayout linearShowWord;
    String[] arrUserMess;
    private float oldXvalue;
    private float oldYvalue;
    public ShowChooseWordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_choose_word, container, false);
        linearShowWord = view.findViewById(R.id.linear_show_word);
        linearShowWord.setOrientation(LinearLayout.HORIZONTAL);
        linearShowWord.setHorizontalScrollBarEnabled(true);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5,5,5,5);

        arrUserMess = getResources().getStringArray(R.array.arr_conversation_topic);
        String statement = arrUserMess[0];
        String[] words = statement.split(" ");
        for(int i=0;i<words.length;i++){
            final Button btnWord = new Button(getContext());
            btnWord.setText(words[i]);
            btnWord.setTextSize(15);
            btnWord.setPadding(5,5,5,5);
            btnWord.setTextColor(Color.parseColor("#ffffff"));
            btnWord.setOnLongClickListener(new View.OnLongClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public boolean onLongClick(View view) {
                    ClipData data = ClipData.newPlainText("","");
                    View.DragShadowBuilder myDragShadowBuilder = new View.DragShadowBuilder(view);
                    view.startDragAndDrop(data,myDragShadowBuilder,0,0);
                    return true;
                }
            });
            btnWord.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View view, DragEvent event) {
                    int drag = event.getAction();
                    switch (drag){
                        case DragEvent.ACTION_DRAG_ENTERED:
                            final View v = (View) event.getLocalState();
                            break;
                        case DragEvent.ACTION_DRAG_EXITED:
                            break;
                            case DragEvent.ACTION_DROP:
                                break;
                    }
                    return false;
                }
            });
                    btnWord.setLayoutParams(params);
            btnWord.setBackgroundResource(R.drawable.custom_word_click_chatting_bot);
            btnWord.setGravity(Gravity.CENTER);
            linearShowWord.addView(btnWord);

        }
        return view;
    }

}
