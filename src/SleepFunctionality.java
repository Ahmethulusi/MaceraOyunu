public class SleepFunctionality {

    private static final int MAX_HEALTH = 100;
    private static final int SLEEP_DURATION_SECONDS = 10;
    private static final int HEALTH_PER_SECOND = 5;
    private static final int MAX_HEALTH_INCREASE = MAX_HEALTH * 30 / 100;

    private static int health = 0;
    private static boolean isSleeping = false;

    public static void main(String[] args) {
        startSleep();
    }

    public static void startSleep() {
        isSleeping = true;
        health = 0;

        Thread sleepThread = new Thread(() -> {
            try {
                for (int i = 0; i < SLEEP_DURATION_SECONDS; i++) {
                    Thread.sleep(1000); // 1 saniye uyuma
                    if (isSleeping) {
                        health += HEALTH_PER_SECOND;
                        System.out.println("Can artışı: " + health);
                        if (health >= MAX_HEALTH_INCREASE) {
                            health = MAX_HEALTH_INCREASE; // Maksimum sağlık sınırını aşmayacak şekilde ayarla
                            System.out.println("Maksimum can ulaşıldı: " + health);
                            break;
                        }
                    } else {
                        System.out.println("Uyku kesildi.");
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                isSleeping = false;
            }
        });

        sleepThread.start();

        try {
            sleepThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
