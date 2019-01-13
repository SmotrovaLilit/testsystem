package ru.lilitweb.testsystem.service;

public interface TestInputService {
    String getUserAnswer(String question) throws TestInputException;
    String getPersonFio() throws TestInputException;
}
