import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Weapon {
    private String name;
    private int id;
    private int damage;
    private int price;
    private int ammo_count;

    private int level;



    public Weapon(String name, int id, int damage, int price,int ammo_count,int level) {
        this.name = name;
        this.id = id;
        this.damage = damage;
        this.price = price;
        this.ammo_count = ammo_count;
        this.level = level;
    }


    public static Weapon[] weapons(){
        Weapon[] weaponList = new Weapon[3];
        weaponList[0] =new Weapon("Tabanca",1,25,25,10,1);
        weaponList[1] =new Weapon("Avcı Tüfeği",2,30,60,5,1);
        weaponList[2] =new Weapon("Pompalı",3,50,100,4,1);
        return weaponList;
  }


    public static Weapon getWeaponObjById(int id){

        for(Weapon w:Weapon.weapons()){
            if(w.getId()== id){
                return w;
            }
        }
        return null;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmmo_count() {
        return ammo_count;
    }

    public void setAmmo_count(int ammo_count) {
        this.ammo_count = ammo_count;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}


