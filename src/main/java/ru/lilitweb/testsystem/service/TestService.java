package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.models.Question;
import ru.lilitweb.testsystem.models.Report;

import java.util.List;

public interface TestService {
    Report process(List<Question> questions) throws TestInputException;
    void print(Report report);
}
