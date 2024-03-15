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
            System.out.println("Ana menüye dön (E/H) ?");
            String selection = input.next().toUpperCase();
            if(selection.equals("E")){
                return true;
            } else if (selection.equals("H")) {
                continue;
            }
            else{
                System.out.println("Geçersin değer !");
                continue;
            }
        }while(ctrl_variable);
        return true;

    }
    // Bu OOP için kötü bir kullanım bunu Inventoryde return ile döndürüp burada kullanman gerekiyor
    public void displayInventory(){
        System.out.println(
                "Silah : " + this.getPlayer().getInventory().getWeapon().getName()+
                        "\t Silahın Hasarı:"+this.getPlayer().getInventory().getWeapon().getDamage()+
                        "\nZırh :" + this.getPlayer().getInventory().getArmor().getName() +
                        "\tDayanıklılık : "+this.getPlayer().getInventory().getArmor().getDurability()+
                        "\tBloklama sayısı : "+this.getPlayer().getInventory().getArmor().getBlocking_count());
        System.out.println("Eşyalar :\n");
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
    }
}