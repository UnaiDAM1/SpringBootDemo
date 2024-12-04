package Main;

import Conexion.Conexion;
import DAO.EquipoDAO;
import DAO.JugadorDAO;
import DTO.EquipoDTO;
import DTO.JugadorDTO;
import Sincronizacion.Sincronizacion;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Conexion conect = new Conexion();
    static JugadorDAO conectJug = new JugadorDAO(conect);
    static EquipoDAO conectEqu = new EquipoDAO(conect);
    public static void menu() {
        Sincronizacion sin = new Sincronizacion(conectEqu, conectJug);
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        sin.sincronizarMemoria();
        while (!salir) {
            System.out.println("\n--- MENÚ DE GESTIÓN ---");
            System.out.println("1. Insertar Equipo");
            System.out.println("2. Insertar Jugador");
            System.out.println("3. Actualizar Equipo");
            System.out.println("4. Actualizar Jugador");
            System.out.println("5. Borrar Equipo");
            System.out.println("6. Borrar Jugador");
            System.out.println("7. Listar Equipos");
            System.out.println("8. Listar Jugadores");
            System.out.println("9. Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    // Inserción de equipo
                    System.out.print("Nombre del equipo: ");
                    String nombreEquipo = scanner.nextLine();
                    System.out.println("Estadio del equipo: ");
                    String nombreEstadio = scanner.nextLine();
                    conectEqu.insertEquipo(new EquipoDTO(nombreEquipo, nombreEstadio));
                    sin.sincronizarMemoria();
                    System.out.println("Equipo insertado correctamente.");
                    break;

                case 2:
                    // Inserción de jugador
                    System.out.print("Nombre del jugador: ");
                    String nombreJugador = scanner.nextLine();
                    System.out.println("Estatura del jugador: ");
                    Float estaturaJugador = scanner.nextFloat();
                    System.out.println("Peso del jugador: ");
                    Float pesoJugador = scanner.nextFloat();
                    System.out.println("ID del equipo al que pertenece: ");
                    int idEquipo = scanner.nextInt();
                    conectJug.insertJugador(new JugadorDTO(nombreJugador, estaturaJugador, pesoJugador, idEquipo));
                    sin.sincronizarMemoria();
                    System.out.println("Jugador insertado correctamente.");
                    break;

                case 3:
                    // Actualización de equipo
                    System.out.println("Introduzca el ID del equipo a cambiar: ");
                    int idEquipoActualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nuevo nombre del equipo: ");
                    String nuevoNombreEquipo = scanner.nextLine();
                    System.out.println("Nuevo estadio del equipo: ");
                    String nuevoEstadio = scanner.nextLine();
                    conectEqu.actualizarEquipo(idEquipoActualizar, new EquipoDTO(nuevoNombreEquipo, nuevoEstadio));
                    sin.sincronizarMemoria();
                    System.out.println("Equipo actualizado correctamente.");
                    break;

                case 4:
                    // Actualización de jugador
                    System.out.println("Introduzca el ID del jugador que quiere cambiar: ");
                    int idJugadorActualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nuevo nombre del jugador: ");
                    String nuevoNombreJugador = scanner.nextLine();
                    System.out.println("Nueva estatura del jugador: ");
                    Float nuevaEstatura = scanner.nextFloat();
                    System.out.println("Nuevo peso del jugador: ");
                    Float nuevoPeso = scanner.nextFloat();
                    System.out.print("Nuevo ID del equipo al que pertenece: ");
                    int nuevoEquipoId = scanner.nextInt();
                    conectJug.actualizarJugador(idJugadorActualizar, new JugadorDTO(nuevoNombreJugador, nuevaEstatura, nuevoPeso, nuevoEquipoId));
                    sin.sincronizarMemoria();
                    System.out.println("Jugador actualizado correctamente.");
                    break;

                case 5:
                    // Borrado de equipo
                    System.out.print("ID del equipo a borrar: ");
                    int idEquipoBorrar = scanner.nextInt();
                    conectEqu.borrarEquipo(idEquipoBorrar);
                    sin.sincronizarMemoria();
                    System.out.println("Equipo borrado correctamente.");
                    break;

                case 6:
                    // Borrado de jugador
                    System.out.print("ID del jugador a borrar: ");
                    int idJugadorBorrar = scanner.nextInt();
                    scanner.nextLine(); // Consume la línea en blanco
                    conectJug.borrarJugador(idJugadorBorrar);
                    sin.sincronizarMemoria();
                    System.out.println("Jugador borrado correctamente.");
                    break;

                case 7:
                    // Listar equipos
                    System.out.println("\n--- LISTA DE EQUIPOS ---");
                    sin.imprimirEquiposMemoria();
                    break;

                case 8:
                    // Listar jugadores
                    System.out.println("\n--- LISTA DE JUGADORES ---");
                    sin.imprimirJugadoresMemoria();
                    break;

                case 9:
                    // Salir del programa
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }

        // Cerrar el scanner al final
        scanner.close();
    }
    public static void main(String[] args) throws SQLException {
        menu();
    }
}