package com.example.gameforkid;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
public class view_score extends AppCompatActivity {
    private Button btn_viewscoreadd;
    private Button btn_viewscoresub;
    private Button btn_backviewscore;
    MediaPlayer player;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_score);
        player=MediaPlayer.create(this,R.raw.tweet);
        btn_viewscoreadd=(Button)findViewById(R.id.btn_viewscoreadd);
        btn_viewscoresub=(Button)findViewById(R.id.btn_viewscoresub);
        btn_backviewscore=(Button)findViewById(R.id.btn_backviewscore);
        // Chuyển sang xem điểm phép cộng
        btn_viewscoreadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view_score.this,save_user.class);
                startActivity(intent);
                player.start();
                finish();
            }
        });
        // Chuyển sang xem điểm phép trừ
        btn_viewscoresub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view_score.this,save_usersub.class);
                startActivity(intent);
                player.start();
                finish();
            }
        });
        // Trở về màn hình PLAY MENU
        btn_backviewscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view_score.this,play_menu.class);
                startActivity(intent);
                player.start();
                finish();
            }
        });
    }
}
