import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Weapon {
    private String name;
    private int id;
    private int damage;
    private int price;


    private int level;



    public Weapon(String name, int id, int damage, int price,int level) {
        this.name = name;
        this.id = id;
        this.damage = damage;
        this.price = price;
        this.level = level;
    }


    public static Weapon[] weapons(){
        Weapon[] weaponList = new Weapon[3];
        weaponList[0] =new Weapon("Tabanca",1,25,25,1);
        weaponList[1] =new Weapon("Avcı Tüfeği",2,30,60,1);
        weaponList[2] =new Weapon("Pompalı",3,50,100,1);
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


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}


