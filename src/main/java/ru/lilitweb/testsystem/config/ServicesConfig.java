package ru.lilitweb.testsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.lilitweb.testsystem.service.*;

@Configuration
public class ServicesConfig {

    @Bean
    public TestInputService inputService() {
        return new ConsoleTestInputService(System.in, System.out);
    }

    @Bean
    public TestOutputService outputService() {
        return new ConsoleTestOutputService(System.out);
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
