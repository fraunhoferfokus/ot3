package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.TciNonValueTemplate;

public class TciNonValueTemplateImpl implements TciNonValueTemplate {
    
    private String templateDef;
    private boolean all;
    private boolean any;
    
    TciNonValueTemplateImpl(String def, boolean isany, boolean isall) {
        templateDef = def;
        all = isall;
        any = isany;
    }

    @Override
    public boolean isAny() {
        return any;
    }

    @Override
    public boolean isAll() {
        return all;
    }

    @Override
    public String getTemplateDef() {
        return templateDef;
    }

}
