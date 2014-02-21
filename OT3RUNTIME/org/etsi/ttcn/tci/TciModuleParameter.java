// TCI IDL TciModuleParameterType
package org.etsi.ttcn.tci;
/**
 * A value of type TciModuleParameter Type is a structure of
 * TciModuleParameterId and Value. This abstract type is used to
 * represent the parameter name and the default value of a module parameter.
 *
 */
public interface TciModuleParameter {
    public String getModuleParameterName();
    public Value getDefaultValue();
}

