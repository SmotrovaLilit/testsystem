package ru.lilitweb.testsystem.commandLineRunners;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.lilitweb.testsystem.service.TestRunnerService;


@Component
public class TestRunner implements CommandLineRunner {
    private final TestRunnerService testService;

    @Autowired
    public TestRunner(TestRunnerService testService) {
        this.testService = testService;
    }

    @Override
    public void run(String... args) throws Exception {
        testService.process();
    }
}
