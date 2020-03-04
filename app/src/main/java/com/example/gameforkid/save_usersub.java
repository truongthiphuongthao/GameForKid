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
// Luu ten nguoi dung, diem hien tai, diem cao nhat cua tung nguoi dung trong phep tru
public class save_usersub extends AppCompatActivity {
    public static final String SHARED_PREFSSUB="sharedPrefsSUB";
    public static final String TEXT = "text";
    private ListView lvsub;
    private Button btn_return;
    private  Button btn_delete;
    MediaPlayer player;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_usersub);
        lvsub = (ListView)findViewById(R.id.list_itemsub);
        player = MediaPlayer.create(this, R.raw.tweet);
        updateChange();
        btn_return=(Button)findViewById(R.id.btn_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(save_usersub.this, play_menu.class);
                startActivity(intent);
                player.start();
                finish();
            }
        });
    }
    public void updateChange(){
        SharedPreferences sharedPreferencesSub = getSharedPreferences(SHARED_PREFSSUB,MODE_PRIVATE);
        ArrayList<player_recordsub> arrayListSub = new ArrayList<>();
        for(Map.Entry <String, ?> u:sharedPreferencesSub.getAll().entrySet()){
            String usernameSub = u.getKey().toString();
            String scores = sharedPreferencesSub.getString(usernameSub,"0,0");
            String[] detail = scores.split (",");
            arrayListSub.add(new player_recordsub(usernameSub,Integer.parseInt(detail[0]),Integer.parseInt(detail[1])));
        }
        base_adaptersub baseAdapterSub = new base_adaptersub(this,arrayListSub);
        lvsub.setAdapter(baseAdapterSub);
        baseAdapterSub.notifyDataSetChanged();
    }

}
