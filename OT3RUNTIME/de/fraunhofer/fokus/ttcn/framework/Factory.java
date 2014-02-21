package de.fraunhofer.fokus.ttcn.framework;

import org.etsi.ttcn.tci.*;
import org.etsi.ttcn.tri.*;

public interface Factory {
    
    TriStatus TriStatus();

    TriAddress TriAddress() ;
    TriAddressList TriAddressList() ;
    TriComponentId TriComponentId() ;
    TriComponentIdList TriComponentIdList() ;
    TriException TriException() ;
    TriFunctionId TriFunctionId() ;
    TriMessage TriMessage() ;
    TriParameter TriParameter() ;
    TriParameterList TriParameterList() ;
    TriPortId TriPortId() ;
    TriPortIdList TriPortIdList() ;
    TriSignatureId TriSignatureId() ;
    TriTestCaseId TriTestCaseId() ;
    TriTimerDuration TriTimerDuration() ;
    TriTimerId TriTimerId() ;

    TciBehaviourId TciBehaviourId() ;
    TciModuleId TciModuleId() ;
    TciModuleIdList TciModuleIdList() ;
    TciModuleParameter TciModuleParameter() ;
    TciModuleParameterId TciModuleParameterId() ;
    TciModuleParameterList TciModuleParameterList() ;
    TciNonValueTemplate TciNonValueTemplate() ;
    TciParameter TciParameter() ;
    TciParameterList TciParameterList() ;
    TciParameterType TciParameterType() ;
    TciParameterTypeList TciParameterTypeList() ;
    TciTestCaseId TciTestCaseId() ;
    TciTestCaseIdList TciTestCaseIdList() ;
    TciValueDifference TciValueDifference() ;
    TciValueDifferenceList TciValueDifferenceList() ;
    TciValueList TciValueList() ;
    TciValueTemplate TciValueTemplate() ;

    AddressValue AddressValue() ;
    BitstringValue BitstringValue() ;
    BooleanValue BooleanValue() ;
    CharstringValue CharstringValue() ;
    EnumeratedValue EnumeratedValue() ;
    FloatValue FloatValue() ;
    HexstringValue HexstringValue() ;
    IntegerValue IntegerValue() ;
    OctetstringValue OctetstringValue() ;
    RecordOfValue RecordOfValue() ;
    RecordValue RecordValue() ;
    UnionValue UnionValue() ;
    UniversalCharstringValue UniversalCharstringValue() ;
    VerdictValue VerdictValue() ;

    Type Type() ;

    QualifiedName QualifiedName() ;

}
