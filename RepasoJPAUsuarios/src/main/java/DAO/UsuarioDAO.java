package DAO;

import Modelos.Usuario;

import java.util.List;

public interface UsuarioDAO {
    void insertar(Usuario usuario);
    void actualizar(Usuario usuario);
    void borrar(Integer id);
    Usuario leer(Integer id);
    List<Usuario> leerTodos();
    List<Usuario> ordenarPorNombre();
}
