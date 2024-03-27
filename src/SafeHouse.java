
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SafeHouse extends NormalLoc {
    public SafeHouse(Player player) {
        super(player, "Güvenli Ev");
    }

    @Override
    public boolean onLocatin() {
        boolean flag = true;

            System.out.println("Kaç saat uyumak istiyorsunuz ?");
            int time = input.nextInt();
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(() -> {
                for (int i = 0; i < time; i++) {
                    try {
                        Thread.sleep(1000); // Her 1 saniyede bir canı 5'er artıracak
                        int newHealth = this.getPlayer().getHealth() + 5;
                        if (newHealth < 100) {
                            this.getPlayer().setHealth(newHealth);
                        } else if (newHealth==100) {
                            break;
                        } else {
                            this.getPlayer().setHealth(100);
                        }
                        System.out.println("Canınız: " + this.getPlayer().getHealth());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });

            try {
                executorService.awaitTermination(time+1, TimeUnit.SECONDS);
                executorService.shutdown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return true;
    }
}
