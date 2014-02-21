package de.fraunhofer.fokus.ttcn.tci;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.etsi.ttcn.tci.TciTestCaseId;
import org.etsi.ttcn.tci.TciTestCaseIdList;

public class TciTestCaseIdListImpl implements TciTestCaseIdList {
    
    private List<TciTestCaseId> testCaseIdList;
    
    public TciTestCaseIdListImpl(List<TciTestCaseId> moduleIdList) {
        testCaseIdList = moduleIdList;
    }

    @Override
    public int size() {
        return testCaseIdList.size();
    }

    @Override
    public boolean isEmpty() {
        return testCaseIdList.isEmpty();
    }

    @Override
    public Enumeration<TciTestCaseId> tciGetTestCases() {
        return Collections.enumeration(testCaseIdList);
    }

    @Override
    public TciTestCaseId get(int index) {
        return testCaseIdList.get(index);
    }

}
