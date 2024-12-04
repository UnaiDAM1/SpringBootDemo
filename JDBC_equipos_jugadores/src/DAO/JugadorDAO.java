package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Conexion.Conexion;
import DTO.JugadorDTO;

public class JugadorDAO {
    static final String tab = "Jugador";


    Connection conexion;

    public JugadorDAO(Conexion conexionBD) {
        this.conexion = conexionBD.conexion;
    }

    public void insertJugador(JugadorDTO jug) {
        String insert = "INSERT INTO " + tab + " (nombre, estatura, peso, idEquipo) VALUES (?, ?, ?, ?);";
        try (PreparedStatement ps = conexion.prepareStatement(insert)){
            ps.setString(1, jug.getNombre());
            ps.setFloat(2, jug.getEstatura());
            ps.setFloat(3, jug.getPeso());
            ps.setInt(4, jug.getIdEquipos());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void actualizarJugador(int id, JugadorDTO jug) {
        String update = "UPDATE " + tab + " SET nombre = ?, estatura = ?, peso = ?, idEquipo = ? WHERE id = '" + id + "'";
        try (PreparedStatement ps = conexion.prepareStatement(update)){
            ps.setString(1, jug.getNombre());
            ps.setFloat(2, jug.getEstatura());
            ps.setFloat(3, jug.getPeso());
            ps.setInt(4, jug.getIdEquipos());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void borrarJugador(int id) {
        String delete = "DELETE FROM " + tab + " WHERE id = " + id;
        try (PreparedStatement ps = conexion.prepareStatement(delete)){
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<JugadorDTO> mostrarJugadores(){
        List<JugadorDTO> jugadores = new ArrayList<>();
        String select = "SELECT * FROM " + tab;
        try (Statement sentencia = conexion.createStatement()) {
            ResultSet rs = sentencia.executeQuery(select);
            while (rs.next()) {
                JugadorDTO j = new JugadorDTO(rs.getString("nombre"), rs.getFloat("estatura"), rs.getFloat("peso"), rs.getInt("idEquipo"));
                j.setId(rs.getInt("id"));
                jugadores.add(j);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return jugadores;
    }
}
