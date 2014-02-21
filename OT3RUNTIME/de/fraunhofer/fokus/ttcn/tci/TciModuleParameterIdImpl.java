package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.TciModuleParameterId;

public class TciModuleParameterIdImpl extends QualifiedNameImpl implements TciModuleParameterId {

    public TciModuleParameterIdImpl(String moduleName, String baseName) {
        super(moduleName, baseName);
    }

}
