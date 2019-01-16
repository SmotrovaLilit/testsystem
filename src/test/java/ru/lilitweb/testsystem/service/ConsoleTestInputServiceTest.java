package ru.lilitweb.testsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
