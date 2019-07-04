/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.services;

import cl.duoc.floresycactus.entities.DecisionEntity;
import cl.duoc.floresycactus.repositories.IDecisionRepository;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jpino
 */
@Service
public class DecisionServiceImpl implements IDecisionService{
    
    @Autowired
    private IDecisionRepository repository;

    @Override
    public List<DecisionEntity> findAll() {
        return (List<DecisionEntity>) repository.findAll();
    }

    @Override
    public DecisionEntity save(DecisionEntity desicion) {
        return repository.save(desicion);
    }

    @Override
    public DecisionEntity findById(Long id) {
       return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(DecisionEntity desicion) {
        repository.delete(desicion);
    }
    
    @Override
    public String obtenerTemporada(){        
    
        Calendar fecha = Calendar.getInstance();
        int mes = fecha.get(Calendar.MONTH) + 1;
        String temporada = "";
        
        switch(mes){
        case 12:
        case 1:
        case 2:
        temporada = "Verano";
        break;

        case 3:
        case 4:
        case 5:
         temporada = "Otoño";
        break;

        case 6:
        case 7:
        case 8:
         temporada = "Invierno";
        break;

        case 9:
        case 10:
        case 11:
         temporada = "Primavera";
        break;

        default:
         temporada = "";
         break;
       }  

        return temporada; 
    }
    
    public Boolean tomarDecisionRiego(String Temporada, Double humedad, Double temperatura, String pronostico){
    
        return true;
    }
}
