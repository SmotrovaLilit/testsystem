package ru.lilitweb.testsystem.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileResolverService {
    BufferedReader getFileReader() throws IOException;
}
