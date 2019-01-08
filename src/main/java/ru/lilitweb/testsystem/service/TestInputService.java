package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.Question;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TestInputService {
    Map<Question, String> loadAnswers(List<Question> questions) throws TestInputException;

    String getPersonFio() throws TestInputException;
}
