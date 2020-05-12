package com.example.miprimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity {

    private EditText etn,etp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etn = (EditText)findViewById(R.id.txtEmail);
        etp = (EditText)findViewById(R.id.txtPassword);

    }

    public void login(View view){
        String nombre = etn.getText().toString();
        String password = etp.getText().toString();

        if(nombre.equals("maestro") && password.equals("123")){
            Toast.makeText(this,"Ingresado", Toast.LENGTH_LONG).show();
            Intent siguiente = new Intent(this,ActivityPrincipal.class);
            startActivity(siguiente);
        }
        else{
            Toast.makeText(this,"Usuario o contraseña incorrecta", Toast.LENGTH_LONG).show();
        }
    }
}
