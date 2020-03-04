package com.example.gameforkid;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Map;
// Luu ten nguoi dung, diem hien tai, diem cao nhat cua tung nguoi dung trong phep cong
public class save_user extends AppCompatActivity {
    public static final String SHARED_PREFSADD="sharedPrefs";
    public static final String TEXT = "text";
    private ListView lvadd;
    private Button  btn_return;
    private Button btn_delete;
    MediaPlayer player;
    base_adapter baseAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_user);
        lvadd = (ListView)findViewById(R.id.list_item);
        btn_return=(Button)findViewById(R.id.btn_return);
        player=MediaPlayer.create(this, R.raw.tweet);
        updateChange();
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(save_user.this,play_menu.class);
                startActivity(intent);
                player.start();
                finish();
            }
        });
    }
    public void updateChange(){
        SharedPreferences sharedPreferencesAdd = getSharedPreferences(SHARED_PREFSADD,MODE_PRIVATE);
        ArrayList<player_record> arrayListAdd= new ArrayList<>();
        for(Map.Entry <String, ?> u:sharedPreferencesAdd.getAll().entrySet()){
            String usernameAdd = u.getKey().toString();
            String scores = sharedPreferencesAdd.getString(usernameAdd,"0,0");
            String[] detail = scores.split (",");
            arrayListAdd.add(new player_record(usernameAdd,Integer.parseInt(detail[0]),Integer.parseInt(detail[1])));
        }
        base_adapter baseAdapter = new base_adapter(this,arrayListAdd);
        lvadd.setAdapter(baseAdapter);
        baseAdapter.notifyDataSetChanged();
    }
}
