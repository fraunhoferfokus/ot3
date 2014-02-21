// TCI IDL Value
package org.etsi.ttcn.tci;
public interface Value {
    /**
     * 
     * @return the type of the specified value.
     */
    public Type getType() ;
    /**
     * 
     * @return true if the specified value is omit, false otherwise.
     */
    public boolean notPresent() ;
    /**
     * 
     * @return the value encoding attribute as defined in the TTCN-
     * 3module, if any. If no encoding attribute has been defined the distinct valuenull
     * will be returned.
     */
    public String getValueEncoding() ;
    /**
     * 
     * @return the value encoding variant attribute as defined in TTCN-3,
     * if any. If no encoding variant attribute has been defined the distinct value null
     * will be returned.
     */
    public String getValueEncodingVariant();
}

