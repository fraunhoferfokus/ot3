package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.TciValueTemplate;

public class TciValueTemplateImpl implements TciValueTemplate {
    
    private String templateDef;
    private boolean omit;
    private boolean any;
    
    TciValueTemplateImpl(String def, boolean isomit, boolean isany) {
        templateDef = def;
        omit = isomit;
        any = isany;
    }

    @Override
    public boolean isOmit() {
        return omit;
    }

    @Override
    public boolean isAny() {
        return any;
    }

    @Override
    public boolean isAnyOrOmit() {
        return any || omit;
    }

    @Override
    public String getTemplateDef() {
        return templateDef;
    }

}
