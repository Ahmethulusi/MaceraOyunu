public class Potions {
    private int id;
    private int price;
    private String name;// Mesela öfke artışı hasar arttırıyor

    private String feature;

    private int magnitude;
    private int count;

    public Potions(int id, int price, String name, String feature, int magnitude, int count) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.feature = feature;
        this.magnitude = magnitude;
        this.count = count;
    }

    public static Potions[] potion() {
        Potions[] potionList = new Potions[3];
        potionList[0] = new Potions(1,30,"Can iksiri","Can artışı",50,0);
        potionList[1] = new Potions(2,20,"Öfke iksiri","Hasar artışı",10,0);
        potionList[2] = new Potions(3,20,"Kalkan iksiri","Etki eden hasarda azalma",10,0);
        return potionList;
    }

    public static Potions getPotionObjById(int id){
        for(Potions potion:Potions.potion()){
            if(potion.getId()==id){
                return potion;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public int getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(int magnitude) {
        this.magnitude = magnitude;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
