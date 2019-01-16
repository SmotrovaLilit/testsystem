package ru.lilitweb.testsystem.service;


import ru.lilitweb.testsystem.models.QuestionModel;

import java.io.IOException;
import java.util.List;

public interface QuestionsLoaderService {
    List<QuestionModel> loadQuestions() throws IOException;
}
