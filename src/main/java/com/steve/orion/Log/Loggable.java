package com.steve.orion.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Loggable {
    Logger CoreLog = logger("ORION");
    Logger AppLog = logger("APP");

    private static Logger logger(String name) {
        return LoggerFactory.getLogger(name);
    }
}
