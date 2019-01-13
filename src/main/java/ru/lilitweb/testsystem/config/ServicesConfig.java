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
    public TestOutputService outputService(LocalisationService sourse) {
        return new ConsoleTestOutputService(System.out, sourse);
    }

    @Bean
    public QuestionsLoaderService testsLoaderService() {
        return new CsvTestsLoaderService();
    }

    @Bean
    public FileResolverService fileResolverService() {
        return new FileResolverServiceImpl();
    }

    @Bean
    public TestRunnerService testService(
            TestInputService input,
            TestOutputService output,
            QuestionsLoaderService loader,
            FileResolverService resolver
    ) {
        return new TestRunnerServiceImpl(input, output, loader, resolver);
    }
}
