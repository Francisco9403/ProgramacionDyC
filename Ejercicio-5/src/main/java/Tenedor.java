class Tenedor {
    private boolean enUso = false;

    public synchronized void tomar() throws InterruptedException {
        while (enUso) {
            wait(); // Espera si el tenedor está en uso
        }
        enUso = true;
    }

    public synchronized void soltar() {
        enUso = false;
        notifyAll(); // Notifica a otros filósofos que el tenedor está disponible
    }
}