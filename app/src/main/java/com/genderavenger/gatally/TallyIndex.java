package com.genderavenger.gatally;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.genderavenger.gatally.TallyWebViewC;
import android.net.Uri;
import android.graphics.Bitmap;
import android.widget.ProgressBar;
import android.widget.Button;


public class TallyIndex extends Activity {

    private WebView tallyWebView;
    private ProgressBar progressBar;
    private Button reloadButton;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && tallyWebView.canGoBack())
        {
            tallyWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tally_index);

        tallyWebView = (WebView) findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        WebViewClient client = new TallyWebViewC() {
            @Override
            public void launchExternalBrowser(String url) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }

            @Override
            public void onPageStarted(WebView codePunkerWebView, String url, Bitmap favicon) {
                super.onPageStarted(codePunkerWebView, url, favicon);
            }

            @Override
            public void onPageFinished(WebView codePunkerWebView, String url) {
                super.onPageFinished(codePunkerWebView, url);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                setContentView(R.layout.error_layout);

                reloadButton = (Button) findViewById(R.id.reloadButton);
                reloadButton.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.activity_tally_index);
                        tallyWebView.loadUrl("http://app.genderavenger.com");
                    }
                });
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        };
        tallyWebView.setWebViewClient(client);
        tallyWebView.getSettings().setJavaScriptEnabled(true);
        tallyWebView.getSettings().setUserAgentString("GenderAvenger.com/0.1 (http://app.genderavenger.com/)");
        tallyWebView.loadUrl("http://app.genderavenger.com");
    }


}
