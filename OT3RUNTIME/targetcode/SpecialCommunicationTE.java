package targetcode;

import de.fraunhofer.fokus.ttcn.tci.*;
import de.fraunhofer.fokus.ttcn.tri.*;
import org.etsi.ttcn.tci.*;
import org.etsi.ttcn.tri.TriAddress;
import org.etsi.ttcn.tri.TriComponentId;
import org.etsi.ttcn.tri.TriMessage;
import org.etsi.ttcn.tri.TriPortId;
import targetcode.Trace;
import targetcode.SystemSetup;

public class SpecialCommunicationTE extends TriCommunicationTEImpl {

    @Override
    public void triEnqueueMsg(
       TriPortId tsiPortId, TriAddress sutAddress,
       TriComponentId componentId, TriMessage receivedMessage
    )
    {
        Trace.print("JAAAAAAAAAAAAAAAA triEnqueueMsg in SpecialCommunicationTE");
        Trace.print("triEnqueueMsg in SpecialCommunicationTE");

        TciCDProvided codec = SystemSetup.Codec;

        Type decodingHypothesis = TypeImpl.charstringType; // xxx
        Trace.print("triEnqueueMsg calls decode [decodingHypothesis?]");
        /* 
        CharstringValue val =
           (CharstringValue) codec.decode(receivedMessage, decodingHypothesis);

        String str = val.getString();
        Trace.print("received '" + str + "'");

        Trace.print("triEnqueueMsg calls Port.Enqueue");
        */
        Value val =
           codec.decode(receivedMessage, decodingHypothesis);
        ((targetcode.Port)tsiPortId).Enqueue(val);
    }

}
