package com.stackroute.muzixservice.startup;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class ApplicationListenerStartup implements ApplicationListener<ContextRefreshedEvent> {

    private MuzixRepository muzixRepository;

    @Value("${spring.muzix.muzixId1}")
    private int muzixId;

    @Value("${spring.muzix.muzixName1}")
    private String muzixName;

    @Value("${spring.muzix.comments1}")
    private String comments;

    public ApplicationListenerStartup(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Muzix muzix=new Muzix(muzixId,muzixName,comments);
        muzixRepository.save(muzix);
    }
}
