public class GecersizDeger extends Location{
    private Player player;
    private String name;
    public GecersizDeger(Player player) {
        super(player, "Geçersiz değer");

    }

    @Override
    public boolean onLocatin() {
        System.out.println("Lütfen geçerli bir değer giriniz.");
        return true;
    }
}
