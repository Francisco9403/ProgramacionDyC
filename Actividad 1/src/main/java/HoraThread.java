public class HoraThread extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("Hora actual: " + java.time.LocalTime.now());
            try {
                Thread.sleep(10000); // Espera 10 segundos
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido");
                break;
            }
        }
    }

    public static void main(String[] args) {
        HoraThread hilo = new HoraThread();
        hilo.start();
    }
}
