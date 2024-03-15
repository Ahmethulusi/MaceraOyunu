public class Quit extends Location {
    public Quit(Player player) {
        super(player, "Çıkış");
    }

    @Override
    public boolean onLocatin() {
        return true;
    }
}
