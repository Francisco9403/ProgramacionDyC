class Filosofo extends Thread {
    private int id;
    private Tenedor izquierdo, derecho;

    public Filosofo(int id, Tenedor izquierdo, Tenedor derecho) {
        this.id = id;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando...");
        Thread.sleep((int) (Math.random() * 1000)); // Simula el tiempo de pensamiento
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " está intentando tomar los tenedores...");

        izquierdo.tomar();
        derecho.tomar();

        System.out.println("Filósofo " + id + " ya tiene ambos tenerdores y está comiendo...");
        Thread.sleep((int) (Math.random() * 1000)); // Simula el tiempo de comida

        izquierdo.soltar();
        derecho.soltar();

        System.out.println("Filósofo " + id + " terminó de comer y soltó los tenedores.");
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                comer();
            }
        } catch (InterruptedException e) {
            System.out.println("Filósofo " + id + " interrumpido.");
        }
    }
}

