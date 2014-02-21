package de.fraunhofer.fokus.ttcn.tci;

import java.util.List;
import java.util.Collections;
import java.util.Enumeration;

import org.etsi.ttcn.tci.TciModuleId;
import org.etsi.ttcn.tci.TciModuleIdList;

public class TciModuleIdListImpl implements TciModuleIdList {
    
    private List<TciModuleId> modlist;
    
    public TciModuleIdListImpl(List<TciModuleId> moduleIdList) {
        modlist = moduleIdList;
    }

    @Override
    public int size() {
        return modlist.size();
    }

    @Override
    public boolean isEmpty() {
        return modlist.isEmpty();
    }

    @Override
    public Enumeration<TciModuleId> tciGetImportedModules() {
        return Collections.enumeration(modlist);
    }

    @Override
    public TciModuleId get(int index) {
        return modlist.get(index);
    }

}
