package com.stackroute.muzixservice.startup;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerStartup implements ApplicationListener<ContextRefreshedEvent> {
    private MuzixRepository muzixRepository;

    public ApplicationListenerStartup(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Muzix muzix=new Muzix(2,"coca cola","Lukka chuppi Track");
        muzixRepository.save(muzix);
    }
}
