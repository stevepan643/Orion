package com.steve.orion.core;

public class Timestep {
    private final float time;

    public Timestep(float time) { this.time = time; }

    public float getSeconds() { return time; }
    public float getMilliseconds() { return time * 1000; }
}
