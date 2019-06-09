package com.stackroute.muzixservice.startup;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private MuzixRepository muzixRepository;

    @Autowired
    private Environment environment;

    public CommandLineAppStartupRunner(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }

    @Override
    public void run(String... args) throws Exception {
       Muzix muzix=Muzix.builder()
               .muzixId(Integer.parseInt(environment.getProperty("spring.muzix.muzixId2")))
               .muzixName(environment.getProperty("spring.muzix.muzixName2"))
               .comments(environment.getProperty("spring.muzix.comments2"))
               .build();
        muzixRepository.save(muzix);
    }
}
