package hashtagrandom.games.seppe.stakescape.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by seppe on 14/11/2016.
 */

public class Shop extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("General Store");
        super.onCreate(savedInstanceState);
        setContentView(hashtagrandom.games.seppe.stakescape.R.layout.activity_shop);

    }

}
