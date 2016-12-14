package hashtagrandom.games.seppe.stakescape.gui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import hashtagrandom.games.seppe.stakescape.R;
import hashtagrandom.games.seppe.stakescape.pojo.Shark;
import hashtagrandom.games.seppe.stakescape.pojo.User;
import hashtagrandom.games.seppe.stakescape.util.Util;
import hashtagrandom.games.seppe.stakescape.util.XmlReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xmlpull.v1.XmlPullParserException;

import static hashtagrandom.games.seppe.stakescape.util.Util.sleepTime;

/**
 * Created by seppe on 14/11/2016.
 */


public class DuelArena extends AppCompatActivity {


    int adAantal = 0;
    int resetAantal = 0;
    boolean adShow;
    Boolean exitWant=false;
    User player = new User(99, 5, 100);
    User ai = new User(99, 5, 100);
    TextView userHp;
    TextView aiHp;
    TextView winLose;
    SoundPool sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
    int soundIds[] = new int[10];
    ImageButton whip;
    ImageButton ags;
    ImageButton dds;
    ImageButton dharok;
    ImageButton shark;
    TextView lblSpecPlayer;
    TextView lblSpecEnemy;
    TextView lblPlayerHit;
    TextView lblAiHit;
    ProgressBar prgSpecPlayer;
    ProgressBar prgSpecEnemy;
    Button start;

    TextView sharkAmount;
    TextView sharkAmountAi;
    Boolean boolStart = false;

    Boolean wachten = true;
    Boolean eatShark = false;

    int firstHp;
    int tweedeHp;
    int verschil;

    private boolean ateSharkPlayer = false;
    private boolean ateSharkAi = false;
    private boolean isStaking;

    InterstitialAd mInterstitialAd;
    private InterstitialAd interstitial;

