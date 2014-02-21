// TCI IDL VerdictValue
package org.etsi.ttcn.tci;
public interface VerdictValue extends Value {
    public static final int NONE = 0;
    public static final int PASS = 1;
    public static final int INCONC = 2;
    public static final int FAIL = 3;
    public static final int ERROR = 4;
    /**
     * 
     * @return the integer value for this VerdictValue. The integer is one of the
     * following constants: ERROR, FAIL, INCONC, NONE, PASS, USER_ERROR.
     */
    public int getVerdict() ;
    /**
     * Sets this VerdictValue to verdict. Note that a VerdictValue can be
     * set to any of the above mentioned verdicts at any time. The VerdictValue
     * does not perform any verdict calculations as defined in TTCN-3. For example, it
     * is legal to set the VerdictValue first to INCONC and then to PASS.
     * @param verdict integer value to set
     */
    public void setVerdict(int verdict) ;
}

