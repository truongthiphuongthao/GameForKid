package com.example.gameforkid;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
// Dem thoi gian truoc khi vao choi phep cong tu 1 den 4 PLAY
public class timer_add extends AppCompatActivity {
    public int counter;
    MediaPlayer player;
    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdown_timer);
        final TextView counttime=findViewById(R.id.counttime);
        player = SoundMgr.SoundMediaNoLoop(this, R.raw.tick);
        new CountDownTimer(3000,500) {
            @Override
            public void onTick(long millisUntilFinished) {
                counttime.setText(String.valueOf(counter));
                player.start();
                counter++;
            }
            @Override
            public void onFinish() {
                counttime.setText("PLAY");
                Intent intent = new Intent(timer_add.this, addition_caculator.class);
                player.stop();
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
