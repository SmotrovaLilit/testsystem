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
    public TestInputService inputService(LocalisationService sourse) {
        return new ConsoleTestInputService(System.in, System.out, sourse);
    }

    @Bean
    public TestOutputService outputService(LocalisationService sourse) {
        return new ConsoleTestOutputService(System.out, sourse);
    }

    @Bean
    public TestsLoaderService testsLoaderService() {
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
            TestsLoaderService loader,
            FileResolverService resolver
    ) {
        return new TestRunnerServiceImpl(input, output, loader, resolver);
    }
}
