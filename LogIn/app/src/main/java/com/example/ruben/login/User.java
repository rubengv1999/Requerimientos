package com.example.ruben.login;

import java.util.Calendar;
import java.util.Date;

public class User {
    private static String email = "fakemailalonso@gmail.com";
    private static String password = "fakepassword";
    private static int failures = 0;
    private static Calendar lastAttempt = null;

    public static String getEmail(){
        return email;
    }

    public static void setEmail(String email){
        User.email = email;
    }

    public static String getPassword(){
        return password;
    }

    public static void setPassword(String password){
        User.password = password;
    }

    public static Calendar getLastAttempt(){
        return lastAttempt;
    }

    public static void setLastAttempt(Calendar last){
        User.lastAttempt = last;
    }

    public static int getFailures(){
        return failures;
    }

    public static void setFailures(int failures){
        User.failures = failures;
    }

    public static boolean isBlocked(){
        if (lastAttempt == null){
            failures = 0;
            return false;
        }
        Calendar now = Calendar.getInstance();
        long minutes = (now.getTimeInMillis() - lastAttempt.getTimeInMillis()) / 60000;
        if(minutes > 5){
            User.lastAttempt = null;
            failures = 0;
            return false;
        }

        return failures >= 3;
    }
}
