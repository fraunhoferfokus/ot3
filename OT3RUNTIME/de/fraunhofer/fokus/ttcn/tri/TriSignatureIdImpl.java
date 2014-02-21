package de.fraunhofer.fokus.ttcn.tri;

import org.etsi.ttcn.tri.TriSignatureId;

public class TriSignatureIdImpl implements TriSignatureId {
    
    private String name;

    @Override
    public String getSignatureName() {
        return name;
    }

    @Override
    public void setSignatureName(String sigName) {
        name = sigName;
    }

    @Override
    public boolean equals(TriSignatureId sig) {
        return name == sig.getSignatureName();
    }

}
