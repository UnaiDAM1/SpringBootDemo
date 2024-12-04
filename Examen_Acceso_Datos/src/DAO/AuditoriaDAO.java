package DAO;

import Conexion.Conexion;
import DTO.AuditoriaDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuditoriaDAO {
    private Connection conexion;

    public AuditoriaDAO(Conexion conexion) {
        this.conexion = conexion.conexion;
    }

    public void insertAuditoria(AuditoriaDTO auditoria) {
        String sql = "INSERT INTO auditoria (nickname, password) VALUES (?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)){
                ps.setString(1, auditoria.getNombre());
                ps.setString(2, auditoria.getPassword());
                ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void actualizarAuditoria(int id, AuditoriaDTO auditoria) {
        String sql = "UPDATE auditoria SET nickname = ?, password = ? WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, auditoria.getNombre());
            ps.setString(2, auditoria.getPassword());
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void eliminarAuditoria(int id) {
        String sql = "DELETE FROM auditoria WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public List<AuditoriaDTO> leerAuditorias() {
        List<AuditoriaDTO> auditorias = new ArrayList<>();
        String sql = "SELECT * FROM auditoria";
        try (Statement st = conexion.createStatement()){
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                AuditoriaDTO a = new AuditoriaDTO(rs.getString("nickname"), rs.getString("password"));
                a.setId(rs.getInt("id"));
                auditorias.add(a);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return auditorias;
    }
}
