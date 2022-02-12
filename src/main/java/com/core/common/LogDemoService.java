package com.core.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.inject.Provider;


@Service
@RequiredArgsConstructor
public class LogDemoService {

    // private final MyLogger myLogger;
    private final Provider<MyLogger> loggerProvider;

    public void logic(String id) {
        MyLogger myLogger = loggerProvider.get();
        myLogger.log("service id : " + id);
    }
}
