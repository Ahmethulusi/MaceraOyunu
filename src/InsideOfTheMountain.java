public class InsideOfTheMountain extends Location{

    public InsideOfTheMountain(Player player) {
        super(player,"Dağın İçi");
    }

    @Override
    public boolean onLocatin() {
        Side_Task_Choose(this.getPlayer());
        return true;
    }

    public boolean Side_Task_Choose(Player player) {
        Location location = null;
        Quit quit = new Quit(player);
        while(true){
            if(getPlayer().isSide_task1()){
                System.out.println("Biraz ilerledin ve 2 farklı yol çıktı birinde kullanılmayan eski bir maden keşfettin,bakmak ister misin ?(E) ");
                System.out.println("Diğer yoldan  devam etmek için (D) ye bas");
            }else{
                System.out.println("Maden temizlendi ,mahkumlar kurtarılmış yoluna devam etmek için (D) ye bas.");
            }
            String selection = input.next().toUpperCase();
            if(selection.equals("E")){
                location = new Mine(player);
            }else{
                location = new Monster_Nest(player);
            }

            if(!location.onLocatin()) {
                System.out.println("GAME OVER");
                System.out.println("Lütfen geçerli bir değer giriniz !");
            }
            break;
        }
        return  true;
    }
}
