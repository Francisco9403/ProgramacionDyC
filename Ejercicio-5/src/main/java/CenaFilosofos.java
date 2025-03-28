public class CenaFilosofos {
    public static void main(String[] args) {
        int numFilosofos = 5;
        Tenedor[] tenedores = new Tenedor[numFilosofos];
        Filosofo[] filosofos = new Filosofo[numFilosofos];

        for (int i = 0; i < numFilosofos; i++) {
            tenedores[i] = new Tenedor();
        }

        for (int i = 0; i < numFilosofos; i++) {
            filosofos[i] = new Filosofo(i, tenedores[i], tenedores[(i + 1) % numFilosofos]);
            filosofos[i].start();
        }
    }
}