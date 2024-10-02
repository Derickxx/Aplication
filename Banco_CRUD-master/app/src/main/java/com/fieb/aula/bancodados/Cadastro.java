package com.fieb.aula.bancodados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;

public class Cadastro extends AppCompatActivity {

    private AppCompatButton btn_cadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        btn_cadastrar = findViewById(R.id.btn_cadastrar);




    }
}