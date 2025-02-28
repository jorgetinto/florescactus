/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.services;

import cl.duoc.floresycactus.entities.DecisionEntity;
import cl.duoc.floresycactus.entities.MuroEntity;
import java.util.List;

/**
 *
 * @author jpino
 */
public interface IDecisionService {
    
    List<DecisionEntity> findAll();

    DecisionEntity save(DecisionEntity desicion);

    DecisionEntity findById(Long id);

    void delete(DecisionEntity desicion);  
    
    String obtenerTemporada();
    
    String obtenerHoraActual();
    
    DecisionEntity NuevoObjeto(Double humedad, Double temperatura,String temporada,String pronostico, MuroEntity muro);
    
    Boolean tomarDecisionRiego(Double humedad, Double temperatura, String pronostico, MuroEntity muro);
}
