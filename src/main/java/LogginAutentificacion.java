public class LogginAutentificacion {

    public static boolean autenticarUsuario(String nombreUsuario, String contrasena) {
        // Autenticación básica para usuario "admin" y contraseña "admin"
        return "admin".equals(nombreUsuario) && "admin".equals(contrasena);
    }
}
