public class SafeHouse extends NormalLoc {
    public SafeHouse(Player player) {
        super(player, "Güvenli Ev");
    }
    @Override
    public boolean onLocatin() {
        boolean ctrl_variable = true;
        do {
            this.getPlayer().setHealth(100);
            System.out.println("Güvenli evdesiniz");
            System.out.println("Canınız : "+this.getPlayer().getHealth());
            System.out.println("Çıkmak için Q'ya basınız.");
            String selection = input.next().toUpperCase();
            if (selection.equals("Q")) {
                return true;
            }
            else {
                System.out.println("Geçersiz değer !");
                continue;
            }
        } while (ctrl_variable);
        return true;
    }
}
