package com.fieb.aula.mobiletcc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.sql.Connection;

public class TesteConexaoBD extends AppCompatActivity {

    TextView BancoTeste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_conexao_bd);

        Connection conn = ConectarBanco.conectar(TesteConexaoBD.this);
        BancoTeste = findViewById(R.id.BancoTeste);

        try {
            if (conn != null) {
                if (!conn.isClosed())
                    BancoTeste.setText("Conexão realizada com sucesso");
                    else
                    BancoTeste.setText("A conexão esta fechada");

            } else {
                BancoTeste.setText("Conexão nula, nao realizada");
            }
        }

catch (java.sql.SQLException ex){
                ex.printStackTrace();
                BancoTeste.setText("conexão falhou!!!\n" +
                        ex.getMessage());
            }
    }
}

