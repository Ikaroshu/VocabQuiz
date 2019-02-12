package edu.gatech.seclass.sdpvocabquiz.student;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface StudentDao {

    @Insert
    public void insert(Student student);

    @Delete
    public void delete(Student student);

    @Query("SELECT * FROM Student WHERE name LIKE :search")
    public Student findStudent(String search);
}
