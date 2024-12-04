package DAO;

import DTO.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {
    EntityManager em;
    public ProductoDAOImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public void insertarProducto(Producto producto) {
        em.getTransaction().begin();
        em.persist(producto);
        em.getTransaction().commit();
    }

    @Override
    public void eliminarProducto(String codigo) {
        em.getTransaction().begin();
        Producto producto = em.find(Producto.class, codigo);
        if (producto != null) {
            em.remove(producto);
        }
        em.getTransaction().commit();
    }

    @Override
    public void modificarProducto(Producto producto) {
        em.getTransaction().begin();
        em.merge(producto);
        em.getTransaction().commit();
    }

    @Override
    public List<Producto> listarProducto() {
        TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p", Producto.class);
        return query.getResultList();
    }
}
