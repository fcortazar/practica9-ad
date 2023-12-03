import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class ModificarRegistro {

    // Método principal para modificar registros
    public static void modificarRegistro(Session session, Scanner scanner) {
        int opcion;
        do {
            System.out.println("\nSeleccione qué registro desea modificar:");
            System.out.println("1. Modificar Usuario");
            System.out.println("2. Modificar Asignatura");
            System.out.println("0. Volver al menú principal");
            System.out.print("Ingrese el número correspondiente: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    modificarUsuario(session, scanner);
                    break;
                case 2:
                    modificarAsignatura(session, scanner);
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal.");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 0);
    }

    // Método para modificar un usuario
    private static void modificarUsuario(Session session, Scanner scanner) {
        System.out.print("Ingrese el nombre del usuario que desea modificar: ");
        String nombreUsuario = scanner.nextLine();

        // Consultar el usuario por nombre
        Usuario usuario = (Usuario) session.createQuery("FROM Usuario WHERE nombre = :nombre")
                .setParameter("nombre", nombreUsuario)
                .uniqueResult();

        if (usuario != null) {
            System.out.println("Usuario encontrado. Seleccione qué desea modificar:");
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar email");
            System.out.println("0. Volver");
            System.out.print("Ingrese el número correspondiente: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt()

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    usuario.setNombre(nuevoNombre);
                    break;
                case 2:
                    System.out.print("Ingrese el nuevo email: ");
                    String nuevoEmail = scanner.nextLine();
                    usuario.setEmail(nuevoEmail);
                    break;
                case 0:
                    System.out.println("Volviendo al menú anterior.");
                    return;
                default:
                    System.out.println("Opción no válida. No se realizaron modificaciones.");
                    return;
            }

            // Guardar los cambios en la base de datos
            Transaction transaction = session.beginTransaction();
            session.update(usuario);
            transaction.commit();

            System.out.println("Usuario modificado exitosamente.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    // Método para modificar una asignatura
    private static void modificarAsignatura(Session session, Scanner scanner) {
        System.out.print("Ingrese el nombre de la asignatura que desea modificar: ");
        String nombreAsignatura = scanner.nextLine();

        // Consultar la asignatura por nombre
        Asignatura asignatura = (Asignatura) session.createQuery("FROM Asignatura WHERE nombre = :nombre")
                .setParameter("nombre", nombreAsignatura)
                .uniqueResult();

        if (asignatura != null) {
            System.out.println("Asignatura encontrada. Seleccione qué desea modificar:");
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar código");
            System.out.println("0. Volver");
            System.out.print("Ingrese el número correspondiente: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    asignatura.setNombre(nuevoNombre);
                    break;
                case 2:
                    System.out.print("Ingrese el nuevo código: ");
                    String nuevoCodigo = scanner.nextLine();
                    asignatura.setCodigo(nuevoCodigo);
                    break;
                case 0:
                    System.out.println("Volviendo al menú anterior.");
                    return;
                default:
                    System.out.println("Opción no válida. No se realizaron modificaciones.");
                    return;
            }

            // Guardar los cambios en la base de datos
            Transaction transaction = session.beginTransaction();
            session.update(asignatura);
            transaction.commit();

            System.out.println("Asignatura modificada exitosamente.");
        } else {
            System.out.println("Asignatura no encontrada.");
        }
    }
}
