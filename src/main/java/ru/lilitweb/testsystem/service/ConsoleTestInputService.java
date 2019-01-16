package ru.lilitweb.testsystem.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConsoleTestInputService implements TestInputService {
    private BufferedReader inputReader;

    public ConsoleTestInputService(InputStream input) {
        this.inputReader = new BufferedReader(new InputStreamReader(input));
    }

    public String getUserAnswer(String question) throws TestInputException {
        try {
            return inputReader.readLine();
        } catch (IOException e) {
            throw new TestInputException(e);
        }
    }

    public String getPersonFio() throws TestInputException {
        try {
            return inputReader.readLine();
        } catch (IOException e) {
            throw new TestInputException(e);
        }
    }
}
