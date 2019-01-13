package ru.lilitweb.testsystem.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.lilitweb.testsystem.service.*;

@Configuration
public class ServicesConfig {

    @Bean
    public LocalisationService localisationService(MessageSource source) {
        return new LocalisationServiceImpl(source);
    }

    @Bean
    public TestInputService inputService() {
        return new ConsoleTestInputService(System.in);
    }

    @Bean
    public TestOutputService outputService(LocalisationService source) {
        return new ConsoleTestOutputService(System.out, source);
    }
}
