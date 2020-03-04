package com.example.gameforkid;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.gameforkid.save_usersub.SHARED_PREFSSUB;
// Hien thi ra diem cua man hinh phep tru bao diem diem hien tai va diem cao nhat cua nguoi choi cao nhat
public class score_screensub extends AppCompatActivity {
    private TextView end_score;
    private TextView highScore_Label;
    private Button play_again;
    private Button btn_saveuser;
    private Button btn_nosave;
    private Button btn_save;
    private EditText editText;
    private Dialog dialog;
    MediaPlayer player;
    MediaPlayer congrat;
    private int scoreSub;
    private String username;
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_screensub);
        // Điểm hiện tại khi trò chơi kết thúc
        end_score=(TextView)findViewById(R.id.endScoreSub);
        highScore_Label=(TextView)findViewById(R.id.txt_highScoreSub);
        Intent intent = getIntent();
        final int scoreSub= intent.getIntExtra("score", 0);
        end_score.setText(scoreSub + "");
        SharedPreferences settingsSub= getSharedPreferences("GAME_DATA_SUB", Context.MODE_PRIVATE);
        int highScoreSub = settingsSub.getInt("HIGH_SCORE",0);
        if(scoreSub > highScoreSub){
            highScore_Label.setText("HIGH SCORE: "+scoreSub);
            SharedPreferences.Editor editorSub = settingsSub.edit();
            editorSub.putInt("HIGH_SCORE",scoreSub);
            editorSub.commit();
        }
        else{
            highScore_Label.setText("HIGH SCORE: "+highScoreSub);
        }
        play_again=(Button)findViewById(R.id.btn_playAgainSub);
        player = MediaPlayer.create(this, R.raw.tweet);
        congrat = MediaPlayer.create(this, R.raw.quite);
        congrat.start();
        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(score_screensub.this,play_menu.class);
                startActivity(intent);
                player.start();
                finish();
            }
        });
        btn_saveuser = (Button)findViewById(R.id.btn_saveUserSub);
        btn_saveuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(score_screensub.this);
                dialog.setContentView(R.layout.dialog);
                dialog.setCancelable(false);
                player.start();
                btn_nosave = (Button) dialog.findViewById(R.id.btn_nosave);
                editText=(EditText)dialog.findViewById(R.id.edittext);
                btn_nosave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        player.start();
                        Toast.makeText(score_screensub.this,"No Save",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                btn_save = (Button) dialog.findViewById(R.id.btn_save);
                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setUp();
                        saveDataUser(username,scoreSub);
                        Intent intent = new Intent(score_screensub.this, save_usersub.class);
                        startActivity(intent);
                        player.start();
                        finish();
                    }
                });
                dialog.show();
            }
        });
    }
    public void saveDataUser(String username, int score){
        SharedPreferences sharedPreferencesSub= getSharedPreferences(SHARED_PREFSSUB,MODE_PRIVATE);
        SharedPreferences.Editor editorSub = sharedPreferencesSub.edit();
        String userInfo=sharedPreferencesSub.getString(username,"");
        String toWrite="";
        if(!userInfo.equals("")){
            String[] userDetail = userInfo.split(",");
            int highScore = Integer.parseInt(userDetail[1]);
            if(score>highScore){
                highScore=score;
            }
            toWrite=score+","+highScore;
        } else {
            toWrite=score+","+score;
        }
        editorSub.putString(username,toWrite);
        editorSub.commit();
        editorSub.apply();
        Toast.makeText(this,"Data saved "+score,Toast.LENGTH_SHORT).show();
    }
    public void setUp (){
        Intent intent =getIntent();
        scoreSub= intent.getIntExtra("score",0);
        username = editText.getText().toString();
    }
}
