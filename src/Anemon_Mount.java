public class Anemon_Mount extends Location{
    public Anemon_Mount(Player player) {
        super(player, "Anemon Dağı");
    }
    //  Anemon dağının eteği olacak,iç kısmı,ve zirvesi olacak şekilde 3 bölümden oluşacak,
    // Etekte terk edilmiş bir kasaba olacak,burada dağ haydutu çıkacak,içindede bazı dağın koruyucusu olarak adlandırılan tehlikeli yaratıklar olacak
    // Zirve kısmında da esir tutulan bazı tutsaklar olacak , bunları kurtarmak için zindan bekçileriyle savaşmamız gerekiyor ama
    // bizim tercihimize kalmış olacak eğer kurtarısak o tutsaklardan bir tanesi güvenli kampa spawn olacak ve bize yeni bir yan görev verebilecek.
    @Override
    public boolean onLocatin() {
        Go_Mount(this.getPlayer());
        return true;
    }

    public boolean Go_Mount(Player player) {
        Location location = null;
        Quit quit = new Quit(player);
        while(true){
            System.out.println("1 - Dağ eteğindeki terkedilmiş kasabayı kontrol et .");
            if(this.getPlayer().isSide_task1()){
                System.out.println("2 - Dağın içine gir");
            }else{
                System.out.println("2 - Dağın içindeki yoldan devam et ");
            }
            System.out.println("3 - Dağın zirvesine doğru tırman.");
            System.out.println("4 - Geri dön");
            System.out.println("Seç : ");
            int selection = input.nextInt();
            switch (selection) {
                case 1:
                    location = new Mount_Foothills(player);
                    break;
                case 2:
                    location = new InsideOfTheMountain(player);
                    break;
                case 3:
                    location = new MountainTop(player);
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
