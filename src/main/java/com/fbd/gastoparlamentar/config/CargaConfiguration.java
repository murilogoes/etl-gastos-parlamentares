package com.fbd.gastoparlamentar.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fbd.gastoparlamentar.service.CargaService;
import com.fbd.gastoparlamentar.service.CargaSimplificadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class CargaConfiguration implements
        ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    CargaService cargaService;

    @Autowired
    CargaSimplificadaService cargaSimplificadaService;

    @Override public void onApplicationEvent(ContextRefreshedEvent event) {

        cargaSimplificadaService.cargaCsv();


/*
        try {
            //cargaService.legislatura();
            // cargaService.partido();
            cargaService.parlamentar();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
*/

//        cargaService.lideranca();
//        cargaService.subCota();
//        cargaService.fornecedor();
//        cargaService.despesa();


    }


}
