package de.fraunhofer.fokus.ttcn.tri;

import org.etsi.ttcn.tri.TriException;

public class TriExceptionImpl implements TriException {
    
    private byte[] encodedException;
    private int numberOfBits;

    @Override
    public byte[] getEncodedException() {
        return encodedException;
    }

    @Override
    public void setEncodedException(byte[] message) {
        encodedException = message;
    }

    @Override
    public int getNumberOfBits() {
        return numberOfBits;
    }

    @Override
    public void setNumberOfBits(int amount) {
        numberOfBits = amount;
    }

    @Override
    public boolean equals(TriException exc) {
        return encodedException.equals(exc.getEncodedException());
    }

}
