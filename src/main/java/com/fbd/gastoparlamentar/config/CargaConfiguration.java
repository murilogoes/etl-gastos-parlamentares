package com.fbd.gastoparlamentar.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fbd.gastoparlamentar.service.CargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class CargaConfiguration implements
        ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    CargaService cargaService;

    @Override public void onApplicationEvent(ContextRefreshedEvent event) {

//        cargaService.lideranca();
        cargaService.despesa();

/*
        try {
            //cargaService.legislatura();
            // cargaService.partido();
            cargaService.parlamentar();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
*/

    }


}
