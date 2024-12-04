package Main;

import DAO.UsuarioDAOImpl;
import Modelos.Usuario;
import Service.UsuarioService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
        EntityManager em = emf.createEntityManager();

        UsuarioService usuarioServices = new UsuarioService(new UsuarioDAOImpl(em));

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n-------- Gestión de Usuarios --------------");
            System.out.println("1. Leer todos los usuarios");
            System.out.println("2. Insertar usuario");
            System.out.println("3. Actualizar usuario");
            System.out.println("4. Borrar usuario");
            System.out.println("5. Ordenar usuarios por nombre");
            System.out.println("6. Buscar usuario");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // Listar usuarios
                    List<Usuario> usuarios = usuarioServices.getUsuariosEnMemoria();
                    System.out.println("Usuarios:");
                    for (Usuario usuario : usuarios) {
                        System.out.println(usuario);
                        System.out.println("-----------------------------");
                    }
                    //equipos.forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("Introduzca el nombre del usuario:");
                    String nombre = scanner.nextLine();
                    System.out.println("Introduzca el DNI del usuario:");
                    String dni = scanner.nextLine();
                    System.out.println("Introduzca el correo del usuario:");
                    String correo = scanner.nextLine();
                    System.out.println("Introduzca la telefono del usuario:");
                    String tlf = scanner.nextLine();
                    System.out.println("Introduzca la fecha de nacimiento del usuario en formato yyyy-MM-dd:");
                    String fechaNacimiento = scanner.nextLine();
                    LocalDate fecha = LocalDate.parse(fechaNacimiento);
                    usuarioServices.insertarUsuario(new Usuario(null, nombre, dni, correo, tlf, fecha));
                    System.out.println("Usuario insertado exitosamente");
                    break;
                case 3:
                    System.out.print("ID del usuario a actualizar: ");
                    int idUsuario = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    Usuario usuarioActualizar = usuarioServices.getUsuariosEnMemoria().stream()
                            .filter(e -> e.getId().equals(idUsuario))
                            .findFirst()
                            .orElse(null);
                    if (usuarioActualizar != null) {
                        System.out.print("Nuevo nombre del usuario: ");
                        usuarioActualizar.setNombre(scanner.nextLine());
                        System.out.println("Nuevo DNI del usuario: ");
                        usuarioActualizar.setDni(scanner.nextLine());
                        System.out.println("Nuevo correo del usuario: ");
                        usuarioActualizar.setEmail(scanner.nextLine());
                        System.out.println("Nuevo telefono del usuario: ");
                        usuarioActualizar.setTelefono(scanner.nextLine());
                        System.out.println("Nuevo fecha del usuario en formato yyyy-MM-dd: ");
                        usuarioActualizar.setFechaNacimiento(LocalDate.parse(scanner.nextLine()));
                        usuarioServices.actualizararUsuario(usuarioActualizar);
                        System.out.println("Usuario actualizado.");
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("ID del usuario a borrar: ");
                    int idUsuarioBorrar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    usuarioServices.eliminarUsuario(idUsuarioBorrar);
                    break;
                case 5:
                    List<Usuario> equiposOrdenados = usuarioServices.getUsuariosEnMemoria();
                    equiposOrdenados.sort((e1, e2) -> e1.getNombre().compareTo(e2.getNombre()));
                    System.out.println("Equipos ordenados por nombre:");
                    equiposOrdenados.forEach(System.out::println);
                    break;
                case 6:
                    System.out.println("Itroduzca el nombre del usuario:");
                    String nom = scanner.nextLine();
                    System.out.println(usuarioServices.getUsuario(nom));    
                case 0:
                    System.out.println("Saliendo");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }while (opcion != 0);
        em.close();
        emf.close();
    }
}
