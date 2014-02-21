package de.fraunhofer.fokus.ttcn.tri;

import de.fraunhofer.fokus.ttcn.framework.*;
import org.etsi.ttcn.tri.TriAddress;
import org.etsi.ttcn.tri.TriAddressList;
import org.etsi.ttcn.tri.TriCommunicationSA;
import org.etsi.ttcn.tri.TriComponentId;
import org.etsi.ttcn.tri.TriException;
import org.etsi.ttcn.tri.TriMessage;
import org.etsi.ttcn.tri.TriParameter;
import org.etsi.ttcn.tri.TriParameterList;
import org.etsi.ttcn.tri.TriPortId;
import org.etsi.ttcn.tri.TriPortIdList;
import org.etsi.ttcn.tri.TriSignatureId;
import org.etsi.ttcn.tri.TriStatus;
import org.etsi.ttcn.tri.TriTestCaseId;

public class TriCommunicationSAImpl implements TriCommunicationSA {

    protected class Status implements TriStatus
    {
        
        Status(int status)
        {
            stat = status;
        }
        int stat;
        public void setStatus(int status)
        {
            stat = status;
        }
        public int getStatus()
        {
            return stat;
        }
        public boolean equals(TriStatus status)
        {
            return status.getStatus() == stat;
        }
    }
    
    protected Status TriOK = new Status(TriStatus.TRI_OK);
    protected Status TriErr = new Status(TriStatus.TRI_ERROR);
    
    @Override
    public TriStatus triSAReset ()
    {
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triSAReset");
       return TriErr;
    }

    @Override
    public  TriStatus triExecuteTestCase (
       TriTestCaseId testcase,
       TriPortIdList tsiList
    )
    {
       return TriOK;
    }

    @Override
    public  TriStatus triMap (
       TriPortId compPortId,
       TriPortId tsiPortId
    )
    {
       return TriOK;
    }
    
    @Override
    public  TriStatus triStaticMap (
       TriPortId compPortId,
       TriPortId tsiPortId
    )
    {
       return TriOK;
    }

    @Override
    public TriStatus triMapParam(TriPortId compPortId, TriPortId tsiPortId,
            TriParameterList paramList) {
        Framework.GetTciTMProvided().tciError
        ("Missing implementation of triMapParam");
        return TriErr;
    }

    @Override
    public  TriStatus triUnmap (
       TriPortId compPortId,
       TriPortId tsiPortId
    )
    {
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triUnmap");
       return TriErr;
    }

    @Override
    public TriStatus triUnmapParam(TriPortId compPortId, TriPortId tsiPortId,
            TriParameterList paramList) {
        Framework.GetTciTMProvided().tciError
        ("Missing implementation of triUnmapParam");
        return TriErr;
    }

    @Override
    public  TriStatus triEndTestCase ()
    {
      return TriOK;
    }

    @Override
    public  TriStatus triSend (
       TriComponentId componentId, 
       TriPortId tsiPortId,
       TriAddress address,
       TriMessage sendMessage
    )
    {
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triSend");
       return TriErr;
    }

    @Override
    public  TriStatus triSendBC (
       TriComponentId componentId,
       TriPortId tsiPortId,
       TriMessage sendMessage
    )
    {
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triSendBC");
       return TriErr;
    }

    @Override
    public  TriStatus triSendMC (
       TriComponentId componentId,
       TriPortId tsiPortId,
       TriAddressList addresses,
       TriMessage sendMessage
    )
    {
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triSendMC");
       return TriErr;
    }

    @Override
    public  TriStatus triCall (
       TriComponentId componentId, 
       TriPortId tsiPortId,
       TriAddress sutAddress, 
       TriSignatureId signatureId,
       TriParameterList parameterList
    )
    {
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triCall");
       return TriErr;
    }

    @Override
    public  TriStatus triCallBC (
       TriComponentId componentId,
       TriPortId tsiPortId,
       TriSignatureId signatureId,
       TriParameterList parameterList
    )
    {
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triCallBC");
       return TriErr;
    }

    @Override
    public  TriStatus triCallMC (
       TriComponentId componentId,
       TriPortId tsiPortId,
       TriAddressList sutAddresses,
       TriSignatureId signatureId,
       TriParameterList parameterList
    )
    {
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triCallMC");
       return TriErr;
    }

    @Override
    public  TriStatus triReply (
       TriComponentId componentId, 
       TriPortId tsiPortId,
       TriAddress sutAddress,
       TriSignatureId signatureId,
       TriParameterList parameterList,
       TriParameter returnValue
    )
    {
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triReply");
       return TriErr;
    }
         
    @Override
    public  TriStatus triReplyBC (
       TriComponentId componentId,
       TriPortId tsiPortId,
       TriSignatureId signatureId,
       TriParameterList parameterList,
       TriParameter returnValue
    )
    {
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triReplyBC");
       return TriErr;
    }

    @Override
    public  TriStatus triReplyMC (
       TriComponentId componentId,
       TriPortId tsiPortId,
       TriAddressList sutAddresses,
       TriSignatureId signatureId,
       TriParameterList parameterList,
       TriParameter returnValue
    )
    {
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triReplyMC");
       return TriErr;
    }

    @Override
    public  TriStatus triRaise (
       TriComponentId componentId,
       TriPortId tsiPortId,
       TriAddress sutAddress,
       TriSignatureId signatureId,
       TriException exception
    )
    {
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triRaise");
       return TriErr;
    }

    @Override
    public  TriStatus triRaiseBC (
       TriComponentId componentId,
       TriPortId tsitPortId,
       TriSignatureId signatureId,
       TriException exc
    )
    {
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triRaiseBC");
       return TriErr;
    }

    @Override
    public  TriStatus triRaiseMC (
       TriComponentId componentId,
       TriPortId tsitPortId,
       TriAddressList sutAddresses,
       TriSignatureId signatureId,
       TriException exc
    )
    {
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triRaiseMC");
       return TriErr;
    }

    @Override
    public  TriStatus triSutActionInformal (
       String description
    )
    {
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triSutActionInformal");
       return TriErr;
    }

}
