package hashtagrandom.games.seppe.stakescape.pojo;

import android.content.ClipData;

import org.dom4j.Element;
import org.dom4j.Node;

import hashtagrandom.games.seppe.stakescape.items.Item;
import hashtagrandom.games.seppe.stakescape.util.Util;
import hashtagrandom.games.seppe.stakescape.util.XMLParser;


import java.util.ArrayList;

/**
 * Created by seppe on 14/11/2016.
 */

public class User {


    public static final int MAX_HEALTH = 99;
    public static final int MIN_HEALTH = 0;
    public static final int MAX_SPEC = 100;
    public static final int MIN_SPEC = 0;

    public static final String userElement = "user";
    public static final String winElement = "win";
    public static final String lossElement = "loss";
    public static final String moneyElement = "money";
    public static final String stakeElement = "stake";

    public static final String HASH_SECRET = ":watermelon:";

    private XMLParser stakerXml;
    private int hp;
    private int spec;
    private int stake;
    private int money;
    private int sharkAmount;
    private Shark shark;
    private boolean isDead = false;
    private int win;
    private int loss;

    private Whip whip;
    private Dharok dharok;
    private Dds dds;
    private Ags ags;

    private String hitString;

    private ArrayList<Item> inventory;

    public User(){
        inventory = new ArrayList<>();
    };

    public User(int sharkAmount, int money, int win, int loss){
        resetStats(5);
        this.win = win;
        this.loss = loss;
        this.money = money;
        this.sharkAmount = sharkAmount;
        this.stake = 0;

        shark = new Shark();
        whip = new Whip();
        dharok = new Dharok();
        dds = new Dds();
        ags = new Ags();

        inventory = new ArrayList<>();

        stake = 0;
    }

    public User(int sharkAmount, int money, int stake, int win, int loss){
        resetStats(5);
        this.win = win;
        this.loss = loss;
        this.money = money;
        this.stake = stake;
        this.sharkAmount = sharkAmount;

        shark = new Shark();
        whip = new Whip();
        dharok = new Dharok();
        dds = new Dds();
        ags = new Ags();

        inventory = new ArrayList<>();

        stake = 0;
    }

    public User(int hp, int sharkAmount, int spec) {
        stakerXml = new XMLParser();

        this.hp = hp;
        this.sharkAmount = sharkAmount;
        this.spec = spec;

        this.win = 0;
        this.loss = 0;
        this.stake = 0;
      //  this.money = stakerXml.readUserFromXML(this).getMoney();

        shark = new Shark();
        whip = new Whip();
        dharok = new Dharok();
        dds = new Dds();
        ags = new Ags();

        inventory = new ArrayList<>();

        stake = 0;
    }

    public XMLParser getStakerXml(){
        return stakerXml;
    }

    public String getHitString() {
        return hitString;
    }

    public int getHp() {
        if (hp > MAX_HEALTH) {
            hp = MAX_HEALTH;
        }else if (hp < MIN_HEALTH) {
            hp = MIN_HEALTH;
        }
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }


    public int getSpec() {
        if (spec > MAX_SPEC) {
            spec = MAX_SPEC;
        } else if (spec < MIN_SPEC) {
            spec = MIN_SPEC;
        }
        return spec;
    }

    public void setSpec(int spec) {
        this.spec = spec;
    }

    public int getStake() {
        return stake;
    }

