package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.Report;

public class ConsoleTestOutputService implements TestOutputService {
    public void print(Report report) {
        System.out.println(report.getFio() + ":");
        System.out.println(String.format("%d/%d", report.getSuccessAnswerCount(), report.getQuestionCount()));
        System.out.println(report.isPassed() ? "Тест успешно пройден" : "Тест провален");
    }
}
