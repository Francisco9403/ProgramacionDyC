import java.util.*;

class Cochera2 {
    private final int capacidadTotal;
    private int espaciosDisponibles;
    private final List<Vehiculo> vehiculosEstacionados;
    private final Queue<Thread> colaCamiones;
    private final Queue<Thread> colaAutos;

    public Cochera2(int capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
        this.espaciosDisponibles = capacidadTotal;
        this.vehiculosEstacionados = new ArrayList<>();
        this.colaCamiones = new LinkedList<>();
        this.colaAutos = new LinkedList<>();
    }

    public synchronized boolean ingresarVehiculo(Vehiculo vehiculo) {
        Thread currentThread = Thread.currentThread();
        boolean esCamion = vehiculo instanceof Camion;

        if (esCamion) {
            colaCamiones.add(currentThread);
        } else {
            colaAutos.add(currentThread);
        }

        while (true) {
            boolean hayEspacio = espaciosDisponibles >= vehiculo.getEspaciosNecesarios();

            if (hayEspacio) {
                if (esCamion) {
                    // El camión puede ingresar solo si no hay autos esperando
                    if (colaCamiones.peek() == currentThread && colaAutos.isEmpty()) {
                        colaCamiones.poll(); // Sale de la cola
                        break;
                    }
                } else {
                    if (colaAutos.peek() == currentThread) {
                        colaAutos.poll(); // Sale de la cola
                        break;
                    }
                }
            }

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
            notifyAll(); // Notificamos a todos para que se evalúe la prioridad
        }
    }
}
