package com.example.gameforkid;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity  {
    // Khai báo tất cả các nút bao gồm nút PLAY, HELP, QUIT
    private Button btn_play;
    private Button btn_help;
    private Button btn_quit;
    // Khai báo MediaPlayer để thêm nhạc
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_play=(Button)findViewById(R.id.btn_play);
        btn_help = (Button)findViewById(R.id.btn_help);
        btn_quit=(Button)findViewById(R.id.btn_quit);
        // Gọi MediaPlayer.create để tạo âm thanh trong màn hình main
        player=MediaPlayer.create(this,R.raw.tweet);
        // Click vào nút PLAY bắt sự kiện chuyển sang màn hình PLAY MENU
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, play_menu.class);
                startActivity(intent);
                // Phát âm thanh khi nhấn nút PLAY
                player.start();
                finish();
            }

        });
        // Click vào nút HELP bắt sự kiện chuyển sang màn hình HELP
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,help.class);
                startActivity(intent);
                // Phát âm thanh khi nhấn nút HELP
                player.start();
                finish();
            }
        });
        // Click vào nút QUIT bắt sự kiện chuyển sang thoát khỏi màn hình
        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Phát âm thanh khi nhấn nút QUIT
                player.start();
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            }
        });
    }
}
