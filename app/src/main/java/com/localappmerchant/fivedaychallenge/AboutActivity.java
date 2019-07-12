package com.localappmerchant.fivedaychallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutActivity extends AppCompatActivity {

    private static final String TAG = "5DC-AboutActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Enable the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // initView
        initUi();
    }


    void initUi(){
        WebView alcWebView = findViewById(R.id.webView);
        // Set the Webview Client..
        alcWebView.setWebViewClient(new ALCWebViewClient());

        // Get the settings reference for this webview and do some settings.
        // Enable Javascript
        WebSettings webSettings = alcWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Finally, Load the ALC URL
        alcWebView.loadUrl(getResources().getString(R.string.alc_about_url));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getParentActivityIntent() == null) {
                    Log.i(TAG, "I must have forgotten to specify the parentActivityName in the AndroidManifest!");
                    onBackPressed();
                } else {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // Added this implementation of WebViewClient to fix https url loading
    private class ALCWebViewClient extends WebViewClient {
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // Ignore SSL certificate errors
        }
    }

}
