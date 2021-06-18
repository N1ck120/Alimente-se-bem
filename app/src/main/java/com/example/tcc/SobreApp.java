package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class SobreApp extends AppCompatActivity {
    //Iniciar variavel
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre_app);

        //Estanciar variavel
        drawerLayout = findViewById(R.id.drawer_layout);
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
    public void Github(View view){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Nickolas120/Alimente-se-bem/")));
    }

    public void ClickMenu(View view){
        //Abrir Drawer
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        //Fechar Drawer
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        //Redirecionar activity para Home
        MainActivity.redirectActivity(this,MainActivity.class);
        finish();
    }

    public void ClickAlimentos(View view){
        //Redirecionar activity para Alimentos
        MainActivity.redirectActivity(this,Alimentos.class);
        finish();
    }

    public void ClickPiramide(View view){
        //Redirecionar activity para Piramide
        MainActivity.redirectActivity(this,Piramide.class);
        finish();
    }

    public void ClickPorcoes(View view){
        // Redirecionar para Piramide Alimentar
        MainActivity.redirectActivity(this,Porcoes.class);
        finish();
    }

    public void ClickCalculadora(View view){
        //Redirecionar activity para Calculadora
        MainActivity.redirectActivity(this,Calculadora.class);
        finish();
    }

    public void ClickGadgets(View view){
        //Redirecionar activity para Gadgets
        MainActivity.redirectActivity(this,Gadgets.class);
        finish();
    }

    public void ClickConfig(View view){
        // Redirecionar para sobre o app
        MainActivity.redirectActivity(this,Config.class);
        finish();
    }

    public void ClickSobreApp(View view){
        //Redirecionar Activity
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickLogout(View view){
        //Sair da conta
        MainActivity.redirectActivity(this,Login.class);
        finish();
    }
    @Override
    public void onBackPressed() {
        MainActivity.sair(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Fechar Drawer
        MainActivity.closeDrawer(drawerLayout);
    }
}