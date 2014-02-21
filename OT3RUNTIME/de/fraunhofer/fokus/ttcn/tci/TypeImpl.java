package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.TciModuleId;
import org.etsi.ttcn.tci.TciParameterList;
import org.etsi.ttcn.tci.TciParameterTypeList;
import org.etsi.ttcn.tci.Type;
import org.etsi.ttcn.tci.TciTypeClass;
import org.etsi.ttcn.tci.Value;

import de.fraunhofer.fokus.ttcn.tci.TypeImpl;
import de.fraunhofer.fokus.ttcn.tci.ValueImpl;

public class TypeImpl implements Type {
    
    private TciModuleId definingModule;
    private String name;
    private int typeClass;
    private TciParameterList parameterList;
    private TciParameterTypeList parameterTypeList;
    private String typeEncoding;
    private String typeEncodingVariant;
    private String[] typeExtension;
    
    public static final TypeImpl addressType = new TypeImpl(null, "address", TciTypeClass.ADDRESS, null, null, null, null, null);
    public static final TypeImpl bitstringType = new TypeImpl(null, "bitstring", TciTypeClass.BITSTRING, null, null, null, null, null);
    public static final TypeImpl booleanType = new TypeImpl(null, "boolean", TciTypeClass.BOOLEAN, null, null, null, null, null);
    public static final TypeImpl charstringType = new TypeImpl(null, "charstring", TciTypeClass.CHARSTRING, null, null, null, null, null);
    public static final TypeImpl enumeratedType = new TypeImpl(null, "enumerated", TciTypeClass.ENUMERATED, null, null, null, null, null);
    public static final TypeImpl floatType = new TypeImpl(null, "float", TciTypeClass.FLOAT, null, null, null, null, null);
    public static final TypeImpl hexstringType = new TypeImpl(null, "hexstring", TciTypeClass.HEXSTRING, null, null, null, null, null);
    public static final TypeImpl integerType = new TypeImpl(null, "integer", TciTypeClass.INTEGER, null, null, null, null, null);
    public static final TypeImpl objidType = new TypeImpl(null, "objid", TciTypeClass.OBJID, null, null, null, null, null);
    public static final TypeImpl octetstringType = new TypeImpl(null, "octetstring", TciTypeClass.OCTETSTRING, null, null, null, null, null);
    public static final TypeImpl recordOfType = new TypeImpl(null, "record of", TciTypeClass.RECORD_OF, null, null, null, null, null);
    public static final TypeImpl recordType = new TypeImpl(null, "record", TciTypeClass.RECORD, null, null, null, null, null);
    public static final TypeImpl unionType = new TypeImpl(null, "union", TciTypeClass.UNION, null, null, null, null, null);
    public static final TypeImpl universalCharstringType = new TypeImpl(null, "universal charstring", TciTypeClass.UNIVERSAL_CHARSTRING, null, null, null, null, null);
    public static final TypeImpl verdictType = new TypeImpl(null, "verdicttype", TciTypeClass.VERDICT, null, null, null, null, null);
        
    public TypeImpl(TciModuleId defModule, String na, int typeCl, String typeEnc, String typeEncVar, String[] typeExt, TciParameterList pars, TciParameterTypeList partypelist) {
        definingModule = defModule;
        name = na;
        typeClass = typeCl;
        typeEncoding = typeEnc;
        typeEncodingVariant = typeEncVar;
        typeExtension = typeExt;
        parameterList = pars;
        parameterTypeList = partypelist;
    }

    @Override
    public TciModuleId getDefiningModule() {
        return definingModule;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getTypeClass() {
        return typeClass;
    }

    @Override
    public Value newInstance() {
        return new ValueImpl(this, typeEncoding, typeEncodingVariant);
    }
    
    @Override
    public TciParameterList getValueParameters() {
        return parameterList;
    }

    @Override
    public TciParameterTypeList getTypeParameters() {
        return parameterTypeList;
    }
    @Override
    public String getTypeEncoding() {
        return typeEncoding;
    }

    @Override
    public String getTypeEncodingVariant() {
        return typeEncodingVariant;
    }

    @Override
    public String[] getTypeExtension() {
        return typeExtension;
    }

}
