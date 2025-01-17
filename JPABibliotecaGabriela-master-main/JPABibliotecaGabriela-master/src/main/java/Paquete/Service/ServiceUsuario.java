package Paquete.Service;

import Paquete.Clases.Usuario;
import Paquete.DAOS.DAOUsuario;

import java.util.ArrayList;
import java.util.List;

public class ServiceUsuario {
    private DAOUsuario daoUsuario;
    private List<Usuario> listaUsuarioMemoria;

    //Constructor
    public ServiceUsuario(DAOUsuario daoUsuario) {
        this.daoUsuario = daoUsuario;
        this.listaUsuarioMemoria = new ArrayList();
        //Para que cargue al iniciar el servicio
        cargarUsuarioMemoria();
    }

    //Cargar de la base de datos a la memoria
    public void cargarUsuarioMemoria() {
        this.listaUsuarioMemoria = daoUsuario.readAllUsuarios();
    }

    //Obtener todos
    public List<Usuario> getListaUsuarioMemoria() {
        return listaUsuarioMemoria;
    }
    //Obtener uno por id
    public Usuario getUsuarioMemoria(int id) {
        for (Usuario usuario : listaUsuarioMemoria) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }
    //Agregar
    public void insertUsuarioMemoria(Usuario usuario) {
        listaUsuarioMemoria.add(usuario);
        daoUsuario.updateUsuario(usuario);
    }

    //Actualizar
    public void updateUsuarioMemoria(Usuario usuario) {
        for (int i = 0; i < listaUsuarioMemoria.size(); i++) {
            if (listaUsuarioMemoria.get(i).getId() == usuario.getId()) {
                listaUsuarioMemoria.set(i, usuario);
                break;
            }
        }
        daoUsuario.updateUsuario(usuario);
    }

    //Eliminar
    public void deleteUsuarioMemoria(int id) {
        Usuario usuarioAEliminar = null;

        //Buca por if
        for (Usuario usuario : listaUsuarioMemoria) {
            if (usuario.getId() == id) {
                usuarioAEliminar = usuario;
                break;
            }
        }

        if (usuarioAEliminar != null) {
            listaUsuarioMemoria.remove(usuarioAEliminar);
            daoUsuario.deleteUsuario(id);
        } else {
            System.out.println("Usuario con ID " + id + " no encontrado.");
        }
    }
}
