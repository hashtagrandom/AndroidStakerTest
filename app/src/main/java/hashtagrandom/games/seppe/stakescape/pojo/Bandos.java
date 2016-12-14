package hashtagrandom.games.seppe.stakescape.pojo;

import hashtagrandom.games.seppe.stakescape.items.AdamantiteOre;
import hashtagrandom.games.seppe.stakescape.items.BandosBoots;
import hashtagrandom.games.seppe.stakescape.items.BandosChestplate;
import hashtagrandom.games.seppe.stakescape.items.BandosHilt;
import hashtagrandom.games.seppe.stakescape.items.BandosPet;
import hashtagrandom.games.seppe.stakescape.items.BandosTassets;
import hashtagrandom.games.seppe.stakescape.items.BigBones;
import hashtagrandom.games.seppe.stakescape.items.ClueScroll;
import hashtagrandom.games.seppe.stakescape.items.Coal;
import hashtagrandom.games.seppe.stakescape.items.CurvedBone;
import hashtagrandom.games.seppe.stakescape.items.GodSwordShard2;
import hashtagrandom.games.seppe.stakescape.items.GodSwordShard3;
import hashtagrandom.games.seppe.stakescape.items.GodswordShard1;
import hashtagrandom.games.seppe.stakescape.items.Item;
import hashtagrandom.games.seppe.stakescape.items.LongBone;
import hashtagrandom.games.seppe.stakescape.items.MagicLogs;
import hashtagrandom.games.seppe.stakescape.items.NatureRune;
import hashtagrandom.games.seppe.stakescape.items.Rune2hSword;
import hashtagrandom.games.seppe.stakescape.items.RuneLongSword;
import hashtagrandom.games.seppe.stakescape.items.RunePlatebody;
import hashtagrandom.games.seppe.stakescape.items.RuneSword;
import hashtagrandom.games.seppe.stakescape.items.SnapdragonSeed;
import hashtagrandom.games.seppe.stakescape.util.Util;

import java.util.ArrayList;

import hashtagrandom.games.seppe.stakescape.items.Rarity;


/**
 * Created by u0098595 on 16/06/2016.
 */
public class Bandos extends Boss{
    public static final int MAX_HEALTH = 255;
    public static final int MIN_HEALTH = 0;
    private int hp;
    private String hitText;
    private boolean isdead;
    private ArrayList<Item> loot;

    public Bandos(){
        this.hp = MAX_HEALTH;
        this.isdead = false;
        loot = new ArrayList<>();
        loot.add(new BandosHilt(Rarity.Rare));
        loot.add(new BandosBoots(Rarity.Rare));
        loot.add(new BandosChestplate(Rarity.Rare));
        loot.add(new BandosTassets(Rarity.Rare));
        loot.add(new BandosPet(Rarity.VeryRare));
        
        loot.add(new GodswordShard1(Rarity.Rare));
        loot.add(new GodSwordShard2(Rarity.Rare));
        loot.add(new GodSwordShard3(Rarity.Rare));
        
        loot.add(new RunePlatebody(Rarity.Common));
        loot.add(new Rune2hSword(Rarity.Common));
        loot.add(new RuneLongSword(Rarity.Common));
        loot.add(new RuneSword(Rarity.Rare));
        
        loot.add(new AdamantiteOre(Rarity.Common,20));
        loot.add(new Coal(Rarity.Common,20));
        loot.add(new MagicLogs(Rarity.UnCommon,20));
        loot.add(new NatureRune(Rarity.UnCommon, 60));
        
        loot.add(new SnapdragonSeed(Rarity.UnCommon));
        
        loot.add(new ClueScroll(Rarity.Rare));
        
        loot.add(new LongBone(Rarity.Rare));
        loot.add(new CurvedBone(Rarity.VeryRare));
        
        loot.add(new BigBones(Rarity.Always));
    }

    public int getHp() {
        return hp;
    }

    public double getHpProcent(){
        return (double)hp/(double)MAX_HEALTH;
    }

    public String getHitText() {
        return hitText;
    }

    public boolean isdead() {
        return isdead;
    }

    @Override
    public String AIAttack(User user) {
        if (!user.isDead()) {
            int random = Util.randInt(0, 100);
            if (random > 75) {
                int range = Util.randInt(0, 24);
                user.decreaseHp(range);
                hitText = "Range attack:- " + range;
            } else if (random <= 75 && random > 50) {
                int melee = Util.randInt(0, 33);
                user.decreaseHp(melee);
                hitText = "Melee attack:- " + melee;
            } else if (random <= 50 && random > 25) {
                int mage = Util.randInt(0, 37);
                user.decreaseHp(mage);
                hitText = "Mage attack:- " + mage;
            } else {
                int range = Util.randInt(0, 45);
                user.decreaseHp(range);
                hitText = "Range attack:- " + range;
            }
            return hitText;
        } else {
            return "player is alreasy dead!";
        }
    }

    @Override
    public void decreaseHp(int amount) {
        if(!isdead){
            hp = hp - amount;
            if(hp < 0){
                hp = 0;
                isdead = true;
            }
        }
    }

    public void resetStats(){
        hp = MAX_HEALTH;
        isdead = false;
    }

    public Item getLoot(){
        int itemroll = Util.randInt(0, 500);
        if(itemroll >= 1 && itemroll <= 175){
            return new MagicLogs();
        }else if(itemroll > 175 && itemroll <= 300){
            return new RunePlatebody();
        }else if(itemroll > 300 && itemroll <= 475){
            return new ClueScroll();
        }else if(itemroll == 493){
            return new BandosBoots();
        }else if(itemroll == 494){
            return new BandosHilt();
        }else if(itemroll == 495){
            return new BandosTassets();
        }else if(itemroll == 496){
            return new BandosChestplate();
        }else if(itemroll == 497){
            return new GodswordShard1();
        }
        int petroll = Util.randInt(0, 2000);
        if(petroll == 1000){
            return new BandosPet();
        }else{
            return new RunePlatebody();
        }
    }

    public ArrayList<Item> getLootTest(){
        int random = Util.randInt(0,1000);
        System.out.println("Roll: " + random);
        ArrayList<Item> loot = new ArrayList<>();
        for(Item item : this.loot){
            switch (item.getRarity()){
                case Always:
                    loot.add(item);
                    break;
                case Common:
                    if(random <= 650){
                        loot.add(item);
                    }
                    break;
                case UnCommon:
                    if(random > 650 && random <= 950){
                        loot.add(item);
                    }
                    break;
                case Rare:
                    if (random > 950 && random <= 990){
                        loot.add(item);
                    }
                    break;
                case VeryRare:
                    if(random > 990 && random <= 1000){
                        loot.add(item);
                    }
                    break;
            }
        }
        if(loot.size() == 0){
            return getLootTest();
        }
        else {
            ArrayList<Item> lootList = new ArrayList<>();
            ArrayList<Item> randomLootList = new ArrayList<>();
            for (Item i : loot){
                if(i.getRarity() == Rarity.Always){
                    lootList.add(i);
                } else {
                    randomLootList.add(i);
                }
            }
            int r = Util.randInt(0,(randomLootList.size()-1));
            lootList.add(randomLootList.get(r));
            return lootList;
        }
    }
}
