package Paquete;

import Paquete.Clases.Ejemplar;
import Paquete.Clases.Libro;
import Paquete.Clases.Prestamo;
import Paquete.Clases.Usuario;
import Paquete.DAOS.DAOEjemplar;
import Paquete.DAOS.DAOLibro;
import Paquete.DAOS.DAOPrestamo;
import Paquete.DAOS.DAOUsuario;
import Paquete.Service.ServiceEjemplar;
import Paquete.Service.ServiceLibro;
import Paquete.Service.ServicePrestamo;
import Paquete.Service.ServiceUsuario;
import jakarta.persistence.NoResultException;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        Scanner t = new Scanner(System.in);
        boolean exit = false;
        List<Usuario> listaUsuarios = new DAOUsuario().readAllUsuarios();
        List<Ejemplar> listaEjemplares = new DAOEjemplar().readAllEjemplares();
        List<Prestamo> listaPrestamos = new DAOPrestamo().readAllPrestamos();
        List<Libro> listaLibros = new DAOLibro().readAllLibros();

        DAOUsuario daoUsuario = new DAOUsuario();
        DAOEjemplar daoEjemplar = new DAOEjemplar();
        DAOPrestamo daoPrestamo = new DAOPrestamo();
        DAOLibro daoLibro = new DAOLibro();

        while (!exit) {
            System.out.println("Que tipo de usuario es?");
            System.out.println("1. Usuario Administrador");
            System.out.println("2. Usuario Normal");
            System.out.println("3. Salir");
            int option = t.nextInt();
            t.nextLine();
            switch (option) {
                //PARA USUARIO ADMIN
                case 1:
                    System.out.println("Introduce Id para ingresar como Administrador");
                    int idAdministrador = t.nextInt();
                    t.nextLine();
                    try {
                        Usuario usuarioAdmin = daoUsuario.readUsuarioPorID(idAdministrador);
                        if (usuarioAdmin.getTipo() == Usuario.TipoUsuario.administrador) {
                            boolean administradorActivo = true;
                            while (administradorActivo) {
                                System.out.println("Que desea gestionar?");
                                System.out.println("1. Gestionar usuarios");
                                System.out.println("2. Gestionar libros");
                                System.out.println("3. Gestionar ejemplares");
                                System.out.println("4. Gestionar prestamos");
                                System.out.println("5. Salir");
                                int option3 = t.nextInt();
                                t.nextLine();
                                switch (option3) {
                                    case 1:
                                        boolean gestionUsuario = true;
                                        while (gestionUsuario) {
                                            System.out.println("Que desea hacer?");
                                            System.out.println("1. Ingresar usuario");
                                            System.out.println("2. Buscar usuario por Id");
                                            System.out.println("3. Actualizar usuario");
                                            System.out.println("4. Eliminar usuario");
                                            System.out.println("5. Salir");
                                            int opcionUsuario = t.nextInt();
                                            t.nextLine();
                                            switch (opcionUsuario) {
                                                case 1:
                                                    System.out.println("Introduce el DNI del usuario");
                                                    String dni = t.nextLine();
                                                    System.out.println("Introduce el nombre del usuario");
                                                    String nombre = t.nextLine();
                                                    System.out.println("Introduce el correo electónico");
                                                    String correo = t.nextLine();
                                                    System.out.println("Introduce la contraseña del usuario");
                                                    String password = t.nextLine();
                                                    System.out.println("Introduce el tipo de usuario (1.Administrador, 2.Normal)");
                                                    int tipo = t.nextInt();
                                                    t.nextLine();

                                                    Usuario.TipoUsuario tipoUsuario = (tipo == 1) ? Usuario.TipoUsuario.administrador : Usuario.TipoUsuario.normal;
                                                    Usuario nuevoUsuario = new Usuario(dni, nombre, correo, password, tipoUsuario, null);
                                                    ServiceUsuario usuarioService = new ServiceUsuario(daoUsuario);
                                                    usuarioService.insertUsuarioMemoria(nuevoUsuario);

                                                    System.out.println("Usuario ingresado exitosamente.");
                                                    break;
                                                case 2:
                                                    System.out.println("Introduce el ID del usuario a buscar:");
                                                    int buscarId = t.nextInt();
                                                    t.nextLine();
                                                    Usuario usuarioBuscado = daoUsuario.readUsuarioPorID(buscarId);
                                                    System.out.println(usuarioBuscado);
                                                    break;
                                                case 3:
                                                    System.out.println("Introduce el ID del usuario a actualizar:");
                                                    int actualizarId = t.nextInt();
                                                    t.nextLine();
                                                    Usuario usuarioActualizar = daoUsuario.readUsuarioPorID(actualizarId);

                                                    if (usuarioActualizar != null) {
                                                        System.out.println("Introduce el nuevo nombre del usuario:");
                                                        String nuevoNombre = t.nextLine();
                                                        usuarioActualizar.setNombre(nuevoNombre);
                                                        System.out.println("Introduce el nuevo correo del usuario:");
                                                        String nuevoCorreo = t.nextLine();
                                                        usuarioActualizar.setEmail(nuevoCorreo);
                                                        System.out.println("Introduce la nueva contraseña del usuario:");
                                                        String nuevaPassword = t.nextLine();
                                                        usuarioActualizar.setPassword(nuevaPassword);
                                                        System.out.println("Introduce el nuevo tipo de usuario (1. Administrador, 2. Normal:");
                                                        String nuevoTipo = t.nextLine();
                                                        Usuario.TipoUsuario nuevoTipoUsuario = (nuevoTipo.equals("1")) ? Usuario.TipoUsuario.administrador : Usuario.TipoUsuario.normal;
                                                        usuarioActualizar.setTipo(nuevoTipoUsuario);
                                                        ServiceUsuario usuarioServiceActualizar = new ServiceUsuario(daoUsuario);
                                                        usuarioServiceActualizar.updateUsuarioMemoria(usuarioActualizar);
                                                        System.out.println("Usuario actualizado exitosamente.");
                                                    }
                                                    break;
                                                case 4:
                                                    System.out.println("Introduce el id del usuario a eliminar:");
                                                    int id = t.nextInt();
                                                    t.nextLine();
                                                    Usuario usuarioEliminar = daoUsuario.readUsuarioPorID(id);
                                                    if (usuarioEliminar != null) {
                                                        ServiceUsuario usuarioServiceEliminar = new ServiceUsuario(daoUsuario);
                                                        usuarioServiceEliminar.deleteUsuarioMemoria(id);
                                                        System.out.println("Usuario eliminado exitosamente.");
                                                    }
                                                    break;
                                                case 5:
                                                    gestionUsuario = false;
                                                    break;
                                                default:
                                                    System.out.println("Opcion no valida");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        boolean gestionLibro = true;
                                        while (gestionLibro) {
                                            System.out.println("Que desea hacer?");
                                            System.out.println("1. Ingresar libro");
                                            System.out.println("2. Buscar libro por Id");
                                            System.out.println("3. Actualizar libro");
                                            System.out.println("4. Eliminar libro");
                                            System.out.println("5. Salir");
                                            int opcionLibro = t.nextInt();
                                            t.nextLine();
                                            switch (opcionLibro) {
                                                case 1:
                                                    System.out.println("Introduce el ISBN del libro");
                                                    String isbn = t.nextLine();
                                                    System.out.println("Introduce el titulo del libro");
                                                    String titulo = t.nextLine();
                                                    System.out.println("Introduce el autor del libro");
                                                    String correo = t.nextLine();

                                                    Libro nuevoLibro = new Libro(isbn, titulo, correo);
                                                    ServiceLibro libroService = new ServiceLibro(daoLibro);
                                                    libroService.insertLibroMemoria(nuevoLibro);

                                                    System.out.println("Libro ingresado exitosamente.");
                                                    break;
                                                case 2:
                                                    System.out.println("Introduce el ISBN del libro a buscar:");
                                                    String buscarISBN = t.nextLine();
                                                    Libro libroBuscado = daoLibro.readLibroPorISBN(buscarISBN);
                                                    System.out.println(libroBuscado);
                                                    break;
                                                case 3:
                                                    System.out.println("Introduce el ISBN del libro a actualizar:");
                                                    String actualizarISBN = t.nextLine();
                                                    Libro libroActualizar = daoLibro.readLibroPorISBN(actualizarISBN);

                                                    if (libroActualizar != null) {
                                                        System.out.println("Introduce el nuevo ISBN:");
                                                        String nuevoISBN = t.nextLine();
                                                        libroActualizar.setIsbn(nuevoISBN);
                                                        System.out.println("Introduce el nuevo título del libro:");
                                                        String nuevoTitulo = t.nextLine();
                                                        libroActualizar.setTitulo(nuevoTitulo);
                                                        System.out.println("Introduce autor del libro:");
                                                        String nuevoAutor = t.nextLine();
                                                        libroActualizar.setAutor(nuevoAutor);

                                                        ServiceLibro libroServiceActualizar = new ServiceLibro(daoLibro);
                                                        libroServiceActualizar.updateLibroMemoria(libroActualizar);
                                                        System.out.println("Libro actualizado exitosamente.");
                                                    }
                                                    break;
                                                case 4:
                                                    System.out.println("Introduce el ISBN del libro a eliminar:");
                                                    String isbnEliminar = t.nextLine();
                                                    Libro libroEliminar = daoLibro.readLibroPorISBN(isbnEliminar);
                                                    if (libroEliminar != null) {
                                                        ServiceLibro libroServiceEliminar = new ServiceLibro(daoLibro);
                                                        libroServiceEliminar.updateLibroMemoria(libroEliminar);
                                                        System.out.println("Libro eliminado exitosamente.");
                                                    }
                                                    break;
                                                case 5:
                                                    gestionLibro = false;
                                                    break;
                                                default:
                                                    System.out.println("Opcion no valida");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 3:
                                        boolean gestionEjemplar = true;
                                        while (gestionEjemplar) {
                                            System.out.println("Que desea hacer?");
                                            System.out.println("1. Ingresar ejemplar");
                                            System.out.println("2. Buscar ejemplar por Id");
                                            System.out.println("3. Actualizar ejemplar");
                                            System.out.println("4. Eliminar ejemplar");
                                            System.out.println("5. Salir");
                                            int opcionEjemplar = t.nextInt();
                                            t.nextLine();
                                            switch (opcionEjemplar) {
                                                case 1:
                                                    System.out.println("Introduce el ISBN del libro:");
                                                    String isbnEjemplar = t.nextLine();
                                                    Libro libro = daoLibro.readLibroPorISBN(isbnEjemplar);
                                                    if (libro == null) {
                                                        System.out.println("No se encontró un libro con el ISBN proporcionado.");
                                                        return; // Salir si no se encuentra el libro
                                                    }

                                                    System.out.println("Introduce el estado del ejemplar (Disponible, Prestado, Danado):");
                                                    String estadoIngresado = t.nextLine();

                                                    Ejemplar.EstadoEjemplar estadoEjemplar;
                                                    try {
                                                        estadoEjemplar = Ejemplar.EstadoEjemplar.valueOf(estadoIngresado.trim());
                                                    } catch (IllegalArgumentException e) {
                                                        System.out.println("Estado inválido. Se asignará el estado por defecto: Disponible.");
                                                        estadoEjemplar = Ejemplar.EstadoEjemplar.Disponible;
                                                    }

                                                    Ejemplar nuevoEjemplar = new Ejemplar(libro, estadoEjemplar);
                                                    ServiceEjemplar ejemplarService = new ServiceEjemplar(daoEjemplar);
                                                    ejemplarService.insertEjemplarMemoria(nuevoEjemplar);
                                                    System.out.println("Ejemplar ingresado exitosamente.");
                                                    break;
                                                case 2:
                                                    System.out.println("Introduce el ID del ejemplar a buscar:");
                                                    int buscarIdEjemplar = t.nextInt();
                                                    t.nextLine();
                                                    Ejemplar ejemplarBuscado = daoEjemplar.readEjemplarPorID(buscarIdEjemplar);
                                                    System.out.println(ejemplarBuscado);
                                                    break;
                                                case 3:
                                                    System.out.println("Introduce el ID del ejemplar a actualizar:");
                                                    int actualizarIdEjemplar = t.nextInt();
                                                    t.nextLine();
                                                    Ejemplar ejemplarActualizar = daoEjemplar.readEjemplarPorID(actualizarIdEjemplar);
                                                    System.out.println("Introduce el nuevo estado del ejemplar (Disponible, Prestado, Danado):");
                                                    String nuevoEstadoIngresado = t.nextLine();
                                                    Ejemplar.EstadoEjemplar nuevoEstado;
                                                    try {
                                                        nuevoEstado = Ejemplar.EstadoEjemplar.valueOf(nuevoEstadoIngresado.trim());
                                                    } catch (IllegalArgumentException e) {
                                                        System.out.println("Estado inválido. Se asignará el estado por defecto: Disponible.");
                                                        nuevoEstado = Ejemplar.EstadoEjemplar.Disponible;
                                                    }
                                                    ejemplarActualizar.setEstado(nuevoEstado);
                                                    ServiceEjemplar ejemplarServiceActualizar = new ServiceEjemplar(daoEjemplar);
                                                    ejemplarServiceActualizar.updateEjemplarMemoria(ejemplarActualizar);
                                                    System.out.println("Ejemplar actualizado exitosamente.");
                                                    break;
                                                case 4:
                                                    System.out.println("Introduce el ID del ejemplar a eliminar:");
                                                    int eliminarIdEjemplar = t.nextInt();
                                                    t.nextLine();
                                                    Ejemplar ejemplarAEliminar = daoEjemplar.readEjemplarPorID(eliminarIdEjemplar);
                                                    ServiceEjemplar ejemplarServiceEliminar = new ServiceEjemplar(daoEjemplar);
                                                    ejemplarServiceEliminar.deleteEjemplarMemoria(eliminarIdEjemplar);
                                                    System.out.println("Ejemplar eliminado exitosamente.");
                                                    break;
                                                case 5:
                                                    gestionEjemplar = false;
                                                    break;
                                                default:
                                                    System.out.println("Opcion no valida");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 4:
                                        boolean gestionPrestamo = true;
                                        while (gestionPrestamo) {
                                            System.out.println("Que desea hacer?");
                                            System.out.println("1. Ingresar prestamo");
                                            System.out.println("2. Buscar prestamo por Id");
                                            System.out.println("3. Actualizar prestamo");
                                            System.out.println("4. Eliminar prestamo");
                                            System.out.println("5. Salir");
                                            int opcionPrestamo = t.nextInt();
                                            t.nextLine();
                                            switch (opcionPrestamo) {
                                                case 1:
                                                    System.out.println("Introduce el ID del usuario:");
                                                    int usuarioId = t.nextInt();
                                                    t.nextLine();
                                                    Usuario usuario = daoUsuario.readUsuarioPorID(usuarioId);
                                                    Validaciones.puedeMásPrestamos(usuario);

                                                    System.out.println("Introduce el ID del ejemplar:");
                                                    int ejemplarId = t.nextInt();
                                                    t.nextLine();
                                                    Ejemplar ejemplar = daoEjemplar.readEjemplarPorID(ejemplarId);

                                                    if (ejemplar.getEstado() != Ejemplar.EstadoEjemplar.Disponible) {
                                                        System.out.println("El ejemplar no está disponible para préstamo.");
                                                        return;
                                                    }

                                                    Prestamo nuevoPrestamo = new Prestamo(usuario, ejemplar);
                                                    ServicePrestamo prestamoService = new ServicePrestamo(daoPrestamo);
                                                    prestamoService.insertPrestamoMemoria(nuevoPrestamo);
                                                    System.out.println("Préstamo registrado exitosamente.");
                                                    break;
                                                case 2:
                                                    System.out.println("Introduce el ID del préstamo a buscar:");
                                                    int prestamoId = t.nextInt();
                                                    t.nextLine();
                                                    Prestamo prestamoBuscado = daoPrestamo.readPrestamoPorID(prestamoId);
                                                    System.out.println(prestamoBuscado);
                                                    break;
                                                case 3:
                                                    System.out.println("Introduce el ID del préstamo a actualizar:");
                                                    int actualizarIdPrestamo = t.nextInt();
                                                    t.nextLine();
                                                    Prestamo prestamoActualizar = daoPrestamo.readPrestamoPorID(actualizarIdPrestamo);

                                                        System.out.println("Préstamo encontrado: " + prestamoActualizar);

                                                        System.out.println("¿Desea actualizar la fecha de devolución? (sí/no):");
                                                        String respuesta = t.nextLine().trim().toLowerCase();

                                                        if (respuesta.equals("sí")) {
                                                            System.out.println("Introduce la nueva fecha de devolución (formato YYYY-MM-DD):");
                                                            String nuevaFechaDevolucion = t.nextLine();

                                                            try {
                                                                // Parseamos la fecha de devolución ingresada
                                                                LocalDate fechaDevolucion = LocalDate.parse(nuevaFechaDevolucion);
                                                                prestamoActualizar.setFechaDevolucion(fechaDevolucion);
                                                                System.out.println("Fecha de devolución actualizada exitosamente.");
                                                            } catch (Exception e) {
                                                                System.out.println("Fecha inválida. No se actualizó la fecha de devolución.");
                                                            }
                                                        }

                                                        // Actualizar el estado del ejemplar asociado
                                                        System.out.println("¿Desea actualizar el estado del ejemplar del préstamo? (sí/no):");
                                                        String respuestaEstado = t.nextLine().trim().toLowerCase();

                                                        if (respuestaEstado.equals("sí")) {
                                                            System.out.println("Introduce el nuevo estado del ejemplar (Disponible, Prestado, Danado):");
                                                            String nuevoEstadoIngresado = t.nextLine().trim();

                                                            Ejemplar.EstadoEjemplar nuevoEstado;
                                                            try {
                                                                nuevoEstado = Ejemplar.EstadoEjemplar.valueOf(nuevoEstadoIngresado);
                                                                Ejemplar ejemplarActualizar = daoEjemplar.readEjemplarPorID(prestamoActualizar.getEjemplar().getId());
                                                                if (ejemplarActualizar != null) {
                                                                    ejemplarActualizar.setEstado(nuevoEstado);
                                                                    ServiceEjemplar ejemplarServiceActualizar = new ServiceEjemplar(daoEjemplar);
                                                                    ejemplarServiceActualizar.updateEjemplarMemoria(ejemplarActualizar);
                                                                    System.out.println("Estado del ejemplar actualizado exitosamente.");
                                                                } else {
                                                                    System.out.println("No se encontró el ejemplar asociado al préstamo.");
                                                                }
                                                            } catch (IllegalArgumentException e) {
                                                                System.out.println("Estado inválido. No se actualizó el estado del ejemplar.");
                                                            }
                                                        }

                                                        ServicePrestamo prestamoServiceActualizar = new ServicePrestamo(daoPrestamo);
                                                        prestamoServiceActualizar.updatePrestamoMemoria(prestamoActualizar);
                                                        System.out.println("Préstamo actualizado exitosamente.");
                                                    break;
                                                case 4:
                                                    System.out.println("Introduce el ID del préstamo a eliminar:");
                                                    int eliminarIdPrestamo = t.nextInt();
                                                    t.nextLine();
                                                    Prestamo prestamoAEliminar = daoPrestamo.readPrestamoPorID(eliminarIdPrestamo);
                                                    ServicePrestamo prestamoServiceEliminar = new ServicePrestamo(daoPrestamo);
                                                    prestamoServiceEliminar.deletePrestamoMemoria(eliminarIdPrestamo);
                                                    System.out.println("Préstamo eliminado.");
                                                    break;
                                                case 5:
                                                    gestionPrestamo = false;
                                                    break;
                                                default:
                                                    System.out.println("Opcion no valida");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 5:
                                        administradorActivo = false;
                                        break;
                                    default:
                                        System.out.println("Opcion no valida");
                                        break;
                                }
                            }
                        } else {
                            System.out.println("El id no es de administrador");
                        }
                    } catch (NoResultException e) {
                        System.out.println("Administrador no encontrado");
                    }
                    break;

                //PARA USUARIO NORMAL
                case 2:
                    System.out.println("Nombre:");
                    String nombre = t.nextLine();
                    System.out.println("Contraseña:");
                    String contraseña = t.nextLine();
                    boolean usuarioValido = false;
                    Usuario usuario = null;
                    for (Usuario u : listaUsuarios) {
                        if (u.getNombre().equals(nombre) && u.getPassword().equals(contraseña)) {
                            usuarioValido = true;
                            usuario = u;
                            break;
                        }
                    }

                    if (!usuarioValido) {
                        System.out.println("Usuario no encontrado o nombre y contraseña no coinciden");
                    } else {
                        try {
                            while (usuarioValido) {
                                System.out.println("Qué desea ver?");
                                System.out.println("1. Mis datos");
                                System.out.println("2. Datos del préstamo");
                                System.out.println("3. Datos de mis ejemplares");
                                System.out.println("4. Salir");
                                int option2 = t.nextInt();
                                t.nextLine();
                                switch (option2) {
                                    case 1:
                                        System.out.println(usuario);
                                        break;
                                    case 2:
                                        List<Prestamo> prestamos = daoPrestamo.readPrestamosPorUsuarioId(usuario.getId());
                                        if (prestamos.isEmpty()) {
                                            System.out.println("No tienes préstamos.");
                                        } else {
                                            System.out.println("Información sobre préstamos:");
                                            for (Prestamo prestamo : prestamos) {
                                                System.out.println(prestamos);
                                            }
                                        }
                                        break;
                                    case 3:
                                        List<Ejemplar> ejemplares = daoEjemplar.readEjemplaresPorUsuarioId(usuario.getId());
                                        if (ejemplares.isEmpty()) {
                                            System.out.println("No tienes ejemplares prestados.");
                                        } else {
                                            System.out.println("Ejemplares prestados:");
                                            for (Ejemplar ejemplar : ejemplares) {
                                                System.out.println(ejemplar);
                                            }
                                        }
                                        break;
                                    case 4:
                                        System.out.println("Saliendo...");
                                        usuarioValido = false;
                                        break;
                                    default:
                                        System.out.println("Opcion no valida.");
                                }
                            }
                        } catch (NoResultException e) {
                            System.out.println("Usuario no encontrado");
                        }
                    }
                    break;
                case 3:
                    exit = true;
                    System.out.println("Programa finalizado");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }
}
