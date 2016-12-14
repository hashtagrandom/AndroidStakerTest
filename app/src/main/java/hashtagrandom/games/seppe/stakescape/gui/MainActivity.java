package hashtagrandom.games.seppe.stakescape.gui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import hashtagrandom.games.seppe.stakescape.R;

public class MainActivity extends AppCompatActivity {

    Button buttonDuelArena;
    Button buttonShop;
    Button buttonInfo;
    Button buttonInventory;
    Button buttonStake;


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing StakeScape")
                .setMessage("Are you sure you want to close StakeScape?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("StakeScape");
        super.onCreate(savedInstanceState);
        setContentView(hashtagrandom.games.seppe.stakescape.R.layout.activity_main);

        AdView adView = (AdView) findViewById(hashtagrandom.games.seppe.stakescape.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        buttonDuelArena = (Button) findViewById(R.id.buttonDuelArena);
        buttonShop = (Button) findViewById(hashtagrandom.games.seppe.stakescape.R.id.buttonShop);
        buttonInfo = (Button) findViewById(R.id.buttonInfo);
        buttonInventory = (Button) findViewById(R.id.buttonInventory);
        buttonStake = (Button) findViewById(R.id.buttonStake);

        buttonDuelArena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DuelArena.class));
            }
        });
        buttonStake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Stake.class));
            }
        });
        buttonShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Shop.class));
            }
        });
        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Info.class));
            }
        });
        buttonInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Inventory.class));
            }
        });

    }

}