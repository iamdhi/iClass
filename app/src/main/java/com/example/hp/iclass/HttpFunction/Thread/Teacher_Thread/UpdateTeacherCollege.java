package com.example.hp.iclass.HttpFunction.Thread.Teacher_Thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by spencercjh on 2017/12/14.
 * iClass
 */

public class UpdateTeacherCollege extends Thread {
    private boolean flag;
    private String url;
    private String teacher_id;
    private String teacher_college;
    private String upadate_state;

    public UpdateTeacherCollege(String url, String teacher_id, String teacher_college) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.teacher_id = teacher_id;
        this.teacher_college = teacher_college;
    }

    private void doGet() throws IOException {
        teacher_college = URLEncoder.encode(teacher_college, "UTF-8");
        teacher_college = URLEncoder.encode(teacher_college, "UTF-8");
        /*将username和password传给Tomcat服务器*/
        url = url + "?teacher_id=" + teacher_id
                + "&teacher_college=" + teacher_college;
        try {
            URL httpUrl = new URL(url);
            /*获取网络连接*/
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            /*设置请求方法为GET方法*/
            conn.setRequestMethod("GET");
            /*设置访问超时时间*/
            conn.setReadTimeout(2000);
            conn.setConnectTimeout(2000);
            conn.connect();
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream inStream = conn.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                StringBuffer buffer = new StringBuffer();
                String line;
                while ((line = in.readLine()) != null) {
                    buffer.append(line);
                }
                upadate_state = buffer.toString();
                upadate_state = URLDecoder.decode(upadate_state, "UTF-8");
            }
            //把服务端返回的数据打印出来
            System.out.println("result:" + upadate_state);
            if (getUpadate_state().equals("update teacher_college failed")) {
                setFlag(false);
            } else {
                setFlag(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean getFlag() {
        return flag;
    }

    private void setFlag(boolean flag) {
        this.flag = flag;
    }

    private String getUpadate_state() {
        return upadate_state;
    }


    /*在run中调用doGet*/
    @Override
    public void run() {
        try {
            doGet();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}