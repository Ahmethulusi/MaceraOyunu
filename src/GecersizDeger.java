public class GecersizDeger extends Location{
    private Player player;
    private String name;
    public GecersizDeger(Player player) {
        super(player, "Geçersiz değer");

    }

    @Override
    public boolean onLocatin() {
        return true;
    }
}
