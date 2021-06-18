package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Formulario extends AppCompatActivity {

    private EditText email, senha, nome, idade, peso, altura;
    private CheckBox msenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        msenha = findViewById(R.id.mostrar_cad);
        email = findViewById(R.id.email_cad);
        senha = findViewById(R.id.senha_cad);
        nome = findViewById(R.id.nome_cad);
        idade = findViewById(R.id.idade_cad);
        peso = findViewById(R.id.peso_cad);
        altura = findViewById(R.id.altura_cad);
        mostrarsenha();
        verif();
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

    public void Cadastrar(View view){
        //Salva pra não abrir esta tela novamente e redireciona para a Home
        MainActivity.redirectActivity(this,Login.class);
        finish();
    }

    private void mostrarsenha(){
        msenha.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (msenha.isChecked()){
                senha.setInputType(0x00000091);
            }else {
                senha.setInputType(0x00000081);
            }
        });
    }

    @Override
    public void onBackPressed() {
        MainActivity.redirectActivity(this,Login.class);
        finish();
    }
}