    //xml domeinen
    private String xmlFilePath;
    public static String rootElement = "stakescape";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Duel Arena");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_duel_arena);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6203295545850837/9271328876");
        AdRequest request = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(request);

        lblSpecEnemy = (TextView) findViewById(R.id.lblSpecEnemy);
        lblSpecEnemy.setText(""+ai.getSpec());

        lblSpecPlayer = (TextView) findViewById(R.id.lblSpecPlayer);
        lblSpecPlayer.setText(""+player.getSpec());

        lblPlayerHit = (TextView)findViewById(R.id.tvPlayerHit);
        lblAiHit = (TextView)findViewById(R.id.tvAiHit);

        prgSpecPlayer = (ProgressBar) findViewById(R.id.prgSpecPlayer);
        prgSpecPlayer.setProgress(User.MAX_SPEC);
        prgSpecPlayer.setProgressTintList(ColorStateList.valueOf(Color.GREEN));

        prgSpecEnemy = (ProgressBar) findViewById(R.id.prgSpecEnemy);
        prgSpecEnemy.setProgress(ai.MAX_SPEC );
        prgSpecEnemy.setProgressTintList(ColorStateList.valueOf(Color.RED));

        xmlFilePath = this.getFilesDir().getPath().toString() + "/userdata.xml";

        System.out.println(this.getFilesDir().getAbsolutePath().toString());

        //Kijkt of er al win/loss gegevens zijn en haalt deze uit de xml/het geheugen
        File input = new File(xmlFilePath);
        if(input.exists()){
            loadFromXml();
        }
        winLose = (TextView) findViewById(R.id.winLose);
        winLose.setText("W: " + player.getWin() + " L: " + player.getLoss());

        //kijken of we aan het staken zijn:
        Intent mIntent = getIntent();
        isStaking = mIntent.getBooleanExtra("isStake", false);

        //audiomanager
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundIds[0] = sp.load(this, R.raw.dds, 1);
        soundIds[1] = sp.load(this, R.raw.whip, 1);
        soundIds[2] = sp.load(this, R.raw.eating, 1);
        soundIds[3] = sp.load(this, R.raw.die, 1);
        soundIds[4] = sp.load(this, R.raw.dharok, 1);
        soundIds[5] = sp.load(this, R.raw.dharokk, 1);
        soundIds[6] = sp.load(this, R.raw.damagereceived, 1);
        soundIds[7] = sp.load(this, R.raw.ags, 1);

        sharkAmount = (TextView) findViewById(R.id.sharkAmount);
        sharkAmountAi = (TextView) findViewById(R.id.aiSharkAmount);
        userHp = (TextView) findViewById(R.id.userHp);
        userHp.setText("HP: " + player.getHp());
        aiHp = (TextView) findViewById(R.id.aiHP);
        aiHp.setText("Enemy: " + ai.getHp());

        //button declaratie
        start = (Button) findViewById(R.id.buttonStart);


        //imagebutton declaratie
        whip = (ImageButton) findViewById(R.id.whipButton);
        ags = (ImageButton) findViewById(R.id.agsButton);
        dds = (ImageButton) findViewById(R.id.ddsButton);
        dharok = (ImageButton) findViewById(R.id.dharokButton);
        shark = (ImageButton) findViewById(R.id.sharbButton);


        //onclicklisteners
        whip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!player.isDead() && !ai.isDead() && wachten) {
                    firstHp = player.getHp();
                    wachten = false;
                    System.out.println("EERSTE");
                    player.doWhip(ai);


                    if (ai.isDead()) {
                        sleepTime(1000);
                        aiHp.setText("You Won!");
                        AIAttack();
                        whipSound();
                    }else {
                       // sleepTime(1000);

                        System.out.println("TWEEDE");
                        AIAttack();
                        whipSound();

                    }

                }
            }
        });

        dds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!player.isDead() && !ai.isDead() && !(player.getSpec() < player.getDds().getDecreaseSpec()) && wachten) {
                    firstHp = player.getHp();
                    wachten = false;

                    player.doDDS(ai);

                    //   lblHitPlayer.setText(player.getHitString());

                    if (ai.isDead()) {
                        sleepTime(1000);
                        aiHp.setText("You Won!");
                        AIAttack();
                        ddsSound();
                    }else{
                      //  sleepTime(1000);
                        System.out.println("TWEEDE");
                        AIAttack();
                        ddsSound();

                    }
                } else if (player.getSpec() < player.getDds().getDecreaseSpec()&& !player.isDead() && !ai.isDead()) {
                    Toast.makeText(getApplicationContext(), "Not enough special!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        ags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!player.isDead() && !ai.isDead() && !(player.getSpec() < player.getAgs().getDecreaseSpec()) && wachten) {
                    firstHp = player.getHp();
                    wachten = false;
                    player.doAGS(ai);
                    //   lblHitPlayer.setText(player.getHitString());

                    if (ai.isDead()) {
                        sleepTime(1000);
                        aiHp.setText("You Won!");
                        AIAttack();
                        agsSound();
                    }else{
                      //  sleepTime(1000);
                        System.out.println("TWEEDE");
                        AIAttack();
                       agsSound();

                    }
                } else if (player.getSpec() < player.getAgs().getDecreaseSpec() && !player.isDead() && !ai.isDead()) {
                    Toast.makeText(getApplicationContext(), "Not enough special!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        dharok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!player.isDead() && !ai.isDead() && wachten) {
                    firstHp = player.getHp();
                    wachten = false;

                    player.doDharok(ai);

                    //       lblHitPlayer.setText(player.getHitString());

                    if (ai.isDead()) {
                        sleepTime(1000);
                        aiHp.setText("You Won!");
                        AIAttack();
                        dharokSound();
                    }else{
                          //  sleepTime(1000);
                            System.out.println("TWEEDE");
                            AIAttack();
                            dharokSound();
                        }
                    }


            }
        });


        shark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!player.isDead() && player.getSharkAmount() != 0 && !ai.isDead() && player.getHp() != User.MAX_HEALTH && wachten) {
                    wachten = false;
                    player.eatShark();
                    ateSharkPlayer = true;
                    updateUserShark(player.getHp());

                    sleepTime(1000);
                    System.out.println("TWEEDE");
                    //AIAttackAfterShark();
                    AIAttack();
                    eatSound();

                    //     lblHitPlayer.setText("SHARK: +" + Integer.toString(player.getShark().getHealing()));
                    sharkAmount.setText(player.getSharkAmount() + "");
                    Toast.makeText(getApplicationContext(), "You ate a shark!", Toast.LENGTH_SHORT).show();
                    System.out.println("geen damage omdat gegeten");
                } else if (player.getSharkAmount() == 0) {
                    Toast.makeText(getApplicationContext(), "Not enough sharks!", Toast.LENGTH_SHORT).show();
                } else if(player.isDead() || ai.isDead()) {

                }else{
                    Toast.makeText(getApplicationContext(), "Already max hp!", Toast.LENGTH_SHORT).show();
                }

            }


        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                if (tvStakeAmount.getText().equals("")) {
                    lblCurrentAmount.setText("Invalid");
                }else{
                    int stake = Integer.parseInt(tvStakeAmount.getText());
                    if(stake <= 0 ){
                        lblCurrentAmount.setText("Invalid");
                    }else if (stake > player.getMoney()) {
                        lblCurrentAmount.setText("Invalid");
                    } else {
                        reset();
                        player.setStake(stake);
                        tvStakeAmount.setText("");
                        tvStakeAmount.setPromptText(player.moneyToString());
                        lblCurrentAmount.setText(player.getStake()+"");
                        lblCashStack.setText(player.moneyToString());
                    }
                }

                */
                if(adShow){
                    mInterstitialAd = null;
                    mInterstitialAd = new InterstitialAd(getApplicationContext());
                    mInterstitialAd.setAdUnitId("ca-app-pub-6203295545850837/9271328876");
                    AdRequest request = new AdRequest.Builder().build();
                    mInterstitialAd.loadAd(request);
                    adShow = false;
                }
                reset();
                start.setClickable(false);
                start.setAlpha(0f);
                prgSpecPlayer.setAlpha(1f);
                prgSpecEnemy.setAlpha(1f);
                lblSpecPlayer.setAlpha(1f);
                lblSpecEnemy.setAlpha(1f);
                boolStart = true;
                sharkAmount.setText(player.getSharkAmount() + "");
                sharkAmountAi.setText(ai.getSharkAmount() + "");

                Toast.makeText(getApplicationContext(), "Duel started!", Toast.LENGTH_SHORT).show();

            }
        });

        //Gui aanpassen als duel niet/wel begonnen is
        if (!boolStart) {
            disableWeapons();
        } else {
            enableWeapons();
        }

    }

    private void enableWeapons() {
        whip.setClickable(true);
        whip.setAlpha(1f);

        ags.setClickable(true);
        ags.setAlpha(1f);

        shark.setClickable(true);
        shark.setAlpha(1f);

        dds.setClickable(true);
        dds.setAlpha(1f);

        dharok.setClickable(true);
        dharok.setAlpha(1f);

        prgSpecPlayer.setAlpha(1f);
        prgSpecEnemy.setAlpha(1f);
        lblSpecPlayer.setAlpha(1f);
        lblSpecEnemy.setAlpha(1f);
    }

    private void whipSound() {
        sp.play(soundIds[1], 1, 1, 1, 0, 1.0f);
    }

    private void ddsSound() {
        sp.play(soundIds[0], 1, 1, 1, 0, 1.0f);
    }

    private void eatSound() {
        sp.play(soundIds[2], 1, 1, 1, 0, 1.0f);
    }

    private void dieSound() {
        sp.play(soundIds[3], 1, 1, 1, 0, 1.0f);
    }

    private void hitSound() {
        sp.play(soundIds[6], 1, 1, 1, 0, 1.0f);
    }

    private void agsSound() {
        sp.play(soundIds[7], 1, 1, 1, 0, 1.0f);
    }


    private void dharokSound() {
        int randInt = Util.randInt(0, 100);
        if (randInt < 50) {
            sp.play(soundIds[4], 1, 1, 1, 0, 1.0f);
        } else {
            sp.play(soundIds[5], 1, 1, 1, 0, 1.0f);
        }
    }


    private void disableWeapons() {
        whip.setClickable(false);
        whip.setAlpha(0f);

        ags.setClickable(false);
        ags.setAlpha(0f);

        shark.setClickable(false);
        shark.setAlpha(0f);

        dds.setClickable(false);
        dds.setAlpha(0f);

        dharok.setClickable(false);
        dharok.setAlpha(0f);

        prgSpecPlayer.setAlpha(0f);
        prgSpecEnemy.setAlpha(0f);
        lblSpecPlayer.setAlpha(0f);
        lblSpecEnemy.setAlpha(0f);
    }

    private void updateUser() {
        tweedeHp = player.getHp();
        Timer t = new Timer(true);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    resetHitScreens();
                    wachten = true;
                    userHp.setText("HP: " + player.getHp() + "");

                    prgSpecEnemy.setProgress(ai.getSpecProcent());
                    lblSpecEnemy.setText(ai.getSpec() + "");

                    lblPlayerHit.setText(ai.getHitString());
                    lblPlayerHit.setTextColor(Color.WHITE);
                    lblPlayerHit.setBackgroundColor(Color.RED);
                    hitSound();
                    }
                });
            }
        },1000);
    }

    private void updateUserShark(final int hp) {
        Timer t = new Timer(true);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //resetHitScreens();
                        wachten = true;
                        ateSharkPlayer = false;
                        Shark viske = new Shark();
                        userHp.setText("HP: " + hp);

                        lblSpecPlayer.setText(player.getSpec()+ "");
                        prgSpecPlayer.setProgress(player.getSpecProcent());

                        lblSpecEnemy.setText(ai.getSpec() + "");
                        prgSpecEnemy.setProgress(ai.getSpecProcent());

                        lblPlayerHit.setText("+"+viske.getHealing());
                        lblPlayerHit.setTextColor(Color.YELLOW);
                        lblPlayerHit.setBackgroundColor(Color.GREEN);
                        //aihit al weg doen
                        lblAiHit.setText("");
                    }
                });

            }
        },1000);
    }

    private void updateAiShark(final int hp) {
        Timer t = new Timer(true);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //resetHitScreens();
                        wachten = true;
                        ateSharkAi = false;
                        Shark viske = new Shark();
                        aiHp.setText("HP: " + hp);

                        lblAiHit.setText("+"+viske.getHealing());
                        lblAiHit.setTextColor(Color.YELLOW);
                        lblAiHit.setBackgroundColor(Color.GREEN);
                        //aihit al weg doen
                        lblPlayerHit.setText("");
                    }
                });

            }
        },1000);
    }


    private void updateAi(){
        Timer t = new Timer(true);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resetHitScreens();
                        aiHp.setText("Enemy: " + ai.getHp() + "");
                        //stuff that updates ui


                        prgSpecEnemy.setProgress(ai.getSpecProcent());
                        lblSpecEnemy.setText(ai.getSpec() + "");

                        lblSpecPlayer.setText(player.getSpec()+ "");
                        prgSpecPlayer.setProgress(player.getSpecProcent());

                        lblAiHit.setText(player.getHitString());
                        lblAiHit.setTextColor(Color.WHITE);
                        lblAiHit.setBackgroundColor(Color.RED);

                        if(firstHp <tweedeHp){
                            aiHp.setText("Enemy: " + ai.getHp() + "" );
                        }

                    }
                });

            }
        },1000);
    }

    private void updateUserDead(){
        Timer t = new Timer(true);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // player.setStake(0);
                        userHp.setText("HP: " + "you're dead!");
                        boolStart = false;
                        aiHp.setText("Enemy: " + ai.getHp() + "");
                        wachten = true;
                        player.verhoogLoss();
                        winLose.setText("W: " + player.getWin() + " L: " + player.getLoss());
                        // setLoseValue(stakesLose);
                        // prgHpPlayer.setProgress(0);
                        //  prgHpEnemy.setProgress(ai.getHpProcent());
                        //  lblCurrentAmount.setText("");
                        //   lblHitEnemy.setText(ai.getHitString());

                        lblSpecEnemy.setText(ai.getSpec() + "");
                        lblSpecPlayer.setText(player.getSpec()+ "");
                        prgSpecPlayer.setProgress(player.getSpecProcent());
                        prgSpecEnemy.setProgress(ai.getSpecProcent());
                        resetHitScreens();

                        start.setClickable(true);
                        start.setAlpha(1f);
                        //nu als we aan het staken zijn de stake resetten en een dialog venster geven
                        if(isStaking){
                            showWinLossDialog(false);
                        }
                        else{
                            showAdd();
                        }
                    }
                });
            }
        },1000);
    }

    private void updateAiDead(){
        Timer t = new Timer(true);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //    player.addStakeMoney();
                        //     lblCashStack.setText(player.moneyToString());
                        userHp.setText("HP: " + player.getHp() + "");
                        aiHp.setText("Enemy: dead!");
                        player.verhoogWin();
                        wachten = true;

                        winLose.setText("W: " + player.getWin() + " L: " + player.getLoss());
                        // setWinValue(stakesWon);
                        boolStart = false;
                        //  prgHpPlayer.setProgress(player.getHpProcent());
                        //   prgHpEnemy.setProgress(0);
                        //   lblCurrentAmount.setText("");
                        //   lblHitEnemy.setText(ai.getHitString());

                        lblSpecEnemy.setText(ai.getSpec() + "");
                        lblSpecPlayer.setText(player.getSpec()+ "");
                        prgSpecPlayer.setProgress(player.getSpecProcent());
                        prgSpecEnemy.setProgress(ai.getSpecProcent());
                        resetHitScreens();


                        start.setClickable(true);
                        start.setAlpha(1f);

                        //nu als we aan het staken zijn de stake resetten en een dialog venster geven
                        if(isStaking){
                            showWinLossDialog(true);
                        }
                        else{
                            showAdd();
                        }
                    }
                });
            }
        },1000);
    }

    public void AIAttackAfterShark(){
        if (!ai.isDead()) {

            if (ai.getHp() >= 15) { // dharok check
                int A = Util.randInt(0, 10);
                if (A <= 8 && ai.getSpec() >= 50) {
                    ai.doAGS(player);


                } else if (ai.getSpec() >= 25) {
                    ai.doDDS(player);

                } else {
                    int a = Util.randInt(0, 10);
                    if (ai.getHp() < 20) {
                        ai.eatShark();
                        sharkAmountAi.setText(ai.getSharkAmount() + "");
                    } else if (a >= 5) {
                        ai.doDharok(player);

                    } else {
                        ai.doWhip(player);

                    }
                }
            } else {
                ai.doDharok(player);

            }
        }
        lblSpecEnemy.setText(ai.getSpec() + "");
        lblSpecPlayer.setText(player.getSpec()+ "");
        prgSpecPlayer.setProgress(player.getSpecProcent());
        prgSpecEnemy.setProgress(ai.getSpecProcent());
    }



    public void AIAttack() {
         if (!ai.isDead() && !player.isDead()) {
                if (ai.getHp() >= 15) { // dharok check
                    int A = Util.randInt(0, 10);
                    if (A <= 8 && ai.getSpec() >= 50) {
                        ai.doAGS(player);


                    } else if (ai.getSpec() >= 25) {
                        ai.doDDS(player);

                    } else {
                        int a = Util.randInt(0, 10);
                        if (ai.getHp() < 20) {
                            ateSharkAi = true;
                            ai.eatShark();
                            sharkAmountAi.setText(ai.getSharkAmount() + "");
                        } else if (a >= 5) {
                            ai.doDharok(player);

                        } else {
                            ai.doWhip(player);

                        }
                    }
                } else {
                    ai.doDharok(player);

                }
        }

        //na de beurt van de ai de stakewinst of verlies toevoegen aan de user
        //TODO: eventueel hier al een reset van alle gegevens doen

        //efkes op elkaar hitten daarna pas kijken of we dood zijn.
        if(ateSharkPlayer){
            sleepTime(1000);
            updateUser();
        }else if(ateSharkAi){
            updateAi();
            sleepTime(1000);
            updateAiShark(ai.getHp());
            eatSound();

        } else{
            updateAi();
            sleepTime(1000);
            if(!ai.isDead()){
                updateUser();
            }else{
                updateAiDead();
            }

        }

        if (player.isDead()) {
            sleepTime(500);
            updateUserDead();
            dieSound();
        }
    }


    private void reset() {
        //  prgHpPlayer.getStyleClass().add("red-bar");
        //   prgHpEnemy.getStyleClass().add("red-bar");
        //   prgSpecPlayer.getStyleClass().add("green-bar");
        //    prgSpecEnemy.getStyleClass().add("green-bar");
        boolStart = true;
        player.resetStats(5);
        ai.resetStats(5);
        userHp.setText("HP: " + player.getHp() + "");

        start.setText("Restart");
        start.setClickable(true);
        start.setAlpha(1f);


        whip.setClickable(true);
        whip.setAlpha(1f);

        ags.setClickable(true);
        ags.setAlpha(1f);

        shark.setClickable(true);
        shark.setAlpha(1f);

        dds.setClickable(true);
        dds.setAlpha(1f);

        dharok.setClickable(true);
        dharok.setAlpha(1f);

        sharkAmount.setText("");
        sharkAmountAi.setText("");
        aiHp.setText("Enemy: " + ai.getHp() + "");
        // lblSharkEnemy.setText(ai.getSharkAmount()+"");
        lblSpecEnemy.setText(ai.getSpec()+"");
        lblSpecPlayer.setText(player.getSpec()+"");

        //    lblHitPlayer.setText("");
        //    lblHitEnemy.setText("");
            prgSpecEnemy.setProgress(ai.getSpecProcent());
           prgSpecPlayer.setProgress(player.getSpecProcent());
        //     prgHpEnemy.setProgress(ai.getHpProcent());
        //    prgHpPlayer.setProgress(player.getHpProcent());
        //    lblCashStack.setText(player.moneyToString());
        //    tvStakeAmount.setPromptText(player.moneyToString());
    }

    public void resetHitScreens(){
        lblPlayerHit.setText("");
        lblAiHit.setText("");
    }

    public void showAdd(){
        if(mInterstitialAd.isLoaded()) {


            if ((resetAantal == 0 || resetAantal == 3 || resetAantal == 5 ||resetAantal == 7) && adAantal < 5) {
                System.out.println("ad");
                mInterstitialAd.show();
                adAantal += 1;
                adShow = true;
            }else{
                System.out.println("Geen ad");
            }
        }
        resetAantal += 1;
    }

    //saves the data to the xml file
    public void saveToXml(){
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement(rootElement);

        //hier de elementen in de XML toevoegen
        player.addToDOM(root);

        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer;

        try{
            writer = new XMLWriter( new FileOutputStream(xmlFilePath), format);
            writer.write(doc);
        }catch (UnsupportedEncodingException e){
            System.out.println("problemen met encoderen " + e);
        }catch (FileNotFoundException e){
            System.out.println("bestand kan niet aangemaakt worden " + e);
        } catch (IOException e){
            System.out.println("Problemen met I/O " +e);
        }
    }

    public void loadFromXml(){
        File input = new File(xmlFilePath);
        XmlReader reader = new XmlReader();
        InputStream is = null;
        List<User> output = new ArrayList<>();

        try {
            is = new FileInputStream(xmlFilePath);
            output = reader.parse(is);
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        if(output.size()>0){
            player = output.get(0);
            System.out.println("data uit db stake moniesss" + player.getStake() + "---------------------------------------------------------" + output);
        }

    }

    public void showWinLossDialog(boolean hasWon){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.stake_popup);
        dialog.setTitle("Stake result");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        TextView tvWinLossTitle = (TextView)dialog.findViewById(R.id.tvWinLoss);
        Button buttonRestart = (Button)dialog.findViewById(R.id.dialogButtonRestart);

        if(hasWon){
            tvWinLossTitle.setText("You are victorious!");
            if(player.getStake() > 1){
                buttonRestart.setText("Claim " + player.getStake() + " coins");
            }else if(player.getStake() == 1){
                buttonRestart.setText("Claim " + player.getStake() + " coin");
            }

            player.addStakeMoney();
            saveToXml();
        }
        else{
            if(player.getStake() > 1) {
                tvWinLossTitle.setText("You lose the duel and " + player.getStake() + " coins");
            }else if(player.getStake() == 1){
                tvWinLossTitle.setText("You lose the duel and " + player.getStake() + " coin");
                }
            buttonRestart.setText("Exit the arena");
            saveToXml();
        }

        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DuelArena.this, Stake.class));
                dialog.dismiss();
                finish();
                System.out.println("ad");
                showAdd();
            }
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        if (boolStart) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Exiting duel")
                    .setMessage("Are you sure you want to leave the duel? (You will lose)")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            exitWant = true;
                            player.verhoogLoss();
                            //setWinValue(player.getWin());
                            //setLoseValue(player.getLoss());

                            saveToXml();

                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        } else {
            saveToXml();
            finish();
        }
    }
    @Override
    public void onStop(){
        super.onStop();
        if(boolStart && !exitWant) {
            player.verhoogLoss();
        }else if(!boolStart || exitWant){
            //op dit moment is het spel nog niet gereset

        }else{
            player.verhoogLoss();
        }

        saveToXml();

        finish();
    }

}
