package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface QuestionsLoaderService {
    List<Question> getQuestions(BufferedReader reader) throws IOException;
}
