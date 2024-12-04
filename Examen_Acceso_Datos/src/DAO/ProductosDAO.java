package DAO;

import Conexion.Conexion;
import DTO.AuditoriaDTO;
import DTO.ProductosDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductosDAO {
    private Connection conexion;

    public ProductosDAO(Conexion conexion) {
        this.conexion = conexion.conexion;
    }
    public void insertProductos(ProductosDTO productos) {
        String sql = "INSERT INTO productos (codigo, nombre, descripcion, precio, stock, disponible) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, productos.getCodigo());
            ps.setString(2, productos.getNombre());
            ps.setString(3, productos.getDescripcion());
            ps.setDouble(4, productos.getPrecio());
            ps.setInt(5, productos.getCantidad());
            ps.setBoolean(6, productos.isDisponible());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void actualizarProductos(String codigo, ProductosDTO producto) {
        String sql = "UPDATE auditoria SET nombre = ?, descripcion = ?, precio = ?, stock = ?, disponible = ? WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getCantidad());
            ps.setBoolean(5, producto.isDisponible());
            ps.setString(6, codigo);
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void eliminarProductos(String codigo) {
        String sql = "DELETE FROM productos WHERE codigo = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, codigo);
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public List<ProductosDTO> leerProductos() {
        List<ProductosDTO> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Statement st = conexion.createStatement()){
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                ProductosDTO p = new ProductosDTO(rs.getString("codigo"), rs.getString("nombre"), rs.getString("descripcion"), rs.getDouble("precio"), rs.getInt("stock"), rs.getBoolean("disponible"));
                productos.add(p);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return productos;
    }
}
