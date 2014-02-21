//%% New interface TriLong for the extension
//%% ES 202 786 Support of interfaces with continuous signals
package org.etsi.ttcn.tri;
/**
 * New interface TriLong for the extension
 * ES 202 786 Support of interfaces with continuous signals
 * for long as out parameter
 *
 */
public interface TriLong {
    /**
     * set the value
     * @param value for setting
     */
    public void setLongValue(long value);
    /**
     * 
     * @return value as long
     */
    public long getLongValue();
}
