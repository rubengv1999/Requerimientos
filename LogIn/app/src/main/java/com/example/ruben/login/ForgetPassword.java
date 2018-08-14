package com.example.ruben.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;



public class ForgetPassword extends AppCompatActivity{

    private EditText mEmailA;
    private EditText mEmailB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


        mEmailA = (AutoCompleteTextView) findViewById(R.id.email);
        mEmailB = (AutoCompleteTextView) findViewById(R.id.email2);

        Button mForgetPassword = (Button) findViewById(R.id.sendEmail);
        mForgetPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailA = mEmailA.getText().toString();
                String emailB = mEmailB.getText().toString();
                attemptRetrieval(emailA, emailB);
            }
        });

        Button mExit = (Button) findViewById(R.id.exit);
        mExit.setOnClickListener(new OnClickListener() {
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

    private void attemptRetrieval(String emailA, String emailB){
        if (emailA.equals(emailB)){

            mEmailB.setError(null);

            try{

                List<String> toMailList = new ArrayList<String>();
                toMailList.add(emailA);
                GMail mail = new GMail("tecpoo@gmail.com", "tecpoo2017",
                        toMailList, "Recuperación de Contraseña", "Utilice el código QZ45VYP0");
                mail.createEmailMessage();
                mail.sendEmail();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Envio correo").setTitle("Alerta");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }catch(Exception ex){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("No se pudo enviar el correo").setTitle("Alerta");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            Intent ListSong = new Intent(getApplicationContext(), ChangePassword.class);
            startActivity(ListSong);

        }else{
            mEmailB.setError("Los correos no coinciden");
        }


    }
}
