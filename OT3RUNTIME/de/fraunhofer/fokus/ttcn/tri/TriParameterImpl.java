package de.fraunhofer.fokus.ttcn.tri;

import org.etsi.ttcn.tri.TriParameter;

public class TriParameterImpl implements TriParameter {
    
    private String parameterName;
    private int numberOfBits;
    private int parameterPassingMode;
    private byte[] encodedParameter;

    @Override
    public String getParameterName() {
        return parameterName;
    }

    @Override
    public void setParameterName(String name) {
        parameterName = name;
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
    public int getParameterPassingMode() {
        return parameterPassingMode;
    }

    @Override
    public void setParameterPassingMode(int mode) {
        parameterPassingMode = mode;
    }

    @Override
    public byte[] getEncodedParameter() {
        return encodedParameter;
    }

    @Override
    public void setEncodedParameter(byte[] parameter) {
        encodedParameter = parameter;
    }

}
