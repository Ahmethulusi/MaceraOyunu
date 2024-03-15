public class Kiyi_kasabasi extends NormalLoc{
    public Kiyi_kasabasi(Player player) {
        super(player, "Kıyı Kasabası");
    }

    @Override
    public boolean onLocatin() {
        kasabaya_git2(this.getPlayer());
        return true;
    }

    public boolean kasabaya_git2(Player player) {
        Location location = null;
        Quit quit = new Quit(player);
        while(true){
            System.out.println("#############\n");
            System.out.println("1 - Kıyı Çarşısı\n");
            System.out.println("2 - Kamp Alanı\n");
            System.out.println("3 - Geri dön\n");
            System.out.println("#############");
            int selection = input.nextInt();
            switch (selection) {
                case 1:
                    location = new Town(player);
                    break;
                case 2:
                    location = new Camp_Area(player);
                    break;
                case 3:
                    location = new Quit(player);
                    return true;
                default:
                    location = new GecersizDeger(player);
                    break;
            }
            if(!location.onLocatin()) {
                System.out.println("GAME OVER");
                System.exit(0);
                break;
            }
        }
        return  true;
    }
}
