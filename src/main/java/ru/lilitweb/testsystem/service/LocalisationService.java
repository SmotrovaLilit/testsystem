package ru.lilitweb.testsystem.service;

import org.springframework.lang.Nullable;

public interface LocalisationService {
    public String getMessage(String code, @Nullable Object[] var);
}
