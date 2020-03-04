package com.example.gameforkid;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import androidx.annotation.Nullable;
public class help extends Activity {
      WebView webView;
      private Button btn_back;
      MediaPlayer player;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        webView=(WebView)findViewById(R.id.web_view);
        player=MediaPlayer.create(this,R.raw.tweet);
        btn_back=(Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(help.this,MainActivity.class);
                startActivity(intent);
                player.start();
                finish();
            }
        });
        loadHTMLPage();
    }
    private void loadHTMLPage(){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/help.html");
    }
}
