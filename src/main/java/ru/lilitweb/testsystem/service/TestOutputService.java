package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.models.Report;

public interface TestOutputService {
    void printTestExecutionReport(Report report);
}
