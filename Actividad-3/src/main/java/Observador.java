class Observador extends Thread {
    private DosContadoresV2 dosContadores;

    public Observador(DosContadoresV2 dosContadores) {
        this.dosContadores = dosContadores;
    }

    @Override
    public void run() {
        while (true) {
            dosContadores.testSincro();
            try {
                sleep(500); // Verifica la sincronización cada 500ms
            } catch (InterruptedException e) {
                System.out.println("Observador interrumpido");
                break;
            }
        }
    }
}
