package com.example.hp.iclass.HistoryActivity.Teacher.OutputExcel;

import android.content.Context;

import com.example.hp.iclass.OBJ.SubjectSumUpOBJ;

/**
 * Created by spencercjh on 2018/1/15.
 * iClass
 */

public class OutputExcelFile_Fun {
    public static boolean http_OutputExcelFile(Context context,SubjectSumUpOBJ subjectSumUpOBJ) throws InterruptedException {
        OutputExcelFile_Thread thread=new OutputExcelFile_Thread(context,subjectSumUpOBJ);
        thread.start();
        thread.join();
        return thread.isFlag();
    }
}
