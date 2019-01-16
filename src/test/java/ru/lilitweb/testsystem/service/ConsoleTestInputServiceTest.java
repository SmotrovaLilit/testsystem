package ru.lilitweb.testsystem.service;

import org.junit.jupiter.api.Test;
import ru.lilitweb.testsystem.service.ConsoleTestInputService;
import ru.lilitweb.testsystem.service.TestInputException;
import ru.lilitweb.testsystem.service.TestInputService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleTestInputServiceTest {
    @Test
    void getUserAnswer() throws TestInputException {
        InputStream reader = new ByteArrayInputStream("answer of tester\n".getBytes());
        TestInputService service = new ConsoleTestInputService(reader);

        String answer = service.getUserAnswer("question");

        assertEquals(answer, "answer of tester");
    }

    @Test
    void getPersonFio() throws TestInputException {
        InputStream reader = new ByteArrayInputStream("fio".getBytes());
        TestInputService service = new ConsoleTestInputService(reader);

        String fio = service.getPersonFio();
        assertEquals(fio, "fio");
    }
}
