package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import edu.gatech.seclass.sdpvocabquiz.student.Student;
import edu.gatech.seclass.sdpvocabquiz.student.StudentDao;
import edu.gatech.seclass.sdpvocabquiz.student.StudentRoom;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Button regist = findViewById(R.id.regist);
        Button cancel = findViewById(R.id.cancel);

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                EditText name = findViewById(R.id.name);
                EditText major = findViewById(R.id.major);
                Spinner seniority = findViewById(R.id.seniority);
                EditText email = findViewById(R.id.email);



                student.name = name.getText().toString();
                student.email = email.getText().toString();
                student.seniority = seniority.getSelectedItem().toString();
                student.major = major.getText().toString();

                StudentRoom db = StudentRoom.getInstance(getApplication());
                StudentDao studentDao = db.studentDao();
                Student finds = studentDao.findStudent(name.getText().toString());
                if (finds == null) {
                    studentDao.insert(student);
                    boolean conti = true;
                    if (student.email == null || student.email.length() == 0) {
                        email.setError("Please input email");
                        conti = false;
                    }
                    if (student.name == null || student.name.length() == 0) {
                        name.setError("Please input user name");
                        conti = false;
                    }
                    if (student.major == null || student.major.length() == 0) {
                        email.setError("Please input major");
                        conti = false;
                    }

                    if (conti) {
                        Toast.makeText(Registration.this, "registration succeed!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Registration.this, Start.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(Registration.this, "user name already registered", Toast.LENGTH_LONG).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, Start.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Registration.this, Start.class);
        startActivity(intent);
        super.onBackPressed();  // optional depending on your needs
    }
}
