package DAO;

import DTO.Jugador;

import java.util.List;

public interface JugadorDAO {
    public void insertarJugador(Jugador j);
    public void actualizarJugador(Jugador j);
    public void eliminarJugador(int id);
    public Jugador buscarJugador(int id);
    public List<Jugador> listarJugadores();
    public List<Jugador> listarJugadoresPorNombre();
}
