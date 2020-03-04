package com.example.gameforkid;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
public class play_menu extends AppCompatActivity {
    // Khai báo các thuộc tính của các nút
    private Button btn_back;
    private Button btn_addition;
    private Button btn_subtraction;
    private Button btn_viewscore;
    // Tạo đối tượng player để phát âm thanh
    MediaPlayer player;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_menu);
        btn_viewscore=(Button)findViewById(R.id.btn_viewscore);
        btn_addition=(Button)findViewById(R.id.btn_addition);
        btn_subtraction=(Button)findViewById(R.id.btn_subtraction);
        btn_back=(Button)findViewById(R.id.btn_back);
        // Tạo âm thanh trong màn hình PLAY MENU
        player = MediaPlayer.create(this,R.raw.tweet);
        // Click vào nút View Score bắt sự kiện chuyển sang màn hình xem điểm
        btn_viewscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(play_menu.this, view_score.class);
                startActivity(intent);
                // phát âm thanh
                player.start();
                finish();
            }
        });
        // Click vào nút Addition bắt sự kiện chuyển sang màn hình đếm thời gian
        btn_addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(play_menu.this,timer_add.class);
                startActivity(intent);
                // phát âm thanh
                player.start();
                finish();
            }
        });
        // Click vào nút Subtraction bắt sự kiện chuyển sang màn hình đếm thời gian
        btn_subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(play_menu.this,timer_sub.class);
                startActivity(intent);
                // phát âm thanh
                player.start();
                finish();
            }
        });
        // Click vào nút Back bắt sự kiện chuyển sang màn hình MAIN MENU trước đó
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(play_menu.this,MainActivity.class);
                startActivity(intent);
                player.start();
                // phát âm thanh
                finish();
            }
        });

    }
}
