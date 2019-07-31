package com.example.uiwelcome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class SmClick extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm_click);
        //webview = findViewById(R.id.webView);
        WebView webview = new WebView(getApplicationContext());
        setContentView(webview);


        webview.loadUrl("https://www.facebook.com");
    }
}
