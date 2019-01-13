package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.models.ReportModel;

public interface TestOutputService {
    void printTestReport(ReportModel report);
    void printQuestion(String question);
    void printFioQuestion();
}
