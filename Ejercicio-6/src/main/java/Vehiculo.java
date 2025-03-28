abstract class Vehiculo {
    protected String patente;
    protected int espaciosNecesarios;

    public Vehiculo(String patente, int espacios) {
        this.patente = patente;
        this.espaciosNecesarios = espacios;
    }

    public String getPatente() {
        return patente;
    }

    public int getEspaciosNecesarios() {
        return espaciosNecesarios;
    }
}
