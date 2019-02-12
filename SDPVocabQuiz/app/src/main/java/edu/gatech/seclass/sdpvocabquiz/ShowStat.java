package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;

import edu.gatech.seclass.sdpvocabquiz.score.Score;
import edu.gatech.seclass.sdpvocabquiz.score.ScoreDaoStat;
import edu.gatech.seclass.sdpvocabquiz.score.ScoreRoom;

public class ShowStat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stat);

        Intent intent = getIntent();
        String quizName = intent.getStringExtra("quizName");
        final String studentName = intent.getStringExtra("studentName");

        TextView recent = findViewById(R.id.firstScore);
        TextView highest = findViewById(R.id.highestscore);
        TextView top3 = findViewById(R.id.top3);

        ScoreRoom db = ScoreRoom.getInstance(getApplication());
        ScoreDaoStat dao = db.scoreDaoStat();

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Score rec = dao.getFirstScore(studentName, quizName);
        if (rec == null) {
            recent.setText("You have not practiced this test yet");
        } else {
            recent.setText(String.format("%.1f at %s", rec.getScore(), f.format(rec.getQuizTime())));
        }

        Score hi = dao.getHighesttScore(studentName, quizName);
        if (hi == null) {
            highest.setText("You have not practiced this test yet");
        } else {
            highest.setText(String.format("%.1f at %s", hi.getScore(), f.format(hi.getQuizTime())));
        }

        ArrayList<Score> top = (ArrayList<Score>) dao.getTopScore(quizName, 100);
        if (top == null) {
            top3.setText("No one has practice this quiz yet");
        } else {
            String res = "";
            HashSet<String> names = new HashSet<>();
            for (int i = 0; i < top.size(); i++) {
                if (!names.contains(top.get(i).getUserName())) {
                    res = res + top.get(i).getUserName() + "\n";
                    names.add(top.get(i).getUserName());
                }
                if (names.size() == 3) {
                    break;
                }
            }
            top3.setText(res);
        }

        Button back = findViewById(R.id.backtostat);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowStat.this, QuizStatistics.class);
                intent.putExtra("name", studentName);
                startActivity(intent);
                finish();
            }
        });
    }
}
