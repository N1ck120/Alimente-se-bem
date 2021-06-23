package com.example.tcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.Toast;

public class Alimentos extends AppCompatActivity {
    //Iniciar variavel
    DrawerLayout drawerLayout;

    private WebView wv;
    public ImageView ref;
    public LinearLayout conect2;
    public Space r_space;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentos);

        //Estanciar variavel
        drawerLayout = findViewById(R.id.drawer_layout);
        ref = findViewById(R.id.refresh);
        conect2 = findViewById(R.id.erro);
        wv = findViewById(R.id.lista);

        if(SplashScreen.escolha == 0){
            WebView myWebView = findViewById(R.id.lista);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.loadUrl("http://siad.net.br/app/listaApp.php");
            myWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(request.getUrl().toString());
                    return false;
                }
            });
        }else{
            WebView myWebView = findViewById(R.id.lista);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.loadUrl("http://siad.net.br/app/listaAppDark.php");
            myWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(request.getUrl().toString());
                    return false;
                }
            });
        }
        isOnline();
        internet();
    }
    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return manager.getActiveNetworkInfo() != null &&
                manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
    public void internet(){
        if(!isOnline()) {
            wv.setVisibility(View.GONE);
            ref.setVisibility(View.INVISIBLE);
            conect2.setVisibility(View.VISIBLE);
        }else {
            wv.setVisibility(View.VISIBLE);
            ref.setVisibility(View.VISIBLE);
            conect2.setVisibility(View.GONE);
        }
    }
    public void reconectar(View view){
        isOnline();
        internet();
        if(SplashScreen.escolha == 0){
            WebView myWebView = findViewById(R.id.lista);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.loadUrl("http://siad.net.br/app/listaApp.php");
        }else{
            WebView myWebView = findViewById(R.id.lista);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.loadUrl("http://siad.net.br/app/listaAppDark.php");
        }
    }
    public void  refresh(View view){

        isOnline();
        if (isOnline()){
            if(SplashScreen.escolha == 0){
                WebView myWebView = findViewById(R.id.lista);
                WebSettings webSettings = myWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                wv.clearCache(true);
                myWebView.loadUrl("http://siad.net.br/app/listaApp.php");
            }else{
                WebView myWebView = findViewById(R.id.lista);
                WebSettings webSettings = myWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                wv.clearCache(true);
                myWebView.loadUrl("http://siad.net.br/app/listaAppDark.php");
            }
            Context context = getApplicationContext();
            CharSequence text = "Recarregando...";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }else{
            internet();
            Context context = getApplicationContext();
            CharSequence text = "Sem internet!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
    public void ClickMenu(View view){
        //Abrir Drawer
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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
        MainActivity.closeDrawer(drawerLayout);
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