public abstract class BattleLoc extends Location {



    private Enemy enemy;

    private String name;
    private String str_award;
    private int int_award;
    private boolean destroy_flag_6=false;

    public BattleLoc(Player player, String name, Enemy enemy, String str_award, int int_award, boolean destroy_flag) {
        super(player,name);
        this.enemy = enemy;
        this.name = name;
        this.str_award = str_award;
        this.int_award = int_award;
        this.destroy_flag_6=destroy_flag;
    }




    @Override
    public boolean onLocatin() {
               String name = this.getName();
               System.out.println("Can: "+this.getPlayer().getHealth());
               if(name.equals("Mağara")){
                   if(!getPlayer().isCave_flag()){
                       System.out.println("Temizlenmiş bölge ... ");
                       return true;
                   }
               }else if(name.equals("Orman")){
                   if(!getPlayer().isAhead_of_forest_flag()){
                       System.out.println("Temizlenmiş bölge ...");
                       return true;
                   }
               }if(name.equals("Terkedilmiş Ev")){
                   if(!getPlayer().isEnemy_camp1_flag()){
                       System.out.println("Temizlenmiş bölge ...");
                       return true;
                   }
               }if(name.equals("Yükselen duman")){
                   if(!getPlayer().isEnemy_camp2_flag()) {
                       System.out.println("Temizlenmiş bölge ... ");
                       return true;
                   }
               }if(name.equals("Maden")) {
                    if (!getPlayer().isSide_task1()) {
                        System.out.println("Temizlenmiş bölge ... ");
                        return true;
                    }
                }if(name.equals("Dağın İçi")){
                    if(!getPlayer().isEnemy_camp2_flag()) {
                        System.out.println("Temizlenmiş bölge ... ");
                        return true;
                    }
               }


               int obs_number = enemy.randomObstacleNumber();
               System.out.println("Dikkatli ol burada bir veya daha fazla "+this.getObstacle().getName()+" olabilir.");
               enemyStats(obs_number);
               System.out.println("<S>avaş veya <K>aç");
               String selection = input.next().toUpperCase();
               if(selection.equals("S")){
                   boolean isWon= savas(this.getObstacle().getStr_Award(),obs_number);
                   return isWon;
               } else if (selection.equals("K")) {
                   System.out.println("Ana menüye dönülüyor...");
                   return true;
               }else{
                   System.out.println("Geçersiz Seçim");
                   return true;
               }

    }


