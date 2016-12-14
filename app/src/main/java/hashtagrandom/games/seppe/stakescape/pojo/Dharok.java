package hashtagrandom.games.seppe.stakescape.pojo;

import hashtagrandom.games.seppe.stakescape.util.Util;


/**
 * Created by u0098595 on 13/06/2016.
 */
public class Dharok extends Weapon{

    public Dharok(){}

    public int getValue(){
        return 5000000;
    }

    public int getHitDharok(int hp){
        if (hp <= 99 && hp > 80) {
            return Util.randInt(0, 20);
        } else if (hp <= 80 && hp > 60) {
            return Util.randInt(0, 25);
        } else if (hp <= 60 && hp > 45) {
            return Util.randInt(0, 30);
        } else if (hp <= 45 && hp > 35) {
            return Util.randInt(0, 35);
        } else if (hp <= 35 && hp > 10) {
            return Util.randInt(0, 40);
        } else if (hp <= 10 && hp > 5) {
            return Util.randInt(0, 45);
        } else if (hp <= 5 && hp > 3) {
            return Util.randInt(0, 50);
        } else if (hp <= 3 && hp > 1) {
            return Util.randInt(0, 55);
        } else {
            return Util.randInt(0, 60);
        }
    }

}
