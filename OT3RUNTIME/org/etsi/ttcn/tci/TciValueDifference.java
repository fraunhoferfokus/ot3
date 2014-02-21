// TCI IDL TciValueDifference
package org.etsi.ttcn.tci;
public interface TciValueDifference {
    /**
     * 
     * @return the value of this TciValueDifference.
     */
    public Value getValue();
    /**
     * 
     * @return the template of this TciValueDifference.
     */
    public TciValueTemplate getTciValueTemplate();
    /**
     * 
     * @return the description of the mismatch.
     */
    public String getDescription(); // ';' added
}

