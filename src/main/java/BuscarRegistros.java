import org.hibernate.Session;
import java.util.List;
import java.util.Scanner;

public class BuscarRegistros {

    // Método principal para buscar registros en la base de datos
    public static void buscarRegistros(Session session) {
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.print("\nIngrese el término de búsqueda: ");
            String terminoBusqueda = scanner.nextLine();

            // Buscar usuarios y asignaturas que coincidan con el término de búsqueda
            List<Usuario> usuarios = buscarUsuarios(session, terminoBusqueda);
            List<Asignatura> asignaturas = buscarAsignaturas(session, terminoBusqueda);

            // Mostrar los resultados de la búsqueda
            mostrarResultados(usuarios, asignaturas);

            // Preguntar al usuario si quiere realizar otra búsqueda
            System.out.println("\n¿Quieres realizar otra búsqueda?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            System.out.print("Ingrese el número correspondiente: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    break; // Continuar con la siguiente iteración del bucle
                case 2:
                    System.out.println("Saliendo del menú de búsqueda.");
                    break;
                default:
                    System.out.println("Opción no válida. Saliendo del menú de búsqueda.");
                    scanner.close();
                    break;
            }
        } while (opcion == 1);
    }

    // Método para buscar usuarios que coincidan con el término de búsqueda
    private static List<Usuario> buscarUsuarios(Session session, String terminoBusqueda) {
        return session.createQuery("FROM Usuario WHERE nombre LIKE :termino OR email LIKE :termino", Usuario.class)
                .setParameter("termino", "%" + terminoBusqueda + "%")
                .list();
    }

    // Método para buscar asignaturas que coincidan con el término de búsqueda
    private static List<Asignatura> buscarAsignaturas(Session session, String terminoBusqueda) {
        return session.createQuery("FROM Asignatura WHERE nombre LIKE :termino OR codigo LIKE :termino", Asignatura.class)
                .setParameter("termino", "%" + terminoBusqueda + "%")
                .list();
    }

    // Método para mostrar los resultados de la búsqueda
    private static void mostrarResultados(List<Usuario> usuarios, List<Asignatura> asignaturas) {
        System.out.println("\nResultados de la búsqueda:");

        // Mostrar usuarios
        System.out.println("\nUsuarios:");
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId() + ", Nombre: " + usuario.getNombre() + ", Email: " + usuario.getEmail());
        }

        // Mostrar asignaturas
        System.out.println("\nAsignaturas:");
        for (Asignatura asignatura : asignaturas) {
            System.out.println("ID: " + asignatura.getId() + ", Nombre: " + asignatura.getNombre() + ", Código: " + asignatura.getCodigo());
        }
    }
}
