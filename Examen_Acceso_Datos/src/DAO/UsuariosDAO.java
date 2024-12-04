package DAO;

import Conexion.Conexion;
import DTO.UsuariosDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {
    private Connection conexion;

    public UsuariosDAO(Conexion conexion) {
        this.conexion = conexion.conexion;
    }

    public void insertUsuario(UsuariosDTO usuario) {
        String sql = "INSERT INTO usuarios (nickname, password, estado) VALUES (?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getPassword());
            ps.setBoolean(3, usuario.isBloqueado());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void actualizarUsuario(String nickname, UsuariosDTO usuario) {
        String sql = "UPDATE usuarios SET nickname = ?, password = ?, estado = ? WHERE nickname = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, nickname);
            ps.setString(2, usuario.getPassword());
            ps.setBoolean(3, usuario.isBloqueado());
            ps.setString(4, nickname);
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void eliminarUsuario(String nickname) {
        String sql = "DELETE FROM usuarios WHERE nickname = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, nickname);
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public List<UsuariosDTO> leerUsuarios() {
        List<UsuariosDTO> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Statement st = conexion.createStatement()){
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                UsuariosDTO u = new UsuariosDTO(rs.getString("nickname"), rs.getString("password"));
                u.setBloqueado(rs.getBoolean("estado"));
                usuarios.add(u);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return usuarios;
    }
}
