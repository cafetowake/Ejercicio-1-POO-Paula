import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Comprador> compradores = new ArrayList<>();
            List<Localidad> localidades = new ArrayList<>();
            localidades.add(new Localidad(1, "Localidad 1", 20, 100));
            localidades.add(new Localidad(5, "Localidad 5", 20, 500));
            localidades.add(new Localidad(10, "Localidad 10", 20, 1000));

            while (true) {
                System.out.println("\nMenú de opciones:");
                System.out.println("1. Nuevo comprador");
                System.out.println("2. Nueva solicitud de boletos");
                System.out.println("3. Consultar disponibilidad total");
                System.out.println("4. Consultar disponibilidad individual");
                System.out.println("5. Reporte de caja");
                System.out.println("6. Salir");
                System.out.print("Ingrese la opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el nombre del comprador: ");
                        scanner.nextLine(); // Limpiar el buffer
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese el correo electrónico: ");
                        String email = scanner.nextLine();
                        System.out.print("Ingrese la cantidad de boletos deseados: ");
                        int cantidadBoletos = scanner.nextInt();
                        System.out.print("Ingrese el presupuesto máximo: ");
                        double presupuesto = scanner.nextDouble();

                        compradores.add(new Comprador(nombre, email, cantidadBoletos, presupuesto));
                        System.out.println("¡Comprador registrado!");
                        break;

                    case 2:
                        if (compradores.isEmpty()) {
                            System.out.println("No hay compradores registrados. Registre un comprador primero.");
                            break;
                        }

                        System.out.println("Seleccione un comprador:");
                        for (int i = 0; i < compradores.size(); i++) {
                            System.out.println((i + 1) + ". " + compradores.get(i).getNombre());
                        }
                        int seleccion = scanner.nextInt() - 1;
                        if (seleccion < 0 || seleccion >= compradores.size()) {
                            System.out.println("Selección inválida.");
                            break;
                        }
                        Comprador comprador = compradores.get(seleccion);

                        Ticket ticket = new Ticket();
                        System.out.println("Número de ticket generado: " + ticket.getNumero());
                        System.out.println("Verificación del ticket...");
                        Localidad localidad = null;
                        for (Localidad loc : localidades) {
                            if (ticket.validar(comprador, loc)) {
                                localidad = loc;
                                break;
                            }
                        }
                        if (localidad == null) {
                            System.out.println("El ticket no es válido para ninguna localidad.");
                        } else {
                            System.out.println("El ticket es válido para la localidad: " + localidad.getNombre());
                            System.out.print("Ingrese la cantidad de boletos a comprar: ");
                            int cantidadBoletosCompra = scanner.nextInt();
                            if (!localidad.validarDisponibilidad(cantidadBoletosCompra)) {
                                System.out.println("No hay suficientes boletos disponibles en la localidad.");
                            } else {
                                double costoTotal = cantidadBoletosCompra * localidad.getPrecio();
                                if (costoTotal > comprador.getPresupuestoMaximo()) {
                                    System.out.println("El costo total excede el presupuesto del comprador.");
                                } else {
                                    localidad.venderBoletos(cantidadBoletosCompra);
                                    comprador.setCantidadBoletosDeseados(comprador.getCantidadBoletosDeseados() - cantidadBoletosCompra);
                                    comprador.setPresupuestoMaximo(comprador.getPresupuestoMaximo() - costoTotal);
                                    System.out.println("¡Compra realizada con éxito!");
                                }
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Disponibilidad total:");
                        for (Localidad loc : localidades) {
                            System.out.println(loc.getNombre() + ": Boletos vendidos: " + loc.getBoletosVendidos() +
                                    ", Boletos disponibles: " + (loc.getCapacidadMaxima() - loc.getBoletosVendidos()));
                        }
                        break;

                    case 4:
                        System.out.print("Ingrese el ID de la localidad: ");
                        int idLocalidad = scanner.nextInt();
                        boolean localidadEncontrada = false;
                        for (Localidad loc : localidades) {
                            if (loc.getLocalidadId() == idLocalidad) {
                                System.out.println("Disponibilidad para la localidad " + loc.getNombre() +
                                        ": Boletos vendidos: " + loc.getBoletosVendidos() +
                                        ", Boletos disponibles: " + (loc.getCapacidadMaxima() - loc.getBoletosVendidos()));
                                localidadEncontrada = true;
                                break;
                            }
                        }
                        if (!localidadEncontrada) {
                            System.out.println("Localidad no encontrada.");
                        }
                        break;

                    case 5:
                        double totalGenerado = 0;
                        for (Localidad loc : localidades) {
                            totalGenerado += loc.getBoletosVendidos() * loc.getPrecio();
                        }
                        System.out.println("Reporte de caja: Total generado: $" + totalGenerado);
                        break;

                    case 6:
                        System.out.println("¡Hasta luego!");
                        return;

                    default:
                        System.out.println("Opción inválida.");
                }
            }
        }
    }
}
