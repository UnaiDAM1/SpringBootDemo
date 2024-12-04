package DAO;
import Modelos.Jugador;
import java.util.List;

public interface JugadorDAO {
    void insertar(Jugador jugador);
    Jugador actualizar(Jugador jugador);
    void borrar(Integer id);
    Jugador leer(Integer id);
    List<Jugador> leerTodos();
}
