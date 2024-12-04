package Service;

import DAO.UsuarioDAO;
import Modelos.Usuario;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    Scanner sc = new Scanner(System.in);
    private UsuarioDAO usuarioDAO;
    private List<Usuario> usuariosEnMemoria = new ArrayList<Usuario>() ;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        this.usuariosEnMemoria = usuariosEnMemoria;
        sincronizar();
    }

    public void insertarUsuario(Usuario usuario) {
        usuarioDAO.insertar(usuario);
        sincronizar();
    }
    public void eliminarUsuario(Integer id) {
        usuarioDAO.borrar(id);
        sincronizar();
    }
    public void actualizararUsuario(Usuario usuario) {
        usuarioDAO.actualizar(usuario);
        sincronizar();
    }

    public List<Usuario> getUsuariosEnMemoria() {
        return usuariosEnMemoria;
    }
    public Usuario getUsuario(String Nombre) {
        for (Usuario usuario : usuariosEnMemoria) {
            if (usuario.getNombre().equals(Nombre)) {
                return usuario;
            } else {
                continue;
            }
        }
        throw new RuntimeException("Usuario no encontrado");
    }

    public void sincronizar(){
        usuariosEnMemoria = usuarioDAO.leerTodos();
    }
}
