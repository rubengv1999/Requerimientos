package com.example.ruben.login;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

public class User {
    private static String email = "fakemailalonso@gmail.com";
    private static String password = "fakepassword";
    private static int publicKeyOne;
    private static BigInteger publicKeyTwo;
    private static int privateKey;
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

    public static void encrypt(String message){
        int P = 7; //randomPrime();
        int Q = 11; //randomPrime();
        int N = P * Q;
        publicKeyOne = N;
        BigInteger M = new BigInteger(message.getBytes());
        int E = 40; //random(2, P * Q) que no divida a N
        BigInteger C = M.modPow(BigInteger.valueOf(E), BigInteger.valueOf(N));
        publicKeyTwo = C;
        int D = (4 * (P - 1) * (Q - 1) + 1)/ E;
        privateKey = D;
    }

    public static String decrypt(){
        BigInteger M = publicKeyTwo.modPow(BigInteger.valueOf(privateKey),
                BigInteger.valueOf(publicKeyOne));
        return M.toByteArray().toString();
    }
}