    boolean flag=true;
    public boolean savas(String name,int obs_number) {
        BattleSimulation battleSimulation1=new BattleSimulation();
        battleSimulation1.startSimulation();
        int targetawardcount  = 0;
        int targetawardcount1 = 0;

        if(this.getObstacle().getName().equals("Ayı")){
            obs_number=1;
        }else if(this.getObstacle().getName().equals("Kurt")){
            obs_number =2;
        }else if(this.getObstacle().getName().equals("Mistik Dağ Canavarı")){
            obs_number=1;
        }else if(this.getObstacle().getName().equals("Kral Yeti")){
            obs_number=1;
        }

        do {
            if (combat(obs_number)) {
                String looking_for_Name =this.getName();
                if(looking_for_Name.equals("Mağara")){
                    this.getPlayer().setCave_flag(false);
                } else if (looking_for_Name.equals("Orman")) {
                    this.getPlayer().setAhead_of_forest_flag(false);
                } else if (looking_for_Name.equals("Terkedilmiş Ev")) {
                    this.getPlayer().setEnemy_camp1_flag(false);
                } else if (looking_for_Name.equals("Yükselen duman")) {
                    this.getPlayer().setEnemy_camp2_flag(false);
                }else if (looking_for_Name.equals("Dağ girişi")){
                    this.getPlayer().setEnemy_camp3_flag(false);
                }else if (looking_for_Name.equals("Dağın içi")){
                    this.getPlayer().setEnemy_camp3_flag(false);
                }else if (looking_for_Name.equals("Zirve")){
                    this.getPlayer().setEnemy_camp3_flag(false);
                }else if(looking_for_Name.equals("Canavar Yuvası")){
                    this.getPlayer().setEnemy_camp4_flag(false);
                }else if(looking_for_Name.equals("Maden")){
                    this.getPlayer().setSide_task1(false);
                }

                if(looking_for_Name.equals("Terkedilmiş Ev")){
                    obs_number=1;
                } else if (looking_for_Name.equals("Maden")) {
                    obs_number=1;
                }

                //  #############a## BURADAN AŞAĞISI SAVAŞTAN SAĞ ÇIKMA DURUMUNDA ÖDÜL SİSTEMİ VE ENVANTERDEKİ DÜZENLEMELERLE İLGİLİ ##################
                if(this.getObstacle().getInt_Award()==0){
                    System.out.println(obs_number + " tane " + this.getObstacle().getStr_Award() + " ve 1 " + this.getStr_Award());
                }
                else{
                    System.out.println(obs_number + " tane " + this.getObstacle().getStr_Award() + " ve 1 " + this.getStr_Award() + "+ " + (this.getObstacle().getInt_Award()*obs_number + int_award) + " gold kazandınız");
                }
                this.getPlayer().setMoney(this.getPlayer().getMoney() + int_award + obs_number * this.getObstacle().getInt_Award());
                for (Awards award : this.getPlayer().getInventory().getAwardsList()) {
                    if (award != null) {
                        if (award.getName().equals(getObstacle().getStr_Award())) {
                            targetawardcount += award.getCount();
                        }
                    }
                }
                for (Awards award : this.getPlayer().getInventory().getAwardsList()) {
                    if (award != null) {
                        if (award.getName().equals(this.getStr_Award())) {
                             targetawardcount1 += award.getCount();
                        }
                    }
                }

                if (targetawardcount > 0) {
                    //Oyuncu savaşı kazanırsa gelen ödüllerle birlikte envanterdeki ve parasındaki düzenlemeler aşağıdaki gibi olur.
                 //   playerStats();
//                    this.getPlayer().getInventory().addAward(selectedaward);

                    this.getPlayer().getInventory().setCountAwardList((targetawardcount + obs_number), this.getObstacle().getStr_Award());
                    System.out.println("Geri dönmek için Q'ya bas.");
                    String choose = input.next().toUpperCase();
                    if(choose.equals("Q"))
                        flag = false;
                    else{
                        continue;
                    }
                } else {
                    //Oyuncu savaşı kazanırsa gelen ödüllerle birlikte envanterdeki ve paramdaki düzenlemeler aşağıdaki gibi olur.
               //     playerStats();
                    Awards selectedaward = Awards.getAwardObjByName(name);
                    this.getPlayer().getInventory().addAward(selectedaward);
                    this.getPlayer().getInventory().setCountAwardList(obs_number, this.getObstacle().getStr_Award());
                    // Mekan Ödülü

                }
                // Aşağıdaki mekan ödülleri için düzenleme
                if(targetawardcount1>0){
                    this.getPlayer().getInventory().setCountAwardList((targetawardcount1+obs_number),str_award);
                }
                else{
                    Awards selectedaward2 = Awards.getAwardObjByName(str_award);
                    this.getPlayer().getInventory().addAward(selectedaward2);
                    this.getPlayer().getInventory().setCountAwardList(1,str_award);
                }
                if(this.getName().equals("Maden")){
                    System.out.println("Mahkumları kurtarmak için anahtarı kullanmak için E ye bas !");
                    String selection =input.next().toUpperCase();
                    if(selection.equals("E")){
                       System.out.println("Zindandan Mehmet ve Ali isminde 2 mahkum kurtardın,Mehmet kıyı çarşısına gitmek istedi ve Ali'yi de  kampına götürdün artık onunla birlikte kalacaksın ");
                        for (Awards award : this.getPlayer().getInventory().getAwardsList()) {
                            if (award != null) {
                                if (award.getName().equals("Zindan Anahtarı")) {
                                    this.getPlayer().getInventory().getAwardsList().remove(award);
                                }
                            }
                        }
                    }
                }
                while(flag){
                    System.out.println("Geri dönmek için Q'ya bas.");
                    String choose = input.next().toUpperCase();
                    if(choose.equals("Q"))
                        flag = false;
                    else{
                        continue;
                    }
                }
                destroy_flag_6=false;
            } else {
                return false;
            }
        } while (flag);
        return true;
    }



