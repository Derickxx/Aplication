package com.fieb.aula.mobiletcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fieb.aula.mobiletcc.Controller.LoginController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends AppCompatActivity {


       private EditText emailEditText, senhaEditText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);


           emailEditText = findViewById(R.id.editTextEmail);
            senhaEditText = findViewById(R.id.editTextPassword);
            Button buttonEntrar = findViewById(R.id.loginRealizado);

            buttonEntrar.setOnClickListener(v -> {
                String email = emailEditText.getText().toString().trim();
                String senha = senhaEditText.getText().toString().trim();

                if (email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(Login.this, "Por favor, insira os seus dados", Toast.LENGTH_SHORT).show();
                } else {
                    LoginController loginController = new LoginController();
                    LoginController.LoginResult loginResult = loginController.validarLogin(Login.this, email, senha);

                    switch (loginResult.status) {
                        case 0:
                            if ("ADMIN".equals(loginResult.nivelAcesso)) {
                                Toast.makeText(Login.this, "Nível de acesso ADMIN, por favor, entre pelo sistema web", Toast.LENGTH_SHORT).show();
                            } else {
                                int id = obterIdUsuarioPorEmail(email);
                                String nome = obterNomeUsuarioPorEmail(email);

                                if (id != -1 && nome != null) {
                                    SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putInt("userId", id);
                                    editor.putString("userEmail", email);
                                    editor.putString("userName", nome);
                                    editor.putBoolean("isLoggedIn", true);
                                    editor.apply();

                                    Toast.makeText(Login.this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show();
                                    telaInicio(nome, email);
                                } else {
                                    Toast.makeText(Login.this, "Erro ao obter dados do usuário", Toast.LENGTH_SHORT).show();
                                }
                            }
                            break;
                        case 1:
                            Toast.makeText(Login.this, "Email não encontrado", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(Login.this, "Senha incorreta", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(Login.this, "Conta bloqueada", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(Login.this, "Erro ao realizar o login", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

            // Verifica se já está logado ao abrir a activity
            SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
            boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
            if (isLoggedIn) {
                String nome = sharedPreferences.getString("userName", null);
                String email = sharedPreferences.getString("userEmail", null);
                telaInicio(nome, email);
            }
        }

        private void telaInicio(String nome, String email) {
            Intent intent = new Intent(Login.this, Perfil.class);
            intent.putExtra("NOME", nome);
            intent.putExtra("EMAIL", email);
            startActivity(intent);
            finish();
        }

        private int obterIdUsuarioPorEmail(String email) {
            try (Connection conn = Conexao.conectar(this)) {
                if (conn != null) {
                    String query = "SELECT id FROM Usuario WHERE email = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(query)) {
                        stmt.setString(1, email);
                        try (ResultSet rs = stmt.executeQuery()) {
                            if (rs.next()) {
                                return rs.getInt("id");
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(this, "Erro ao obter dados do usuário", Toast.LENGTH_SHORT).show();
            }
            return -1;
        }

        private String obterNomeUsuarioPorEmail(String email) {
            try (Connection conn = Conexao.conectar(this)) {
                if (conn != null) {
                    String query = "SELECT nome FROM Usuario WHERE email = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(query)) {
                        stmt.setString(1, email);
                        try (ResultSet rs = stmt.executeQuery()) {
                            if (rs.next()) {
                                return rs.getString("nome");
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(this, "Erro ao obter dados do usuário", Toast.LENGTH_SHORT).show();
            }
            return null;
        }
    }
