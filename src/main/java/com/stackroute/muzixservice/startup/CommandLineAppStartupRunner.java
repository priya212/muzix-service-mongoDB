package com.stackroute.muzixservice.startup;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.repository.MuzixRepository;
import com.stackroute.muzixservice.service.MuzixServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@PropertySource("classpath:application.properties")
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private static  final Logger logger=LoggerFactory.getLogger(CommandLineAppStartupRunner.class);
    private MuzixRepository muzixRepository;

    @Autowired
    private Environment environment;

    public CommandLineAppStartupRunner(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }

    @Override
    public void run(String... args) throws Exception {
       Muzix muzix=Muzix.builder()
               .trackId(Integer.parseInt(environment.getProperty("spring.muzix.trackId2")))
               .trackName(environment.getProperty("spring.muzix.trackName2"))
               .comments(environment.getProperty("spring.muzix.comments2"))
               .build();
        muzixRepository.save(muzix);
    }
}
