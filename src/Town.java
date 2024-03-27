import java.util.Scanner;

public class Town extends NormalLoc{


    public Town(Player player) {
        super(player, "Kasaba");
    }

    @Override
    public boolean onLocatin() {
        kasabaya_git(this.getPlayer());
        return true;
    }

    public boolean kasabaya_git(Player player) {
        Location location = null;
        Quit quit = new Quit(player);
        while(true){
            System.out.println("#############\n");
            System.out.println("0 - Geri dön\n");
            System.out.println("1 - Eşya dükkanı\n");
            System.out.println("2 - Demirci\n");
            if(!this.getPlayer().isSide_task1()){
                System.out.println("3 - Mehmeti bul\n");
            }
            System.out.println("#############");
            int selection = input.nextInt();
            switch (selection) {
                case 1:
                    location = new ToolStore(player);
                    break;
                case 2:
                    location = new BlackSmith(player);
                    break;
                case 3:
                    //location = new FindMehmet(player);
                    break;
                case 0:
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
