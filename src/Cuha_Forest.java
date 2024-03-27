
public class Cuha_Forest extends Location{
    public Cuha_Forest(Player player) {
        super(player, "Çuha Ormanı");
    }

    @Override
    public boolean onLocatin() {
        Go_forest(this.getPlayer());
        return true;
    }
    public boolean Go_forest(Player player) {
        Location location = null;
        Quit quit = new Quit(player);
        while(true){
            System.out.println("\n\t ################ ÇUHA ORMANI ############### ");
            System.out.println("\n\t1 - Yükselen bir duman var istersen bi bak !\n");
            System.out.println("\t2- Terkedilmiş eve gir\n");
            System.out.println("\t3- Ormanın İçine doğru ilerle\n");
            System.out.println("\t4- Mağaraya gir\n");
            System.out.println("\t5- Çıkış yap\n");
            int selection = input.nextInt();
            switch (selection) {
                case 1:
                    location = new Rising_smoke(player);
                    break;
                case 2:
                    location = new Abondoned_house(player);
                    break;
                case 3:
                    location = new Forest(player);
                    break;
                case 4:
                    location = new Cave(player);
                    break;
                case 5:
                    location = new Quit(player);
                    return true;
                default:
                    location = new GecersizDeger(player);
                    break;
            }
            if(!location.onLocatin()) {
                System.out.println("GAME OVER");
                System.out.println("Lütfen geçerli bir değer giriniz !");
                break;
            }
        }
        return  true;
    }
}
