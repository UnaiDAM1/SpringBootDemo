package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Conexion.Conexion;
import DTO.EquipoDTO;

public class EquipoDAO {
    static final String tab = "Equipo";

    Connection conexion;

    public EquipoDAO(Conexion conexionBD) {
        this.conexion = conexionBD.conexion;
    }
    public void insertEquipo(EquipoDTO equipo) {
        String insert = "INSERT INTO " + tab + "(nombre, estadio) VALUES (?, ?);";
        try (PreparedStatement ps = conexion.prepareStatement(insert)){
            ps.setString(1, equipo.getNombre());
            ps.setString(2, equipo.getEstadio());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void actualizarEquipo(int id, EquipoDTO equipo) {
        String update = "UPDATE " + tab + " SET nombre = ?, estadio = ? WHERE id = '" + id + "'";
        try (PreparedStatement ps = conexion.prepareStatement(update)){
            ps.setString(1, equipo.getNombre());
            ps.setString(2, equipo.getEstadio());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void borrarEquipo(int id) {
        String delete = "DELETE FROM " + tab + " WHERE id = " + id;
        try (PreparedStatement ps = conexion.prepareStatement(delete)){
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<EquipoDTO> mostrarEquipos(){
        List<EquipoDTO> equipos = new ArrayList<>();
        String select = "SELECT * FROM " + tab;
        try (Statement sentencia = conexion.createStatement()) {
            ResultSet rs = sentencia.executeQuery(select);
            while (rs.next()) {
                EquipoDTO e = new EquipoDTO(rs.getString("nombre"), rs.getString("estadio"));
                e.setId(rs.getInt("id"));
                equipos.add(e);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return equipos;
    }
}
