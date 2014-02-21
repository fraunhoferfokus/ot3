package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.AddressValue;
import org.etsi.ttcn.tci.Value;

import de.fraunhofer.fokus.ttcn.tci.TypeImpl;
import de.fraunhofer.fokus.ttcn.tci.ValueImpl;

public class AddressValueImpl extends ValueImpl implements AddressValue {

    private Value val;

    public AddressValueImpl() {
        super(TypeImpl.addressType, null, null);
    }
    
    @Override
    public Value getAddress() {
        return val;
    }

    @Override
    public void setAddress(Value value) {
        val = value;
        present = true;
    }

}
