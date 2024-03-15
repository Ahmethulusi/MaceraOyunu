import java.util.Scanner;

public class MainMenu{
    private Player player;
    private Location location;

    private Scanner input = new Scanner(System.in);

    public void showMenu(){
        System.out.println("----------- Ana Menü ----------");
        System.out.println("1 - Oyunu Başlat");
        System.out.println("2 - Ayarlar");
        System.out.println("3 - Zorluk");
        System.out.println("4 - Diller");
        System.out.println("5 - Çıkış");
        System.out.println("-------------------------------");
        System.out.print("Seçiminiz : ");
        int choice = input.nextInt();

        switch (choice){
            case 1:
                startGame();
                break;
            case 2:
                showSettings();
                break;
            case 3:
//                zorluklar();
                break;

            case 4:
                //showLanguages();
                break;
            case 5:
                System.out.println("Oyundan çıkılıyor...");
                System.exit(0);
                break;
            default:
                System.out.println("Geçersiz seçim ! Lütfen geçerli bir seçenek seçiniz !");
                break;
        }
    }
//    public void stoppingMenu(Player currentPlayer,Location currenLocation){
//        this.player = currentPlayer;
//        this.location = currenLocation;
//        System.out.println("----------- Ana Menü ----------");
//        System.out.println("1 - Oyuna devam et");
//        System.out.println("2 - Ayarlar");
//        System.out.println("3 - Zorluk");
//        System.out.println("4 - Diller");
//        System.out.println("5 - Çıkış");
//        System.out.println("-------------------------------");
//        System.out.print("Seçiminiz : ");
//        int choice = input.nextInt();
//
//        switch (choice){
//            case 1:
//                continueGame();
//                break;
//            case 2:
//                showSettings();
//                break;
//            case 3:
////                zorluklar();
//                break;
//            case 4:
//                //showLanguages();
//                break;
//
//            default:
//                System.out.println("Geçersiz seçim ! Lütfen geçerli bir seçenek seçiniz !");
//                break;
//        }
//    }
//    private void continueGame(){
//        System.out.println("Oyuna devam ediliyor...");
//        player.printInfo();
//        System.out.println();
//        System.out.println("-----------------");
//        location.onLocatin();
//
//    }
    private void showSettings(){
        System.out.println(" ------ Ayarlar -------");

        System.out.println("Ses: Açık");
        System.out.println("Grafik:Yüksek");
        System.out.println("-----------------------");
    }

    private void startGame(){
        Game game = new Game();
        game.start();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
