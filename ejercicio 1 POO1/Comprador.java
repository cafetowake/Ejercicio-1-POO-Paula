class Comprador {
    private String nombre;
    private int cantidadBoletosDeseados;
    private double presupuestoMaximo;

    public Comprador(String nombre, String email, int cantidadBoletosDeseados, double presupuestoMaximo) {
        this.nombre = nombre;
        this.cantidadBoletosDeseados = cantidadBoletosDeseados;
        this.presupuestoMaximo = presupuestoMaximo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadBoletosDeseados() {
        return cantidadBoletosDeseados;
    }

    public double getPresupuestoMaximo() {
        return presupuestoMaximo;
    }

    public void setCantidadBoletosDeseados(int cantidadBoletosDeseados) {
        this.cantidadBoletosDeseados = cantidadBoletosDeseados;
    }

    public void setPresupuestoMaximo(double presupuestoMaximo) {
        this.presupuestoMaximo = presupuestoMaximo;
    }
}
