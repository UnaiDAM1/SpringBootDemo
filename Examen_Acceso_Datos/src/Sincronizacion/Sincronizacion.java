package Sincronizacion;

import DAO.AuditoriaDAO;
import DAO.ProductosDAO;
import DAO.UsuariosDAO;
import DTO.AuditoriaDTO;
import DTO.ProductosDTO;
import DTO.UsuariosDTO;

import java.util.ArrayList;
import java.util.List;

public class Sincronizacion {
    List<AuditoriaDTO> auditoriasEnMemoria = new ArrayList<>();
    List<ProductosDTO> productosEnMemoria = new ArrayList<>();
    List<UsuariosDTO> usuariosEnMemoria = new ArrayList<>();

    AuditoriaDAO auditoriaDAO;
    ProductosDAO productosDAO;
    UsuariosDAO usuariosDAO;

    public Sincronizacion(AuditoriaDAO auditoriaDAO, ProductosDAO productosDAO, UsuariosDAO usuariosDAO) {
        this.auditoriaDAO = auditoriaDAO;
        this.productosDAO = productosDAO;
        this.usuariosDAO = usuariosDAO;
    }

    public void sincronizar() {
        auditoriasEnMemoria = auditoriaDAO.leerAuditorias();
        productosEnMemoria = productosDAO.leerProductos();
        usuariosEnMemoria = usuariosDAO.leerUsuarios();
    }

    public void leerAuditorias() {
        for (AuditoriaDTO auditoria : auditoriasEnMemoria) {
            System.out.println(auditoria);
        }
    }
    public void leerProductos() {
        for (ProductosDTO producto : productosEnMemoria) {
            System.out.println(producto);
        }
    }
    public void leerUsuarios() {
        for (UsuariosDTO usuario : usuariosEnMemoria) {
            System.out.println(usuario);
        }
    }
}
