/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.repositories;

import java.util.List;
import cl.duoc.floresycactus.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jpino
 */
@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long>
{  
    @Query( value = "SELECT * FROM usuarios WHERE correo = ?1 AND password = ?2", nativeQuery = true)
    List<UsuarioEntity> findUsuarioByCorreoAndPass(String correo, String password);
}
