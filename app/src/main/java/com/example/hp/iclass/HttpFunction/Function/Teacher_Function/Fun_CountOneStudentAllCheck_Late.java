package com.example.hp.iclass.HttpFunction.Function.Teacher_Function;

import android.support.annotation.NonNull;

import com.example.hp.iclass.HttpFunction.Function.IPCondition;
import com.example.hp.iclass.HttpFunction.Thread.Teacher_Thread.CountOneStudentAllCheck_Late;

/**
 * Created by spencercjh on 2018/1/15.
 * iClass
 */

public class Fun_CountOneStudentAllCheck_Late {
    @NonNull
    public static String http_CountOneStudentAllCheck_Late(String subject_id, String student_id) throws InterruptedException {
        String ip = IPCondition.server_ip;
        String url = ip + "iClass_Sever/CountOneStudentAllCheck_Late";
        CountOneStudentAllCheck_Late thread = new CountOneStudentAllCheck_Late(url, subject_id, student_id);
        thread.start();
        thread.join();
        if (!thread.getFlag()) {
            return "failed";
        } else {
            return thread.getNum_late().trim();
        }
    }
}
