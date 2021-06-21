package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private EditText eTemail, eTsenha;
    private String email, senha;
    private String URL = "http://siad.net.br/app/loginUsuarioPHP.php";
    Button btn_login;

    private EditText lSenha;
    private CheckBox msenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        msenha = findViewById(R.id.mostrar);
        eTemail = findViewById(R.id.email_login);
        eTsenha = findViewById(R.id.senha_login);
        btn_login = findViewById(R.id.btnLogar);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        mostrarsenha();
        verif();
    }
    // Login Usuario
    public void login() {
        // Virifica se o email e senha sao validos no Mysql
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // "1" e a confirmacao vinda do php com resultado da verificacao do login e senha
                // o acesso ao app e liberado.
                if (response.contains("1")){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Usuario ou senha incorretos", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            //Joga as informcaoes no php
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("email", eTemail.getText().toString());
                params.put("senha", eTsenha.getText().toString());
                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }


    public void verif(){
        //Verifica qual o tema escolhido pelo usuário
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SplashScreen.escolha = prefs.getInt("escolha", 0);
        if (SplashScreen.escolha == 0){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else if (SplashScreen.escolha == 1){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    private void mostrarsenha(){
        msenha.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (msenha.isChecked()){
                eTsenha.setInputType(0x00000091);
            }else {
                eTsenha.setInputType(0x00000081);
            }
        });
    }
/*
    public void Login(View view){
        //Salva pra não abrir esta tela novamente e redireciona para a Home

        MainActivity.redirectActivity(this,MainActivity.class);
        finish();
    }
*/
    public void Cad(View view){
        //Salva pra não abrir esta tela novamente e redireciona para a Home

        MainActivity.redirectActivity(this,Formulario.class);
        finish();
    }
    @Override
    public void onBackPressed() {
        MainActivity.sair(this);
    }



    public void cadastro(View view){
        Intent intent = new Intent(this, Formulario.class);
        startActivity(intent);
        finish();
    }
    //login fim
}