package com.fieb.aula.bancodados;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexaoBanco {
    public static Connection conectar (TesteConexaoBD testeConexaoBD){
        Connection conn = null;
        try {
            StrictMode.ThreadPolicy politica;
            politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(politica);
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:jtds:sqlserver://172.19.0.64;"+
                    "databaseName=Banco_Android;user=sa;password=@ITB123456;");

        } catch (SQLException e){
            e.getMessage();

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }
}
