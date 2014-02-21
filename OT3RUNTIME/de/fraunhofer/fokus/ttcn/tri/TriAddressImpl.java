package de.fraunhofer.fokus.ttcn.tri;

import org.etsi.ttcn.tri.TriAddress;

public class TriAddressImpl implements TriAddress {
    
    private byte[] encodedAddress;
    private int numberOfBits;

    @Override
    public byte[] getEncodedAddress() {
        return encodedAddress;
    }

    @Override
    public void setEncodedAddress(byte[] address) {
        encodedAddress = address;
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
    public boolean equals(TriAddress address) {
        return encodedAddress.equals(address.getEncodedAddress());
    }

}
