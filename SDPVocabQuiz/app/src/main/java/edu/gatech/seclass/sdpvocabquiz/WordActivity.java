package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.sdpvocabquiz.quiz.Quiz;
import edu.gatech.seclass.sdpvocabquiz.quiz.QuizContent;
import edu.gatech.seclass.sdpvocabquiz.quiz.QuizDao;
import edu.gatech.seclass.sdpvocabquiz.quiz.QuizRoom;

public class WordActivity extends AppCompatActivity {
    private final boolean[] canmov = new boolean[1];
    private String ss;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        Button submit = findViewById(R.id.submit);
        final Button finish = findViewById(R.id.finish);

        canmov[0] = false;

        Bundle b = new Bundle();
        b = getIntent().getExtras();
        final String name = b.getString("name");
        final String username = b.getString("username");
        ss = username;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText wordname = findViewById(R.id.wordname);
                EditText def1 = findViewById(R.id.def1);
                EditText def2 = findViewById(R.id.def2);
                EditText def3 = findViewById(R.id.def3);
                EditText def4 = findViewById(R.id.def4);

                QuizRoom db = QuizRoom.getInstance(getApplication());
                QuizDao wordDao = db.quizDao();
                Quiz quiz = wordDao.fetchQuiz(name.toString());
                QuizContent quizContent = QuizContent.fromJson(quiz.getQuizContent());
                quizContent.addWord(wordname.getText().toString(), def1.getText().toString());
                quizContent.addDefinitions(def2.getText().toString());
                quizContent.addDefinitions(def3.getText().toString());
                quizContent.addDefinitions(def4.getText().toString());
                wordDao.updateQuiz(name.toString(), QuizContent.toJson(quizContent));

                Toast.makeText(WordActivity.this, "Word Added!", Toast.LENGTH_SHORT).show();

                wordname.getText().clear();
                def1.getText().clear();
                def2.getText().clear();
                def3.getText().clear();
                def4.getText().clear();
                canmov[0] = true;

            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                QuizRoom db = QuizRoom.getInstance(getApplication());
//                QuizDao wordDao = db.quizDao();
//                Quiz quiz = wordDao.fetchQuiz(name.toString());
                if (!canmov[0]) {
                    Toast.makeText(WordActivity.this, "Please add at least one word!", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(WordActivity.this, MaintainQuiz.class);
                    intent.putExtra("name", username);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }
    @Override
    public void onBackPressed() {
        if (!canmov[0]) {
            Toast.makeText(WordActivity.this, "Please add at least one word!", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(WordActivity.this, MaintainQuiz.class);
            intent.putExtra("name", ss);
            startActivity(intent);
            super.onBackPressed();
        }
    }

}