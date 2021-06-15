package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class Piramide extends AppCompatActivity {
    //Iniciar variavel
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piramide);

        //Estanciar variavel
        drawerLayout = findViewById(R.id.drawer_layout);
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
        //Redirecionar Activity
        MainActivity.closeDrawer(drawerLayout);
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
        //Redirecionar activity para Sobre o App
        MainActivity.redirectActivity(this,SobreApp.class);
        finish();
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