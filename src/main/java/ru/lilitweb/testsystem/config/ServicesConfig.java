package ru.lilitweb.testsystem.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.lilitweb.testsystem.service.*;

import java.util.Locale;

@Configuration
public class ServicesConfig {

    @Bean
    public LocalisationService localisationService(MessageSource source) {
        return new LocalisationServiceImpl(source);
    }

    @Bean
    public TestInputService inputService(LocalisationService sourse) {
        return new ConsoleTestInputService(System.in, System.out, sourse);
    }

    @Bean
    public TestOutputService outputService(LocalisationService sourse) {
        return new ConsoleTestOutputService(System.out, sourse);
    }

    @Bean
    public QuestionsLoaderService questionsLoaderService() {
        return new CsvQuestionsLoaderService();
    }

    @Bean
    public TestService testService(TestInputService input, TestOutputService output) {
        return new TestServiceImpl(input, output);
    }
}
