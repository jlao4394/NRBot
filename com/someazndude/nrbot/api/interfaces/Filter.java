package com.someazndude.nrbot.api.interfaces;

public interface Filter<T> {
    public boolean accept(T t);
}
