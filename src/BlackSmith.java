public class BlackSmith extends NormalLoc{
    // Her zırh bir defa
    private int repair_armor=50;
    private int armor_first_development=20;
    private int armor_second_development=45;
    private int armor_third_development=75;

    private int weapon_first_development=20;
    private int weapon_second_development=45;
    private int weapon_third_development=75;


    public BlackSmith(Player player) {
        super(player,"Demirci");
    }

    boolean flag=true;
    @Override
    public boolean onLocatin() {

        while(flag){
            System.out.println("----------- Demirciye hoşgeldiniz ! -----------");
            int avaliable_levelof_weapon = this.getPlayer().getInventory().getWeapon().getLevel();
            int avaliable_levelof_armor = this.getPlayer().getInventory().getArmor().getLevel();
            System.out.println("---------------------------------");
            System.out.println("Zırh tamir ücreti "+repair_armor+" gold");
            System.out.println("2 level silah geliştirmesi "+weapon_first_development +" gold\n"+"3 level silah geliştirmesi "+weapon_second_development+" gold\n"+"4 level silah geliştirmesi"+weapon_third_development+" gold");
            System.out.println("2 level zırh geliştirmesi "+armor_first_development +" gold\n"+"3 level zırh geliştirmesi "+armor_second_development+"\n"+"4 level zırh geliştirmesi"+armor_third_development);
            System.out.println("---------------------------------");
            System.out.println("1 - Zırhı tamir ettir ! ");
            System.out.println("2 - "+(avaliable_levelof_weapon+1)+" level silah geliştirmesi");
            System.out.println("3 - "+(avaliable_levelof_armor+1)+" level zırh geliştirmesi");
            System.out.println("4 - Çıkış yap");
            int selection = input.nextInt();
            switch (selection){
                case 1:
                    repairArmor();
                    break;
                case 2:
                    if(avaliable_levelof_weapon==1){
                        developWeapon2();
                    } else if (avaliable_levelof_weapon==2) {
                        developWeapon3();
                    }else{
                        developWeapon4();
                    }
                    break;
                case 3:
                    if(avaliable_levelof_armor==1){
                        developArmor2();
                    } else if (avaliable_levelof_armor==2) {
                        developArmor3();
                    }else{
                        developArmor4();
                    }
                    break;
                case 4:
                    flag=false;
                    break;
                default:
                    System.out.println("Geçersiz değer ! Tekrar deneyiniz ... ");
                    break;
            }
        }
        return true;
    }

    public void repairArmor(){
        Armors available_armor= Armors.getArmObjById(this.getPlayer().getInventory().getArmor().getId());
        int new_money = this.getPlayer().getMoney()-repair_armor;
        if(new_money>=0){
            this.getPlayer().getInventory().setArmor(available_armor);
            this.getPlayer().setMoney(new_money);
        }else{
            System.out.println("Yeterli paranız bulunmamaktadır !");
        }


//        assert available_armor != null;
//        this.getPlayer().getInventory().getArmor().setDurability(available_armor.getDurability());
//        this.getPlayer().getInventory().getArmor().setBlocking_count(available_armor.getBlocking_count());
    }

    public void developArmor2(){// Level 1 zırh geliştirme 20 gold (level 1 den level 2 ye)
        Armors available_armor= Armors.getArmObjById(this.getPlayer().getInventory().getArmor().getId());
        assert available_armor != null;

        int new_money = this.getPlayer().getMoney()-armor_first_development;
        if(new_money>=0){
            this.getPlayer().getInventory().getArmor().setDurability(available_armor.getDurability()+5);
            this.getPlayer().getInventory().getArmor().setDurability(available_armor.getBlocking_count()+3);
            this.getPlayer().setMoney(new_money);
        }else{
            System.out.println("Yeterli paranız bulunmamaktadır !");
        }
    }

    public void developArmor3(){// Level 2 zırh geliştirme 45 gold (level 2 den level 3 e)
        Armors available_armor= Armors.getArmObjById(this.getPlayer().getInventory().getArmor().getId());
        assert available_armor != null;
        int new_money = this.getPlayer().getMoney()-armor_second_development;
        if(new_money>=0){
            this.getPlayer().getInventory().getArmor().setDurability(available_armor.getDurability()+10);
            this.getPlayer().getInventory().getArmor().setDurability(available_armor.getBlocking_count()+2);
            this.getPlayer().setMoney(new_money);
        }else{
            System.out.println("Yeterli paranız bulunmamaktadır !");
        }
    }
    public void developArmor4(){// Level 3 zırh geliştirme 75 gold (level 3 den level 4 e)
        Armors available_armor= Armors.getArmObjById(this.getPlayer().getInventory().getArmor().getId());
        assert available_armor != null;
        int new_money = this.getPlayer().getMoney()-armor_third_development;
        if(new_money>=0){
            this.getPlayer().getInventory().getArmor().setDurability(available_armor.getDurability()+15);
            this.getPlayer().getInventory().getArmor().setDurability(available_armor.getBlocking_count()+1);
            this.getPlayer().setMoney(new_money);
        }else{
            System.out.println("Yeterli paranız bulunmamaktadır !");
        }
    }

    public void developWeapon2(){//Level 1 silah geliştirme 20 gold
        Weapon available_weapon= Weapon.getWeaponObjById(this.getPlayer().getInventory().getWeapon().getId());
        assert available_weapon != null;
        int new_money = this.getPlayer().getMoney()-weapon_first_development;
        if(new_money>=0){
            this.getPlayer().getInventory().getWeapon().setDamage(available_weapon.getDamage()+5);
            this.getPlayer().setMoney(new_money);
        }else{
            System.out.println("Yeterli paranız bulunmamaktadır !");
        }
    }
    public void developWeapon3(){//Level 2 silah geliştirme 45 gold
        Weapon available_weapon= Weapon.getWeaponObjById(this.getPlayer().getInventory().getWeapon().getId());
        assert available_weapon != null;
        int new_money = this.getPlayer().getMoney()-weapon_second_development;
        if(new_money>=0){
            this.getPlayer().getInventory().getWeapon().setDamage(available_weapon.getDamage()+10);
            this.getPlayer().setMoney(new_money);
        }else{
            System.out.println("Yeterli paranız bulunmamaktadır !");
        }
    }
    public void developWeapon4(){//Level 3 silah geliştirme 20 gold
        Weapon available_weapon= Weapon.getWeaponObjById(this.getPlayer().getInventory().getWeapon().getId());
        assert available_weapon != null;
        int new_money = this.getPlayer().getMoney()-weapon_third_development;
        if(new_money>=0){
            this.getPlayer().getInventory().getWeapon().setDamage(available_weapon.getDamage()+15);
            this.getPlayer().setMoney(new_money);
        }else{
            System.out.println("Yeterli paranız bulunmamaktadır !");
        }
    }

    public int getArmor_first_development() {
        return armor_first_development;
    }

    public void setArmor_first_development(int armor_first_development) {
        this.armor_first_development = armor_first_development;
    }

    public int getArmor_second_development() {
        return armor_second_development;
    }

    public void setArmor_second_development(int armor_second_development) {
        this.armor_second_development = armor_second_development;
    }

    public int getArmor_third_development() {
        return armor_third_development;
    }

    public void setArmor_third_development(int armor_third_development) {
        this.armor_third_development = armor_third_development;
    }

    public int getWeapon_first_development() {
        return weapon_first_development;
    }

    public void setWeapon_first_development(int weapon_first_development) {
        this.weapon_first_development = weapon_first_development;
    }

    public int getWeapon_second_development() {
        return weapon_second_development;
    }

    public void setWeapon_second_development(int weapon_second_development) {
        this.weapon_second_development = weapon_second_development;
    }

    public int getWeapon_third_development() {
        return weapon_third_development;
    }

    public void setWeapon_third_development(int weapon_third_development) {
        this.weapon_third_development = weapon_third_development;
    }

    public int getRepair_armor() {
        return repair_armor;
    }

    public void setRepair_armor(int repair_armor) {
        this.repair_armor = repair_armor;
    }
}
