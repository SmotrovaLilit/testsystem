package ru.lilitweb.testsystem.service;

import org.junit.jupiter.api.Test;
import ru.lilitweb.testsystem.Report;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ConsoleTestOutputServiceTest {

    @Test
    void print() {
        PrintStream stream = mock(PrintStream.class);
        TestOutputService service = new ConsoleTestOutputService(stream);
        Report report = new Report();
        report.setPassed(true);
        report.setQuestionCount(2);
        report.incSuccessAnswerCount();
        report.incSuccessAnswerCount();
        report.setFio("fio");

        service.print(report);
        verify(stream).println("Тест успешно пройден");
    }

    @Test
    void printFailTest() {
        PrintStream stream = mock(PrintStream.class);
        TestOutputService service = new ConsoleTestOutputService(stream);
        Report report = new Report();
        report.setPassed(false);
        report.setQuestionCount(1);
        report.incSuccessAnswerCount();
        report.setFio("fio");

        service.print(report);
        verify(stream).println("Тест провален");
    }
}
