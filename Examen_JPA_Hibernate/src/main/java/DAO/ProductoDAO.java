package DAO;

import DTO.Producto;

import java.util.List;

public interface ProductoDAO {
    public void insertarProducto(Producto producto);
    public void eliminarProducto(String codigo);
    public void modificarProducto(Producto producto);
    public List<Producto> listarProducto();
}
