import java.util.Scanner;

public class Town extends NormalLoc{

    private Scanner input= new Scanner(System.in);

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
            System.out.println("1-Güvenli Ev");
            System.out.println("2-Eşya dükkanı");
            System.out.println("3-Demirci");
            System.out.println("4-Çıkış yap");
            System.out.println("Seç : ");
            int selection = input.nextInt();
            switch (selection) {
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new BlackSmith(player);
                    break;
                case 4:
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
