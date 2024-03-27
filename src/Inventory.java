import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventory {
    private Weapon weapon;
    private Armors armor;
    private List<Awards> awardsList;
    private List<Potions> potionsList;
    private ArrayList<Awards> awards_section;// en fazla 10 item koyabilsin
    private ArrayList<Weapon> weapon_section;// en fazla 2 silah alınabilsin

    private ArrayList<Armors> armors_section;// en fazla 2 zırh alınabilsin


    private Awards awards;
    public Inventory() {
        this.weapon = new Weapon("Yumruk", 0, 1, 0, 0);
        this.armor = new Armors("Yok", 0, 0, 0, 0,0);
        this.awardsList=new ArrayList<Awards>();// ENVANTER İÇİN
        this.potionsList=new ArrayList<Potions>();
        this.armors_section=new ArrayList<Armors>();
        this.awards_section=new ArrayList<Awards>(); // SANDIK İÇİN
        this.weapon_section=new ArrayList<Weapon>();
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
    public void setCountAwardList(int award_number,String name){
        for (Awards award : awardsList) {
            if(award!=null){
                if(award.getName().equals(name)){
                    award.setCount(award_number);
                }
            }
        }
    }
    public void addPotion(Potions potion){
        potionsList.add(potion);
    }
    public void setCountPotionList(int potion_number,String name){
        for (Potions potion : potionsList) {
            if(potion!=null){
                if(potion.getName().equals(name)){
                    potion.setCount(potion_number);
                }
            }
        }
    }

    public List<Potions> getPotionsList() {
        return potionsList;
    }

    public ArrayList<Awards> getAwards_section() {
        return awards_section;
    }

    public void setCountListAwardSection(int award_number,String name){
        for (Awards award : awards_section) {
            if(award!=null){
                if(award.getName().equals(name)){
                    award.setCount(award_number);
                }
            }
        }
    }


    public ArrayList<Weapon> getWeapon_section() {
        return weapon_section;
    }


    public void setWeapon_section(ArrayList<Weapon> weapon_section) {
        this.weapon_section = weapon_section;
    }

    public ArrayList<Armors> getArmors_section() {
        return armors_section;
    }

    public void setArmors_section(ArrayList<Armors> armors_section) {
        this.armors_section = armors_section;
    }
}
