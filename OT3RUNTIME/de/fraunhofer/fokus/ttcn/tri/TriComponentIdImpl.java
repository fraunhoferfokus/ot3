package de.fraunhofer.fokus.ttcn.tri;

import org.etsi.ttcn.tri.TriComponentId;
import org.etsi.ttcn.tri.TriPortIdList;

public class TriComponentIdImpl implements TriComponentId {
    
    private String componentId;
    private String componentName;
    private String componentTypeName;
    private TriPortIdList portIdList;

    public TriComponentIdImpl(String id, String name, String typeName, TriPortIdList list) {
        componentId = id;
        componentName = name;
        componentTypeName = typeName;
        portIdList = list;
    }
    
    @Override
    public String getComponentId() {
        return componentId;
    }

    @Override
    public String getComponentName() {
        return componentName;
    }

    @Override
    public String getComponentTypeName() {
        return componentTypeName;
    }

    @Override
    public TriPortIdList getPortList() {
        return portIdList;
    }

    @Override
    public boolean equals(TriComponentId component) {
        return componentId.equals(component.getComponentId());
    }

}
