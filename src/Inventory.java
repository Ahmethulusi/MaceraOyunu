import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventory {
    private Weapon weapon;
    private Armors armor;
    private List<Awards> awardsList;
    private Awards awards;
    public Inventory() {
        this.weapon = new Weapon("Yumruk", 0, 1, 0, 10000000,0);
        this.armor = new Armors("Yok", 0, 0, 0, 0,0);
        this.awardsList=new ArrayList<Awards>();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armors getArmor() {
        return armor;
    }

    public void setArmor(Armors armor) {
        this.armor = armor;
    }

    public List<Awards> getAwardsList() {
        return awardsList;
    }

    public void addAward(Awards award) {
        awardsList.add(award);
    }

    // arraylistteki ödülleri kontrol et ve 0 ise direkt ekle,0'dan fazla ise o ödülün olduğu indeksi bul ve
    public void setCountList(int award_number,String name){
        for (Awards award : awardsList) {
            if(award!=null){
                if(award.getName().equals(name)){
                    award.setCount(award_number);
                }
            }
        }
    }

}
