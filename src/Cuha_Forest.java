
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
            System.out.println("1- Ormanın İçine doğru ilerle");
            System.out.println("2- Mağaraya gir");
            System.out.println("3- Terkedilmiş eve gir");
            System.out.println("4- Yükselen bir duman var istersen bi bak !");
            System.out.println("5- Çıkış yap");
            System.out.println("Seç : ");
            int selection = input.nextInt();
            switch (selection) {
                case 1:
                    location = new Cave(player);
                    break;
                case 2:
                    location = new Forest(player);
                    break;
                case 3:
                    location = new Abondoned_house(player);
                    break;
                case 4:
                    location = new Rising_smoke(player);
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
