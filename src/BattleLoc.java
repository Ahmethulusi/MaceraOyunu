import java.util.ArrayList;
import java.util.List;

public abstract class BattleLoc extends Location {



    private Obstacle obstacle;

    private String name;
    private String str_award;
    private int int_award;
    private boolean destroy_flag_6=false;

    public BattleLoc(Player player, String name, Obstacle obstacle, String str_award, int int_award, boolean destroy_flag) {
        super(player,name);
        this.obstacle = obstacle;
        this.name = name;
        this.str_award = str_award;
        this.int_award = int_award;
        this.destroy_flag_6=destroy_flag;
    }




    @Override
    public boolean onLocatin() {
               String name = this.getName();
               if(name.equals("Mağara")){
                   if(!getPlayer().isCave_flag()){
                       System.out.println("Temiz bölge");
                       return true;
                   }
               }else if(name.equals("Orman")){
                   if(!getPlayer().isAhead_of_forest_flag()){
                       System.out.println("Temiz bölge");
                       return true;
                   }
               }if(name.equals("Terkedilmiş Ev")){
                   if(!getPlayer().isEnemy_camp1_flag()){
                       System.out.println("Temiz bölge");
                       return true;
                   }
               }if(name.equals("Yükselen duman")){
                   if(!getPlayer().isEnemy_camp2_flag()) {
                       System.out.println("Temiz bölge");
                       return true;
                   }
               }

               System.out.println("Şu anda " + this.getName() + " konumundasınız.");
               System.out.println("Dikkatli olun burada " + this.getObstacle().getName() + " var");
               obstacleStats();
               playerStats();
               System.out.println("<S>avaş veya <K>aç");
               String selection = input.next().toUpperCase();

               if(selection.equals("S")){
                   boolean isWon= savas(this.getObstacle().getStr_Award());
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
    public boolean savas(String name) {
        BattleSimulation battleSimulation1=new BattleSimulation();
        battleSimulation1.startSimulation();
        int targetawardcount  = 0;
        int targetawardcount1 = 0;
        int obs_number = obstacle.randomObstacleNumber();
        if(this.getObstacle().getName().equals("Ayı")){
            obs_number=1;
        }else if(this.getObstacle().getName().equals("Kurt")){
            obs_number =2;
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
                } else if (looking_for_Name.equals("Yükselen Duman")) {
                    this.getPlayer().setEnemy_camp2_flag(false);
                }


                //  ############### BURADAN AŞAĞISI SAVAŞTAN SAĞ ÇIKMA DURUMUNDA ÖDÜL SİSTEMİ VE ENVANTERDEKİ DÜZENLEMELERLE İLGİLİ ##################
                if(this.getObstacle().getInt_Award()==0){
                    System.out.println(obs_number + " tane " + this.getObstacle().getStr_Award() + " ve 1 " + this.getStr_Award() + "+ ");
                }
                else{
                    System.out.println(obs_number + " tane " + this.getObstacle().getStr_Award() + " ve 1 " + this.getStr_Award() + "+ " + (this.getObstacle().getInt_Award()*obs_number + int_award) + " gold kazandınız");
                }
                this.getPlayer().setMoney(this.getPlayer().getMoney() + int_award + obs_number * this.getObstacle().getInt_Award());
                for (Awards award : this.getPlayer().getInventory().getAwardsList()) {
                    if (award != null) {
                        if (award.getName().equals(getObstacle().getStr_Award())) {
                            targetawardcount = award.getCount();
                        }
                    }
                }
                for (Awards award : this.getPlayer().getInventory().getAwardsList()) {
                    if (award != null) {
                        if (award.getName().equals(str_award)) {
                             targetawardcount1= award.getCount();
                        }
                    }
                }

                if (targetawardcount > 0) {
                    //Oyuncu savaşı kazanırsa gelen ödüllerle birlikte envanterdeki ve parasındaki düzenlemeler aşağıdaki gibi olur.
                    playerStats();
                    Awards selectedaward = Awards.getAwardObjByName(name);
//                    this.getPlayer().getInventory().addAward(selectedaward);
                    this.getPlayer().getInventory().setCountList((targetawardcount + obs_number), this.getObstacle().getStr_Award());
                    // Aşağıdaki mekan ödülleri için düzenleme
                    Awards selectedaward2 = Awards.getAwardObjByName(str_award);
                    if(targetawardcount1>0){
                        //this.getPlayer().getInventory().addAward(selectedaward2);
                        this.getPlayer().getInventory().setCountList(targetawardcount1++,str_award);
                    }
                    else{
                        this.getPlayer().getInventory().addAward(selectedaward);
                        this.getPlayer().getInventory().setCountList(1,str_award);
                    }
                        System.out.println("Ana menüye dönmek için Q'ya bas.");
                    String choose = input.next().toUpperCase();
                    if(choose.equals("Q"))
                        flag = false;
                    else{
                        continue;
                    }
                } else {
                    //Oyuncu savaşı kazanırsa gelen ödüllerle birlikte envanterdeki ve paramdaki düzenlemeler aşağıdaki gibi olur.
                    playerStats();
                    Awards selectedaward = Awards.getAwardObjByName(name);
                    this.getPlayer().getInventory().addAward(selectedaward);
                    this.getPlayer().getInventory().setCountList(obs_number, this.getObstacle().getStr_Award());
                    // Mekan Ödülü
                    Awards selectedaward2 = Awards.getAwardObjByName(str_award);
                    this.getPlayer().getInventory().addAward(selectedaward2);
                    this.getPlayer().getInventory().setCountList(1, str_award);
                    System.out.println("Ana menüye dönmek için Q'ya bas.");
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
            if(this.getPlayer().getInventory().getArmor().getBlocking_count()==0){
                this.getPlayer().getInventory().getArmor().setDurability(0);
                System.out.println("Zırhınız kırıldı...,Tamirciye gidip yaptırabilirsiniz ya da yeni bir kalkan alabilirsiniz !");
            }
            this.getPlayer().setHealth(this.getPlayer().getHealth()-(this.getObstacle().getDamage()*obsnumber));

            System.out.println(this.getObstacle().getName()+" düşmanı ile savaştan başarıyla çıktınız,Tebrikler.");
            return true;
        }
        else{
            System.out.println("Öldünüz,Oyun bitti...");
            return false;
        }
    }


    public void obstacleStats(){
        System.out.println(this.getObstacle().getName() + " Değerleri");
        System.out.println("--------------------------");
        System.out.println("Sağlık : " + this.getObstacle().getHealth());
        System.out.println("Hasar  : " + this.getObstacle().getDamage());
    }
    public void playerStats(){
        System.out.println("Oyuncu Değerleri");
        System.out.println("-------------------");
        System.out.println("Sağlık : "+this.getPlayer().getHealth());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Mevcut Para : " + this.getPlayer().getMoney());
        System.out.println("Silah : " + this.getPlayer().getWeapon().getName());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Zırh Dayanıklılığı : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : "+this.getPlayer().getInventory().getArmor().getBlocking_count());
    }
    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
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

