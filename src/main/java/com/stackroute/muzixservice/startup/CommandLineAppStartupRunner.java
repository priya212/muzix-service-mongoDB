package com.stackroute.muzixservice.startup;

import com.stackroute.muzixservice.domain.Muzix;
import com.stackroute.muzixservice.repository.MuzixRepository;
import com.stackroute.muzixservice.service.MuzixServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private static  final Logger logger=LoggerFactory.getLogger(CommandLineAppStartupRunner.class);
    private MuzixServices muzixServices;

    public CommandLineAppStartupRunner(MuzixServices muzixServices) {
        this.muzixServices = muzixServices;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Application started with command line argument", Arrays.toString(args));
        Muzix muzix=new Muzix(1,"Kalank","Kalank Track");
        muzixServices.saveMuzixs(muzix);
    }
}
