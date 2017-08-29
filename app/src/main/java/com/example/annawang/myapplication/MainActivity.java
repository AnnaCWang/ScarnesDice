package com.example.annawang.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.annawang.myapplication.R;

public class MainActivity extends AppCompatActivity {

    //UI elements
    Button holdButton;
    ImageView diceImage;

    int currentRoundScore;
    TextView javaRoundScore;

    int p1TotalScore;
    int p2TotalScore;


    TextView javaP1Score;
    TextView javaP2Score;


    ScarnesDice game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceImage = (ImageView) findViewById(R.id.diceImage);
        javaP1Score = (TextView) findViewById(R.id.player_1_score);
        javaP2Score = (TextView) findViewById(R.id.player_2_score);
        javaRoundScore = (TextView) findViewById(R.id.current_score);

        game = new ScarnesDice();
        p1TotalScore = 0;
        p2TotalScore = 0;
        currentRoundScore = 0;

    }
    public void roll(View view) {
        int rolledNumber = (int) (Math.random() * 6) + 1;
        currentRoundScore += rolledNumber;
        javaRoundScore.setText("" + currentRoundScore);
        switch (rolledNumber)
        {
            case 1: diceImage.setImageResource(R.drawable.dice1);
                currentRoundScore = 0;
                javaRoundScore.setText("" + currentRoundScore);
                    break;
            case 2: diceImage.setImageResource(R.drawable.dice2);
                break;
            case 3: diceImage.setImageResource(R.drawable.dice3);
                break;
            case 4: diceImage.setImageResource(R.drawable.dice4);
                break;
            case 5: diceImage.setImageResource(R.drawable.dice5);
                break;
            case 6: diceImage.setImageResource(R.drawable.dice6);
                break;
        }
    }

    public void hold(View view) //any function written from button pressed, view passed in is hold button
    {
        p1TotalScore += currentRoundScore;
        javaP1Score.setText("Player 1: " + p1TotalScore);
        currentRoundScore = 0;
        javaRoundScore.setText("" + currentRoundScore);
        if (p1TotalScore > 99)
        {
            p1TotalScore = 0;
            p2TotalScore = 0;
            currentRoundScore = 0;
            javaP1Score.setText("0");
            javaP2Score.setText("0");
            javaRoundScore.setText("0");
            Intent intent = new Intent(getApplicationContext(), WinActivity.class);
            startActivity(intent);
        }
        p2Turn();

    }

    public void p2Turn(){
      //  int rollCount = 0;
        int p2RoundTotal = 0;
        int randomNum;

        for (int roll = 0; roll < 3; roll++)
        {
            randomNum = (int) (Math.random() * 6) + 1;
            p2RoundTotal += randomNum;
            if (randomNum ==1)
            {
                p2RoundTotal = 0;
                Toast.makeText(getApplicationContext(), "Player 2 rolled a 1!", Toast.LENGTH_SHORT).show();
                break;
            }

        }

        p2TotalScore += p2RoundTotal;

        javaP2Score.setText("" + p2TotalScore);
        if (p2TotalScore > 99)
        {
            p1TotalScore = 0;
            p2TotalScore = 0;
            currentRoundScore = 0;
            javaP1Score.setText("0");
            javaP2Score.setText("0");
            javaRoundScore.setText("0");
            Intent intent = new Intent(getApplicationContext(), WinActivity.class);
            startActivity(intent);
        }
        p2Turn();
    }
    public void reset (View view)
    {
        p1TotalScore = 0;
        p2TotalScore = 0;
        currentRoundScore = 0;
        updateUI();
    }

    public void updateUI()
    {
        javaP1Score.setText("Player 1 " + p1TotalScore);
        javaP2Score.setText("Player 2 " + p2TotalScore);
        javaRoundScore.setText("" + currentRoundScore);
    }

    public void showWinner(String winner)
    {
        Intent newIntent = new Intent (getApplicationContext(), WinActivity.class);
        newIntent.putExtra("WINNER", winner);
        startActivity(newIntent);
    }


}