import java.util.Random;

public class Ticket {
    private int numero;
    private int a;
    private int b;

    public Ticket() {
        Random random = new Random();
        this.numero = random.nextInt(15000) + 1;
        this.a = random.nextInt(15000) + 1;
        this.b = random.nextInt(15000) + 1;
    }

    public int getNumero() {
        return numero;
    }

    public boolean validar(Comprador comprador, Localidad localidad) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);

        return (min <= comprador.getCantidadBoletosDeseados()) &&
               localidad.validarDisponibilidad(comprador.getCantidadBoletosDeseados()) &&
               (comprador.getPresupuestoMaximo() >= max * localidad.getPrecio());
    }
}
