package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Gadgets extends AppCompatActivity {
    //Iniciar variavel
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadgets);

        //Estanciar variavel
        drawerLayout = findViewById(R.id.drawer_layout);
        MainActivity.nome = findViewById(R.id.nome_toolbar);
        if (!Login.keep){
            MainActivity.nome.setText("Olá" + " " + Login.getEmail1());
        }else{
            MainActivity.nome.setText("Olá" + " " + Login.getEmail2());
        }
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
        //Redirecionar Activity
        MainActivity.redirectActivity(this,Alimentos.class);
        finish();
    }
    public void ClickPiramide(View view){
        //Redirecionar activity para Piramide de Alimentos
        MainActivity.redirectActivity(this,Piramide.class);
        finish();
    }
    public void ClickPorcoes(View view){
        // Redirecionar para Piramide Alimentar
        MainActivity.redirectActivity(this,Porcoes.class);
        finish();
    }
    public void ClickCalculadora(View view){
        //Redirecionar activity para Calculadora IMC
        MainActivity.redirectActivity(this,Calculadora.class);
        finish();
    }
    public void ClickGadgets(View view){
        //Redirecionar activity para Gadgets
        MainActivity.closeDrawer(drawerLayout);
    }
    public void ClickConfig(View view){
        // Redirecionar para sobre o app
        MainActivity.redirectActivity(this,Config.class);
        finish();
    }
    public void ClickSobreApp(View view){
        //Redirecionar activity para Sobre o App
        MainActivity.redirectActivity(this,SobreApp.class);
        finish();
    }
    public void ClickLogout(View view){
        //Sair da conta
        SplashScreen.prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = SplashScreen.prefs.edit();
        editor.putBoolean("keep", false);
        editor.apply();
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