package ru.lilitweb.testsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.lilitweb.testsystem.Question;
import ru.lilitweb.testsystem.Report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class TestServiceImplTest {
    private TestService testService;

    @Mock
    private TestOutputService outputService;

    @Mock
    private TestInputService inputService;

    @BeforeEach
    void setUp() {
        inputService = mock(TestInputService.class);
        outputService = mock(TestOutputService.class);
        testService = new TestServiceImpl(inputService, outputService);

    }

    @Test
    void process() throws TestInputException {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("question1", "answer1"));
        questions.add(new Question("question2", "answer2"));
        when(inputService.getPersonFio()).thenReturn("fio");
        when(inputService.loadAnswers(questions)).thenReturn(getAnswers(questions));

        Report report = testService.process(questions);

        assertEquals("fio", report.getFio());
        assertEquals(questions.size(), report.getQuestionCount());
        assertEquals(questions.size(), report.getQuestionCount());
        assertTrue(report.isPassed());
    }

    @Test
    void processFail() throws TestInputException {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("question1", "answer1"));
        questions.add(new Question("question2", "answer2"));

        when(inputService.getPersonFio()).thenReturn("fio");

        Map<Question, String> answers = new HashMap<>();
        answers.put(questions.get(0), questions.get(0).getCorrectAnswer());
        answers.put(questions.get(1), "fail answer");
        when(inputService.loadAnswers(questions)).thenReturn(answers);

        Report report = testService.process(questions);
        assertEquals("fio", report.getFio());
        assertEquals(questions.size(), report.getQuestionCount());
        assertEquals(1, report.getSuccessAnswerCount());
        assertFalse(report.isPassed());
    }

    private Map<Question, String> getAnswers(List<Question> questions) {
        Map<Question, String> answers = new HashMap<>();
        for (Question q : questions) {
            answers.put(q, q.getCorrectAnswer());
        }
        return answers;
    }

    @Test
    void print() {
        Report report = new Report();
        testService.print(report);
        verify(outputService, times(1)).print(report);
    }
}
