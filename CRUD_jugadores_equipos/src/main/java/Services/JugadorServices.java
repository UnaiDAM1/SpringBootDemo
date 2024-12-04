package Services;
import DAO.JugadorDAO;
import Modelos.Jugador;
import java.util.ArrayList;
import java.util.List;

public class JugadorServices {
    private JugadorDAO jugadorDAO;
    private List<Jugador> jugadoresEnMemoria = new ArrayList<>();

    public JugadorServices(JugadorDAO jugadorDAO) {
        this.jugadorDAO = jugadorDAO;
        sincronizarJugadores();
    }

    public void insertarJugador(Jugador jugador) {
        jugadorDAO.insertar(jugador);
        sincronizarJugadores();
    }

    public void actualizarJugador(Jugador jugador) {
        jugadorDAO.actualizar(jugador);
        sincronizarJugadores();
    }

    public void borrarJugador(Integer id) {
        jugadorDAO.borrar(id);
        sincronizarJugadores();
    }

    public List<Jugador> obtenerJugadores() {
        return jugadoresEnMemoria;
    }

    private void sincronizarJugadores() {
        jugadoresEnMemoria = jugadorDAO.leerTodos();
    }
}
