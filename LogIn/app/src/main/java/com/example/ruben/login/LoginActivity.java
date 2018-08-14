package com.example.ruben.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {


    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        Button mForgetPassword = (Button) findViewById(R.id.forgetPassword);
        mForgetPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                forgetPassword();
            }
        });


    }


    private void forgetPassword() {
        Intent ListSong = new Intent(getApplicationContext(), ForgetPassword.class);
        startActivity(ListSong);

    }

    private void attemptLogin() {
        boolean failure = false;
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError("Este campo es requerido");
            failure = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError("La contraseña es demasiado corta");
            failure = true;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError("Este campo es requerido");
            failure = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError("Este correo no es válido");
            failure = true;
        }

        if(User.isBlocked()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Este usuario está bloqueado por exceder 3 intentos fallidos").setTitle("Bloqueo");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            failure = true;
        }
        TextView t = (TextView) findViewById(R.id.textView1);
        if (!failure && isLoginValid(email, password)){
            t.setText("Ingreso Autorizado");
        }else{
            t.setText("Ingreso No Autorizado");
            User.setFailures(User.getFailures() + 1);
            User.setLastAttempt(Calendar.getInstance());
        }

    }

    private boolean isEmailValid(String email) {
        return email.contains("@") && email.contains(".com");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private boolean isLoginValid (String email, String password){
        //In the real application this should access the user database
        return email.equals(User.getEmail()) && password.equals(User.getPassword());
    }

}



