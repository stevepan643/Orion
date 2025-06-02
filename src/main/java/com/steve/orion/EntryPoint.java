package com.steve.orion;

public abstract class EntryPoint {
    private static EntryPoint instance;
    protected abstract Application createApplication();

    protected static void register(EntryPoint entryPoint) {
        instance = entryPoint;
    }

    public void run() {
        try(Application app = createApplication()) {
            app.run();
        }
    }


    public static void main(String[] args) {
        if (instance == null) {
            System.err.println("No EntryPoint registered!");
            System.exit(-1);
        }
        instance.run();
    }
}
