package Main;



import DAO.EquipoDAOImpl;
import DAO.JugadorDAOImpl;
import Modelos.Equipo;
import Modelos.Jugador;
import Services.EquipoServices;
import Services.JugadorServices;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
        EntityManager em = emf.createEntityManager();

        EquipoServices equipoService = new EquipoServices(new EquipoDAOImpl(em));
        JugadorServices jugadorService = new JugadorServices(new JugadorDAOImpl(em));

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
                    List<Equipo> equipos = equipoService.obtenerEquipos();
                    System.out.println("Equipos:");
                    equipos.forEach(System.out::println);
                    break;
                case 2:
                    //Leer jugadores
                    List<Jugador> jugadores = jugadorService.obtenerJugadores();
                    System.out.println("Jugadores:");
                    jugadores.forEach(System.out::println);
                    break;
                case 3:
                    //Insertar equipo
                    System.out.print("Nombre del equipo: ");
                    String nombreEquipo = scanner.nextLine();
                    System.out.print("Estadio del equipo: ");
                    String estadio = scanner.nextLine();
                    equipoService.insertarEquipo(new Equipo(null, nombreEquipo, estadio));
                    System.out.println("Equipo insertado.");
                    break;
                case 4:
                    //Insertar jugador
                    System.out.print("Nombre del jugador: ");
                    String nombreJugador = scanner.nextLine();
                    System.out.print("Estatura del jugador (en metros): ");
                    float estatura = scanner.nextFloat();
                    System.out.print("Peso del jugador (en kg): ");
                    float peso = scanner.nextFloat();
                    System.out.print("ID del equipo al que pertenece: ");
                    int equipoId = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer de entrada
                    Equipo equipo = equipoService.obtenerEquipos().stream()
                            .filter(e -> e.getId().equals(equipoId))
                            .findFirst()
                            .orElse(null);
                    if (equipo != null) {
                        jugadorService.insertarJugador(new Jugador(null, nombreJugador, estatura, peso, equipo));
                        System.out.println("Jugador insertado.");
                    } else {
                        System.out.println("Equipo no encontrado.");
                    }
                    break;
                case 5:
                    //Actualizar equipo
                    System.out.print("ID del equipo a actualizar: ");
                    int idEquipo = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    Equipo equipoActualizar = equipoService.obtenerEquipos().stream()
                            .filter(e -> e.getId().equals(idEquipo))
                            .findFirst()
                            .orElse(null);
                    if (equipoActualizar != null) {
                        System.out.print("Nuevo nombre del equipo: ");
                        equipoActualizar.setNombre(scanner.nextLine());
                        System.out.print("Nuevo estadio del equipo: ");
                        equipoActualizar.setEstadio(scanner.nextLine());
                        equipoService.actualizarEquipo(equipoActualizar);
                        System.out.println("Equipo actualizado.");
                    } else {
                        System.out.println("Equipo no encontrado.");
                    }
                    break;
                case 6:
                    //Actualizar jugador
                    System.out.print("ID del jugador a actualizar: ");
                    int idJugador = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    Jugador jugadorActualizar = jugadorService.obtenerJugadores().stream()
                            .filter(j -> j.getId().equals(idJugador))
                            .findFirst()
                            .orElse(null);
                    if (jugadorActualizar != null) {
                        System.out.print("Nuevo nombre del jugador: ");
                        jugadorActualizar.setNombre(scanner.nextLine());
                        System.out.println("Nueva estatura del jugador: ");
                        jugadorActualizar.setEstatura(scanner.nextFloat());
                        System.out.println("Nuevo peso del jugador: ");
                        jugadorActualizar.setPeso(scanner.nextFloat());
                        System.out.println("Nuevo equipo del jugador: ");
                        int nuevoIdEquipo = scanner.nextInt();
                        Equipo equipoCambio = equipoService.obtenerEquipos().stream()
                                .filter(e -> e.getId().equals(nuevoIdEquipo))
                                .findFirst()
                                .orElse(null);
                        if (equipoCambio != null) {
                            jugadorActualizar.setIdEquipo(equipoCambio);
                            jugadorService.actualizarJugador(jugadorActualizar);
                            System.out.println("Jugador actualizado.");
                        } else {
                            System.out.println("Equipo no encontrado.");
                        }
                    }
                    break;
                case 7:
                    //Borrar un equipo
                    System.out.print("ID del equipo a borrar: ");
                    int idEquipoBorrar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    equipoService.borrarEquipo(idEquipoBorrar);
                    break;
                case 8:
                    //Borrar un jugador
                    System.out.print("ID del jugador a borrar: ");
                    int idJugadorBorrar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    jugadorService.borrarJugador(idJugadorBorrar);
                    break;
                case 9:
                    //Ordenar equipos por su nombre
                    List<Equipo> equiposOrdenados = equipoService.obtenerEquipos();
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
        }while (opcion != 0);
        em.close();
        emf.close();
    }
}


