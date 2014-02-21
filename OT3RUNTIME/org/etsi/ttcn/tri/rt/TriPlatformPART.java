package org.etsi.ttcn.tri.rt;
import org.etsi.ttcn.tri.*;
public interface TriPlatformPART {
// Timer handling operations
// Ref: TRI-Definition 5.6.2.1
public TriStatus triStartClock(long ticksPerSecond);
// Ref: TRI-Definition 5.6.2.2
public TriStatus triReadClock(TriTimerDuration timestamp);
// Ref: TRI-Definition 5.6.2.3
public TriStatus triBeginWait(TriTimerDuration timestamp, TriComponentId componentId);
}
