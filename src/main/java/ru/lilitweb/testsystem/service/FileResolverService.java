package ru.lilitweb.testsystem.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

public interface FileResolverService {
    BufferedReader getFileReader() throws FileNotFoundException;
}
