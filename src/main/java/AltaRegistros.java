import org.hibernate.Session;

import java.util.Scanner;

public class AltaRegistros {

    // Método para dar de alta usuarios y asignaturas
    public static void darDeAlta(Session session) {
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            // Menú para seleccionar el tipo de alta
            System.out.println("\nSeleccione el tipo de alta:");
            System.out.println("1. Usuario");
            System.out.println("2. Asignatura");
            System.out.println("0. Volver al menú principal");
            System.out.print("Ingrese el número correspondiente: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    darDeAltaUsuario(session, scanner);
                    break;
                case 2:
                    darDeAltaAsignatura(session, scanner);
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal.");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 0);
    }

    // Método privado para dar de alta un usuario
    private static void darDeAltaUsuario(Session session, Scanner scanner) {
        System.out.print("Ingrese el nombre del usuario: ");
        String nombreUsuario = scanner.nextLine();

        System.out.print("Ingrese el email del usuario: ");
        String emailUsuario = scanner.nextLine();

        // Crear el objeto Usuario y guardarlo en la base de datos
        Usuario nuevoUsuario = new Usuario(nombreUsuario, emailUsuario);
        session.save(nuevoUsuario);

        System.out.println("Usuario dado de alta exitosamente.");
    }

    // Método privado para dar de alta una asignatura
    private static void darDeAltaAsignatura(Session session, Scanner scanner) {
        System.out.print("Ingrese el nombre de la asignatura: ");
        String nombreAsignatura = scanner.nextLine();

        System.out.print("Ingrese el código de la asignatura: ");
        String codigoAsignatura = scanner.nextLine();

        // Crear el objeto Asignatura y guardarlo en la base de datos
        Asignatura nuevaAsignatura = new Asignatura(nombreAsignatura, codigoAsignatura);
        session.save(nuevaAsignatura);

        System.out.println("Asignatura dada de alta exitosamente.");
    }
}
