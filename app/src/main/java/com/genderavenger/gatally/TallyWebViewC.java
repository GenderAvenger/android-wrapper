package com.genderavenger.gatally;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.net.MailTo;

public abstract class TallyWebViewC extends WebViewClient
{
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        System.out.println(url);
        if (url.startsWith("mailto")){
            MailTo mt = MailTo.parse(url);
            sendEmail(url);
            return true;
        }
        return false;
    }

    public abstract void launchExternalBrowser(String url);

    public abstract void sendEmail(String url);
}