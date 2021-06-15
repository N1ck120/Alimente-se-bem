package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    private EditText senha;
    private CheckBox msenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        msenha = findViewById(R.id.checkBox3);
        senha = findViewById(R.id.senha);
        mostrarsenha();
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