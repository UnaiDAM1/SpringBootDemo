package Modelos;

import jakarta.persistence.Persistence;
import jakarta.persistence.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factoriaEntidades = Persistence.createEntityManagerFactory("miUnidadPersistencia");
        EntityManager gestorEntidad = factoriaEntidades.createEntityManager();
        EntityTransaction transaccion = gestorEntidad.getTransaction();


        String consultaSQL = "SELECT e from Departamento e";


        ArrayList<Departamento> listaDep = (ArrayList<Departamento>) gestorEntidad.createQuery(consultaSQL).getResultList();
        System.out.println(listaDep);

//        ArrayList<Empleado> listaEmpleados = (ArrayList<Empleado>) gestorEntidad.createQuery(consultaSQL).getResultList();
//        System.out.println(listaEmpleados);

//        Empleado empleado = gestorEntidad.find(Empleado.class, 1);

    }
}
