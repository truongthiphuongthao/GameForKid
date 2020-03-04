package com.example.gameforkid;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
public class addition_caculator extends AppCompatActivity {
    // Khai báo các thuộc tính trong màn hình Addition
    private TextView ttScore;
    private Button btn_A, btn_B, btnChoice_A, btnChoice_B, btnChoice_C, btnChoice_D;
    private ProgressBar progressBar;
    private CountDownTimer countDownTimer;
    private ImageView background;
    int round=1;
    int score=0;
    // Nhạc trong khi chơi
    MediaPlayer bckgndMusic;
    // Nhạc click vào các nút
    MediaPlayer player;
    // Tạo mảng động chứa các danh sách background
    ArrayList<Drawable> lst_background;
    HashSet<Pair<Integer, Integer>> history;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addition_caculator);
        ttScore=(TextView)findViewById(R.id.score_add);
        btn_A=(Button)findViewById(R.id.btn_A);
        btn_B=(Button)findViewById(R.id.btn_B) ;
        btnChoice_A=(Button)findViewById(R.id.btnChoice_A);
        btnChoice_B=(Button)findViewById(R.id.btnChoice_B);
        btnChoice_C=(Button)findViewById(R.id.btnChoice_C);
        btnChoice_D=(Button)findViewById(R.id.btnChoice_D);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        background=(ImageView) findViewById(R.id.background_addition);
        // thay đổi các background
        lst_background = new ArrayList<Drawable>();
        lst_background.add(getResources().getDrawable(R.drawable.add1,null));
        lst_background.add(getResources().getDrawable(R.drawable.add2,null));
        lst_background.add(getResources().getDrawable(R.drawable.add3,null));
        lst_background.add(getResources().getDrawable(R.drawable.add4,null));
        lst_background.add(getResources().getDrawable(R.drawable.add5,null));
        lst_background.add(getResources().getDrawable(R.drawable.add6,null));
        // Phát nhạc khi chơi
        bckgndMusic = SoundMgr.SoundMedia(this, R.raw.summertime);
        player=MediaPlayer.create(this, R.raw.tweet);
        history = new HashSet<>();
        randomTuple(1);
        CountDown();
    }
    int myRandom (int a, int b) {
        Random rd = new Random();
        int result = rd.nextInt(b-a) + a;
        return result;
    }
    void randomTuple (int round) {
    // Hàm này sẽ được gọi mỗi khi cần sinh bộ mới, dựa vào ROUND
        int numberA,numberB;
        Random rd= new Random();
        background.setImageDrawable(lst_background.get(rd.nextInt(lst_background.size())));
//        Sinh 2 số ngẫu nhiên A và B không trùng lặp
        int[] randTuple = randomask();
        numberA = randTuple[0];
        numberB = randTuple[1];
        history.add(new Pair (numberA, numberB)); // Chèn vào tập hợp để kiểm tra trong tương lai
        // Doi so thanh chuoi
        btn_A.setText(numberA+ "");
        btn_B.setText(numberB+ "");
        Button[] choice = new Button[4];
        choice[0]=btnChoice_A;
        choice[1]=btnChoice_B;
        choice[2]=btnChoice_C;
        choice[3]=btnChoice_D;
        int result=numberA+numberB;
        int index=myRandom(0,4);
        // Vị trí chứa kết quả
        choice[index].setText(result+"");
        choice[index].setTag("this");
        int number;
        // Tao mang xu ly khong trung
        int arrAd[]=random(3,result);
        int idx_limit=0;
        for(int i=0;i<=3;i++) {
            if (i != index) {
                number=arrAd[idx_limit++];
                choice[i].setText(number + "");
                choice[i].setTag("not this");
            }
            choice[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onAnswer(view);
                }
            });
        }
    }
    // Ham random 3 phuong an nhieu
    private int[] random(int limit ,int result){
        int n;
        int index=0;
        boolean check;
        // tao ra mang thu nhat
        int arrA[]=new int[limit];
        do{
            check=true;
            n=myRandom((int)(Math.pow(round-1,2)*1/5) ,(int)Math.max((int)(Math.pow(round-1,2) * 2/5), 5));
            for(int i=0;i<index;i++){
                if(arrA[i]==n){
                    check=false;
                    break;
                }
            }
            if(n==result){
                check=false;
            }
            if(check==true){
                arrA[index]=n;
                index++;
            }
        }while(index<limit);
        return arrA;
    }
    // kiem tra trung cau hoi
    private int[] randomask(){
        int arrask[]=new int[2];
        int numberA, numberB;
        do{
            numberA= myRandom((int)(Math.pow(round-1,2)*1/5) , (int)Math.max((int)(Math.pow(round-1,2) * 2/5), 5));
            numberB= myRandom((int)(Math.pow(round-1,2)*1/5) , (int)Math.max((int)(Math.pow(round-1,2) * 2/5),5));
            if (!history.contains(new Pair(numberA, numberB))) {
                break;
            }
        }while(true);
        arrask[0]=numberA;
        arrask[1]=numberB;
        return arrask;
    }
    // Ham neu tra loi dung thi round+1 va score+10 nguoc lai thoat ra man hinh bao diem
    void onAnswer(View view){
        if(view.getTag().equals("this")){
            player.start();
            round+=1;
            score+=10;
            countDownTimer.cancel();
            CountDown();
            randomTuple(round);
            ttScore.setText("SCORE: " +score+"");
        }
        else{
            if(view.getTag().equals("not this")){
                player.start();
                 EndTime();
            }
        }
    }
    // Thoat ra man hinh bao diem
    public void EndTime(){
        Intent intent = new Intent(addition_caculator.this, score_screenadd.class);
        intent.putExtra("score",score);
        bckgndMusic.stop();
        startActivity(intent);
        finish();
        // Hủy thời gian chơi
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
        finish();
    }
    // Set thanh thoi gian
    public void CountDown(){
        progressBar.setMax(10000);
        countDownTimer=  new CountDownTimer(10000, 33) {
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress((int) millisUntilFinished);
            }
            public void onFinish() {
                progressBar.setProgress(0);
                EndTime();
            }
        };
        countDownTimer.start();
    }
}
