package de.fraunhofer.fokus.ttcn.tri;
import org.etsi.ttcn.tri.*;

public class TriMessageImpl implements TriMessage {
    private byte[] msg;

    public byte[] getEncodedMessage() { return msg; }
    public void setEncodedMessage(byte[] message) { msg = message; }
    public int getNumberOfBits() { return 0; }
    public void setNumberOfBits(int amount) { return ; }
    public boolean equals(TriMessage message) { return false; }
}

