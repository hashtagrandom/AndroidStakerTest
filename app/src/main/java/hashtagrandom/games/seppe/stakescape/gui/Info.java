package hashtagrandom.games.seppe.stakescape.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import hashtagrandom.games.seppe.stakescape.R;

/**
 * Created by seppe on 17/11/2016.
 */

public class Info extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Info");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        AdView adView = (AdView) findViewById(hashtagrandom.games.seppe.stakescape.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);



    }

}