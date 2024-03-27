import java.util.ArrayList;
import java.util.List;

public class OpentheChest extends NormalLoc{

    /// Sandıkta 2 tane silah iki tan zırh ve çeşit farketmeksizin toplam 10 tane item saklanabilecek

    public OpentheChest(Player player) {
        super(player, "Sandık");

    }

    @Override
    public boolean onLocatin() {
        System.out.println("\n######### ENVANTER #########");
        displayInventory(getPlayer());
        System.out.println("\n######### SANDIK #########\n");
        displayChest(getPlayer());
        System.out.println("\n");
        makeTransfer();

        return true;
    }
    boolean quit_flag=true;
    public void displayInventory(Player player){
        System.out.println("\n1 - Silah : " + this.getPlayer().getInventory().getWeapon().getName()+ " ,"+
                        "\t Level :     "+this.getPlayer().getInventory().getWeapon().getLevel()+
                        "\n2 - Zırh : " + this.getPlayer().getInventory().getArmor().getName() +" ,"+
                        "\t Level : "+this.getPlayer().getInventory().getArmor().getLevel());

        if (player != null) {
            Inventory inventory = player.getInventory();
            if (inventory != null) {
                List<Awards> awards = inventory.getAwardsList();
                if (awards != null) {
                    for (int i = 0; i < awards.size(); i++) {
                        Awards award = awards.get(i);
                        if (award != null) {
                            System.out.println(i+3+" - "+award.getName() + " x" + award.getCount()+"\t");

                        }
                    }
                }
            }
        }
        if (player != null) {
            Inventory inventory = player.getInventory();
            if (inventory != null) {
                List<Potions> potions = inventory.getPotionsList();
                if ( potions!= null) {
                    for (int i = 0; i < potions.size(); i++) {
                        Potions potion = potions.get(i);
                        if (potion != null) {
                            System.out.println(potion.getName() + " x" + potion.getCount()+"\t");

                        }
                    }
                }
            }
        }

    }
    public void displayChest(Player player) {
        int last_number=1;
        for(int i=0;i<this.getPlayer().getInventory().getWeapon_section().size();i++){
            Weapon weapon = this.getPlayer().getInventory().getWeapon_section().get(i);
            if(weapon!=null){
                System.out.println((last_number) + " - " +weapon.getName());
            }
            last_number++;
        }

        for (int i = 0; i < this.getPlayer().getInventory().getArmors_section().size(); i++) {
            Armors armor = this.getPlayer().getInventory().getArmors_section().get(i);
            if (armor != null) {
                System.out.println(last_number + " - "+armor.getName());
            }
            last_number++;
        }
        for (int i = 0; i < this.getPlayer().getInventory().getAwards_section().size(); i++) {
            Awards award = this.getPlayer().getInventory().getAwards_section().get(i);
            if (award != null) {
                System.out.println((i+1)+" - "+award.getName());
            }
            last_number++;
        }
    }
    public void makeTransfer(){
        System.out.println("Envanterde yalnızca 1 adet silah ve zırh taşınabiliyor");
        int targetawardcount=0;
        System.out.println("Eşya transferi için T'ye /Çıkış için Q'ya basın.");
        String selection = input.next().toUpperCase();
        if(selection.equals("T")){
            System.out.println("Transfer nereden nereye ?\n(Envanterden Sandığa->E)/(Sandıktan Envantere->S)");
            String where_transfer=input.next().toUpperCase();
            if(where_transfer.equals("E")){
                System.out.println("Aktarılacak eşyayı seçin");
                int item_selection = input.nextInt();
                switch(item_selection){
                    case 1:
                        if(!this.getPlayer().getInventory().getWeapon().getName().equals("Yumruk")){
                            this.getPlayer().getInventory().getWeapon_section().add(this.getPlayer().getInventory().getWeapon());
                            System.out.println(this.getPlayer().getInventory().getWeapon()+" sandığa bırakıldı !");
                            this.getPlayer().getInventory().setWeapon(new Weapon("Yumruk", 0, 1, 0, 0));
                        }

                        break;
                    case 2:
                        if(!this.getPlayer().getInventory().getArmor().getName().equals("Yok")){
                            this.getPlayer().getInventory().getArmors_section().add(this.getPlayer().getInventory().getArmor());
                            System.out.println(this.getPlayer().getInventory().getArmor().getName()+" sandığa bırakıldı !");
                            this.getPlayer().getInventory().setArmor(new Armors("Yok", 0, 0, 0, 0,0));
                        }
                        break;
                    case 3:
                        System.out.println("Lütfen adet belirtin:");
                        int count_selection3 = input.nextInt();
                        if(!this.getPlayer().getInventory().getAwardsList().isEmpty()){ // Arraylist'in boş olup olmadığını kontrol et
                            Awards award = this.getPlayer().getInventory().getAwardsList().get(0);// Envanterdeki ödül listesini alıyor
                            Awards real_award =Awards.getAwardObjByName(award.getName());
                            int new_count = award.getCount() - count_selection3; // Negatif sayı kontrolü yapılmalı
                            int award_section_size =this.getPlayer().getInventory().getAwards_section().size();
                            for (Awards referance_award : this.getPlayer().getInventory().getAwards_section()) {
                                if (referance_award != null) {
                                    if (award.getName().equals(award.getName())) {
                                        targetawardcount= award.getCount();
                                    }
                                }
                            }
                            if (new_count > 0) {
                                if(targetawardcount==0){
                                    real_award.setCount(count_selection3);
                                    award.setCount(new_count);
                                    this.getPlayer().getInventory().getAwards_section().add(real_award);
                                }else{
                                    real_award.setCount(count_selection3);
                                    award.setCount(new_count);
                                    Awards will_change_award = Awards.getAwardObjByName(real_award.getName());
                                    will_change_award.setCount(will_change_award.getCount()+count_selection3);
                                }

                            }else if (new_count==0) {
                                real_award.setCount(count_selection3);
                                this.getPlayer().getInventory().getAwards_section().add(real_award);
                                this.getPlayer().getInventory().getAwardsList().remove(0);
                            }
                            else {
                                System.out.println("Yetersiz miktar! Stokta yeterince " + award.getName() + " yok.");
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Lütfen adet belirtin:");
                        int count_selection4 = input.nextInt();
                        if(!this.getPlayer().getInventory().getAwardsList().isEmpty()){ // Arraylist'in boş olup olmadığını kontrol et
                            Awards award = this.getPlayer().getInventory().getAwardsList().get(1);// Envanterdeki ödül listesini alıyor
                            Awards real_award =Awards.getAwardObjByName(award.getName());
                            int new_count = award.getCount() - count_selection4; // Negatif sayı kontrolü yapılmalı
                            int award_section_size =this.getPlayer().getInventory().getAwards_section().size();
                            for (Awards referance_award : this.getPlayer().getInventory().getAwards_section()) {
                                if (referance_award != null) {
                                    if (award.getName().equals(award.getName())) {
                                        targetawardcount= award.getCount();
                                    }
                                }
                            }
                            if (new_count > 0) {
                                if(targetawardcount==0){
                                    real_award.setCount(count_selection4);
                                    award.setCount(new_count);
                                    this.getPlayer().getInventory().getAwards_section().add(real_award);
                                }else{
                                    real_award.setCount(count_selection4);
                                    award.setCount(new_count);
                                    Awards will_change_award = Awards.getAwardObjByName(real_award.getName());
                                    will_change_award.setCount(will_change_award.getCount()+count_selection4);
                                }

                            }else if (new_count==0) {
                                real_award.setCount(count_selection4);
                                this.getPlayer().getInventory().getAwards_section().add(real_award);
                                this.getPlayer().getInventory().getAwardsList().remove(1);
                            }
                            else {
                                System.out.println("Yetersiz miktar! Stokta yeterince " + award.getName() + " yok.");
                            }
                        }
                        break;

                    case 5:
                        System.out.println("Lütfen adet belirtin:");
                        int count_selection5 = input.nextInt();
                        if(!this.getPlayer().getInventory().getAwardsList().isEmpty()){ // Arraylist'in boş olup olmadığını kontrol et
                            Awards award = this.getPlayer().getInventory().getAwardsList().get(2);// Envanterdeki ödül listesini alıyor
                            Awards real_award =Awards.getAwardObjByName(award.getName());
                            int new_count = award.getCount() - count_selection5; // Negatif sayı kontrolü yapılmalı
                            int award_section_size =this.getPlayer().getInventory().getAwards_section().size();
                            for (Awards referance_award : this.getPlayer().getInventory().getAwards_section()) {
                                if (referance_award != null) {
                                    if (award.getName().equals(award.getName())) {
                                        targetawardcount= award.getCount();
                                    }
                                }
                            }
                            if (new_count > 0) {
                                if(targetawardcount==0){
                                    real_award.setCount(count_selection5);
                                    award.setCount(new_count);
                                    this.getPlayer().getInventory().getAwards_section().add(real_award);
                                }else{
                                    real_award.setCount(count_selection5);
                                    award.setCount(new_count);
                                    Awards will_change_award = Awards.getAwardObjByName(real_award.getName());
                                    will_change_award.setCount(will_change_award.getCount()+count_selection5);
                                }

                            }else if (new_count==0) {
                                real_award.setCount(count_selection5);
                                this.getPlayer().getInventory().getAwards_section().add(real_award);
                                this.getPlayer().getInventory().getAwardsList().remove(2);
                            }
                            else {
                                System.out.println("Yetersiz miktar! Stokta yeterince " + award.getName() + " yok.");
                            }
                        }
                        break;
                    case 6:
                        System.out.println("Lütfen adet belirtin:");
                        int count_selection6 = input.nextInt();
                        if(!this.getPlayer().getInventory().getAwardsList().isEmpty()){ // Arraylist'in boş olup olmadığını kontrol et
                            Awards award = this.getPlayer().getInventory().getAwardsList().get(3);// Envanterdeki ödül listesini alıyor
                            Awards real_award =Awards.getAwardObjByName(award.getName());
                            int new_count = award.getCount() - count_selection6; // Negatif sayı kontrolü yapılmalı
                            int award_section_size =this.getPlayer().getInventory().getAwards_section().size();
                            for (Awards referance_award : this.getPlayer().getInventory().getAwards_section()) {
                                if (referance_award != null) {
                                    if (award.getName().equals(award.getName())) {
                                        targetawardcount= award.getCount();
                                    }
                                }
                            }
                            if (new_count > 0) {
                                if(targetawardcount==0){
                                    real_award.setCount(count_selection6);
                                    award.setCount(new_count);
                                    this.getPlayer().getInventory().getAwards_section().add(real_award);
                                }else{
                                    real_award.setCount(count_selection6);
                                    award.setCount(new_count);
                                    Awards will_change_award = Awards.getAwardObjByName(real_award.getName());
                                    will_change_award.setCount(will_change_award.getCount()+count_selection6);
                                }

                            }else if (new_count==0) {
                                real_award.setCount(count_selection6);
                                this.getPlayer().getInventory().getAwards_section().add(real_award);
                                this.getPlayer().getInventory().getAwardsList().remove(4);
                            }
                            else {
                                System.out.println("Yetersiz miktar! Stokta yeterince " + award.getName() + " yok.");
                            }
                        }
                        break;
                    case 7:
                        System.out.println("Lütfen adet belirtin:");
                        int count_selection7 = input.nextInt();
                        if(!this.getPlayer().getInventory().getAwardsList().isEmpty()){ // Arraylist'in boş olup olmadığını kontrol et
                            Awards award = this.getPlayer().getInventory().getAwardsList().get(4);// Envanterdeki ödül listesini alıyor
                            Awards real_award =Awards.getAwardObjByName(award.getName());
                            int new_count = award.getCount() - count_selection7; // Negatif sayı kontrolü yapılmalı
                            int award_section_size =this.getPlayer().getInventory().getAwards_section().size();
                            for (Awards referance_award : this.getPlayer().getInventory().getAwards_section()) {
                                if (referance_award != null) {
                                    if (award.getName().equals(award.getName())) {
                                        targetawardcount= award.getCount();
                                    }
                                }
                            }
                            if (new_count > 0) {
                                if(targetawardcount==0){
                                    real_award.setCount(count_selection7);
                                    award.setCount(new_count);
                                    this.getPlayer().getInventory().getAwards_section().add(real_award);
                                }else{
                                    real_award.setCount(count_selection7);
                                    award.setCount(new_count);
                                    Awards will_change_award = Awards.getAwardObjByName(real_award.getName());
                                    will_change_award.setCount(will_change_award.getCount()+count_selection7);
                                }

                            }else if (new_count==0) {
                                real_award.setCount(count_selection7);
                                this.getPlayer().getInventory().getAwards_section().add(real_award);
                                this.getPlayer().getInventory().getAwardsList().remove(1);
                            }
                            else {
                                System.out.println("Yetersiz miktar! Stokta yeterince " + award.getName() + " yok.");
                            }
                        }
                        break;
                    case 8:
                        System.out.println("Lütfen adet belirtin:");
                        int count_selection8 = input.nextInt();
                        if(!this.getPlayer().getInventory().getAwardsList().isEmpty()){ // Arraylist'in boş olup olmadığını kontrol et
                            Awards award = this.getPlayer().getInventory().getAwardsList().get(5);// Envanterdeki ödül listesini alıyor
                            Awards real_award =Awards.getAwardObjByName(award.getName());
                            int new_count = award.getCount() - count_selection8; // Negatif sayı kontrolü yapılmalı
                            int award_section_size =this.getPlayer().getInventory().getAwards_section().size();
                            for (Awards referance_award : this.getPlayer().getInventory().getAwards_section()) {
                                if (referance_award != null) {
                                    if (award.getName().equals(award.getName())) {
                                        targetawardcount= award.getCount();
                                    }
                                }
                            }
                            if (new_count > 0) {
                                if(targetawardcount==0){
                                    real_award.setCount(count_selection8);
                                    award.setCount(new_count);
                                    this.getPlayer().getInventory().getAwards_section().add(real_award);
                                }else{
                                    real_award.setCount(count_selection8);
                                    award.setCount(new_count);
                                    Awards will_change_award = Awards.getAwardObjByName(real_award.getName());
                                    will_change_award.setCount(will_change_award.getCount()+count_selection8);
                                }

                            }else if (new_count==0) {
                                real_award.setCount(count_selection8);
                                this.getPlayer().getInventory().getAwards_section().add(real_award);
                                this.getPlayer().getInventory().getAwardsList().remove(1);
                            }
                            else {
                                System.out.println("Yetersiz miktar! Stokta yeterince " + award.getName() + " yok.");
                            }
                        }
                        break;
                    case 9:
                        this.onLocatin();
                        break;

                    default:
                        System.out.println("Geçersiz değer tekrar deneyiniz !");
                }
            }
            else{
                System.out.println("Aktarılacak eşyayı seçin");
                int item_selection = input.nextInt();
                switch(item_selection){

                    case 9:
                        this.onLocatin();
                        break;

                    default:
                        System.out.println("Geçersiz değer tekrar deneyiniz !");
                }

            }
        }
        else if(selection.equals("Q")){
            quit_flag=false;
        }
        else{
            System.out.println("Geçersiz seçim tekrar deneyiniz !");
        }

    }
}
