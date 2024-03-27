import java.util.ArrayList;
import java.util.List;

public class GoInventory extends NormalLoc {
    private Inventory inventory;

    public GoInventory(Player player) {
        super(player,"Envanteri Görüntüle");
        this.inventory=new Inventory();
    }

    @Override
    public boolean onLocatin() {
        boolean ctrl_variable = true;
        do {
            displayInventory();
            System.out.println("\n\tAna menüye dön (E/H) ?");
            String selection = input.next().toUpperCase();
            if(selection.equals("E")){
                return true;
            } else if (selection.equals("H")) {
                continue;
            }
            else{
                System.out.println("Geçersiz değer !");
                continue;
            }
        }while(ctrl_variable);
        return true;

    }
    // Bu OOP için kötü bir kullanım bunu Inventoryde return ile döndürüp burada kullanman gerekiyor
    public void displayInventory(){
        System.out.println("\n######### ENVANTER #########");
        System.out.println(
                "\nSilah : " + this.getPlayer().getInventory().getWeapon().getName()+ "  ,"+
                        "\t Hasarı:"+this.getPlayer().getInventory().getWeapon().getDamage()+"  ,"+
                        "\t Level :     "+this.getPlayer().getInventory().getWeapon().getLevel()+
                        "\n\nZırh : " + this.getPlayer().getInventory().getArmor().getName() +"  ,"+
                        "\t Dayanıklılık : "+this.getPlayer().getInventory().getArmor().getDurability()+"  ,"+
                        "\t B.sayısı : "+this.getPlayer().getInventory().getArmor().getBlocking_count()+"  ,"+
                        "\t Level : "+this.getPlayer().getInventory().getArmor().getLevel()+
                        "\nEşyalar :\n");

        Player player = this.getPlayer();

        if (player != null) {
            Inventory inventory = player.getInventory();
            if (inventory != null) {
                List<Awards> awards = inventory.getAwardsList();
                if (awards != null) {
                    for (int i = 0; i < awards.size(); i++) {
                        Awards award = awards.get(i);
                        if (award != null) {
                            System.out.println(award.getName() + " x" + award.getCount()+"\t");

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
}