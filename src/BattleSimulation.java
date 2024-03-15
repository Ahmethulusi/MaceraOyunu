public class BattleSimulation {
    public void startSimulation(){
        // Savaş başlamadan önce mesaj yazdırma
        System.out.println("Savaş başladı!");

        // Savaşı simüle etmek için Thread kullanımı
        Thread battleThread = new Thread(new Runnable() {
            @Override
            public void run() {
                simulateBattle(); // Savaş simülasyonunu başlat
            }
        });
        battleThread.start(); // Savaş simülasyonunu başlat

        // Ana thread, savaş simülasyonunun tamamlanmasını bekler
        try {
            battleThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Savaş sonucunu yazdırma
        System.out.println("Savaş sona erdi ! ");


    }

    public static void simulateBattle() {
        try {
            // Savaşçılar arasındaki mücadeleyi simüle et
            System.out.println("Savaş devam ediyor...");
            Thread.sleep(1500); // 3 saniye beklet (savaş simülasyonu)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
