package Main;

import DAO.EquipoDAOImpl;
import DAO.JugadorDAOImpl;
import DTO.Equipo;
import DTO.Jugador;
import Service.EquipoService;
import Service.JugadorService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
        EntityManager em = emf.createEntityManager();
        EquipoService equipoService = new EquipoService(new EquipoDAOImpl(em));
        JugadorService jugadorService = new JugadorService(new JugadorDAOImpl(em));

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n-------- Gestión de Equipos y Jugadores --------------");
            System.out.println("1. Leer todos los equipos");
            System.out.println("2. Leer todos los jugadores");
            System.out.println("3. Insertar equipo");
            System.out.println("4. Insertar jugador");
            System.out.println("5. Actualizar equipo");
            System.out.println("6. Actualizar jugador");
            System.out.println("7. Borrar equipo");
            System.out.println("8. Borrar jugador");
            System.out.println("9. Ordenar equipos por nombre");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    //Leer equipos
                    List<Equipo> equipos = equipoService.getEquiposEnMemoria();
                    System.out.println("Equipos:");
                    equipos.forEach(System.out::println);
                    break;
                case 2:
                    //Leer jugadores
                    List<Jugador> jugadores = jugadorService.getJugadores();
                    System.out.println("Jugadores:");
                    jugadores.forEach(System.out::println);
                    break;
                case 3:
                    //Insertar equipo
                    System.out.print("Nombre del equipo: ");
                    String nombreEquipo = scanner.nextLine();
                    System.out.print("Estadio del equipo: ");
                    String estadio = scanner.nextLine();
                    System.out.println("Número de títulos del equipo: ");
                    int titulos = scanner.nextInt();
                    equipoService.insertEquipo(new Equipo(null, nombreEquipo, estadio, titulos));
                    System.out.println("Equipo insertado.");
                    break;
                case 4:
                    //Insertar jugador
                    System.out.println("Nombre del jugador: ");
                    String nombre = scanner.nextLine();
                    System.out.println("DNI del jugador: ");
                    String dni = scanner.nextLine();
                    System.out.println("Fecha de nacimiento en formato yyyy-MM-dd: ");
                    LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine());
                    System.out.println("Estatura del jugador: ");
                    Float estatura = scanner.nextFloat();
                    System.out.println("Peso del jugador: ");
                    Float peso = scanner.nextFloat();
                    System.out.println("ID del equipo del jugador: ");
                    int idEquipo = scanner.nextInt();
                    System.out.println("Numero de goles: ");
                    int numeroGoles = scanner.nextInt();
                    System.out.println("Asistencias: ");
                    int asistencias = scanner.nextInt();
                    System.out.println("Partidos: ");
                    int partidos = scanner.nextInt();


                        Equipo equipo = equipoService.getEquiposEnMemoria().stream()
                                .filter(e -> e.getId().equals(idEquipo))
                                .findFirst().orElse(null);
                        if (equipo != null) {
                            jugadorService.insertarJugador(new Jugador(dni, nombre, fechaNacimiento, estatura, peso, equipo, numeroGoles, asistencias, partidos));
                            System.out.println("Jugador insertado.");
                        } else {
                            System.out.println("Equipo no encontrado.");
                        }
                    break;
                case 5:
                    System.out.println("Todavia no se puede actualizar");
                    break;
                case 6:
                    System.out.println("Todavia no se puede actualizar");
                    break;

                case 7:
                    //Borrar un equipo
                    System.out.print("ID del equipo a borrar: ");
                    int idEquipoBorrar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    equipoService.eliminarEquipo(idEquipoBorrar);
                    break;
                case 8:
                    //Borrar un jugador
                    System.out.print("ID del jugador a borrar: ");
                    int idJugadorBorrar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    jugadorService.eliminarJugador(idJugadorBorrar);

                    break;
                case 9:
                    //Ordenar equipos por su nombre
                    List<Equipo> equiposOrdenados = equipoService.getEquiposEnMemoriaPorNombre();
                    equiposOrdenados.sort((e1, e2) -> e1.getNombre().compareTo(e2.getNombre()));
                    System.out.println("Equipos ordenados por nombre:");
                    equiposOrdenados.forEach(System.out::println);
                    break;
                case 0:
                    System.out.println("Saliendo");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
        em.close();
        emf.close();
    }
}
