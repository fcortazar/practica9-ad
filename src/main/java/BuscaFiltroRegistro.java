import org.hibernate.Session;
import java.util.List;
import java.util.Scanner;

public class BuscaFiltroRegistro {

    // Método principal para buscar con filtros
    public static void buscarConFiltro(Session session) {
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\nSeleccione el tipo de filtro:");
            System.out.println("1. Filtrar por nombre");
            System.out.println("2. Filtrar por código");
            System.out.println("0. Volver al menú principal");
            System.out.print("Ingrese el número correspondiente: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    buscarPorNombre(session, scanner);
                    break;
                case 2:
                    buscarPorCodigo(session, scanner);
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal.");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 0);
    }

    // Método para buscar por nombre
    private static void buscarPorNombre(Session session, Scanner scanner) {
        System.out.print("\nIngrese el nombre para el filtro: ");
        String nombreFiltro = scanner.nextLine();

        // Consultar usuarios y asignaturas que coinciden con el filtro de nombre
        List<Usuario> usuarios = session.createQuery("FROM Usuario WHERE nombre LIKE :nombre", Usuario.class)
                .setParameter("nombre", "%" + nombreFiltro + "%")
                .list();

        List<Asignatura> asignaturas = session.createQuery("FROM Asignatura WHERE nombre LIKE :nombre", Asignatura.class)
                .setParameter("nombre", "%" + nombreFiltro + "%")
                .list();

        // Mostrar los resultados
        mostrarResultados(usuarios, asignaturas);
    }

    // Método para buscar por código
    private static void buscarPorCodigo(Session session, Scanner scanner) {
        System.out.print("\nIngrese el código para el filtro: ");
        String codigoFiltro = scanner.nextLine();

        // Consultar asignaturas que coinciden con el filtro de código
        List<Asignatura> asignaturas = session.createQuery("FROM Asignatura WHERE codigo LIKE :codigo", Asignatura.class)
                .setParameter("codigo", "%" + codigoFiltro + "%")
                .list();

        // Mostrar los resultados
        mostrarResultados(null, asignaturas);
    }

    // Método para mostrar los resultados de la búsqueda
    private static void mostrarResultados(List<Usuario> usuarios, List<Asignatura> asignaturas) {
        System.out.println("\nResultados de la búsqueda:");

        // Mostrar usuarios si la lista no es nula
        if (usuarios != null) {
            System.out.println("\nUsuarios:");
            for (Usuario usuario : usuarios) {
                System.out.println("ID: " + usuario.getId() + ", Nombre: " + usuario.getNombre() + ", Email: " + usuario.getEmail());
            }
        }

        // Mostrar asignaturas si la lista no es nula
        if (asignaturas != null) {
            System.out.println("\nAsignaturas:");
            for (Asignatura asignatura : asignaturas) {
                System.out.println("ID: " + asignatura.getId() + ", Nombre: " + asignatura.getNombre() + ", Código: " + asignatura.getCodigo());
            }
        }
    }
}
