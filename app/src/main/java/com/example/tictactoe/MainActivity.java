package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
        int[] gameState = {2,2,2,2,2,2,2,2,2};
        int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        int activePlayer = 0;
        boolean gameActive = true;
        public void dropIn(View view){
            ImageView counter = (ImageView) view;
            int tappedCounter = Integer.parseInt(counter.getTag().toString());
            if(gameState[tappedCounter]==2 && gameActive) {
                gameState[tappedCounter] = activePlayer;
                counter.setTranslationY(-1500);
                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.yellow);
                    activePlayer = 1;
                } else {
                    counter.setImageResource(R.drawable.red);
                    activePlayer = 0;
                }
                counter.animate().translationYBy(1500).setDuration(300);
                for (int[] winningPosition : winningPositions) {
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                        gameActive =false;
                        String winner = "";
                        if (activePlayer == 1) {
                            winner = "Yellow";
                        } else {
                            winner = "Red";
                        }
                        Button restart = (Button)findViewById(R.id.restart);
                        TextView winnerT= (TextView)findViewById(R.id.winner);
                        winnerT.setText(winner +" has won !");
                        restart.setVisibility(View.VISIBLE);
                        winnerT.setVisibility(View.VISIBLE);
                    }
                }
            }
        }

        public void playAgain(View view){
            Button restart = (Button)findViewById(R.id.restart);
            TextView winnerT= (TextView)findViewById(R.id.winner);
            restart.setVisibility(View.INVISIBLE);
            winnerT.setVisibility(View.INVISIBLE);
            GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
            for(int i =0; i<gridLayout.getChildCount();i++){
                ImageView counter = (ImageView) gridLayout.getChildAt(i);
                counter.setImageDrawable(null);
            }
            for(int i=0; i<gameState.length;i++) {
                gameState[i] = 2;
            }
            activePlayer = 0;
            gameActive = true;

    }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
}