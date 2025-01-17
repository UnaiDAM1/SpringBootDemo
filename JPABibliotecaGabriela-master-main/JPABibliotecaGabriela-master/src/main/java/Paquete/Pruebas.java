package Paquete;

import Paquete.Clases.Ejemplar;
import Paquete.DAOS.DAOEjemplar;
import Paquete.DAOS.DAOLibro;
import Paquete.DAOS.DAOPrestamo;
import Paquete.DAOS.DAOUsuario;

import java.util.List;

public class Pruebas {
    public static void main(String[] args) {
        DAOUsuario daoUsuario = new DAOUsuario();
        DAOLibro daoLibro = new DAOLibro();
        DAOEjemplar daoEjemplar = new DAOEjemplar();
        DAOPrestamo daoPrestamo = new DAOPrestamo();

//        //----Pruebas usuarios-----
//        Usuario u1= daoUsuario.readUsuarioPorID(1);
//        System.out.println(u1);
//        Usuario u3=daoUsuario.readUsuarioPorID(3);
//        System.out.println(u3);
//        System.out.println("------");
//        //-----Insertar usuario

//        //Usuario u4= new Usuario("0229745S", "Gabriela", "gabriela@example.com","123", Usuario.TipoUsuario.normal,null);
//        //daoUsuario.insertUsuario(u4);
//        Usuario u4=daoUsuario.readUsuarioPorID(4);
//        u4.setDni("02297485S");
//        daoUsuario.updateUsuario(u4);
//        List<Usuario> listaUsuarios = daoUsuario.readAllUsuarios();
//        listaUsuarios.forEach(System.out::println);
//
//        //----Pruebas libros-----
//        List<Libro> listaLibros = daoLibro.readAllLibros();
//        listaLibros.forEach(System.out::println);
//        System.out.println("------");
//        //----Pruebas ejemplares-----
//        List<Ejemplar> listaEjemplares = daoEjemplar.readAllEjemplares();
//        listaEjemplares.forEach(System.out::println);
//        System.out.println("------");
//        //----Pruebas prestamos-----
//        List<Prestamo> listaPrestamos = daoPrestamo.readAllPrestamos();
//        listaPrestamos.forEach(System.out::println);
//        System.out.println("------");

//        //INTENTO DE ACTUALIZAR EL NULL DE LA FECHA DE DEVOLUCION DEL PRESTAMO DE JUAN DEL QUIJOTE QUE ESTÁ EN NULL
//        Prestamo p1 = daoPrestamo.readPrestamoPorID(1);
//        p1.setFechaDevolucion();
//        daoPrestamo.updatePrestamo(p1);
//        List<Prestamo> listaPrestamos = daoPrestamo.readAllPrestamos();
//        listaPrestamos.forEach(System.out::println);

        //Pruebas contar ejemplares
        List<Ejemplar> listaEjemplares = daoEjemplar.readAllEjemplares();
        int disponibles =Validaciones.calcularStockDisponible(listaEjemplares);
        System.out.println(disponibles);
    }
}
