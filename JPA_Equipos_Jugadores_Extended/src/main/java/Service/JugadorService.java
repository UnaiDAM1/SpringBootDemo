package Service;

import DAO.JugadorDAO;
import DTO.Jugador;

import java.util.ArrayList;
import java.util.List;

public class JugadorService {
    JugadorDAO jugadorDAO;
    List<Jugador> jugadoresEnMemoria = new ArrayList<>();

    public JugadorService(JugadorDAO jugadorDAO) {
        this.jugadorDAO = jugadorDAO;
        sincronizar();
    }

    public void insertarJugador(Jugador jugador) {
        jugadorDAO.insertarJugador(jugador);
        sincronizar();
    }
    public void actualizarJugador(Jugador jugador) {
        jugadorDAO.actualizarJugador(jugador);
        sincronizar();
    }
    public void eliminarJugador(int id) {
        jugadorDAO.eliminarJugador(id);
        sincronizar();
    }
    public Jugador getJugador(int id) {
        return jugadorDAO.buscarJugador(id);
    }
    public List<Jugador> getJugadores() {
        return jugadoresEnMemoria;
    }
    public List<Jugador> getJugadoresPorNombre() {
        return jugadorDAO.listarJugadoresPorNombre();
    }

    public void sincronizar(){
        jugadoresEnMemoria = jugadorDAO.listarJugadores();
    }
}
