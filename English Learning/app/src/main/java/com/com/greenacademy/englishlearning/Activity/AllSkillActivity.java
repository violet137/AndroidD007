package com.com.greenacademy.englishlearning.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.greenacademy.englishlearning.R;

public class AllSkillActivity extends AppCompatActivity {

    ImageView skillWrite, skillTalk, skillListen;
    int idLesson, resourceBg;
    String title;
    Button btnCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_skill);

        idLesson = getIntent().getIntExtra("idLesson", -1);
        resourceBg = getIntent().getIntExtra("resourceBg", -1);
        title = getIntent().getStringExtra("title");

//        skillCon = this.findViewById(R.id.skillCon);
        skillListen = this.findViewById(R.id.skillLissen);
        skillWrite = this.findViewById(R.id.skillWrite);
        skillTalk = this.findViewById(R.id.skillTalk);
        btnCon = findViewById(R.id.skillCon);

        btnCon.setOnClickListener(new View.OnClickListener() {
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
