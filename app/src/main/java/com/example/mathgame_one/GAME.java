package com.example.mathgame_one;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Random;

public class GAME extends AppCompatActivity {

    TextView score , Rlife ,Rtime, Q;
    EditText ans ;
    Random random = new Random();
    int num1 ;
    int num2;
    int Tscore=0;

    Button ok,next;
    int Cans;
    int TLife =3;
    CountDownTimer timer;
    private static  final long START_TIMER =10000;
    private  static long TIME_LEFT =START_TIMER; // time in milisecond
    Boolean timer_running ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        score = findViewById(R.id.score);
        Rlife = findViewById(R.id.life);
        Rtime = findViewById(R.id.time);
        Q = findViewById(R.id.Qu);
        ok = findViewById(R.id.btnOK);
        next = findViewById(R.id.btnNEXT);
        ans = findViewById(R.id.ans);
        //continueGame();
       continueGame();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                pushtimer();


                int Uans = Integer.valueOf(ans.getText().toString());

                if(TLife <=0){
                    Intent intent = new Intent(GAME.this , Result.class);
                    intent.putExtra("Score",Tscore);
                    startActivity(intent);
                    finish();

                }
                if(Uans == Cans){
                   Q.setText("Congress !! Your ans is right");

                    Tscore = Tscore +10;
                    score.setText(""+Tscore);
                }
                else {

                    Q.setText("Sorry Try Again !");
                    TLife--;
                    Rlife.setText(""+TLife);

                }

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ans.setText("");
                resetTimer();
                if(TLife <=0){
                    Intent intent = new Intent(GAME.this , Result.class);
                    intent.putExtra("Score",Tscore);
                    startActivity(intent);
                    finish();

                }
                else {
                continueGame();
            }
            }
        });



    }

    public  void continueGame(){

        num1 = random.nextInt(100);
        num2 = random.nextInt(100);
        Cans = num1 + num2 ;
        Q.setText(num1 + "+" + num2);
        startTimer();




    }
    public void startTimer(){

        timer = new CountDownTimer(TIME_LEFT , 1000) {
            @Override
            public void onTick(long l) {
             TIME_LEFT =  l;
                updatetimer();  //--> this method will update over counter like 60 ,59,58....





            }

            @Override
            public void onFinish() {
                timer_running = false;
                pushtimer();
                resetTimer();
                updatetimer();
                TLife--;
                Rlife.setText(""+TLife);
                Q.setText("Sorry Time is Up");


            }
        }.start();

        timer_running = true;

    }


    public void  updatetimer(){

        int second = (int)(TIME_LEFT/1000)%60; // convert milsecond to second
        String LeftTime = String.format(Locale.getDefault(),"%02d",second);
        Rtime.setText(LeftTime);

    }
    public  void pushtimer(){

        timer.cancel();
        timer_running = false;

    }

    public  void  resetTimer(){

        TIME_LEFT =START_TIMER;
        updatetimer();
    }

}