package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    private EditText senha;
    private CheckBox msenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        msenha = findViewById(R.id.mostrar);
        senha = findViewById(R.id.senha_login);
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

    private void mostrarsenha(){
        msenha.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (msenha.isChecked()){
                senha.setInputType(0x00000091);
            }else {
                senha.setInputType(0x00000081);
            }
        });
    }

    public void Login(View view){
        //Salva pra não abrir esta tela novamente e redireciona para a Home

        MainActivity.redirectActivity(this,MainActivity.class);
        finish();
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