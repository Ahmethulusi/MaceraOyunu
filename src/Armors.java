public class Armors {
    // bu zırhların id si ismi hasarı ve parası olacak private olacaklar ve
    // birkaç farklı zırh için bir dizi oluşturacaz dayanıklılığı ve bloklama sayısı olacak

    private int id;
    private String name;
    private int durability;
    private int blocking_count;
    private int price;

    private int level;


    public Armors( String name,int id, int durability, int price,int blocking_count,int level) {
        this.id = id;
        this.name = name;
        this.durability = durability;
        this.blocking_count = blocking_count;
        this.price = price;
        this.level = level;
    }
    public static Armors getArmObjById(int id){

        for(Armors a:Armors.arms()){
            if(a.getId()== id){
                return a;
            }
        }
        return null;
    }

    public static Armors[] arms(){
        Armors[] arms = new Armors[3];
        arms[0] = new Armors("Hafif Zırh",1,4,15,2,1);
        arms[1] = new Armors("Elit Şövalye Zırhı",2,8,50,5,1);
        arms[2] = new Armors("Ağır Zırh",3,12,80,8,1);
        return arms;
    }

    public int getId() {
        return id;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
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


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBlocking_count() {
        return blocking_count;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setBlocking_count(int blocking_count) {
        if(blocking_count<0){
            this.blocking_count=0;
        }
        else{
            this.blocking_count = blocking_count;
        }
    }
}
