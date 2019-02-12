package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import edu.gatech.seclass.sdpvocabquiz.quiz.QuizContent;
import edu.gatech.seclass.sdpvocabquiz.quiz.Quiz;
import edu.gatech.seclass.sdpvocabquiz.quiz.QuizDao;
import edu.gatech.seclass.sdpvocabquiz.quiz.QuizRoom;

public class PracticeQuiz extends AppCompatActivity {

    public static final String QUIZ = "quiz";
    public static final String QUESTIONS = "questions";
    public static final String CORRECT = "correct";
    public static final String CURRENT = "current";
    public static final String TOTAL = "total";

    private Spinner quizzes;
    private Button doQuiz;
    private Button cancel;
    private ArrayAdapter<String> adapter;
    private String studentName;
    private String currentQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_quiz);

        quizzes = findViewById(R.id.quizzes);
        doQuiz = findViewById(R.id.doQuiz);
        cancel = findViewById(R.id.cancel);

        Bundle extras = getIntent().getExtras();
        studentName = extras.getString("name");

        QuizRoom quizRoom = QuizRoom.getInstance(getApplication());
        final QuizDao quizDao = quizRoom.quizDao();

        String[] quizNames = quizDao.fetchQuizNames();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, quizNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quizzes.setAdapter(adapter);

        //quizRoom.close();

        quizzes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentQuiz = adapterView.getItemAtPosition(i).toString();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        doQuiz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // fetch quiz based on unique quiz name
                Quiz quiz = quizDao.fetchQuiz(currentQuiz);
                QuizContent quizContent = QuizContent.fromJson(quiz.getQuizContent());

                // jump to DoQuestion page
                Intent intent = new Intent(PracticeQuiz.this, DoQuestion.class);

                // pass down questions and corresponding metadata
                intent.putStringArrayListExtra(QUESTIONS, quizContent.generateQuestions());
                intent.putExtra("name", studentName);
                intent.putExtra(QUIZ, currentQuiz);
                intent.putExtra(CORRECT, 0);
                intent.putExtra(CURRENT, 0);
                intent.putExtra(TOTAL, quizContent.getWords().size());
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // jump back to Menu page
                Intent intent = new Intent(PracticeQuiz.this, Menu.class);
                intent.putExtra("name", studentName);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(PracticeQuiz.this, Menu.class);
        intent.putExtra("name", studentName);
        startActivity(intent);
        super.onBackPressed();  // optional depending on your needs
    }
}
