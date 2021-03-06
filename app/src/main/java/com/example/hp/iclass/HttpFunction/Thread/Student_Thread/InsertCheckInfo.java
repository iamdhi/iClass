package com.example.hp.iclass.HttpFunction.Thread.Student_Thread;

import com.example.hp.iclass.OBJ.CheckOBJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by spencercjh on 2017/11/30.
 * iClass
 */

public class InsertCheckInfo extends Thread {
    private boolean flag;
    private String url;
    private CheckOBJ checkOBJ = new CheckOBJ();
    private String state = "";

    public InsertCheckInfo(String url, CheckOBJ checkOBJ) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.checkOBJ = checkOBJ;
    }

    private void doGet() throws IOException {
        /*将username和password传给Tomcat服务器*/
        url = url + "?subject_id=" + checkOBJ.getSubject_id()
                + "&subject_th=" + checkOBJ.getSubject_th()
                + "&student_id=" + checkOBJ.getStudent_id()
                + "&seat_index=" + checkOBJ.getSeat_index()
                + "&start_time=" + checkOBJ.getStart_time();
//        System.out.println("URL:        "+url);
        try {
//            URLEncoder.encode(URLEncoder.encode(url));
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
                state = buffer.toString();
                state = URLDecoder.decode(state, "UTF-8");
            }
            //把服务端返回的数据打印出来
            System.out.println("result:" + state);
            if (state.equals("insert check_info failed")) {
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

    public String getstate() {
        return state.trim();
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