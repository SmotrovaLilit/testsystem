package ru.lilitweb.testsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.lilitweb.testsystem.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ConsoleTestInputServiceTest {
    @Mock
    private LocalisationService source;

    @Mock
    private PrintStream stream;

    @BeforeEach
    void setUp() {
        source = mock(LocalisationService.class);
        stream = mock(PrintStream.class);
    }

    @Test
    void loadAnswers() throws TestInputException {
        InputStream reader = new ByteArrayInputStream("answer of tester\n".getBytes());
        TestInputService service = new ConsoleTestInputService(reader, stream, source);

        List<Question> questions = new ArrayList<>();
        questions.add(new Question("question", "answer"));

        Map<Question, String> answers = service.loadAnswers(questions);
        assertEquals(answers.get(questions.get(0)), "answer of tester");
        verify(stream).println(questions.get(0).getQuestionContent());
    }



    @Test
    void getPersonFio() throws TestInputException {
        InputStream reader = new ByteArrayInputStream("fio".getBytes());
        TestInputService service = new ConsoleTestInputService(reader, stream, source);

        String fio = service.getPersonFio();
        assertEquals(fio, "fio");
    }
}
