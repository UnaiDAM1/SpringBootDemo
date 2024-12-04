package DAO;

import DTO.Equipo;

import java.util.List;

public interface EquipoDAO {
    public void insertarEquipo(Equipo equipo);
    public void actualizarEquipo(Equipo equipo);
    public void eliminarEquipo(int id);
    public Equipo buscarEquipo(int id);
    public List<Equipo> listarEquipos();
    public List<Equipo> listarEquiposPorNombre();

}
