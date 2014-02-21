// TCI IDL TciValueTemplate
package org.etsi.ttcn.tci;
public interface TciValueTemplate {
    /**
     * 
     * @return true if the template is omit, false otherwise.
     */
    public boolean isOmit();
    /**
     * 
     * @return true if the template is any, false otherwise.
     */
    public boolean isAny();
    /**
     * 
     * @return true if the template is anyoromit, false otherwise.
     */
    public boolean isAnyOrOmit();
    /**
     * 
     * @return the template definition.
     */
    public String getTemplateDef();
}

