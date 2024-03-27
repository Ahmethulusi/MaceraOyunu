import java.util.List;
import java.util.Locale;

public class ToolStore extends NormalLoc {

    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    boolean flag = true;
    private Awards awards;

    @Override
    public boolean onLocatin() {

        while (flag) {
            System.out.println("-------Mağazaya Hoşgeldiniz!-------");
            System.out.println("Mevcut paranız: " + displayBalance()+" gold");
            System.out.println("Yapmak istediğiniz işlemi seçiniz:");
            System.out.println("1 - Silahlar");
            System.out.println("2 - Zırhlar");
           // System.out.println("3-  İksirler");
            System.out.println("3-  Satış yap");
            System.out.println("4 - Geri dön");
            System.out.print("Seçiminiz: ");

            // Kullanıcıdan seçim al
            int selection = Location.input.nextInt();

            switch (selection) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    // Zırhlar menüsü
                    printArms();
                    buyArm();
                    break;
//                case 3:
//                    // İksirler ve ev yapım malzemeleri gibi temel ihtiyaçlar satılsın
//                    printPotions();
//                    buyPotions();
//                    break;
                case 3:
                    if(this.getPlayer().getInventory().getAwardsList().isEmpty()){
                        System.out.println("Envanter boş , Satılabilecek herhangi bir şey yok");
                        break;
                    } else{
                        printSellableAward();
                        sellItem();
                        break;
                    }
                case 4:
                    System.out.println("Mağazadan çıkılıyor...");
                    flag = false;
                    break;
                default:
                    System.out.println("Geçersiz seçim ! Tekrar deneyiniz");
                    break;
            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("------Silahlar------");
        System.out.println("Mevcut paranız: " + displayBalance());
        System.out.println();
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + " - " + w.getName() + " > Fiyat: " + w.getPrice() + "-Hasar: " + w.getDamage());
        }
        System.out.println("\n\tMağazaya geri dönmek için 4'e basınız");


    }

    public void buyWeapon() {
        // We make the user select a weapon and
        // If he can't afford the weapon of his choice, we send a warning message
        // If the user can afford it, we deduct the user's money
        System.out.println("Lütfen bir silah seçin:");
        int selection = input.nextInt();
        if (selection == 4) {
            onLocatin();
        } else {
            while (selection < 1 || selection > Weapon.weapons().length) {
                System.out.println("Geçersiz değer ! Tekrar deneyiniz:");
                selection = input.nextInt();
            }
            Weapon selectedWeapon = Weapon.getWeaponObjById(selection);
            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunmamaktadır !");
                    onLocatin();
                } else {
                    System.out.println(selectedWeapon.getName() + " silahını satın aldınız !");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    System.out.println("Önceki silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
                    System.out.println("Önceki paranız: " + this.getPlayer().getMoney());
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız:" + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println(this.getPlayer().getInventory().getWeapon().getName() + " silah olarak kuşanıldı.");

                }
            }
        }
    }

    public void printArms() {

        System.out.println("-----------Zırhlar-----------");
        System.out.println("Mevcut paranız: " + displayBalance());
        System.out.println();
        for (Armors a : Armors.arms()) {
            System.out.println(a.getId() + " - " + a.getName() + " > Fiyat: " + a.getPrice() + "-Dayanıklılık: " + a.getDurability());
        }
        System.out.println("\n\tMağazaya geri dönmek için 4'e basınız");

    }

    public void buyArm() {

        System.out.println("Lütfen bir zırh seçin:");
        int selection = input.nextInt();
        if (selection == 4) {
            onLocatin();
        } else {
            while (selection < 1 || selection > Armors.arms().length) {
                System.out.println("Geçersiz değer ! Tekrar deneyiniz:");
                selection = input.nextInt();
            }
            Armors selectedArm = Armors.getArmObjById(selection);
            if (selectedArm != null) {
                if (selectedArm.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunmamaktadır !");
                    onLocatin();
                } else {
                    System.out.println(selectedArm.getName() + "zırhını satın aldınız !");
                    System.out.println("Önceki zırhınız: " + this.getPlayer().getInventory().getArmor().getName());
                    System.out.println("Önceki paranız: " + this.getPlayer().getMoney());
                    int balance = this.getPlayer().getMoney() - selectedArm.getPrice();
                    this.getPlayer().setMoney(balance);
                    this.getPlayer().getInventory().setArmor(selectedArm);
                    System.out.println("Kalan paranız:" + this.getPlayer().getMoney());
                    System.out.println(this.getPlayer().getInventory().getArmor().getName() + " zırh olarak kuşanıldı.");
                }
            }
        }
    }

    public int displayBalance() {
        return this.getPlayer().getMoney();
    }

    public void printSellableAward() {
        System.out.println("Satılabilir eşyalar");
        Player player = this.getPlayer();

        if (player != null) {
            Inventory inventory = player.getInventory();
            if (inventory != null) {
                List<Awards> awards = inventory.getAwardsList();
                if (awards != null) {
                    for (int i = 1; i <= awards.size(); i++) {
                        Awards award = awards.get(i-1);
                        if (award != null) {
                            System.out.println(i+" - "+award.getName() + " x" + award.getCount() + "\t" +"     Birim Satış Fiyatı : "+ award.getSelling_price());
                        }
                    }
                }
            }
        }

        System.out.println("\nÇıkış yapmak için 0'a basınız");
    }
    public void sellItem(){
        System.out.println("Satılacak eşyayı seçin");
        int selection = input.nextInt();
        switch(selection){
            case 1:
                System.out.println("Lütfen kaç tane satmak istediğinizi söyleyin:");
                int count_selection1 = input.nextInt();
                if(!this.getPlayer().getInventory().getAwardsList().isEmpty()){ // Arraylist'in boş olup olmadığını kontrol et
                    Awards award = this.getPlayer().getInventory().getAwardsList().get(0);
                    int new_count = award.getCount() - count_selection1; // Negatif sayı kontrolü yapılmalı

                    if (new_count > 0) {
                        award.setCount(new_count);
                        int new_money=this.getPlayer().getMoney()+(award.getSelling_price()*count_selection1);
                        this.getPlayer().setMoney(new_money);
                    }
                    else if(new_count==0){
                        award.setCount(new_count);
                        int new_money=this.getPlayer().getMoney()+(award.getSelling_price()*count_selection1);
                        this.getPlayer().setMoney(new_money);
                        this.getPlayer().getInventory().getAwardsList().remove(0);
                    }
                    else {
                        System.out.println("Yetersiz miktar! Stokta yeterince " + award.getName() + " yok.");
                    }
                }

                break;
            case 2:
                System.out.println("Lütfen kaç tane satmak istediğinizi söyleyin:");
                int count_selection2 = input.nextInt();
                if(!this.getPlayer().getInventory().getAwardsList().isEmpty()){ // Arraylist'in boş olup olmadığını kontrol et
                    Awards award = this.getPlayer().getInventory().getAwardsList().get(1);
                    int new_count = award.getCount() - count_selection2; // Negatif sayı kontrolü yapılmalı

                    if (new_count > 0) {
                        award.setCount(new_count);
                        int new_money=this.getPlayer().getMoney()+(award.getSelling_price()*count_selection2);
                        this.getPlayer().setMoney(new_money);
                    } else if (new_count==0) {
                        award.setCount(new_count);
                        int new_money=this.getPlayer().getMoney()+(award.getSelling_price()*count_selection2);
                        this.getPlayer().setMoney(new_money);
                        this.getPlayer().getInventory().getAwardsList().remove(1);
                    } else {

                        System.out.println("Yetersiz miktar! Stokta yeterince " + award.getName() + " yok.");
                    }
                }
                break;
            case 3:
                System.out.println("Lütfen kaç tane satmak istediğinizi söyleyin:");
                int count_selection3 = input.nextInt();
                if(!this.getPlayer().getInventory().getAwardsList().isEmpty()){ // Arraylist'in boş olup olmadığını kontrol et
                    Awards award = this.getPlayer().getInventory().getAwardsList().get(2);
                    int new_count = award.getCount() - count_selection3; // Negatif sayı kontrolü yapılmalı

                    if (new_count > 0) {
                        award.setCount(new_count);
                        int new_money=this.getPlayer().getMoney()+(award.getSelling_price()*count_selection3);
                        this.getPlayer().setMoney(new_money);
                    } else if (new_count==0) {
                        award.setCount(new_count);
                        int new_money=this.getPlayer().getMoney()+(award.getSelling_price()*count_selection3);
                        this.getPlayer().setMoney(new_money);
                        this.getPlayer().getInventory().getAwardsList().remove(2);
                    } else {
                        System.out.println("Yetersiz miktar! Stokta yeterince " + award.getName() + " yok.");
                    }
                }
                break;
            case 4:
                System.out.println("Lütfen kaç tane satmak istediğinizi söyleyin:");
                int count_selection4 = input.nextInt();
                if(!this.getPlayer().getInventory().getAwardsList().isEmpty()){ // Arraylist'in boş olup olmadığını kontrol et
                    Awards award = this.getPlayer().getInventory().getAwardsList().get(3);
                    int new_count = award.getCount() - count_selection4; // Negatif sayı kontrolü yapılmalı

                    if (new_count > 0) {
                        award.setCount(new_count);
                        int new_money=this.getPlayer().getMoney()+(award.getSelling_price()*count_selection4);
                        this.getPlayer().setMoney(new_money);
                    } else if (new_count==0) {
                        award.setCount(new_count);
                        int new_money=this.getPlayer().getMoney()+(award.getSelling_price()*count_selection4);
                        this.getPlayer().setMoney(new_money);
                        this.getPlayer().getInventory().getAwardsList().remove(3);
                    } else {
                        System.out.println("Yetersiz miktar! Stokta yeterince " + award.getName() + " yok.");
                    }
                }
                break;
            case 5:
                System.out.println("Lütfen kaç tane satmak istediğinizi söyleyin:");
                int count_selection5 = input.nextInt();
                if(!this.getPlayer().getInventory().getAwardsList().isEmpty()){ // Arraylist'in boş olup olmadığını kontrol et
                    Awards award = this.getPlayer().getInventory().getAwardsList().get(4);
                    int new_count = award.getCount() - count_selection5; // Negatif sayı kontrolü yapılmalı

                    if (new_count > 0) {
                        award.setCount(new_count);
                        int new_money=this.getPlayer().getMoney()+(award.getSelling_price()*count_selection5);
                        this.getPlayer().setMoney(new_money);
                    } else if (new_count==0) {
                        award.setCount(new_count);
                        int new_money=this.getPlayer().getMoney()+(award.getSelling_price()*count_selection5);
                        this.getPlayer().setMoney(new_money);
                        this.getPlayer().getInventory().getAwardsList().remove(4);
                    } else {
                        System.out.println("Yetersiz miktar! Stokta yeterince " + award.getName() + " yok.");
                    }
                }
                break;
            case 6:
                System.out.println("Lütfen kaç tane satmak istediğinizi söyleyin:");
                int count_selection6 = input.nextInt();
                if(!this.getPlayer().getInventory().getAwardsList().isEmpty()){ // Arraylist'in boş olup olmadığını kontrol et
                    Awards award = this.getPlayer().getInventory().getAwardsList().get(5);
                    int new_count = award.getCount() - count_selection6; // Negatif sayı kontrolü yapılmalı

                    if (new_count > 0) {
                        award.setCount(new_count);
                        int new_money=this.getPlayer().getMoney()+(award.getSelling_price()*count_selection6);
                        this.getPlayer().setMoney(new_money);
                    } else if (new_count==0) {
                        award.setCount(new_count);
                        int new_money = this.getPlayer().getMoney() + (award.getSelling_price() * count_selection6);
                        this.getPlayer().setMoney(new_money);
                        this.getPlayer().getInventory().getAwardsList().remove(5);
                    }else {
                        System.out.println("Yetersiz miktar! Stokta yeterince " + award.getName() + " yok.");
                    }
                }
                break;
            case 7:
                System.out.println("Lütfen kaç tane satmak istediğinizi söyleyin:");
                int count_selection7 = input.nextInt();
                if(!this.getPlayer().getInventory().getAwardsList().isEmpty()){ // Arraylist'in boş olup olmadığını kontrol et
                    Awards award = this.getPlayer().getInventory().getAwardsList().get(6);
                    int new_count = award.getCount() - count_selection7; // Negatif sayı kontrolü yapılmalı

                    if (new_count > 0) {
                        award.setCount(new_count);
                        int new_money=this.getPlayer().getMoney()+(award.getSelling_price()*count_selection7);
                        this.getPlayer().setMoney(new_money);
                    }else if (new_count==0) {
                        award.setCount(new_count);
                        int new_money = this.getPlayer().getMoney() + (award.getSelling_price() * count_selection7);
                        this.getPlayer().setMoney(new_money);
                        this.getPlayer().getInventory().getAwardsList().remove(6);
                    }else {
                        System.out.println("Yetersiz miktar! Stokta yeterince " + award.getName() + " yok.");
                    }
                }
            case 8:
                System.out.println("Lütfen kaç tane satmak istediğinizi söyleyin:");
                int count_selection8 = input.nextInt();
                if(!this.getPlayer().getInventory().getAwardsList().isEmpty()){ // Arraylist'in boş olup olmadığını kontrol et
                    Awards award = this.getPlayer().getInventory().getAwardsList().get(6);
                    int new_count = award.getCount() - count_selection8; // Negatif sayı kontrolü yapılmalı

                    if (new_count > 0) {
                        award.setCount(new_count);
                        int new_money=this.getPlayer().getMoney()+(award.getSelling_price()*count_selection8);
                        this.getPlayer().setMoney(new_money);
                    }else if (new_count==0) {
                        award.setCount(new_count);
                        int new_money = this.getPlayer().getMoney() + (award.getSelling_price() * count_selection8);
                        this.getPlayer().setMoney(new_money);
                        this.getPlayer().getInventory().getAwardsList().remove(7);
                    }else {
                        System.out.println("Yetersiz miktar! Stokta yeterince " + award.getName() + " yok.");
                    }
                }
            case 9:
                this.onLocatin();
                break;

            default:
                System.out.println("Geçersiz değer tekrar deneyiniz !");
        }
    }
    public void printPotions(){
        System.out.println("------ İksirler ------");
        System.out.println("Mevcut paranız: " + displayBalance());
        System.out.println();
        for(Potions p:Potions.potion()){
            System.out.println(p.getId() + " - " + p.getName() + " > Fiyat: " + p.getPrice() + "- Özellik: " + p.getFeature()+"  Etki: "+p.getMagnitude());
        }
        System.out.println("\n\tMağazaya geri dönmek için 4'e basınız");

    }
    public void buyPotions() {
        int targetpotioncount=0;
        System.out.println("Lütfen bir seçim yapınız:");
        int selection = input.nextInt();
        if (selection == 4) {
            onLocatin();
        } else {

            while (selection < 1 || selection > Potions.potion().length) {
                System.out.println("Geçersiz değer ! Tekrar deneyiniz:");
                selection = input.nextInt();
            }
            Potions selectedPotion = Potions.getPotionObjById(selection);

            if (selectedPotion != null) {
                for (Potions potion : this.getPlayer().getInventory().getPotionsList()) {
                    if (potion != null) {
                        if (potion.getName().equals(selectedPotion.getName())) {
                            targetpotioncount= potion.getCount();
                        }
                    }
                }
                if (selectedPotion.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunmamaktadır !");
                    onLocatin();
                } else {
                    System.out.println(selectedPotion.getName() + "  satın aldınız !");
                    int balance = this.getPlayer().getMoney() - selectedPotion.getPrice();
                    this.getPlayer().setMoney(balance);
                    this.getPlayer().getInventory().addPotion(selectedPotion);
                    if(targetpotioncount==0){
                        this.getPlayer().getInventory().setCountPotionList(1,selectedPotion.getName());
                    }else{
                        this.getPlayer().getInventory().setCountPotionList(targetpotioncount+1,selectedPotion.getName());
                    }


                }
            }
        }
    }
}