// TCI IDL Type
package org.etsi.ttcn.tci;
public interface Type {
    /**
     * 
     * @return the module identifier of the module the type has been defined in.If the
     * type represents a TTCN-3 base type the distinct value null will be returned.
     */
    public TciModuleId getDefiningModule ();
    /**
     * 
     * @return name of the type as defined in the TTCN-3 module
     */
    public String getName ();
    /**
     * 
     * @return the type class of the respective type. A value of TciTypeClassType
     * can have on of the following constants: ADDRESS, ANYTYPE,
     * BITSTRING, BOOLEAN, CHARSTRING, COMPONENT, ENUMERATED,
     * FLOAT, HEXSTRING, INTEGER, OCTETSTRING, RECORD,
     * RECORD_OF, ARRAY, SET, SET_OF, UNION,
     * UNIVERSAL_CHARSTRING, VERDICT.
     */
    public int getTypeClass ();
    /**
     * 
     * @return a freshly created value of the given type. This initial value of the created
     * value is undefined
     */
    public Value newInstance ();
    //%% Extensions ES 202 784 Advanced Parameterization
    public TciParameterList getValueParameters();
    public TciParameterTypeList getTypeParameters();
    //
    /**
     * 
     * @return the type encoding attribute as defined in the TTCN-3 module.
     */
    public String getTypeEncoding ();
    /**
     * 
     * @return the value encoding variant attribute as defined in the
     * TTCN-3 module, if any. If no encoding variant attribute has been defined the
     * distinct value null will be returned.
     */
    public String getTypeEncodingVariant();
    /**
     * 
     * @return the type extension attribute as defined in the TTCN-3 module.
     */
    public String[] getTypeExtension();
}

