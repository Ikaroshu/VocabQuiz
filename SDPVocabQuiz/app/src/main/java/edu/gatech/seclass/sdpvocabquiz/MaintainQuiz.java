package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MaintainQuiz extends AppCompatActivity {
    private String ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain_quiz);

        Button addquiz = findViewById(R.id.addquiz);
        Button remquiz = findViewById(R.id.remquiz);
        final Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        this.ss = name;

        addquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaintainQuiz.this, QuizActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        remquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaintainQuiz.this, RemQuiz.class);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        Button back = findViewById(R.id.backinmaint);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaintainQuiz.this, Menu.class);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(MaintainQuiz.this, Menu.class);
        intent.putExtra("name", this.ss);
        startActivity(intent);
        super.onBackPressed();  // optional depending on your needs
    }
}