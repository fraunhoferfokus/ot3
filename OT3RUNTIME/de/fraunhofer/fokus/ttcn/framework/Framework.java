package de.fraunhofer.fokus.ttcn.framework;

import de.fraunhofer.fokus.ttcn.tri.*;
//import de.fraunhofer.ttcn.tci.*;

import org.etsi.ttcn.tci.*;
import org.etsi.ttcn.tri.*;

public class Framework {
    
    // --- Status
    public static TriStatus TriStatusOk = new TriStatusImpl(TriStatus.TRI_OK);
    public static TriStatus TriStatusError = new TriStatusImpl(TriStatus.TRI_ERROR);
    // --- End Status

    public static void SetTriCommunicationSA(TriCommunicationSA obj)
    {
       _TriCommunicationSA = obj;
    }

    public static TriCommunicationSA GetTriCommunicationSA()
    {
       return _TriCommunicationSA;
    }

    public static void SetTriCommunicationTE(TriCommunicationTE obj)
    {
       _TriCommunicationTE = obj;
    }

    public static TriCommunicationTE GetTriCommunicationTE()
    {
       return _TriCommunicationTE;
    }

    public static void SetTriPlatformPA(TriPlatformPA obj)
    {
       _TriPlatformPA = obj;
    }

    public static TriPlatformPA GetTriPlatformPA()
    {
       return _TriPlatformPA;
    }

    public static void SetTriPlatformTE(TriPlatformTE obj)
    {
       _TriPlatformTE = obj;
    }

    public static TriPlatformTE GetTriPlatformTE()
    {
       return _TriPlatformTE;
    }

    public static void SetTciTMProvided(TciTMProvided obj)
    {
       _TciTMProvided = obj;
    }

    public static TciTMProvided GetTciTMProvided()
    {
       return _TciTMProvided;
    }

    public static void SetTciTMRequired(TciTMRequired obj)
    {
       _TciTMRequired = obj;
    }

    public static TciTMRequired GetTciTMRequired()
    {
       return _TciTMRequired;
    }

    public static void SetTciCDProvided (TciCDProvided obj)
    {
       _TciCDProvided = obj;
    }

    public static TciCDProvided GetTciCDProvided ()
    {
       return _TciCDProvided;
    }

    public static void SetTciCDRequired(TciCDRequired obj)
    {
       _TciCDRequired = obj;
    }

    public static TciCDRequired GetTciCDRequired()
    {
       return _TciCDRequired;
    }

    public static void SetTciCHProvided(TciCHProvided obj)
    {
       _TciCHProvided = obj;
    }

    public static TciCHProvided GetTciCHProvided()
    {
       return _TciCHProvided;
    }

    public static void SetTciCHRequired(TciCHRequired obj)
    {
       _TciCHRequired = obj;
    }

    public static TciCHRequired GetTciCHRequired()
    {
       return _TciCHRequired;
    }

    public static void SetTciTLProvided(TciTLProvided Adapter)
    {
       _TciTLProvided = Adapter;
    }

    public static TciTLProvided GetTciTLProvided()
    {
       return _TciTLProvided;
    }

    public static void SetFactory(Factory obj)
    {
       _Factory = obj;
    }

    public static Factory GetFactory()
    {
       return _Factory;
    }

    private static TriCommunicationSA _TriCommunicationSA;
    private static TriCommunicationTE _TriCommunicationTE;
    private static TriPlatformPA _TriPlatformPA;
    private static TriPlatformTE _TriPlatformTE;
    private static TciTMProvided _TciTMProvided;
    private static TciTMRequired _TciTMRequired;
    private static TciCDProvided _TciCDProvided;
    private static TciCDRequired _TciCDRequired;
    private static TciCHProvided _TciCHProvided;
    private static TciCHRequired _TciCHRequired;
    private static TciTLProvided _TciTLProvided;

    private static Factory _Factory = new FactoryImpl();

    static
    {
       SetTriCommunicationTE( new TriCommunicationTEImpl() );
       // redefined in targetcode.SystemSetup xxx
    }
}
