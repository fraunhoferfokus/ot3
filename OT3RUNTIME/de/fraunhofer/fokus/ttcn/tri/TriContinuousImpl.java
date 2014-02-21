package de.fraunhofer.fokus.ttcn.tri;

import org.etsi.ttcn.tri.TriAddress;
import org.etsi.ttcn.tri.TriComponentId;
import org.etsi.ttcn.tri.TriContinuous;
import org.etsi.ttcn.tri.TriLong;
import org.etsi.ttcn.tri.TriMessage;
import org.etsi.ttcn.tri.TriPortId;
import org.etsi.ttcn.tri.TriPortIdList;
import org.etsi.ttcn.tri.TriStatus;

public class TriContinuousImpl implements TriContinuous {

    @Override
    public TriStatus triStartClock(long ticksPerSecond) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TriStatus triReadClock(TriLong timepoint) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TriStatus triNextSampling(long timepoint, TriPortId port) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TriStatus triBeginWait(long timepoint, TriComponentId component) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void triProcessStep(TriPortIdList ports) {
        // TODO Auto-generated method stub

    }

    @Override
    public void triEndWait(TriComponentId component) {
        // TODO Auto-generated method stub

    }

    @Override
    public TriStatus triSetStreamValue(TriComponentId componentId,
            TriPortId tsiPortId, TriAddress SUTaddress, TriMessage streamValue) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TriStatus triGetStreamValue(TriComponentId componentId,
            TriPortId tsiPortId, TriAddress SUTaddress, TriMessage streamValue) {
        // TODO Auto-generated method stub
        return null;
    }

}
