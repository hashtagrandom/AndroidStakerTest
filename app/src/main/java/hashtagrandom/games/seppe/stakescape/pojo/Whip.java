package hashtagrandom.games.seppe.stakescape.pojo;


import hashtagrandom.games.seppe.stakescape.util.Util;

/**
 * Created by u0098595 on 13/06/2016.
 */
public class Whip extends Weapon {

    public Whip(){}

    public int getValue(){
        return 5000000;
    }

    public int getHit(){
        if(Util.randInt(0 , 100) < 75){
            System.out.println("Whip kans 1-26");
            return Util.randInt(1,26);
        }else {
            System.out.println("Whip kans 0-26");
            return Util.randInt(0, 26);
        }
    }
}
