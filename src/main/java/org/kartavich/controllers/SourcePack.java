package org.kartavich.controllers;

public class SourcePack<T> {
    public boolean haveError;
    public String description;
    public T data;

    public SourcePack(T data){
        this.data = data;
        haveError = false;
    }
    public SourcePack(T data, String description) {
        this.data = data;
        this.description = description;
        haveError = true;
    }

}
