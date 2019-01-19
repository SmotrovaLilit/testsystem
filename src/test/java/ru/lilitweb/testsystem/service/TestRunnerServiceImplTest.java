package ru.lilitweb.testsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.lilitweb.testsystem.models.QuestionModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestRunnerServiceImplTest {

    @Mock
    QuestionsLoaderService questionsLoaderService;

    private TestRunnerService runner;

    private List<QuestionModel> questions;

    @BeforeEach
    void setUp() throws IOException {
        questionsLoaderService = mock(QuestionsLoaderService.class);
        questions = new ArrayList<>();
        questions.add(new QuestionModel("question1", "answer1"));
        questions.add(new QuestionModel("question2", "answer2"));
        when(questionsLoaderService.loadQuestions()).thenReturn(questions);

        runner = new TestRunnerServiceImpl(questionsLoaderService);
    }

    @Test
    void start() throws IOException {
        String fio = "fio";
        runner.start(fio);
        assertEquals(fio, runner.getPerson());
        assertTrue(runner.isStarted());
    }

    @Test
    void stop() {
        runner.stop();
        assertEquals(0, runner.getCurrentQuestionNumber());
        assertEquals("", runner.getPerson());
        assertFalse(runner.isStarted());
        assertFalse(runner.isFinishedAllQuestionsAnswering());
    }

    @Test
    void getPerson() {
        assertEquals("", runner.getPerson());

        String fio = "fio";
        runner.start(fio);
        assertEquals(fio, runner.getPerson());
    }

    @Test
    void getNextQuestion() throws TestQuestionsFinishedException {
        String fio = "fio";
        runner.start(fio);
        String question = runner.getNextQuestion();
        assertEquals(questions.get(0).getQuestionContent(), question);
    }

    @Test
    void answerQuestion() throws TestQuestionsFinishedException {
        runner.start("fio");

        runner.getNextQuestion();
        runner.answerQuestion("answer");
        assertFalse(runner.isWaitAnswer());
        assertFalse(runner.isFinishedAllQuestionsAnswering());

        runner.getNextQuestion();
        runner.answerQuestion("answer");
        assertFalse(runner.isWaitAnswer());
        assertTrue(runner.isFinishedAllQuestionsAnswering());
    }


    @Test
    void getCurrentQuestionNumber() throws TestQuestionsFinishedException {
        runner.start("fio");
        assertEquals(0, runner.getCurrentQuestionNumber());
        runner.getNextQuestion();
        assertEquals(1, runner.getCurrentQuestionNumber());
    }

    @Test
    void getQuestionsCount() {
        assertEquals(questions.size(), runner.getQuestionsCount());
    }

    @Test
    void canAskNextQuestion() {
        assertFalse(runner.canAskNextQuestion());
    }

    @Test
    void canAskNextQuestionIfTestStarted() {
        runner.start("fio");
        assertTrue(runner.canAskNextQuestion());
    }

    @Test
    void canAskNextQuestionIfTestStopped() {
        runner.start("fio");
        runner.stop();
        assertFalse(runner.canAskNextQuestion());
    }

    @Test
    void canAskNextQuestionIfTestFinished() throws TestQuestionsFinishedException {
        runner.start("fio");
        runner.getNextQuestion();
        runner.answerQuestion("");
        runner.getNextQuestion();
        runner.answerQuestion("");

        assertFalse(runner.canAskNextQuestion());
    }

    @Test
    void canAskNextQuestionIfTestWaitAnswer() throws TestQuestionsFinishedException {
        runner.start("fio");
        runner.getNextQuestion();
        assertFalse(runner.canAskNextQuestion());
    }
}
