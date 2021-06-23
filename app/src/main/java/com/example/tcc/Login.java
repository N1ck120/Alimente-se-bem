package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    public static boolean keep;
    public static String email1, senha1, email2, senha2;
    private CheckBox kc;
    public  EditText eTemail, eTsenha;
    private String URL = "http://siad.net.br/app/loginUsuarioPHP.php";
    Button btn_login;

    private CheckBox msenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        msenha = findViewById(R.id.mostrar);
        eTemail = findViewById(R.id.email_login);
        eTsenha = findViewById(R.id.senha_login);
        btn_login = findViewById(R.id.btnLogar);
        kc = findViewById(R.id.keepconect);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        keep = prefs.getBoolean("keep", false);
        kc.setChecked(keep);

        mostrarsenha();
        keep();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email1 = eTemail.getText().toString();
                senha1 = eTsenha.getText().toString();

                if (kc.isChecked()){
                    SplashScreen.prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = SplashScreen.prefs.edit();
                    editor.putString("email", email1);
                    editor.putString("senha", senha1);
                    editor.putBoolean("keep", true);
                    editor.apply();
                    login();
                }else{
                    login();
                }
            }
        });
    }

    public  void keep(){
        if (keep){
            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            email2 = prefs.getString("email", "" );
            senha2 = prefs.getString("senha", "");

            eTemail.setText(email2);
            eTsenha.setText(senha2);

                // Virifica se o email e senha sao validos no Mysql
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // "1" e a confirmacao vinda do php com resultado da verificacao do login e senha
                        // o acesso ao app e liberado.
                        if (response.contains("1")){
                            Intent i = new Intent(Login.this, MainActivity.class);
                            startActivity(i);
                            finish();
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
                        params.put("email", email2);
                        params.put("senha", senha2);
                        return params;
                    }
                };
                Volley.newRequestQueue(this).add(request);
        }
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
                    Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);
                    finish();
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
    private void mostrarsenha(){
        msenha.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (msenha.isChecked()){
                eTsenha.setInputType(0x00000091);
            }else {
                eTsenha.setInputType(0x00000081);
            }
        });
    }
    public void Cad(View view){
        //Salva pra não abrir esta tela novamente e redireciona para a Home
        MainActivity.redirectActivity(this,Formulario.class);
        finish();
    }
    @Override
    public void onBackPressed() {
        MainActivity.sair(this);
    }
}