package ru.lilitweb.testsystem.service;

import ru.lilitweb.testsystem.Question;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsoleTestInputService implements TestInputService {
    private BufferedReader inputReader;
    private PrintStream outputStream;

    public ConsoleTestInputService(InputStream input, PrintStream output) {
        this.inputReader = new BufferedReader(new InputStreamReader(input));
        this.outputStream = output;
    }

    public Map<Question, String> loadAnswers(List<Question> questions) throws TestInputException {
        Map<Question, String> res = new HashMap<>();
        try {
            for (Question q : questions) {
                outputStream.println(q.getQuestionContent());
                String answer = inputReader.readLine();
                res.put(q, answer);
            }
        } catch (IOException e) {
            throw new TestInputException(e);
        }

        return res;
    }

    public String getPersonFio() throws TestInputException {
        outputStream.println("Фамилия имя:");
        try {
            return inputReader.readLine();
        } catch (IOException e) {
            throw new TestInputException(e);
        }
    }
}
