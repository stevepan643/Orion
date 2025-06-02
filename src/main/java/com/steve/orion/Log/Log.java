package com.steve.orion.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    public static Logger CoreLog = logger("ORION");
    public static Logger AppLog = logger("APP");

    private static Logger logger(String name) {
        return LoggerFactory.getLogger(name);
    }
}
