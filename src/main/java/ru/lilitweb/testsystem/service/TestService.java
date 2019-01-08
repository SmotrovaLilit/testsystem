package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.Question;
import ru.lilitweb.testsystem.Report;

import java.util.List;

public interface TestService {
    Report process(List<Question> questions) throws TestInputException;
    void print(Report report);
}
