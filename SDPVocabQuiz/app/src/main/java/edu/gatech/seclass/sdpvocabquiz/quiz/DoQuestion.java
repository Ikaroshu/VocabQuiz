package edu.gatech.seclass.sdpvocabquiz.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.gatech.seclass.sdpvocabquiz.PracticeQuiz;
import edu.gatech.seclass.sdpvocabquiz.R;

import static edu.gatech.seclass.sdpvocabquiz.PracticeQuiz.CORRECT;
import static edu.gatech.seclass.sdpvocabquiz.PracticeQuiz.CURRENT;
import static edu.gatech.seclass.sdpvocabquiz.PracticeQuiz.QUESTIONS;
import static edu.gatech.seclass.sdpvocabquiz.PracticeQuiz.TOTAL;

public class DoQuestion extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private LinearLayout layout;
    private Button submit;
    private Button next;
    private RadioButton radioButton;

    private String word;
    private String answer;

    private int correct;
    private int current;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_question);

        // fetch questions and metadata
        Bundle extras = getIntent().getExtras();
        final ArrayList<String> questions = extras.getStringArrayList(QUESTIONS);
        correct = extras.getInt(CORRECT);
        current = extras.getInt(CURRENT);
        total = extras.getInt(TOTAL);
        word = questions.remove(0);
        answer = questions.get(0);

        // set text view
        textView1 = findViewById(R.id.textView1);
        textView1.setText(word);
        textView2 = findViewById(R.id.textView2);
        if (current == 0) {
            textView2.setText("Current Score: 0");
        } else {
            textView2.setText(String.format("Current Score: %3d", correct * 100 / current));
        }

        // construct radio button choices
        layout = findViewById(R.id.layout2);
        final RadioGroup radioGroup = new RadioGroup(this);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(radioGroup, p);

        List<String> choices = questions.subList(0, 4);
        Collections.shuffle(choices);
        for (String choice: choices) {
            RadioButton radioButtonView = new RadioButton(this);
            radioButtonView.setText(choice);
            radioGroup.addView(radioButtonView, p);
        }

        // construct buttons
        submit = findViewById(R.id.submit);
        next = findViewById(R.id.next);
        next.setEnabled(false);

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                if (radioButton == null || radioButton.getText() == null) return;
                if (radioButton.getText().equals(answer)) {
                    Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                    correct++;
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT).show();
                }
                submit.setEnabled(false);
                next.setEnabled(true);
                current++;
                textView2.setText(String.format("Current Score: %3d", correct * 100 / current));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (current == total) {
                    Intent intent = new Intent(DoQuestion.this, PracticeQuiz.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(DoQuestion.this, DoQuestion.class);
                    intent.putExtra(QUESTIONS, new ArrayList<>(questions.subList(4, questions.size())));
                    intent.putExtra(CORRECT, correct);
                    intent.putExtra(CURRENT, current);
                    intent.putExtra(TOTAL, total);
                    startActivity(intent);
                }
            }
        });

    }
}
