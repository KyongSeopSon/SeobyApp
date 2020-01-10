package com.aos.seobyapp;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aos.seobyapp.WebViewInterface;

public class ViewWebview extends AppCompatActivity {

    private WebView mWebView;
    private WebSettings mWebSettings;

    private FirebaseInterface ieFirebase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_webview);

        ieFirebase = new FirebaseInterface(this);

        // 화면뷰 트래킹
        ieFirebase.SendFirebaseEvent("PAGEVIEW", "APP_메인", "ViewWebview", null);
        Toast.makeText(getApplicationContext(), "앱 화면뷰 페이지뷰를 전송하였습니다",Toast.LENGTH_LONG).show();

        // 웹뷰 시작
        mWebView = (WebView) findViewById(R.id.webView);

        WebViewInterface mWebViewInterface = new WebViewInterface(this, mWebView);


        mWebView.addJavascriptInterface(mWebViewInterface, "app");

        mWebView.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        mWebSettings = mWebView.getSettings(); //세부 세팅 등록
        mWebSettings.setJavaScriptEnabled(true); // 웹페이지 자바스클비트 허용 여부
        mWebSettings.setSupportMultipleWindows(false); // 새창 띄우기 허용 여부
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true); // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        mWebSettings.setLoadWithOverviewMode(true); // 메타태그 허용 여부
        mWebSettings.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
        mWebSettings.setSupportZoom(false); // 화면 줌 허용 여부
        mWebSettings.setBuiltInZoomControls(false); // 화면 확대 축소 허용 여부
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT); // 브라우저 캐시 허용 여부
        mWebSettings.setDomStorageEnabled(true); // 로컬저장소 허용 여부

        mWebView.loadUrl("http://seoby.kr/m/index.html"); // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
    }
}

