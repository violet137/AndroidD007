package com.com.greenacademy.englishlearning.Fragment;


import android.content.ClipData;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.com.greenacademy.englishlearning.Adapter.MessageListAdapter;
import com.com.greenacademy.englishlearning.Holder.User;
import com.com.greenacademy.englishlearning.Holder.UserMessage;
import com.greenacademy.englishlearning.R;
import com.nex3z.flowlayout.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChattingFragment extends Fragment {
    FlowLayout layoutShowWord,layoutChooseWord;
    String[] arrUserMess;
    int i,index;
    FrameLayout btnReturn,btnHint;
    TextView tvHint,tvStatus;
    ImageView btnSend;
    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    List<UserMessage> list;

    public ChattingFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatting_bot,container,false);
        layoutShowWord = view.findViewById(R.id.layout_show_word);
        layoutChooseWord = view.findViewById(R.id.layout_choose_word);
        btnReturn = view.findViewById(R.id.btn_return);
        btnHint = view.findViewById(R.id.btn_hint);
        tvHint = view.findViewById(R.id.tv_hint);
        tvStatus = view.findViewById(R.id.tv_show_status);
        btnSend = view.findViewById(R.id.btn_send);

        layoutShowWord.setOnDragListener(new MyDragListener());
        layoutChooseWord.setOnDragListener(new MyDragListener());
