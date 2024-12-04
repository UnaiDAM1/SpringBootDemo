package DAO;

import Modelos.Equipo;
import java.util.List;

public interface EquipoDAO {
    void insertar(Equipo equipo);
    void actualizar(Equipo equipo);
    void borrar(Integer id);
    Equipo leer(Integer id);
    List<Equipo> leerTodos();
    List<Equipo> ordenarPorNombre();
}
