package ru.lilitweb.testsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lilitweb.testsystem.models.QuestionModel;
import ru.lilitweb.testsystem.models.TestResultModel;

import java.io.IOException;
import java.util.List;

@Service
public class TestRunnerServiceImpl implements TestRunnerService {
    private final List<QuestionModel> questions;
    private int questionNumber;
    private TestResultModel testResultModel;
    private boolean isFinishedAllQuestionsAnswering;
    private boolean isWaitAnswer;

    @Autowired
    public TestRunnerServiceImpl(QuestionsLoaderService questionsLoaderService) throws IOException {
        this.questions = questionsLoaderService.loadQuestions();
        resetValues();
    }

    private void resetValues() {
        testResultModel = null;
        isFinishedAllQuestionsAnswering = false;
        isWaitAnswer = false;
        questionNumber = -1;
    }

    @Override
    public void start(String fio) {
        testResultModel = new TestResultModel(questions, fio);
    }

    @Override
    public void stop() {
        resetValues();
    }


    @Override
    public boolean isStarted() {
        return testResultModel != null;
    }

    @Override
    public String getPerson() {
        return testResultModel == null ? "" : testResultModel.getFio();
    }

    @Override
    public String getNextQuestion() throws TestQuestionsFinishedException {
        if (!canAskNextQuestion()) {
            throw new TestQuestionsFinishedException();
        }

        isWaitAnswer = true;
        return questions.get(++questionNumber).getQuestionContent();
    }

    @Override
    public void answerQuestion(String answer) {
        isWaitAnswer = false;
        QuestionModel questionModel = questions.get(questionNumber);
        testResultModel.setQuestionAnswer(questionModel, answer);
        if (!canAskNextQuestion()) {
            isFinishedAllQuestionsAnswering = true;
        }
    }

    @Override
    public boolean isFinishedAllQuestionsAnswering() {
        return isFinishedAllQuestionsAnswering;
    }

    @Override
    public int getCurrentQuestionNumber() {
        return questionNumber + 1;
    }

    @Override
    public int getQuestionsCount() {
        return questions.size();
    }

    @Override
    public boolean isWaitAnswer() {
        return isWaitAnswer;
    }

    @Override
    public boolean canAskNextQuestion() {
        if (!isStarted()) {
            return false;
        }

        if (isWaitAnswer) {
            return false;
        }

        return questions.size() > getCurrentQuestionNumber();
    }

    public TestResultModel getResult() {
        return testResultModel;
    }
}
