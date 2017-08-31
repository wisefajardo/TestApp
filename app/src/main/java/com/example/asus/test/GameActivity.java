package com.example.asus.test;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    Button deal;
    ImageView com_card, player_card;
    LinearLayout deal_layout;
    String com, player, playernumber="",comnumber="";
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().setTitle("Game");


        deal = (Button) findViewById(R.id.deal);
        com_card = (ImageView)findViewById(R.id.com_card);
        player_card = (ImageView)findViewById(R.id.player_card);
        deal_layout = (LinearLayout)findViewById(R.id.deal_layout);

        deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com_card.setClickable(true);
                player_card.setClickable(true);
                deal.setVisibility(View.GONE);
                deal_layout.setVisibility(View.VISIBLE);
                int imageResource = getResources().getIdentifier("@drawable/card1", null, getPackageName());
                Drawable res = getResources().getDrawable(imageResource);
                player_card.setImageDrawable(res);
                com_card.setImageDrawable(res);
                //comcard
                com_card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TypedArray imgs = getResources().obtainTypedArray(R.array.cards);
                        Random rand = new Random();
                        int rndInt = rand.nextInt(imgs.length());
                        int resID = imgs.getResourceId(rndInt, 0);
                        com_card.setImageResource(resID);
                        com = getResources().getResourceName(resID);
                        for (int i = 0; i < com.length(); i++) {
                            char comdigit = com.charAt(i);
                            if (Character.isDigit(comdigit)) {
                                comnumber = comnumber + comdigit;
                            }
                        }
                        com_card.setClickable(false);
                        flag++;
                        flags();

                    }
                });
                //playercard
                player_card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TypedArray imgs = getResources().obtainTypedArray(R.array.cards);
                        Random rand = new Random();
                        int rndInt = rand.nextInt(imgs.length());
                        int resID = imgs.getResourceId(rndInt, 0);
                        player_card.setImageResource(resID);
                        player = getResources().getResourceName(resID);
                        for (int i = 0; i < player.length(); i++) {
                            char comdigit = player.charAt(i);
                            if (Character.isDigit(comdigit)) {
                                playernumber = playernumber + comdigit;
                            }
                        }
                        player_card.setClickable(false);
                        flag++;
                        flags();

                    }

                });
            }
        });
    }
    public void flags(){
        if(flag >= 2){
            flag = 0;
            deal.setVisibility(View.VISIBLE);
            if(Integer.valueOf(playernumber) > Integer.valueOf(comnumber))
                Toast.makeText(GameActivity.this, "You Win!", Toast.LENGTH_LONG).show();
            if(Integer.valueOf(playernumber) < Integer.valueOf(comnumber))
                Toast.makeText(GameActivity.this, "Computer Wins!", Toast.LENGTH_LONG).show();
            if(Integer.valueOf(playernumber) == Integer.valueOf(comnumber))
                Toast.makeText(GameActivity.this, "Draw!", Toast.LENGTH_LONG).show();

            playernumber = "0";
            comnumber = "0";
        }
    }
}
