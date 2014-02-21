package de.fraunhofer.fokus.ttcn.tri;

import org.etsi.ttcn.tri.TriTestCaseId;

public class TriTestCaseIdImpl implements TriTestCaseId {
    
    private String name;

    public TriTestCaseIdImpl(String id) {
        name = id;
    }
    
    @Override
    public String getTestCaseName() {
        return name;
    }

    @Override
    public boolean equals(TriTestCaseId tc) {
        return name == tc.getTestCaseName();
    }

}
