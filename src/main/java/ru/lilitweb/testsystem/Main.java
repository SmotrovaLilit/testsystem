package ru.lilitweb.testsystem;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.lilitweb.testsystem.service.LocalisationService;
import ru.lilitweb.testsystem.service.TestInputException;
import ru.lilitweb.testsystem.service.TestRunnerService;

import java.io.IOException;

@Configuration
@ComponentScan
public class Main {
    public static void main(String[] args) throws TestInputException, IOException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        LocalisationService localisationService = context.getBean(LocalisationService.class);
        String csvFile = localisationService.getMessage("questions.file", null);

        TestRunnerService testService = context.getBean(TestRunnerService.class);
        testService.process(csvFile);
    }
}
