import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class MostrarRegistros {

    // Método principal para mostrar registros
    public static void mostrarRegistros(Session session) {
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\nSelecciona qué registros deseas mostrar:");
            System.out.println("1. Mostrar Usuarios");
            System.out.println("2. Mostrar Asignaturas");
            System.out.println("0. Volver al menú principal");
            System.out.print("Ingresa el número correspondiente: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mostrarUsuarios(session);
                    break;
                case 2:
                    mostrarAsignaturas(session);
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // Método para mostrar usuarios
    private static void mostrarUsuarios(Session session) {
        // Consultar todos los usuarios
        List<Usuario> usuarios = session.createQuery("FROM Usuario", Usuario.class).list();

        System.out.println("\nRegistros de Usuarios:");
        for (Usuario usuario : usuarios) {
            if (usuario != null) {
                // Mostrar información del usuario
                System.out.println("ID: " + usuario.getId() + ", Nombre: " + usuario.getNombre() + ", Email: " + usuario.getEmail());
            }
        }
    }

    // Método para mostrar asignaturas
    private static void mostrarAsignaturas(Session session) {
        // Consultar todas las asignaturas
        List<Asignatura> asignaturas = session.createQuery("FROM Asignatura", Asignatura.class).list();

        System.out.println("\nRegistros de Asignaturas:");
        for (Asignatura asignatura : asignaturas) {
            // Mostrar información de la asignatura
            System.out.println("ID: " + asignatura.getId() + ", Nombre: " + asignatura.getNombre() + ", Código: " + asignatura.getCodigo());
        }
    }
}