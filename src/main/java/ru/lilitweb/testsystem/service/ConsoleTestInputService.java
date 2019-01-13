package ru.lilitweb.testsystem.service;

import java.io.*;

public class ConsoleTestInputService implements TestInputService {
    private BufferedReader inputReader;
    private PrintStream outputStream;
    private LocalisationService source;


    public ConsoleTestInputService(InputStream input, PrintStream output, LocalisationService source) {
        this.inputReader = new BufferedReader(new InputStreamReader(input));
        this.outputStream = output;
        this.source = source;
    }

    public String getUserAnswer(String question) throws TestInputException {
        try {
            outputStream.println(question);
            return inputReader.readLine();
        } catch (IOException e) {
            throw new TestInputException(e);
        }
    }

    public String getPersonFio() throws TestInputException {
        outputStream.println(source.getMessage("input.name.label", null));
        try {
            return inputReader.readLine();
        } catch (IOException e) {
            throw new TestInputException(e);
        }
    }
}
