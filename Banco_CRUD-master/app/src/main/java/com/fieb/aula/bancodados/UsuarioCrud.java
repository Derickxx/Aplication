package com.fieb.aula.bancodados;

import android.content.Context;

import java.sql.PreparedStatement;

public class UsuarioCrud {
    public static int InserirUsuario (usuario usuario, Context ctx){
        int resposta = 0;

        try {
        PreparedStatement pst = conexaoBanco.conectar((TesteConexaoBD) ctx).prepareStatement(
                "Insert Into usuario (nome, email, senha) " + "values (?,?,?)");

        pst.setString(1, usuario.getNome());
        pst.setString(2, usuario.getEmail());
        pst.setString(3, usuario.getSenha());

        resposta = pst.executeUpdate();

        } catch (Exception e){
            e.getMessage();
        }

        return resposta;
        }
}
