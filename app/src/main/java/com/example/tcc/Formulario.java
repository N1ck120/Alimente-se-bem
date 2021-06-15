package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Formulario extends AppCompatActivity {

    private EditText senha, i;
    private CheckBox msenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        msenha = findViewById(R.id.checkBox4);
        senha = findViewById(R.id.senha);
        i = findViewById(R.id.idade);
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

    public void Cadastrar(View view){
        //Salva pra não abrir esta tela novamente e redireciona para a Home
            MainActivity.redirectActivity(this,Login.class);
            finish();

        }
    }