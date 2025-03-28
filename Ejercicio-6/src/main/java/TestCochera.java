import java.util.Random;

class TestCochera {
    public static void main(String[] args) {
        final Cochera cochera = new Cochera(5);

        for (int i = 0; i < 10; i++) {
            final int index = i;
            new Thread(() -> {
                Random random = new Random();
                Vehiculo vehiculo;
                if (random.nextBoolean()) {
                    vehiculo = new Auto("Auto-" + index);
                } else {
                    vehiculo = new Camion("Camion-" + index);
                }

                if (cochera.ingresarVehiculo(vehiculo)) {
                    try {
                        Thread.sleep(random.nextInt(5000) + 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cochera.salirVehiculo(vehiculo);
                }
            }).start();
        }
    }
}
