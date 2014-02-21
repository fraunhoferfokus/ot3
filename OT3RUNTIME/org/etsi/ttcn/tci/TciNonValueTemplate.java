// TCI IDL TciNonValueTemplate
package org.etsi.ttcn.tci;
public interface TciNonValueTemplate {
    /**
     * 
     * @return true if the template is any, false otherwise.
     */
    public boolean isAny();
    /**
     * 
     * @return true if the template is all, false otherwise.
     */
    public boolean isAll();
    /**
     * 
     * @return the template definition.
     */
    public String getTemplateDef();
}

