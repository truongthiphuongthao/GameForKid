package com.example.gameforkid;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
class player_recordsub{
    public String username;
    public int score;
    public int highScore;
    public player_recordsub(String username, int score, int highScore){
        this.username=username;
        this.score=score;
        this.highScore=highScore;
    }
}
public class base_adaptersub extends BaseAdapter {
    Context context;
    LayoutInflater inflter;
    ArrayList<player_recordsub> list_adapter;
    private TextView listTestViewUserSub;
    private TextView ListTestScoreSub;
    private TextView ListTestHighScore;
    public base_adaptersub(Context applicationContext, ArrayList<player_recordsub> list_adapter) {
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
        view = inflter.inflate(R.layout.list_itemsub, null);
        listTestViewUserSub=(TextView) view.findViewById(R.id.listTestViewUserSub);
        ListTestScoreSub=(TextView)view.findViewById(R.id.listTextViewScoreSub);
        ListTestHighScore=(TextView)view.findViewById(R.id.listTextViewHighScore);
        player_recordsub p = list_adapter.get(i);
        listTestViewUserSub.setText(p.username);
        ListTestScoreSub.setText(p.score+"");
        ListTestHighScore.setText(p.highScore+"");
        return view;
    }
}
