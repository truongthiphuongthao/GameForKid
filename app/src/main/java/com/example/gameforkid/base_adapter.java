package com.example.gameforkid;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
class player_record{
    public String username;
    public int score;
    // diem cao nhat
    public int highScore;
    //  hàm xây dựng
    public player_record(String username, int score, int highScore){
        this.username=username;
        this.score=score;
        this.highScore=highScore;
    }
}
public class base_adapter extends BaseAdapter {
    Context context;
    LayoutInflater inflter;
    ArrayList<player_record> list_adapter;
    // Tên người chơi
    private TextView listTestViewUser;
    // Điểm hiện tại
    private TextView ListTestScore;
    private TextView ListTestHighScore;
    public base_adapter(Context applicationContext, ArrayList<player_record> list_adapter) {
        this.context = applicationContext;
        inflter =((Activity)  applicationContext).getLayoutInflater();
        this.list_adapter = list_adapter;
    }
    @Override
    public int getCount() {
      return list_adapter.size();
    }
    @Override
    public Object getItem(int i) {
        return list_adapter.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.list_item, null);
        listTestViewUser=(TextView) view.findViewById(R.id.listTestViewUser);
        ListTestScore=(TextView)view.findViewById(R.id.listTextViewScore);
        ListTestHighScore=(TextView)view.findViewById(R.id.listTextViewHighScore);
        player_record p = list_adapter.get(i);
        listTestViewUser.setText(p.username);
        ListTestScore.setText(p.score+"");
        ListTestHighScore.setText(p.highScore+"");
        return view;
    }
}
