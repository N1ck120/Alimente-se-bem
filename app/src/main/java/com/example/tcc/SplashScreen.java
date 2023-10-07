package com.example.tcc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SplashScreen extends AppCompatActivity {

    private static final int timer = 2000;
    public static SharedPreferences prefs;
    private static int escolha;

    public static int getEscolha() {
        return escolha;
    }

    public static void setEscolha(int escolha) {
        SplashScreen.escolha = escolha;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        verif();
        new Handler().postDelayed(() -> {
            //Verifica se é a primeira vez que o app foi aberto
            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            boolean firstStart = prefs.getBoolean("firstStart", true);
            if (firstStart) {
                Intent i = new Intent(SplashScreen.this, Boasvindas.class);
                startActivity(i);
            }else {
                Intent i = new Intent(SplashScreen.this, Login.class);
                startActivity(i);
            }
            finish();
        }, timer);
    }
    public void verif(){
        //Verifica qual o tema escolhido pelo usuário
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        escolha = prefs.getInt("escolha", 0);
        if (escolha == 0){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else if (escolha == 1){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }
}