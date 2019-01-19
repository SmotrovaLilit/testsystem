package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.models.TestResultModel;

public interface TestRunnerService {
    void start(String fio);

    void stop();

    void answerQuestion(String answer);

    TestResultModel getResult();

    String getPerson();

    String getNextQuestion() throws TestQuestionsFinishedException;

    boolean isWaitAnswer();

    boolean canAskNextQuestion();

    boolean isStarted();

    boolean isFinishedAllQuestionsAnswering();

    int getCurrentQuestionNumber();

    int getQuestionsCount();
}
