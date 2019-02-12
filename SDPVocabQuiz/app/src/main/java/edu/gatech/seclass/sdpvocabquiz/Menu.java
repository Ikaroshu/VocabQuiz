package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.seclass.sdpvocabquiz.student.Student;
import edu.gatech.seclass.sdpvocabquiz.student.StudentDao;
import edu.gatech.seclass.sdpvocabquiz.student.StudentRoom;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView welcome = findViewById(R.id.welcome);
        final Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        StudentRoom db = StudentRoom.getInstance(getApplication());
        StudentDao studentDao = db.studentDao();
        Student st = studentDao.findStudent(name);
        welcome.setText("Welcome " + st.name +"!");

        Button maintain = findViewById(R.id.maintainQuiz);
        Button practice = findViewById(R.id.practiceQuiz);
        Button statistics = findViewById(R.id.quizStatistics);
        Button back = findViewById(R.id.backinmenu);

        maintain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MaintainQuiz.class);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, PracticeQuiz.class);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, QuizStatistics.class);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Menu.this, Start.class);
                startActivity(intent1);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(Menu.this, Start.class);
        startActivity(intent1);
        super.onBackPressed();
    }
}
