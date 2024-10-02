package com.fieb.aula.mobiletcc;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBanco {
public static Connection conectar(TesteConexaoBD testeConexaoBD){
    Connection conn = null;
    try{
        StrictMode.ThreadPolicy politica;
        politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(politica);
        Class.forName("net.sourceforme.jtds.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:jtds:sqlserver://172.24.64.1;"+
                "databaseName=bd_GreenCycle_v2;user=sa;passaword=@ITB123456");
    }
    catch(SQLException e){
        e.getMessage();
    }
    catch (ClassNotFoundException e){
        e.printStackTrace();
    }
    return conn;
}



}
