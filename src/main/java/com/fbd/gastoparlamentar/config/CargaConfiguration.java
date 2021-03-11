package com.fbd.gastoparlamentar.config;

import com.fbd.gastoparlamentar.service.CargaSimplificadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class CargaConfiguration implements
        ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    CargaSimplificadaService cargaSimplificadaService;

    @Override public void onApplicationEvent(ContextRefreshedEvent event) {

        cargaSimplificadaService.cargaCsv();
    }


}
