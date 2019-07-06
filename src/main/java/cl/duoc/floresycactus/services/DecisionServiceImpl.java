/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.services;

import cl.duoc.floresycactus.entities.DecisionEntity;
import cl.duoc.floresycactus.entities.HorariosRiegoEntity;
import cl.duoc.floresycactus.entities.MuroEntity;
import cl.duoc.floresycactus.repositories.IDecisionRepository;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
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
    
    @Autowired
    private IHorariosRiegoService horarioRepository;

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
    
    public String obtenerHoraActual(){
        LocalDateTime locaDate = LocalDateTime.now();
        return locaDate.getHour() + ":" + locaDate.getMinute();
    }
    
    public DecisionEntity NuevoObjeto(Double humedad, Double temperatura,String temporada,String pronostico, MuroEntity muro){
        
        Date date = new Date();        
        DecisionEntity decision = new DecisionEntity();
        decision.setHumedad(humedad);
        decision.setTemperatura(temperatura);
        decision.setTemporada(temporada);
        decision.setPronostico(pronostico);
        decision.setFechaCreacion(date);
        decision.setMuroId(muro);
        return decision;
    }
    
    @Override
    public Boolean tomarDecisionRiego(Double humedad, Double temperatura, String pronostico, MuroEntity muro){
        
        Boolean respuesta       =  false;
        String temporada        = this.obtenerTemporada();
        String horaActual       = this.obtenerHoraActual(); //"08:00";
        List<HorariosRiegoEntity> horarios = this.horarioRepository.findHorariosPorMuro(muro.getId());
        
        for (HorariosRiegoEntity horario : horarios) {
            
            String hour = horario.getHorario();
            
            if (hour.equals(horaActual)) {                
                if (temporada == "Verano") {
                    if (temperatura >= 16.00) {
                       if (humedad < 50.00) {                           
                           respuesta = true;       
                       }
                    }
                }
                if (temporada == "Otoño") {
                    if(pronostico != "Lluvia" || pronostico != "Neblina"){            
                        if (temperatura > 14.00) {
                            if (humedad < 50.00) {
                               respuesta = true;  
                            }
                        }
                    }
                }
                if (temporada == "Invierno") {
                    if(pronostico != "Lluvia" || pronostico != "Nieve" || pronostico != "Neblina"){            
                        if (temperatura > 10.00) {
                            if (humedad < 40.00) {
                               respuesta = true;  
                            }
                        }
                    }
                }
                if (temporada == "Primavera") {
                    if(pronostico != "Lluvia"){            
                        if (temperatura > 14.00) {
                            if (humedad < 40.00) {
                               respuesta = true;  
                            }
                        }
                    }
                }
            }
        }
                    
        DecisionEntity de = NuevoObjeto(humedad, temperatura, temporada, pronostico, muro);
        this.save(de); 
        
        return respuesta;
    }
}
