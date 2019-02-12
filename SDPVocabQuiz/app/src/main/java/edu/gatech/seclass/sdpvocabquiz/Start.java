package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.gatech.seclass.sdpvocabquiz.student.Student;
import edu.gatech.seclass.sdpvocabquiz.student.StudentDao;
import edu.gatech.seclass.sdpvocabquiz.student.StudentRoom;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button login = findViewById(R.id.loginButton);
        Button register = findViewById(R.id.registerButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start.this, Registration.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start.this, Menu.class);
                EditText name = findViewById(R.id.userName);
                boolean conti = true;
                StudentRoom db = StudentRoom.getInstance(getApplication());
                StudentDao studentDao = db.studentDao();
                Student finds = studentDao.findStudent(name.getText().toString());
                if (finds == null) {
                    name.setError("No such user, consider register.");
                } else {
                    intent.putExtra("name", name.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
