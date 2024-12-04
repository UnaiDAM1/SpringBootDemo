package DAO;

import DTO.Usuario;

import java.util.List;

public interface UsuarioDAO {
    public void insertar(Usuario usuario);
    public void actualizar(Usuario usuario);
    public void eliminar(String nickname);
    public List<Usuario> leerUsuarios();
}
