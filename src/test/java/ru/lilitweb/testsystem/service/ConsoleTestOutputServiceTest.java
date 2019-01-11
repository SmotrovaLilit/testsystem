package ru.lilitweb.testsystem.service;

import org.junit.jupiter.api.Test;
import ru.lilitweb.testsystem.models.Report;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ConsoleTestOutputServiceTest {

    @Test
    void printTestExecutionReport() {
        PrintStream stream = mock(PrintStream.class);
        TestOutputService service = new ConsoleTestOutputService(stream);
        Report report = new Report();
        report.setPassed(true);
        report.setQuestionCount(2);
        report.incSuccessAnswerCount();
        report.incSuccessAnswerCount();
        report.setFio("fio");

        service.printTestExecutionReport(report);
        verify(stream).println("Тест успешно пройден");
    }

    @Test
    void printTestExecutionReportFailTest() {
        PrintStream stream = mock(PrintStream.class);
        TestOutputService service = new ConsoleTestOutputService(stream);
        Report report = new Report();
        report.setPassed(false);
        report.setQuestionCount(1);
        report.incSuccessAnswerCount();
        report.setFio("fio");

        service.printTestExecutionReport(report);
        verify(stream).println("Тест провален");
    }
}
