package com.fieb.aula.bancodados;

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

        Connection conn = conexaoBanco.conectar(TesteConexaoBD.this);
        BancoTeste = findViewById(R.id.BancoTeste);

        try {
            if (conn != null) {
                if (!conn.isClosed())
                    BancoTeste.setText("CONEXAO OK");
                else
                    BancoTeste.setText("A CONEXÃO ESTÁ FECHADA");
            } else {
                BancoTeste.setText("CONEXAO NULA, NÃO REALIZADA");
            }
        } catch (java.sql.SQLException ex) {
            ex.printStackTrace();
            BancoTeste.setText("CONEXÃO FALHOU!!!\n" +
                    ex.getMessage());
        }

    }
}
