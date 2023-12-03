import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class EliminarRegistros {

    // Método principal para la eliminación de registros
    public static void eliminarAlta(Session session) {
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\nSeleccione el tipo de eliminación:");
            System.out.println("1. Eliminar Usuario");
            System.out.println("2. Eliminar Asignatura");
            System.out.println("0. Volver al menú principal");
            System.out.print("Ingrese el número correspondiente: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    eliminarUsuario(session, scanner);
                    break;
                case 2:
                    eliminarAsignatura(session, scanner);
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal.");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 0);
    }

    // Método para eliminar un usuario
    private static void eliminarUsuario(Session session, Scanner scanner) {
        System.out.print("Ingrese el nombre del usuario que desea eliminar: ");
        String nombreUsuario = scanner.nextLine();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Buscar el usuario por nombre en la base de datos
            Usuario usuario = (Usuario) session.createQuery("FROM Usuario WHERE nombre = :nombre")
                    .setParameter("nombre", nombreUsuario)
                    .uniqueResult();

            if (usuario != null) {
                // Eliminar el usuario y confirmar la transacción
                session.delete(usuario);
                System.out.println("Usuario eliminado exitosamente.");
            } else {
                System.out.println("Usuario no encontrado. Verifique el nombre e intente nuevamente.");
            }

            transaction.commit();
        } catch (Exception e) {
            // Manejar excepciones y hacer rollback si es necesario
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para eliminar una asignatura
    private static void eliminarAsignatura(Session session, Scanner scanner) {
        System.out.print("Ingrese el nombre de la asignatura que desea eliminar: ");
        String nombreAsignatura = scanner.nextLine();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Buscar la asignatura por nombre en la base de datos
            Asignatura asignatura = (Asignatura) session.createQuery("FROM Asignatura WHERE nombre = :nombre")
                    .setParameter("nombre", nombreAsignatura)
                    .uniqueResult();

            if (asignatura != null) {
                // Eliminar la asignatura y confirmar la transacción
                session.delete(asignatura);
                System.out.println("Asignatura eliminada exitosamente.");
            } else {
                System.out.println("Asignatura no encontrada. Verifique el nombre e intente nuevamente.");
            }

            transaction.commit();
        } catch (Exception e) {
            // Manejar excepciones y hacer rollback si es necesario
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
