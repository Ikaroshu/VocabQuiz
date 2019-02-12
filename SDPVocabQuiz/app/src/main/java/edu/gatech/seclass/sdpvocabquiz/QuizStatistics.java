package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import edu.gatech.seclass.sdpvocabquiz.quiz.QuizDaoStat;
import edu.gatech.seclass.sdpvocabquiz.quiz.QuizRoom;
import edu.gatech.seclass.sdpvocabquiz.score.Score;
import edu.gatech.seclass.sdpvocabquiz.score.ScoreDaoStat;
import edu.gatech.seclass.sdpvocabquiz.score.ScoreRoom;

public class QuizStatistics extends AppCompatActivity {
    private String ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_statistics);

        final Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        ss = name;

        ListView listView = findViewById(R.id.scorelist);

        final List<String> quizName = new ArrayList<>();

        QuizRoom quizRoom = QuizRoom.getInstance(getApplication());
        ScoreRoom scoreRoom = ScoreRoom.getInstance(getApplication());

        QuizDaoStat quizDao = quizRoom.quizDaoStat();
        ScoreDaoStat scoreDao = scoreRoom.scoreDaoStat();

        ArrayList<String> allQuiz = (ArrayList<String>) quizDao.getAllQuiz();
        ArrayList<Score> allScores = (ArrayList<Score>) scoreDao.getScoresByTime(name);

        HashSet<String> scoredQuiz = new HashSet<>();

        for (Score score : allScores) {
            if (!scoredQuiz.contains(score.getQuizName())) {
                quizName.add(String.format("%s      latest score:%.1f", score.getQuizName(), score.getScore()));
                scoredQuiz.add(score.getQuizName());
            }
        }

        for (String qz : allQuiz) {
            if (!scoredQuiz.contains(qz)) {
                quizName.add(String.format("%s      not practiced yet", qz));
            }
        }

//        ArrayList<String> test = new ArrayList<>();
//        test.add("hello");
//        test.add("how");
//        test.add("are");
//        test.add("you");

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quizName);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Intent statIntent = new Intent(QuizStatistics.this, ShowStat.class);
                String quizn = quizName.get(position);
                statIntent.putExtra("quizName", quizn.substring(0, quizn.indexOf("      ")));
                statIntent.putExtra("studentName", name);
                startActivity(statIntent);
                finish();
            }
        });

        Button back = findViewById(R.id.backinstat);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizStatistics.this, Menu.class);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(QuizStatistics.this, Menu.class);
        intent.putExtra("name", ss);
        startActivity(intent);
        super.onBackPressed();  // optional depending on your needs
    }
}