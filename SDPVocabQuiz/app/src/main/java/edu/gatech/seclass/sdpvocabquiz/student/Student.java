package edu.gatech.seclass.sdpvocabquiz.student;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Student {
    @PrimaryKey(autoGenerate=true)
    public int id;

    public String name;

    public String major;

    public String seniority;

    public String email;
}