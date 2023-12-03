import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Configuración de Hibernate
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        Scanner scanner = new Scanner(System.in);

        try {
            transaction = session.beginTransaction();

            // Variable para controlar la autenticación del usuario
            boolean autenticacionExitosa = false;

            // Intentar autenticar al usuario
            do {
                // Solicitar nombre de usuario y contraseña al usuario
                System.out.print("\nIngrese el nombre de usuario: ");
                String nombreUsuario = scanner.nextLine();

                System.out.print("Ingrese la contraseña: ");
                String contrasena = scanner.nextLine();

                // Llamar al método de autenticación y asignar el resultado
                autenticacionExitosa = LogginAutentificacion.autenticarUsuario(nombreUsuario, contrasena);

                // Mostrar mensaje en caso de autenticación fallida
                if (!autenticacionExitosa) {
                    System.out.println("\nAutenticación fallida. Usuario o contraseña incorrectos. Inténtelo de nuevo.");
                }
            } while (!autenticacionExitosa);

            // Mensaje de bienvenida después de una autenticación exitosa
            System.out.println("Autenticación exitosa. Bienvenido.");

            // Menú de opciones
            int opcion;
            do {
                System.out.println("\nMenu:");
                System.out.println("1. Dar de alta");
                System.out.println("2. Modificar un alta");
                System.out.println("3. Eliminar un alta");
                System.out.println("4. Mostrar registros");
                System.out.println("5. Buscar");
                System.out.println("6. Buscar con filtro");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();

                // Switch para manejar las opciones del menú
                switch (opcion) {
                    case 1:
                        AltaRegistros.darDeAlta(session);
                        break;
                    case 2:
                        if (transaction != null && transaction.isActive()) {
                            transaction.commit();
                        }
                        ModificarRegistro.modificarRegistro(session, scanner);
                        transaction = session.beginTransaction(); // Iniciar una nueva transacción
                        break;
                    case 3:
                        if (transaction != null && transaction.isActive()) {
                            transaction.commit();
                        }
                        EliminarRegistros.eliminarAlta(session);
                        transaction = session.beginTransaction();
                        break;
                    case 4:
                        MostrarRegistros.mostrarRegistros(session);
                        break;
                    case 5:
                        BuscarRegistros.buscarRegistros(session);
                        break;
                    case 6:
                        BuscaFiltroRegistro.buscarConFiltro(session);
                        break;
                    case 0:
                        System.out.println("Saliendo del programa. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtelo de nuevo.");
                        break;
                }
            } while (opcion != 0);

            // Confirmar transacción al finalizar
            transaction.commit();
        } catch (Exception e) {
            // En caso de error, hacer rollback y mostrar la traza del error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar sesión y factory de sesiones de Hibernate
            session.close();
            sessionFactory.close();
        }
    }
}