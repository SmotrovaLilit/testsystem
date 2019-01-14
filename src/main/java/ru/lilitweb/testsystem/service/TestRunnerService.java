package ru.lilitweb.testsystem.service;

import java.io.IOException;

public interface TestRunnerService {
    void process() throws TestInputException, IOException;
}
