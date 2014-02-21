package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.TciTestCaseId;

public class TciTestCaseIdImpl extends QualifiedNameImpl implements TciTestCaseId {

    public TciTestCaseIdImpl(String moduleName, String baseName) {
        super(moduleName, baseName);
    }

}
