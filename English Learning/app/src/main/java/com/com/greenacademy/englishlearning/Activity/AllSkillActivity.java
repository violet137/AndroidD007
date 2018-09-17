package com.com.greenacademy.englishlearning.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.greenacademy.englishlearning.R;

public class AllSkillActivity extends AppCompatActivity {

    ImageView skillWrite, skillTalk, skillListen, skillCon;
    int idLesson, resourceBg;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_skill);



        idLesson = getIntent().getIntExtra("idLesson", -1);
        resourceBg = getIntent().getIntExtra("resourceBg", -1);
        title = getIntent().getStringExtra("title");

        getSupportActionBar().setTitle(title);

//        skillCon = this.findViewById(R.id.skillCon);
        skillListen = this.findViewById(R.id.skillLissen);
        skillWrite = this.findViewById(R.id.skillWrite);
        skillTalk = this.findViewById(R.id.skillTalk);
        skillCon = findViewById(R.id.skillCon);

        skillCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("============== gogogo");
                Intent intent = new Intent(getApplicationContext(), LessonOfListenSkillActivity.class);
                intent.putExtra("idLesson", idLesson);
                intent.putExtra("title", title);
                intent.putExtra("resourceBg", resourceBg);
                startActivity(intent);

            }
        });

        skillWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WriteSkillActivity.class);
                startActivity(intent);
            }
        });


    }

//    @Override
//    public void onClick(View v) {
//
//
//        switch (v.getId()) {
//            case R.id.skillWrite:
//                break;
//            case R.id.skillTalk:
//                break;
//            case R.id.skillCon:
//
//                break;
//            case R.id.skillLissen:
//                break;
//        }


//    }
}
