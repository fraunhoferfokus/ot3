package de.fraunhofer.fokus.ttcn.framework;

import de.fraunhofer.fokus.ttcn.tri.*;
import de.fraunhofer.fokus.ttcn.tci.*;

import org.etsi.ttcn.tci.*;
import org.etsi.ttcn.tri.*;

public class FactoryImpl implements Factory {
    
    public TriStatus TriStatus()
    {
       return new TriStatusImpl();
    }

    public TriAddress TriAddress() { return new TriAddressImpl(); }
    public TriAddressList TriAddressList() { return new TriAddressListImpl(); }
    public TriComponentId TriComponentId() { return null; }
    public TriComponentIdList TriComponentIdList() { return null; }
    public TriException TriException() { return new TriExceptionImpl(); }
    public TriFunctionId TriFunctionId() { return null; }

    //public TriMessage TriMessage() { return new TriMessageImpl(); }
    public TriMessage TriMessage() { return new targetcode.Message(); } //xxx

    public TriParameter TriParameter() { return new TriParameterImpl(); }
    public TriParameterList TriParameterList() { return new TriParameterListImpl(); }
    public TriPortId TriPortId() { return null; }
    public TriPortIdList TriPortIdList() { return null; }
    public TriSignatureId TriSignatureId() { return new TriSignatureIdImpl(); }
    public TriTestCaseId TriTestCaseId() { return null; }
    public TriTimerDuration TriTimerDuration() { return new TriTimerDurationImpl(); }
    public TriTimerId TriTimerId() { return null; }

    public TciBehaviourId TciBehaviourId() { return null; }
    public TciModuleId TciModuleId() { return null; }
    public TciModuleIdList TciModuleIdList() { return null; }
    public TciModuleParameter TciModuleParameter() { return null; }
    public TciModuleParameterId TciModuleParameterId() { return null; }
    public TciModuleParameterList TciModuleParameterList() { return null; }
    public TciNonValueTemplate TciNonValueTemplate() { return null; }
    public TciParameter TciParameter() { return new TciParameterImpl(); }
    public TciParameterList TciParameterList() { return null; }
    public TciParameterType TciParameterType() { return null; }
    public TciParameterTypeList TciParameterTypeList() { return null; }
    public TciTestCaseId TciTestCaseId() { return null; }
    public TciTestCaseIdList TciTestCaseIdList() { return null; }
    public TciValueDifference TciValueDifference() { return null; }
    public TciValueDifferenceList TciValueDifferenceList() { return null; }
    public TciValueList TciValueList() { return null; }
    public TciValueTemplate TciValueTemplate() { return null; }

    public AddressValue AddressValue() { return new AddressValueImpl(); }
    public BitstringValue BitstringValue() { return new BitstringValueImpl(); }
    public BooleanValue BooleanValue() { return new BooleanValueImpl(); }
    public CharstringValue CharstringValue() { return new CharstringValueImpl(); }
    public EnumeratedValue EnumeratedValue() { return new EnumeratedValueImpl(); }
    public FloatValue FloatValue() { return new FloatValueImpl(); }
    public HexstringValue HexstringValue() { return new HexstringValueImpl(); }
    public IntegerValue IntegerValue() { return new IntegerValueImpl(); }
    public OctetstringValue OctetstringValue() { return new OctetstringValueImpl(); }
    public RecordOfValue RecordOfValue() { return new RecordOfValueImpl(); }
    public RecordValue RecordValue() { return new RecordValueImpl(); }
    public UnionValue UnionValue() { return new UnionValueImpl(); }
    public UniversalCharstringValue UniversalCharstringValue() { return new UniversalCharstringValueImpl(); }
    public VerdictValue VerdictValue() { return new VerdictValueImpl(); }

    public Type Type() { return null; }

    public QualifiedName QualifiedName() { return null; }

}
