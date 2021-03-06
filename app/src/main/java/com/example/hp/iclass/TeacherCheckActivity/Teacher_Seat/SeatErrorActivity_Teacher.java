package com.example.hp.iclass.TeacherCheckActivity.Teacher_Seat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hp.iclass.OBJ.SubjectOBJ;
import com.example.hp.iclass.OBJ.TeacherOBJ;
import com.example.hp.iclass.R;
import com.example.hp.iclass.TeacherCheckActivity.CheckConditionActivity;

public class SeatErrorActivity_Teacher extends AppCompatActivity {
    private TeacherOBJ teacherOBJ = new TeacherOBJ();
    private SubjectOBJ subjectOBJ = new SubjectOBJ();
    private int class_type = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seaterror);
        Intent intent = getIntent();
        teacherOBJ = (TeacherOBJ) intent.getSerializableExtra("teacherOBJ");
        subjectOBJ = (SubjectOBJ) intent.getSerializableExtra("subjectOBJ");
    }

    private void goback() {
        Intent it = new Intent(this, CheckConditionActivity.class);
        it.putExtra("teacherOBJ", teacherOBJ);
        it.putExtra("subjectOBJ", subjectOBJ);
        startActivity(it);
        finish();
        System.gc();
    }

    public void onBackPressed() {
        goback();
    }
}
