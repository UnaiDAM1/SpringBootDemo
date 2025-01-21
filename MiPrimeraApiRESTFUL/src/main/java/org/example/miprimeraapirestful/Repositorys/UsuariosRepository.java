package org.example.miprimeraapirestful.Repositorys;

import org.example.miprimeraapirestful.DAOS.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuario, String> {
}