//        getConversation content = new getConversation();
//        content.getContent();
        arrUserMess = getResources().getStringArray(R.array.arr_conversation_topic);
        list = new ArrayList<>();
        index = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        User bot = new User("Teacher TeLlo");
        UserMessage first = new UserMessage(arrUserMess[index],bot,sdf.format(new Date()));
        list.add(first);
        mMessageRecycler = view.findViewById(R.id.reyclerview_message_list);
        mMessageAdapter = new MessageListAdapter(getContext(),list);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mMessageRecycler.setAdapter(mMessageAdapter);

        createWords();
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index >= arrUserMess.length -1){
                }else {
                    layoutChooseWord.removeAllViews();
                    layoutShowWord.removeAllViews();
                    createWords();
                }
            }
        });
        i = Integer.parseInt(tvHint.getText().toString());
        btnHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(layoutShowWord.getChildCount() == 0){
                        if(layoutChooseWord.getChildCount() == 0){
                            tvStatus.setText("Complete");
                            tvStatus.setBackgroundColor(Color.parseColor("#00000000"));
                            tvStatus.setTextColor(Color.parseColor("#03d644"));
                            tvStatus.setVisibility(View.VISIBLE);
                            new CountDownTimer(3000,1000){
                                @Override
                                public void onTick(long l) {
                                }
                                @Override
                                public void onFinish() {
                                    tvStatus.setVisibility(View.INVISIBLE);
                                }
                            }.start();
                        }
                    }else {
                        if (i > 0) {
                            tvHint.setText(String.valueOf(--i));
                            hintWord();
                        } else {
                            tvStatus.setText("Your hint is empty !!!");
                            tvStatus.setVisibility(View.VISIBLE);
                            new CountDownTimer(3000, 1000) {
                                @Override
                                public void onTick(long l) {
                                }

                                @Override
                                public void onFinish() {
                                    tvStatus.setVisibility(View.INVISIBLE);
                                }
                            }.start();
                        }
                    }
                }
            });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int countShowWord = layoutShowWord.getChildCount();
                String answer="";
                if(countShowWord == 0){
                    if(layoutChooseWord.getChildCount() == 0){
                        tvStatus.setText("Complete");
                        tvStatus.setBackgroundColor(Color.parseColor("#00000000"));
                        tvStatus.setTextColor(Color.parseColor("#03d644"));
                        tvStatus.setVisibility(View.VISIBLE);
                        new CountDownTimer(3000,1000){
                            @Override
                            public void onTick(long l) {
                            }
                            @Override
                            public void onFinish() {
                                tvStatus.setVisibility(View.INVISIBLE);
                            }
                        }.start();
                    }else {
                        for (int i = 0; i < layoutChooseWord.getChildCount(); i++) {
                            answer += ((Button) layoutChooseWord.getChildAt(i)).getText().toString() + " ";
                        }
                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
                        User bot = new User("Teacher TeLlo");
                        if (answer.trim().equals(arrUserMess[index])) {
                            getMessageList();
                            UserMessage message = new UserMessage(arrUserMess[index], bot, sdf.format(new Date()));
                            list.add(message);
                            if (index < arrUserMess.length - 1) {
                                createWords();
                            }else{
                                layoutChooseWord.removeAllViews();
                            }
                        } else {
                            list.add(new UserMessage(answer, null, sdf.format(new Date())));
                            list.add(new UserMessage("I don't understand what did you say ? Please say again !!", bot, sdf.format(new Date())));
                        }
                        mMessageAdapter.notifyDataSetChanged();
                        mMessageRecycler.setAdapter(mMessageAdapter);
                        mMessageRecycler.smoothScrollToPosition(mMessageAdapter.getItemCount() - 1);
                    }
                }
            }
        });
        return view;
    }

    private void getMessageList(){
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            UserMessage message = new UserMessage(arrUserMess[index],null,sdf.format(new Date()));
            list.add(message);
            index++;
            mMessageAdapter.notifyDataSetChanged();
            mMessageRecycler.setAdapter(mMessageAdapter);
    }
    private void createWords(){
        if(index%2 == 0){
            index++;
        }
        String statement = arrUserMess[index];
        String[] words = statement.split(" ");
        layoutChooseWord.removeAllViews();
        for(int i=0;i<words.length;i++){
            final Button btnWord = new Button(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(5,5,5,5);
            btnWord.setLayoutParams(params);
            btnWord.setText(words[i]);
            btnWord.setTextSize(15);
            btnWord.setTransformationMethod(null);
            btnWord.setBackgroundColor(Color.parseColor("#27a0ee"));
            btnWord.setPadding(5,5,5,5);
            btnWord.setTextColor(Color.parseColor("#ffffff"));
            btnWord.setOnTouchListener(new MyTouchListener());

            layoutShowWord.addView(btnWord);

        }
    }
    private void hintWord(){
        String[] result = arrUserMess[index].split(" ");
        String[] answer = new String[result.length];
        String stranswer = "";
        int countChooseWord = layoutChooseWord.getChildCount();
        int countShowWord = layoutShowWord.getChildCount();
        int temp = 0;

        if(countChooseWord == 0){
                for(int i=0;i<countShowWord;i++){
                    Button btn = (Button) layoutShowWord.getChildAt(i);
                    if(btn.getText().toString().equals(result[temp])){
                        btn.setBackgroundColor(Color.parseColor("#03d644"));
                    }
                }
        }else{
                for(int i=0;i<countChooseWord;i++){
                    Button button = (Button) layoutChooseWord.getChildAt(i);
                    answer[i] = button.getText().toString();
                    stranswer+= answer[i] + " ";
                    if(!result[i].equals(answer[i])){
                        button.setBackgroundColor(Color.parseColor("#ff0000"));
                    }else{
                        button.setBackgroundColor(Color.parseColor("#03d644"));
                    }
                }
                if(arrUserMess[index].contains(stranswer.trim())){
                    temp = countChooseWord;
                    for(int i=0;i<countShowWord;i++){
                        Button btn = (Button) layoutShowWord.getChildAt(i);
                        if(btn.getText().toString().equals(result[temp])){
                            btn.setBackgroundColor(Color.parseColor("#03d644"));
                        }
                    }
                }
        }

    }
    private final class MyTouchListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                ClipData data = ClipData.newPlainText("","");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data,shadowBuilder,view,0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }else{
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener{
        private boolean isInView = false;
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            View view1;
            switch (dragEvent.getAction()){
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    isInView = true;
                    break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        isInView =false;
                        break;
                case DragEvent.ACTION_DROP:
                    view1 = (View) dragEvent.getLocalState();
                    ViewGroup owner = (ViewGroup) view1.getParent();
                    if(isInView){
                        owner.removeView(view1);
                    }
                    if(owner.getId() == R.id.layout_show_word){
                        view1.setBackgroundColor(Color.parseColor("#27a0ee"));
                        layoutChooseWord.addView(view1);
                    }else{
                        view1.setBackgroundColor(Color.parseColor("#27a0ee"));
                        layoutShowWord.addView(view1);
                    }

                    break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        view1 = (View) dragEvent.getLocalState();
                        view1.setVisibility(View.VISIBLE);
                    default:
                        break;
            }
            return true;
        }
    }

}

