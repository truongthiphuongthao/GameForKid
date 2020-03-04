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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.gameforkid.save_user.SHARED_PREFSADD;
// Hien thi ra diem cua man hinh phep cong bao diem diem hien tai va diem cao nhat cua nguoi choi cao nhat
public class score_screenadd extends AppCompatActivity {
    // Khai báo các thuộc tính
    private TextView end_score;
    private Button play_again;
    private TextView highScore_Label;
    private Button btn_saveuser;
    private Button btn_save;
    private  Button btn_nosave;
    private  String username;
    private ListView lv;
    private int score;
    private Dialog dialog;
    private EditText editText;
    MediaPlayer player;
    MediaPlayer congrat;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_screenadd);
        // Điểm hiện tại khi chơi thua
        end_score=(TextView)findViewById(R.id.endScoreAdd);
        highScore_Label=(TextView)findViewById(R.id.txt_highScoreAdd);
        Intent intent = getIntent();
        // Lấy score từ intent trước
        final int score= intent.getIntExtra("score", 0);
        end_score.setText(score + "");
        SharedPreferences settingsAdd= getSharedPreferences("GAME_DATA_ADD", Context.MODE_PRIVATE);
        int highScore = settingsAdd.getInt("HIGH_SCORE",0);
        if(score > highScore){
            highScore_Label.setText("HIGH SCORE: "+score);
            SharedPreferences.Editor editorAdd = settingsAdd.edit();
            editorAdd.putInt("HIGH_SCORE",score);
            editorAdd.commit();
        }
        else{
            highScore_Label.setText("HIGH SCORE: "+highScore);
        }
        play_again=(Button)findViewById(R.id.btn_playAgainAdd);
        player = MediaPlayer.create(this, R.raw.tweet);
        congrat = MediaPlayer.create(this, R.raw.quite);
        congrat.start();
        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(score_screenadd.this,play_menu.class);
                startActivity(intent);
                player.start();
                finish();
            }
        });
        btn_saveuser = (Button)findViewById(R.id.btn_saveUserAdd);
        btn_saveuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(score_screenadd.this);
                dialog.setContentView(R.layout.dialog);
                dialog.setCancelable(false);
                player.start();
                // Nút no save
                btn_nosave = (Button) dialog.findViewById(R.id.btn_nosave);
                // Nhập tên người dùng vào
                editText=(EditText)dialog.findViewById(R.id.edittext);
                btn_nosave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        player.start();
                        Toast.makeText(score_screenadd.this,"No Save",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                btn_save = (Button) dialog.findViewById(R.id.btn_save);
                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setUp();
                        saveDataUser(username,score);
                        Intent intent = new Intent(score_screenadd.this, save_user.class);
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
        SharedPreferences sharedPreferencesAdd= getSharedPreferences(SHARED_PREFSADD,MODE_PRIVATE);
        SharedPreferences.Editor editorAdd = sharedPreferencesAdd.edit();
        String userInfo=sharedPreferencesAdd.getString(username,"");
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
        editorAdd.putString(username,toWrite);
        editorAdd.commit();
        editorAdd.apply();
        Toast.makeText(this,"Data saved "+score,Toast.LENGTH_SHORT).show();
    }
    public void setUp (){
        Intent intent =getIntent();
        score= intent.getIntExtra("score",0);
        username = editText.getText().toString();
    }
}

