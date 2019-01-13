package ru.lilitweb.testsystem.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;


public class LocalisationServiceImpl implements LocalisationService {

    private MessageSource source;
    private Locale locale;

    public LocalisationServiceImpl(MessageSource source) {
        this.source = source;
        this.locale = Locale.getDefault();
    }

    @Override
    public String getMessage(String code, Object[] var) {
        return source.getMessage(code, var, locale);
    }
}
