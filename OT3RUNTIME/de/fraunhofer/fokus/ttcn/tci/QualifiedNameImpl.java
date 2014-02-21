package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tri.QualifiedName;

public class QualifiedNameImpl implements QualifiedName {
    
    private String mName;
    private String bName;
    
    public QualifiedNameImpl(String moduleName, String baseName) {
        mName = moduleName;
        bName = baseName;
    }

    @Override
    public String getModuleName() {
        return mName;
    }

    @Override
    public String getBaseName() {
        return bName;
    }

    @Override
    public boolean equals(QualifiedName name) {
        return mName == name.getModuleName() && bName == name.getBaseName();
    }

}
