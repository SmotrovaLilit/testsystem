package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.models.Question;

import java.util.List;
import java.util.Map;

public interface TestInputService {
    Map<Question, String> readAnswers(List<Question> questions) throws TestInputException;

    String getPersonFio() throws TestInputException;
}
