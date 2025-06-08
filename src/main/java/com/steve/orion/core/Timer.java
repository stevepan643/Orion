package com.steve.orion.core;

public record Timer(float time) {
    public float getSeconds() { return time; }
    public float getMilliseconds() { return time * 1000; }
}