package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.TciModuleId;

public class TciModuleIdImpl extends QualifiedNameImpl implements TciModuleId {

    public TciModuleIdImpl(String moduleName, String baseName) {
        super(moduleName, baseName);
    }

}
