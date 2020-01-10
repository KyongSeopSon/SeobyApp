package com.aos.seobyapp;

import android.app.Activity;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class WebViewInterface {

    private WebView mWebView;
    private Activity mActivity;
    private final Handler mHandler = new Handler();

    private FirebaseInterface ieFirebase;

    public WebViewInterface(Activity activity, WebView view)
    {
        mActivity = activity;
        mWebView = view;
    }

    @JavascriptInterface
    public void SendEvent(String type, String param1, String param2, String param3) {
        ieFirebase = new FirebaseInterface(mActivity);

        // 화면뷰 트래킹
        ieFirebase.SendFirebaseEvent(type, param1, param2, param3);
        Toast.makeText(mActivity, "웹 -" + type + " 데이터를 전송하였습니다",Toast.LENGTH_LONG).show();

        //Toast.makeText(mContext, "[" + message + "] 메세지를 받았습니다", Toast.LENGTH_LONG).show();
    }
}