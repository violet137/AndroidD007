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
    String title, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_skill);


        desc = getIntent().getStringExtra("desc");
        idLesson = getIntent().getIntExtra("idLesson", -1);
        resourceBg = getIntent().getIntExtra("resourceBg", -1);
        title = getIntent().getStringExtra("title");

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        skillCon = this.findViewById(R.id.skillCon);
        skillListen = this.findViewById(R.id.skillLissen);
        skillWrite = this.findViewById(R.id.skillWrite);
        skillTalk = this.findViewById(R.id.skillTalk);
        skillCon = findViewById(R.id.skillCon);

        skillCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                               Intent intent = new Intent(getApplicationContext(), ConversionSkillActivity.class);
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
                intent.putExtra("idLesson", idLesson);
                intent.putExtra("title", title);
                intent.putExtra("resourceBg", resourceBg);
                startActivity(intent);
            }
        });

        skillListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListenSkillActivity.class);
                intent.putExtra("idLesson", idLesson);
                intent.putExtra("title", title);
                intent.putExtra("resourceBg", resourceBg);
                intent.putExtra("desc", desc);
                startActivity(intent);
            }
        });

        skillTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VocabularySkillActivity.class);
                intent.putExtra("idLesson", idLesson);
                intent.putExtra("title", title);
                intent.putExtra("resourceBg", resourceBg);
                startActivity(intent);
            }
        });


    }

}
