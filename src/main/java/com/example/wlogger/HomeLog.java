package com.example.wlogger;

public class HomeLog {
    int dp;
    String name;
    String date;
    String log1;
    String log2;
    String log3;
    String userId;

    public HomeLog(int dp,String name, String date, String log1, String log2, String log3, String userId) {
        this.dp = dp;
        this.name = name;
        this.date = date;
        this.log1 = log1;
        this.log2 = log2;
        this.log3 = log3;
        this.userId = userId;
    }

    public int getDp() {
        return dp;
    }

    public void setDp(int dp) {
        this.dp = dp;
    }

    public String getLog1() {
        return log1;
    }

    public void setLog1(String log1) {
        this.log1 = log1;
    }

    public String getLog2() {
        return log2;
    }

    public void setLog2(String log2) {
        this.log2 = log2;
    }

    public String getLog3() {
        return log3;
    }

    public void setLog3(String log3) {
        this.log3 = log3;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
