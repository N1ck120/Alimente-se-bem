package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    public static boolean keep;
    public static String email1, senha1, email2, senha2;
    private CheckBox kc;
    public  EditText eTemail, eTsenha;
    private final String URL = "http://siad.net.br/app/loginUsuarioPHP.php";
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

        if (isOnline()){
            keep();
        }else if(!isOnline() && keep){
            email2 = prefs.getString("email", "" );
            senha2 = prefs.getString("senha", "");
            eTemail.setText(email2);
            eTsenha.setText(senha2);

            kc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        keep = true;
                    }else {
                        keep = false;
                    }
                    SplashScreen.prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = SplashScreen.prefs.edit();
                    editor.putBoolean("keep", keep);
                    editor.apply();
                }
            });

            Context context = getApplicationContext();
            CharSequence text = "Sem internet!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        btn_login.setOnClickListener(view -> {

            email1 = eTemail.getText().toString();
            senha1 = eTsenha.getText().toString();

            if (!isOnline()){
                Context context = getApplicationContext();
                CharSequence text = "Sem internet!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

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
        });
        isOnline();
    }
    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return manager.getActiveNetworkInfo() != null &&
                manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public  void keep(){
        if (keep){
            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            email2 = prefs.getString("email", "" );
            senha2 = prefs.getString("senha", "");

            eTemail.setText(email2);
            eTsenha.setText(senha2);

                // Virifica se o email e senha sao validos no Mysql
                StringRequest request = new StringRequest(Request.Method.POST, URL, response -> {
                    // "1" e a confirmacao vinda do php com resultado da verificacao do login e senha
                    // o acesso ao app e liberado.
                    if (response.contains("1")){
                        Intent i = new Intent(Login.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Usuario ou senha incorretos", Toast.LENGTH_SHORT).show();
                    }
                }, volleyError -> {
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
        StringRequest request = new StringRequest(Request.Method.POST, URL, response -> {
            // "1" e a confirmacao vinda do php com resultado da verificacao do login e senha
            // o acesso ao app e liberado.
            if (response.contains("1")){
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "Usuario ou senha incorretos", Toast.LENGTH_SHORT).show();
            }
        }, volleyError -> {
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