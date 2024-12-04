package Sincronizacion;

import DAO.EquipoDAO;
import DAO.JugadorDAO;
import DTO.EquipoDTO;
import DTO.JugadorDTO;

import java.util.List;

public class Sincronizacion {
    List<JugadorDTO> jugadoresMemoria;
    List<EquipoDTO> equiposMemoria;
    EquipoDAO equipo;
    JugadorDAO jugador;

    public Sincronizacion(EquipoDAO equipo, JugadorDAO jugador) {
        this.equipo = equipo;
        this.jugador = jugador;
    }

    public void sincronizarMemoria() {
        jugadoresMemoria = jugador.mostrarJugadores();
        equiposMemoria = equipo.mostrarEquipos();
    }
    public void imprimirJugadoresMemoria() {
        for (JugadorDTO jug : jugadoresMemoria) {
            System.out.println(jug);
        }
    }
    public void imprimirEquiposMemoria() {
        for (EquipoDTO equ : equiposMemoria) {
            System.out.println(equ);
        }
    }
}
