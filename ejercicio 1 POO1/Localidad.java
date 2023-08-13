class Localidad {
    private String nombre;
    private int capacidadMaxima;
    private int boletosVendidos;
    private double precio;

    public Localidad(int localidadId, String nombre, int capacidadMaxima, double precio) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.precio = precio;
    }

    public boolean validarEspacio() {
        return boletosVendidos < capacidadMaxima;
    }

    public boolean validarDisponibilidad(int cantidad) {
        return boletosVendidos + cantidad <= capacidadMaxima;
    }

    public boolean validarPrecio(double presupuesto) {
        return precio <= presupuesto;
    }

    public void venderBoletos(int cantidad) {
        boletosVendidos += cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCapacidadMaxima() {
        return 0;
    }

    public double getBoletosVendidos() {
        return 0;
    }

    public int getLocalidadId() {
        return 0;
    }
}
