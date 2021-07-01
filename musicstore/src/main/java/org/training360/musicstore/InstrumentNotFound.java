package org.training360.musicstore;

public class InstrumentNotFound extends IllegalArgumentException{

    public InstrumentNotFound(String message) {
        super(message);
    }
}
