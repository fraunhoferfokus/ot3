package de.fraunhofer.fokus.ttcn.framework;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

// java.net
import java.net.Socket;

// tri
import org.etsi.ttcn.tri.TriAddress;
import org.etsi.ttcn.tri.TriComponentId;
import org.etsi.ttcn.tri.TriMessage;
import org.etsi.ttcn.tri.TriPortId;
import org.etsi.ttcn.tri.TriStatus;
import org.etsi.ttcn.tri.TriCommunicationTE;
import org.etsi.ttcn.tri.TriCommunicationSA;
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


public class BaseAdapter implements TriCommunicationSA
{

    @Override
    public TriStatus triSAReset ()
    {
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triSAReset");
       return Framework.TriStatusError;
    }

    @Override
    public  TriStatus triExecuteTestCase (
       TriTestCaseId testcase,
       TriPortIdList tsiList
    )
    {
       return Framework.TriStatusOk;
    }

    @Override
    public  TriStatus triMap (
       TriPortId compPortId,
       TriPortId tsiPortId
    )
    {
       return Framework.TriStatusOk;
       /*
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triMap");
       return Framework.TriStatusError;
       */
    }

    @Override
    public  TriStatus triStaticMap (
       TriPortId compPortId,
       TriPortId tsiPortId
    )
    {
       return Framework.TriStatusOk;
    }

    @Override
    public TriStatus triMapParam(TriPortId compPortId, TriPortId tsiPortId,
            TriParameterList paramList) {
        Framework.GetTciTMProvided().tciError
        ("Missing implementation of triMapParam");
        return Framework.TriStatusError;
    }

    @Override
    public  TriStatus triUnmap (
       TriPortId compPortId,
       TriPortId tsiPortId
    )
    {
       return Framework.TriStatusOk;
       /*
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triUnmap");
       return Framework.TriStatusError;
       */
    }

    @Override
    public TriStatus triUnmapParam(TriPortId compPortId, TriPortId tsiPortId,
            TriParameterList paramList) {
        Framework.GetTciTMProvided().tciError
        ("Missing implementation of triUnmapParam");
        return Framework.TriStatusError;
    }

    @Override
    public  TriStatus triEndTestCase ()
    {
      return Framework.TriStatusOk;
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
       return Framework.TriStatusError;
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
       return Framework.TriStatusError;
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
       return Framework.TriStatusError;
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
       return Framework.TriStatusError;
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
       return Framework.TriStatusError;
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
       return Framework.TriStatusError;
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
       return Framework.TriStatusError;
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
       return Framework.TriStatusError;
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
       return Framework.TriStatusError;
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
       return Framework.TriStatusError;
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
       return Framework.TriStatusError;
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
       return Framework.TriStatusError;
    }

    @Override
    public  TriStatus triSutActionInformal (
       String description
    )
    {
       Framework.GetTciTMProvided().tciError
          ("Missing implementation of triSutActionInformal");
       return Framework.TriStatusError;
    }
    
   private static byte[] ConvertStringToByteArray(String str)
   {
      try {
      byte[] bytes = str.getBytes("UTF8");
          return bytes;
      } catch (Exception e) {
          return null;
      }
   }

   private static String ConvertByteArrayToString(byte[] bytes)
   {
      try {
      String str = new String(bytes, "UTF-8");
      return str;
      } catch (Exception e) {
          return null;
      }
   }

}
