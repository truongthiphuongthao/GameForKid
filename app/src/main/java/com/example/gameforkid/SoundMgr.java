package com.example.gameforkid;
import android.app.Activity;
import android.media.MediaPlayer;
// Lặp âm thanh
public class SoundMgr {
    public static MediaPlayer SoundMedia(Activity mm, int id){
        MediaPlayer player = MediaPlayer.create(mm, id);
        player.start();
        player.setLooping(true);
        return player;
    }
    // Không lặp âm thanh
    public static MediaPlayer SoundMediaNoLoop(Activity mm, int id){
        MediaPlayer player = MediaPlayer.create(mm, id);
        player.start();
        player.setLooping(false);
        return player;
    }
}
