import java.util.Random;

public class Obstacle{
    private int id;
    private String name;
    private int damage;
    private String str_Award;
    private int int_Award;

    private int health;


    public Obstacle(int id, String name, int damage,int health,String str_Award,int int_Award) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.str_Award = str_Award;
        this.int_Award = int_Award;
        this.health = health;
    }

    public int randomObstacleNumber(){
        Random random = new Random();
        int number = random.nextInt(1,4);
        return number;
    }

    public String getStr_Award() {
        return str_Award;
    }

    public void setStr_Award(String str_Award) {
        this.str_Award = str_Award;
    }

    public int getInt_Award() {
        return int_Award;
    }

    public void setInt_Award(int int_Award) {
        this.int_Award = int_Award;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(health<0){
            health=0;
        }
        this.health = health;
    }


}



