public class Camp_Area extends NormalLoc{
    public Camp_Area(Player player) {
        super(player, "Kamp Alanı");
    }

    @Override
    public boolean onLocatin() {
        Go_CampArea(this.getPlayer());
        return super.onLocatin();
    }
    public boolean Go_CampArea(Player player) {
        Location location = null;
        Quit quit = new Quit(player);
        while(true){
            System.out.println("#############\n");
            System.out.println("1   Dinlen\n");
            System.out.println("2 - Ateş yak\n");
 //           System.out.println("2 - Ateş yak\n");
            System.out.println("3 - Geri dön\n");
            System.out.println("#############");

            int selection = input.nextInt();
            switch (selection) {
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
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