    // Şimdilik savaş algoritması  düşmanın hem sağlığının hem hasarının playerdan daha az ise kazanacak şeklinde olur
    public boolean combat(int obsnumber){
        // ## GİRDİĞİMİZ FİGHTLARDAN BAŞARIYLA ÇIKABİLMEMİZ İÇİN HEM SAĞLIĞIMIZIN DÜŞMANLARIN TOPLAM SAĞLIĞINDAN DAHA FAZLA ,
        // ## HEM TOPLAM HASARIMIZIN DÜŞMANLARIN TOPLAM HASARININ ZIRHIN DURABİLİTYSİNİN FARKINDAN FAZLA OLMASI GEREKİYOR
        if((obsnumber*(this.getObstacle().getHealth())<this.getPlayer().getHealth()) && (this.getPlayer().getTotalDamage()>(this.getObstacle().getDamage()*obsnumber)-(this.getPlayer().getInventory().getArmor().getDurability()))){
            int new_blocking_count = this.getPlayer().getInventory().getArmor().getBlocking_count()-obsnumber;
            this.getPlayer().getInventory().getArmor().setBlocking_count(new_blocking_count);
            if(this.getPlayer().getInventory().getArmor().getBlocking_count()==0 && !this.getPlayer().getInventory().getArmor().getName().equals("Yok")){
                this.getPlayer().getInventory().getArmor().setDurability(0);
                System.out.println("Zırhınız kullanılamaz halde...,Tamirciye gidip yaptırabilirsiniz ya da yeni bir kalkan alabilirsiniz !");
            }
            this.getPlayer().setHealth(this.getPlayer().getHealth()-(this.getObstacle().getDamage()*obsnumber));

            System.out.println(this.getObstacle().getName()+" düşmanı ile savaştan başarıyla çıktınız,Tebrikler.");
            return true;
        }
        else{
            System.out.println("Öldünüz,Oyun bitti...");
            System.exit(0);
            return false;
        }
    }

//
    public void enemyStats(int obs_number){
        if(this.getObstacle().getName().equals("Ayı")){
            obs_number=1;
        }else if(this.getObstacle().getName().equals("Kurt")){
            obs_number =2;
        }else if(this.getObstacle().getName().equals("Mistik Dağ Canavarı")){
            obs_number=1;
        }else if(this.getObstacle().getName().equals("Kral Yeti")){
            obs_number=1;
        }

        System.out.println(this.getObstacle().getName() + " Değerleri");
        System.out.println("--------------------------");
        System.out.println("Sağlık : " + (this.getObstacle().getHealth()*obs_number));
        System.out.println("Hasar  : " + (this.getObstacle().getDamage()*obs_number));
    }
//    public void playerStats(){
//        System.out.println("Oyuncu Değerleri");
//        System.out.println("-------------------");
//        System.out.println("Sağlık : "+this.getPlayer().getHealth());
//        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
//        System.out.println("Mevcut Para : " + this.getPlayer().getMoney());
//        System.out.println("Silah : " + this.getPlayer().getWeapon().getName());
//        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
//        System.out.println("Zırh Dayanıklılığı : " + this.getPlayer().getInventory().getArmor().getName());
//        System.out.println("Bloklama : "+this.getPlayer().getInventory().getArmor().getBlocking_count());
//    }
    public Enemy getObstacle() {
        return enemy;
    }

    public void setObstacle(Enemy enemy) {
        this.enemy = enemy;
    }

    public String getStr_Award() {
        return str_award;
    }

    public void setStr_Award(String award) {
        this.str_award = str_award;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getInt_award() {
        return int_award;
    }

    public void setInt_award(int int_award) {
        this.int_award = int_award;
    }

    public boolean isDestroy_flag() {
        return destroy_flag_6;
    }

    public void setDestroy_flag(boolean destroy_flag) {
        this.destroy_flag_6 = destroy_flag;
    }


}

