package edu.gatech.seclass.sdpvocabquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import edu.gatech.seclass.sdpvocabquiz.quiz.Quiz;
import edu.gatech.seclass.sdpvocabquiz.quiz.QuizContent;
import edu.gatech.seclass.sdpvocabquiz.quiz.QuizDao;
import edu.gatech.seclass.sdpvocabquiz.quiz.QuizRoom;

public class QuizActivity extends AppCompatActivity {
    private String ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Button submit = findViewById(R.id.submit);
        final Button finish = findViewById(R.id.finish);
        final Intent intent = getIntent();
        final String username = intent.getStringExtra("name");
        ss = username;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText name = findViewById(R.id.name);
                EditText desctxt = findViewById(R.id.desctxt);
                QuizContent quizContent = new QuizContent(name.getText().toString(), desctxt.getText().toString());
                Quiz quiz = new Quiz(name.getText().toString(), username, QuizContent.toJson(quizContent));

                QuizRoom db = QuizRoom.getInstance(getApplication());
                QuizDao quizDao = db.quizDao();
                if (quizDao.fetchQuiz(name.getText().toString()) != null) {
                    Toast.makeText(QuizActivity.this, "Quiz name already exist!", Toast.LENGTH_LONG).show();
                } else {
                    quizDao.insertQuiz(quiz);

                    //Toast.makeText(QuizActivity.this, "Quiz Added!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(QuizActivity.this, WordActivity.class);

                    Bundle b = new Bundle();

                    //Inserts a String value into the mapping of this Bundle
                    b.putString("name", name.getText().toString());
                    b.putString("username", username);
                    b.putString("desc", desctxt.getText().toString());

                    //Add the bundle to the intent.
                    intent.putExtras(b);

                    startActivity(intent);
                    finish();
                }

            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, MaintainQuiz.class);
                intent.putExtra("name", username);
                startActivity(intent);
                finish();
            }

        });


    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(QuizActivity.this, MaintainQuiz.class);
        intent.putExtra("name", ss);
        startActivity(intent);
        super.onBackPressed();  // optional depending on your needs
    }

}