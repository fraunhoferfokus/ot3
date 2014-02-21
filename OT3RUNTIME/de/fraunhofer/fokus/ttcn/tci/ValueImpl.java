package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.Type;
import org.etsi.ttcn.tci.Value;

public class ValueImpl implements Value {
    
    private Type type;
    private String encoding;
    private String encodingVariant;
    protected boolean present = false;
    
    public ValueImpl(Type ty, String enc, String encVariant) {
        type = ty;
        encoding = enc;
        encodingVariant = encVariant;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public boolean notPresent() {
        return !present;
    }

    @Override
    public String getValueEncoding() {
        return encoding;
    }

    @Override
    public String getValueEncodingVariant() {
        return encodingVariant;
    }

}
