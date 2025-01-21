package org.example.miprimeraapirestful.Repositorys;

import org.example.miprimeraapirestful.DAOS.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamosRepository extends JpaRepository<Prestamo, Integer> {
}
