/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.floresycactus.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 *
 * @author jpino
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DecisionServiceUnitTest {
    
    @Autowired
    private IDecisionService service;
    
    @Test
    public void itShouldObtenerTemporada() {
        Assert.assertEquals("Invierno", service.obtenerTemporada());
    }
    
    @Test
    public void itNotShouldObtenerTemporada() {
        Assert.assertNotEquals("Verano", service.obtenerTemporada());
    }
    
    @Test
    public void itNotShouldObtenerHoraActual() {
        Assert.assertNotEquals("10:00", service.obtenerHoraActual());
    }
}