    public void setStake(int stake) {
        money = money - stake;
        this.stake = stake;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addMoney(int value) {
        this.money = this.money + value;
    }

    public void substractMoney(int value){
        this.money = this.money - value;
    }

    public Shark getShark() {
        return shark;
    }

    public void setShark(Shark shark) {
        this.shark = shark;
    }

    public int getSharkAmount() {
        return sharkAmount;
    }

    public void setSharkAmount(int sharkAmount) {
        this.sharkAmount = sharkAmount;
    }

    public Whip getWhip() {
        return whip;
    }

    public Dharok getDharok() {
        return dharok;
    }

    public Dds getDds() {
        return dds;
    }

    public Ags getAgs() {
        return ags;
    }

    public void addSpecFive() {
        this.spec += 5;
    }

    public void decreaseSpec(int decreaseSpec) {
        spec = spec - decreaseSpec;
    }

    public void setWin(int win){
        this.win = win;
    }

    public int getWin(){
        return win;
    }

    public void setLoss(int loss){
        this.loss = loss;
    }

    public int getLoss(){
        return loss;
    }

    public void verhoogWin(){
        this.win++;
    }

    public void verhoogLoss(){
        this.loss++;
    }

    public String decreaseHp(int damage) {
        if (!this.isDead()) {
            this.hp = this.hp - damage;
            return "Damage";
        } else {
            this.setHp(0);
            return "isDead";
        }
    }

    public boolean isDead() {

        if (this.hp <= User.MIN_HEALTH) {
            isDead = true;
        }

        return isDead;
    }

    public boolean eatShark() {
        boolean success;

        if (this.sharkAmount > 0 && !this.isDead && this.getHp() < MAX_HEALTH) {
            this.sharkAmount--;
            this.hp = this.hp + this.shark.getHealing();
            this.addSpecFive();
            success = true;
        } else {
            success = false;
        }

        return success;
    }

    /*
     * Attack target with a chosen weapon
     */
    public void doAttack(Weapon weapon, User target) {
        if (weapon instanceof Whip) {
            doWhip(target);
        } else if (weapon instanceof Dharok) {
            doDharok(target);
        } else if (weapon instanceof Dds) {
            doDDS(target);
        } else if (weapon instanceof Ags) {
            doAGS(target);
        }
    }

    /*
     * Attack target with a random weapon
     */
    public void doAttack(User target) {
        int random = Util.randInt(0, 4);
        switch (random) {
            case 1:
                this.doWhip(target);
                break;
            case 2:
                this.doDharok(target);
                break;
            case 3:
                this.doDDS(target);
                break;
            case 4:
                this.doAGS(target);
                break;
            default:
                break;
        }
    }

    public void doWhip(User target) {
        int damage = this.whip.getHit();
        target.decreaseHp(damage);

        this.addSpecFive();

        hitString = "-" + damage;
    }

    public void doWhip(User target, int time) {
        int damage = this.whip.getHit();
        target.decreaseHp(damage);

        this.addSpecFive();

        hitString = "Whip: -" + damage;
    }




    public void doWhip(Boss target, double multi) {
        int damage = (int) (this.whip.getHit()*multi);
        target.decreaseHp(damage);

        this.addSpecFive();

        hitString = "Whip: -" + damage;
    }

    public void doDharok(User target) {
        int damage = this.dharok.getHitDharok(hp);
        target.decreaseHp(damage);
        this.addSpecFive();

        hitString = "-" + damage;
    }

    public void doDharok(Boss target, double multi) {
        int damage = (int) (this.dharok.getHitDharok(hp)*multi);
        target.decreaseHp(damage);
        this.addSpecFive();

        hitString = "Dh: -" + damage;
    }

    public void doDDS(User target) {
        int damage1 = this.dds.getHit();
        int damage2 = this.dds.getHit2();
        target.decreaseHp(damage1);
        target.decreaseHp(damage2);
        this.addSpecFive();
        this.decreaseSpec(this.dds.getDecreaseSpec());

        this.hitString = "-" + damage1 + " -" + damage2;
    }

    public void doDDS(Boss target, double multi) {
        int damage1 = (int) (this.dds.getHit()*multi);
        int damage2 = (int) (this.dds.getHit2()*multi);
        target.decreaseHp(damage1);
        target.decreaseHp(damage2);
        this.addSpecFive();
        this.decreaseSpec(this.dds.getDecreaseSpec());

        this.hitString = "DDS: -" + damage1 + " + -" + damage2;
    }

    public void doAGS(User target) {
        int damage = this.ags.getHit();
        target.decreaseHp(damage);

        this.addSpecFive();
        this.decreaseSpec(this.ags.getDecreaseSpec());

        this.hitString = "-" + damage;
    }

    public void doAGS(Boss target, double multi) {
        int damage = (int) (this.ags.getHit()*multi);
        target.decreaseHp(damage);

        this.addSpecFive();
        this.decreaseSpec(this.ags.getDecreaseSpec());

        this.hitString = "AGS: -" + damage;
    }

    public void resetStats(int sharkAmount) {
        this.hp = 99;
        this.spec = 100;
        this.sharkAmount = sharkAmount;
        isDead = false;
    }

    public void addStakeMoney() {
        this.money = this.money + 2 * this.stake;
    }

    public String moneyToString() {
        if (this.money > 9999 && this.money < 999999) {
            return this.money/1000 + "M";
        } else if (money > 999999) {
            return this.money/1000000 + "B";
        } else {
            return this.money + "K";
        }
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void addItem(Item item){
        System.out.println("item:" + item.getName() + " isStack " + item.isStackable());
        if(inventory.size()>0 && item.isStackable()){
            boolean isIn = false;
            System.out.println("item:" + item.getName() + " isStack " + item.isStackable());
            for(Item i : inventory){
                if(item.getName().equals(i.getName())){
                    i.addAmount(1);
                    isIn = true;
                }
            }
            if(!isIn){
                inventory.add(item);
            }
        }
        else{
            inventory.add(item);
        }
    }

    public void addItem(ArrayList<Item> items){
        if(inventory.size()>0){
            for(Item i : items){
                addItem(i);
            }
        }
        else{
            inventory.addAll(items);
        }
    }

    public void deleteItem(Item item){
        inventory.remove(item);
    }

    public void deleteItem(Item item, int n){
        if(inventory.size()>0 && item.isStackable()){
            boolean isIn = false;
            for(Item i : inventory){
                if(item.getName().equals(i.getName())){
                    i.decreaseAmount(n);
                    if(i.getAmount()<=0){
                        isIn = false;
                    }else{
                        isIn = true;
                    }

                }
            }
            if(!isIn){
                inventory.remove(item);
            }
        }
        else{
            inventory.remove(item);
        }
    }

    public double getHpProcent(){
        return (double)hp/(double)MAX_HEALTH;
    }

    public int getSpecProcent(){
        double specwaarde = (double)spec/(double)MAX_SPEC *100;
        return (int)specwaarde;
    }

    //checks if the item already exists in the inventory and increases the amount of the item.
    public boolean isItemInInventory(Item item){
        boolean isIn = false;
        for(Item i : inventory){
            if(item.getName().equals(i.getName())){
                i.addAmount(1);
                isIn = true;
            }
        }
        return isIn;
    }

    //add the object to an xml element
    public void addToDOM(Element element){
        Element platform = element.addElement(userElement);
        platform.addElement("win").setText(win+"");
        platform.addElement("loss").setText(loss+"");
        platform.addElement("money").setText(money+"");
        platform.addElement("stake").setText(stake+"");
    }

    public  User createFromDOM(Node n) {
        int win = Integer.valueOf(n.selectSingleNode("win").getText());
        int loss = Integer.valueOf(n.selectSingleNode("loss").getText());
        int money = Integer.valueOf(n.selectSingleNode("money").getText());
        int stake = Integer.valueOf(n.selectSingleNode("stake").getText());

        return new User(money, win, loss);
    }

}
