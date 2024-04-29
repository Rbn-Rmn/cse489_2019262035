package com.example.cse489_2019262035;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class homelist extends AppCompatActivity {

    private ArrayList<ClassSummary> lectures;
    String course = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homelist);

        Intent i = this.getIntent();
        if(i.hasExtra("_COURSE_")){
            course = i.getStringExtra("_COURSE_");
            ((TextView)findViewById(R.id.tvTitle)).setText(course+" : Class Lectures");
        }
        findViewById(R.id.btnCreateNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homelist.this, home.class);
                i.putExtra("_COURSE_", course);
                startActivity(i);
            }
        });
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lectures = new ArrayList<>();
        loadClassSummary();
    }
    private void loadClassSummary(){
        String q = "SELECT * FROM lectures;";
        ClassSummaryDB db = new ClassSummaryDB(this);
        Cursor cur = db.selectLectures(q);
        lectures.clear();
        if(cur!=null && cur.getCount() > 0){
            while (cur.moveToNext()){
                String id = cur.getString(0);
                String course = cur.getString(1);
                String type = cur.getString(2);
                long date = cur.getLong(3);
                String lecture = cur.getString(4);
                String topic = cur.getString(5);
                String summary = cur.getString(6);

                ClassSummary cs = new ClassSummary(id, course, type, date, lecture, topic, summary);
                lectures.add(cs);
            }
        }
    }