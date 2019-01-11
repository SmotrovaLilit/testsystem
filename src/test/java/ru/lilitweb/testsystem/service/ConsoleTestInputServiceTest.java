package ru.lilitweb.testsystem.service;

import org.junit.jupiter.api.Test;
import ru.lilitweb.testsystem.models.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ConsoleTestInputServiceTest {

    @Test
    void readAnswers() throws TestInputException {
        InputStream reader = new ByteArrayInputStream("answer of tester\n".getBytes());
        PrintStream stream = mock(PrintStream.class);
        TestInputService service = new ConsoleTestInputService(reader, stream);

        List<Question> questions = new ArrayList<>();
        questions.add(new Question("question", "answer"));

        Map<Question, String> answers = service.readAnswers(questions);
        assertEquals(answers.get(questions.get(0)), "answer of tester");
        verify(stream).println(questions.get(0).getQuestionContent());
    }



    @Test
    void getPersonFio() throws TestInputException {
        PrintStream stream = mock(PrintStream.class);
        InputStream reader = new ByteArrayInputStream("fio".getBytes());
        TestInputService service = new ConsoleTestInputService(reader, stream);

        String fio = service.getPersonFio();
        assertEquals(fio, "fio");
    }
}
