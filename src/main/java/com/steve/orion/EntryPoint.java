package com.steve.orion;

import com.steve.orion.annotations.Entry;
import com.steve.orion.annotations.UseApplication;
import com.steve.orion.annotations.UseRendererAPI;
import com.steve.orion.renderer.RendererAPI;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;

public abstract class EntryPoint {
    private static EntryPoint instance;

    // 这里不是抽象，框架负责创建Application
    protected Application createApplication() {
        Class<?> clazz = this.getClass();

        if (clazz.isAnnotationPresent(UseApplication.class)) {
            try {
                Class<? extends Application> appClass = clazz.getAnnotation(UseApplication.class).value();
                return appClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Cannot create Application instance.", e);
            }
        }

        throw new IllegalStateException("No @UseApplication annotation found and createApplication() not overridden.");
    }

    public void run() {
        System.gc();
        try (Application app = createApplication()) {
            app.run();
        }
    }

    public static void main(String[] args) {
        if (instance == null) {
            instance = findEntryPoint();
        }

        if (instance == null) {
            System.err.println("No EntryPoint registered!");
            System.exit(-1);
        }

        instance.run();
    }

    private static EntryPoint findEntryPoint() {
        try {
            Reflections reflections = new Reflections(new ConfigurationBuilder()
                    .setUrls(ClasspathHelper.forJavaClassPath())
                    .setScanners(
                            Scanners.TypesAnnotated,
                            Scanners.SubTypes
                    )
            );
            Set<Class<?>> entryClasses = reflections.getTypesAnnotatedWith(Entry.class);

            if (entryClasses.isEmpty()) {
                System.err.println("No class with @Entry annotation found!");
                return null;
            }

            Class<?> clazz = entryClasses.iterator().next();

            if (!EntryPoint.class.isAssignableFrom(clazz)) {
                System.err.println("@Entry class must extend EntryPoint");
                return null;
            }

            if (clazz.isAnnotationPresent(UseRendererAPI.class)) {
                RendererAPI.API api = clazz.getAnnotation(UseRendererAPI.class).value();
                RendererAPI.setApi(api);
            }

            Application appInstance = null;
            if (clazz.isAnnotationPresent(UseApplication.class)) {
                Class<? extends Application> appClass = clazz.getAnnotation(UseApplication.class).value();
                appInstance = appClass.getDeclaredConstructor().newInstance();
            }

            EntryPoint entry;
            if (appInstance != null) {
                final Application finalApp = appInstance;
                entry = new EntryPoint() {
                    @Override
                    protected Application createApplication() {
                        return finalApp;
                    }
                };
            } else {
                entry = (EntryPoint) clazz.getDeclaredConstructor().newInstance();
            }

            return entry;

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
}