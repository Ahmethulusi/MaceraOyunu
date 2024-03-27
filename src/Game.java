import java.util.Enumeration;
import java.util.Scanner;
public class Game {
    private Scanner input= new Scanner(System.in);
    public void start(){
        System.out.println("Lütfen bir isim giriniz:");
        String playername = input.next();
        Player player = new Player(playername);
        System.out.println("Oyuna Hoşgeldin "+playername+" !");
        System.out.println("What is Lorem Ipsum?\n" +
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,\nWhen an unknown printer took a galley of type and scrambled it to make a type specimen book.\nIt has survived not only five centuries, but also the leap into electronic typesetting,\nRemaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, " +
                "\nand more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum\n");
        System.out.println("Devam etmek için d ye bas");
        do{ // Eğer kullanıcı boşluk girmeden bir şeyler yazdıysa, tekrar giriş iste
            String giris = input.next().toUpperCase(); // Kullanıcının girişini al
            if(giris.equals("D"))break;
            System.out.println("Geçersiz giriş. Devam etmek için sadece d'ye basın.");
        }while(true);
        System.out.println("Devam ediliyor...");
        player.makeInit();
        Location location = null;
        Town town = new Town(player);
        while(true){
            System.out.println("----------------");
            player.printInfo();
            System.out.println("----------------");
            System.out.println("########### Bölgeler ###########");
            System.out.println("\n0- Çıkış yap --> Oyundan çıkarsanız bir daha en baştan başlarsınız ! ");
            System.out.println("\n1- Kıyı Kasabası -- !");
            System.out.println("\n2- Çuha Ormanı -- !");
            System.out.println("\n3- Anemon Dağı  -- !");
            System.out.println("\n4- Sınır  -- !");
            System.out.println("\n5 -Envanteri Görüntüle -- !");
            System.out.println("\nLütfen gitmek istediğiniz bölgeyi seçiniz : ");

            int selection = input.nextInt();

            // ASAGIDAKİ İFADELERDE POLİMORFİZM İLKESİ KULLANILIYOR.ORTAK BİR NESNEDEN ALTINDAKİ NESNELERİ OLUŞTURUYORUZ
            switch (selection){

                case 0:
                    System.out.println("Oyundan çıkış yapılıyor...");
                    System.exit(0);
                    break;
                case 1:
                    location = new Kiyi_kasabasi(player);
                    break;
                case 2:
                    location = new Cuha_Forest(player);
                    break;
                case 3:
                    location = new Anemon_Mount(player);
                    break;
//                case 4:
//                    location = new Border(player);
//                    break;
                case 5:
                    location = new GoInventory(player);
                    break;

                default:
                    location = new GecersizDeger(player);
                    System.out.println("Lütfen geçerli bir değer giriniz !");
                    break;
            }


            if(!location.onLocatin()) {
                System.out.println("GAME OVER");
                System.exit(0);
                break;
            }
        }

    }
}
