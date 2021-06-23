package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Boasvindas extends AppCompatActivity {

    private CheckBox aceito;
    private Button prosseguir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boasvindas);

        aceito = findViewById(R.id.checkBox2);
        prosseguir = findViewById(R.id.button2);

        termos();
    }
    public void  termos(){
        aceito.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prosseguir.setEnabled(aceito.isChecked());
        });
    }
    @Override
    public void onBackPressed() {
        MainActivity.sair(this);
    }
    public void Prosseguir(View view){
        //Salva pra não abrir esta tela novamente e redireciona para a Home
        SplashScreen.prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = SplashScreen.prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();

        if (SplashScreen.escolha == 0){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else if (SplashScreen.escolha == 1){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        MainActivity.redirectActivity(this,Login.class);
        finish();
    }
}