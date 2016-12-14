package hashtagrandom.games.seppe.stakescape.gui;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import hashtagrandom.games.seppe.stakescape.R;
import hashtagrandom.games.seppe.stakescape.pojo.User;
import hashtagrandom.games.seppe.stakescape.util.XmlReader;


public class Stake extends Activity implements RewardedVideoAdListener{

    private EditText userInput;
    private TextView enemyInput;
    private Button startStake;
    private TextView tvPlayerMoney;

    private Button buttonWatchAdForGold;

    private User player;
    private String xmlFilePath;


    private RewardedVideoAd mAd;

    SoundPool sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
    int soundIds[] = new int[10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stake);
        userInput = (EditText)findViewById(R.id.etPlayerInput);
        enemyInput = (TextView)findViewById(R.id.tvEnemyStake);
        tvPlayerMoney = (TextView)findViewById(R.id.tvPlayerMoney);
        startStake = (Button)findViewById(R.id.buttonStartStake);
        buttonWatchAdForGold = (Button)findViewById(R.id.buttonWatchAdForGold);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundIds[0] = sp.load(this, R.raw.casinoslot, 1);



        // Use an activity context to get the rewarded video instance.
        mAd = MobileAds.getRewardedVideoAdInstance(this);
        mAd.setRewardedVideoAdListener(this);
        loadAdd();


        xmlFilePath = this.getFilesDir().getPath().toString() + "/userdata.xml";

        File input = new File(xmlFilePath);
        if(input.exists()) {
            loadFromXml();
        }else{
            player = new User(5,1000,0,0);
        }
        tvPlayerMoney.setText(player.getMoney()+"");
        userInput.setHint(player.getMoney()+"");

        startStake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userInput.getText().toString().equals("")){
                    System.out.println("niks ingevuld");
                    userInput.setError("Make a bet!");
                }
                else if(Integer.valueOf(userInput.getText().toString())>player.getMoney()){
                    System.out.println("U heeft te weinig monies");
                    userInput.setError("You don't have enough cash!");
                }
                else if(userInput.getText().toString().equals("0050")) {
                    player.addMoney(50000);
                    saveToXml();
                    tvPlayerMoney.setText(player.getMoney()+"");
                    userInput.setHint(player.getMoney()+"");
                    Toast.makeText(getApplicationContext(), "Test: received 50k coins!", Toast.LENGTH_SHORT).show();
                    casinoSound();
                }
                else{
                    player.setStake(Integer.valueOf(userInput.getText().toString()));
                    saveToXml();
                    Intent isStaking = new Intent(Stake.this, DuelArena.class);
                    isStaking.putExtra("isStake", true);
                    startActivity(isStaking);
                    System.out.println("Stake start!!!!!!!!!!!!!!!");
                    finish();
                }
            }
        });

        userInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enemyInput.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(player.getMoney() < 2000) {
            enableButtonAd();
            buttonWatchAdForGold.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mAd.isLoaded()) {
                        System.out.println("ad watched for money");
                        mAd.show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Wait some seconds", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            disableButtonAd();
        }
    }

    private void disableButtonAd(){
        buttonWatchAdForGold.setAlpha(0f);
        buttonWatchAdForGold.setClickable(false);
    }

    private void enableButtonAd(){
        buttonWatchAdForGold.setAlpha(1f);
        buttonWatchAdForGold.setClickable(true);
    }

    private void casinoSound() {
        sp.play(soundIds[0], 1, 1, 1, 0, 1.0f);
    }

    //saves the data to the xml file
    public void saveToXml(){
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement(DuelArena.rootElement);

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

    public void loadFromXml() {
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

        if (output.size() > 0) {
            player = output.get(0);
            System.out.println("data uit db " + player.getLoss() + "---------------------------------------------------------" + output);
        }
    }

    private void loadAdd(){
        if (!mAd.isLoaded()){
            mAd.loadAd("ca-app-pub-6203295545850837/1671533279", new AdRequest.Builder().build());
        }
    }




    @Override
    public void onRewardedVideoAdLoaded() {
        buttonWatchAdForGold.setEnabled(true);
    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        buttonWatchAdForGold.setEnabled(false);
        loadAdd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        player.addMoney(1000);
        saveToXml();
        tvPlayerMoney.setText(player.getMoney()+"");
        userInput.setHint(player.getMoney()+"");
        Toast.makeText(this, "You received 1000 coins!", Toast.LENGTH_SHORT).show();
        casinoSound();
        if(player.getMoney() < 2000){
            enableButtonAd();
        }else{
            disableButtonAd();
        }
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }
}
