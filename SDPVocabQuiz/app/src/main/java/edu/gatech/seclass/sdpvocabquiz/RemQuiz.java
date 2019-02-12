package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.sdpvocabquiz.quiz.Quiz;
import edu.gatech.seclass.sdpvocabquiz.quiz.QuizDao;
import edu.gatech.seclass.sdpvocabquiz.quiz.QuizRoom;
import edu.gatech.seclass.sdpvocabquiz.score.ScoreDao;
import edu.gatech.seclass.sdpvocabquiz.score.ScoreRoom;

public class RemQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rem_quiz);
        Button submit = findViewById(R.id.rembutton);
        Button cancel = findViewById(R.id.cancelremove);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = findViewById(R.id.name);


                QuizRoom db = QuizRoom.getInstance(getApplication());
                QuizDao quizDao = db.quizDao();
                Quiz qz = quizDao.fetchQuiz(name.getText().toString());
                boolean conti = true;
                if (qz == null) {
                    name.setError("No such quiz, check your input");
                    conti = false;
                }
                Intent intent = getIntent();
                String username = intent.getStringExtra("name");
                if (qz == null) {
                    name.setError("No such quiz.");
                    conti = false;
                }
                if (qz != null && !username.equals(qz.getQuizAuthor())) {
                    name.setError("This quiz does not belong to you.");
                    conti = false;
                }
                if (conti) {
                    quizDao.deleteQuiz(name.getText().toString());
                    ScoreRoom scoreRoom = ScoreRoom.getInstance(getApplication());
                    ScoreDao scoreDao = scoreRoom.scoreDao();
                    scoreDao.deleteScore(name.getText().toString());

                    Toast.makeText(RemQuiz.this, "Quiz Removed!", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(RemQuiz.this, MaintainQuiz.class);
                    intent1.putExtra("name", username);
                    startActivity(intent1);
                    finish();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String username = intent.getStringExtra("name");
                Intent intent1 = new Intent(RemQuiz.this, MaintainQuiz.class);
                intent1.putExtra("name", username);
                startActivity(intent1);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = getIntent();
        String username = intent.getStringExtra("name");
        Intent intent1 = new Intent(RemQuiz.this, MaintainQuiz.class);
        intent1.putExtra("name", username);
        startActivity(intent1);
        super.onBackPressed();  // optional depending on your needs
    }

}
