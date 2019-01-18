package ru.lilitweb.testsystem.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.lilitweb.testsystem.service.TestInputException;
import ru.lilitweb.testsystem.service.TestRunnerService;

import java.io.IOException;

@ShellComponent
public class TestCommands {
    private final TestRunnerService service;

    @Autowired
    public TestCommands(TestRunnerService service) {
        this.service = service;
    }

    @ShellMethod("Run test")
    public void test() throws IOException, TestInputException {
        service.process();
    }
}
