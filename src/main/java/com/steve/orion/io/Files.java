package com.steve.orion.io;

import com.steve.orion.Log.Loggable;

import java.io.IOException;
import java.nio.file.Path;

public class Files implements Loggable {
    public static String read(String path) {
        try {
            return java.nio.file.Files.readString(Path.of(path));
        } catch (IOException e) {
            CoreLog.error("Error reading file: {}", path);
            return "";
        }
    }
}
