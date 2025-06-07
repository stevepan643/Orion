package com.steve.orion.annotations;

import com.steve.orion.renderer.RendererAPI;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseRendererAPI {
    RendererAPI.API value();
}
