import java.util.ArrayList;
import java.util.Scanner;


public class Player {
    private int xp; // İlerde karakterin xp si öldürülen düşman başına gelicek ve
    // belli bir xp'ye ulaşınca level atlayacak ve farklı bir bölgeye gidecek.
    private int damage;
    private int health;
    private int money;
    private String name;
    private String characterName;
    private Scanner input= new Scanner(System.in);
    private Inventory inventory;

    private boolean enemy_camp1_flag=true;// TERKEDİLMİŞ EV
    private boolean enemy_camp2_flag=true;// YÜKSELEN DUMAN
    private boolean enemy_camp3_flag=true;// DAĞ ETEĞİ
    private boolean enemy_camp4_flag=true; // DAĞ İÇİ
    private boolean enemy_camp5_flag=true; // DAĞ ZİRVESİ
    private boolean enemy_camp6_flag=true; // SINIR1
    private boolean side_task1 =true;
    private boolean side_task2 = true;
    private boolean side_task3 = true;

    private boolean cave_flag=true;  // MAĞARA
    private boolean ahead_of_forest_flag=true;// BEAR


    // Cuha Ormanı -- > düşman kampı 1 , düşman kampı 2 , mağaradaki kurtlar , Ormanın derinliklerindeki ayı
    // Anemon dağı -- > Terkedilmiş kasaba(Dağ haydutu),düşman kampı 3,Maden yeti,zindan tutsakları,
    // İskele -- > düşman kampı 4
    //




    public Player(String name) {
        this.name = name;
        this.inventory=new Inventory();
    }



    public void makeInit(){
        initPlayer(new Omeron());
    }


    public void initPlayer(GameChar gameChar){
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharacterName(gameChar.getName());
    }


    public void printInfo(){
        System.out.println(
                "Hasar : "+(getTotalDamage()-1)+"\n"+ "Sağlık : " + this.getHealth()+
                "\nSilah : " + this.getInventory().getWeapon().getName()+
                        "\nZırh :" + this.getInventory().getArmor().getName()+
                "\nPara : "+this.getMoney()+" gold");

    }


// ##################  GETTER AND SETTER METHODS ###################
    public int getDamage() {
        return this.getInventory().getWeapon().getDamage()+this.damage;
    }
    public int getTotalDamage(){
        return this.getInventory().getWeapon().getDamage()+getDamage();
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        if(this.money<0){
            this.money = money;
        }
        else{
            this.money=money;
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCharacterName() {
        return characterName;
    }
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
    public Inventory getInventory() {
        return inventory;
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public Weapon getWeapon(){
        return this.getInventory().getWeapon();
    }
    public int getXp() {
        return xp;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }
    public boolean isEnemy_camp1_flag() {
        return enemy_camp1_flag;
    }
    public void setEnemy_camp1_flag(boolean enemy_camp1_flag) {
        this.enemy_camp1_flag = enemy_camp1_flag;
    }
    public boolean isEnemy_camp2_flag() {
        return enemy_camp2_flag;
    }
    public void setEnemy_camp2_flag(boolean enemy_camp2_flag) {
        this.enemy_camp2_flag = enemy_camp2_flag;
    }
    public boolean isEnemy_camp3_flag() {
        return enemy_camp3_flag;
    }
    public void setEnemy_camp3_flag(boolean enemy_camp3_flag) {
        this.enemy_camp3_flag = enemy_camp3_flag;
    }
    public boolean isCave_flag() {
        return cave_flag;
    }
    public void setCave_flag(boolean cave_flag) {
        this.cave_flag = cave_flag;
    }
    public boolean isAhead_of_forest_flag() {
        return ahead_of_forest_flag;
    }
    public void setAhead_of_forest_flag(boolean ahead_of_forest_flag) {
        this.ahead_of_forest_flag = ahead_of_forest_flag;
    }
    public boolean isEnemy_camp4_flag() {
        return enemy_camp4_flag;
    }
    public void setEnemy_camp4_flag(boolean enemy_camp4_flag) {
        this.enemy_camp4_flag = enemy_camp4_flag;
    }
    public boolean isEnemy_camp5_flag() {
        return enemy_camp5_flag;
    }
    public void setEnemy_camp5_flag(boolean enemy_camp5_flag) {
        this.enemy_camp5_flag = enemy_camp5_flag;
    }
    public boolean isEnemy_camp6_flag() {
        return enemy_camp6_flag;
    }
    public void setEnemy_camp6_flag(boolean enemy_camp6_flag) {
        this.enemy_camp6_flag = enemy_camp6_flag;
    }
    public boolean isSide_task1() {
        return side_task1;
    }
    public void setSide_task1(boolean side_task1) {
        this.side_task1 = side_task1;
    }
    public boolean isSide_task2() {
        return side_task2;
    }
    public void setSide_task2(boolean side_task2) {
        this.side_task2 = side_task2;
    }
    public boolean isSide_task3() {
        return side_task3;
    }
    public void setSide_task3(boolean side_task3) {
        this.side_task3 = side_task3;
    }
    // ###########################################################
}

