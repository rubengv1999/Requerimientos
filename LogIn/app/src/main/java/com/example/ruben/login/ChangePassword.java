package com.example.ruben.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class ChangePassword extends AppCompatActivity {
    private EditText mPasswordA;
    private EditText mPasswordB;
    private EditText mCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        mPasswordA = (AutoCompleteTextView) findViewById(R.id.password1);
        mPasswordB = (AutoCompleteTextView) findViewById(R.id.password2);
        mCode = (EditText) findViewById(R.id.code);
        Button mChangePassword = (Button) findViewById(R.id.changePassword);
        mChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passwordA = mPasswordA.getText().toString();
                String passwordB = mPasswordB.getText().toString();
                String code = mCode.getText().toString();
                attemptPasswordChange(passwordA, passwordB, code);
            }
        });
        Button mExit = (Button) findViewById(R.id.exit);
        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });
    }

    private void exit(){
        Intent ListSong = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(ListSong);

    }

    private void attemptPasswordChange(String passwordA, String passwordB, String code){
        boolean failure = false;
        if (!code.equals("QZ45VYP0")){
            mCode.setError("El código es incorrecto");
            failure = true;
        }else{
            mCode.setError(null);
        }
        if (!passwordA.equals(passwordB)){
            mPasswordB.setError("Las contraseñas no coinciden");
            failure = true;
        }else{
            mPasswordB.setError(null);
        }
        if (!failure) {
            User.setPassword(passwordA);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Cambio de Contraseña Exitoso").setTitle("Felicidades");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    exit();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Cambio de Contraseña sin Éxito").setTitle("Error");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });

        }
    }

}

