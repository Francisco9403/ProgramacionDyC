import java.util.ArrayList;
import java.util.List;

class Cochera {
    private final int capacidadTotal;
    private int espaciosDisponibles;
    private final List<Vehiculo> vehiculosEstacionados;

    public Cochera(int capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
        this.espaciosDisponibles = capacidadTotal;
        this.vehiculosEstacionados = new ArrayList<>();
    }

    public synchronized boolean ingresarVehiculo(Vehiculo vehiculo) {
        while (espaciosDisponibles < vehiculo.getEspaciosNecesarios()) {
            try {
                System.out.println(vehiculo.getPatente() + " esperando un lugar...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        vehiculosEstacionados.add(vehiculo);
        espaciosDisponibles -= vehiculo.getEspaciosNecesarios();
        System.out.println(vehiculo.getPatente() + " ingresó a la cochera. Espacios disponibles: " + espaciosDisponibles);
        return true;
    }

    public synchronized void salirVehiculo(Vehiculo vehiculo) {
        if (vehiculosEstacionados.remove(vehiculo)) {
            espaciosDisponibles += vehiculo.getEspaciosNecesarios();
            System.out.println(vehiculo.getPatente() + " salió de la cochera. Espacios disponibles: " + espaciosDisponibles);
            notifyAll();
        }
    }
}
