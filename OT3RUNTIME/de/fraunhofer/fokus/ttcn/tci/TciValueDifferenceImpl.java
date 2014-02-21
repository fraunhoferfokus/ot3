package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.TciValueDifference;
import org.etsi.ttcn.tci.TciValueTemplate;
import org.etsi.ttcn.tci.Value;

public class TciValueDifferenceImpl implements TciValueDifference {
    
    private Value val;
    private TciValueTemplate template;
    private String difference;
    
    public TciValueDifferenceImpl(Value value, TciValueTemplate temp, String diff) {
        val = value;
        template = temp;
        difference = diff;
    }

    @Override
    public Value getValue() {
        return val;
    }

    @Override
    public TciValueTemplate getTciValueTemplate() {
        return template;
    }

    @Override
    public String getDescription() {
        return difference;
    }

}
