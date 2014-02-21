// TCI IDL TciTypeClassType
package org.etsi.ttcn.tci;
/**
 * The following constants shall be used for value handling
 *
 */
public interface TciTypeClass {
    public final static int ADDRESS = 0 ;
    public final static int ANYTYPE = 1 ;
    public final static int BITSTRING = 2 ;
    public final static int BOOLEAN = 3 ;
    public final static int CHARSTRING = 5 ;
    public final static int COMPONENT = 6 ;
    public final static int ENUMERATED = 7 ;
    public final static int FLOAT = 8 ;
    public final static int HEXSTRING = 9 ;
    public final static int INTEGER = 10 ;
    public final static int OBJID = 11 ; //%% Extension ES 202 785 Behaiour Types
    public final static int OCTETSTRING = 12 ;
    public final static int RECORD = 13 ;
    public final static int RECORD_OF = 14 ;
    public final static int ARRAY = 15 ;
    public final static int SET = 16 ;
    public final static int SET_OF = 17 ;
    public final static int UNION = 18 ;
    public final static int UNIVERSAL_CHARSTRING = 20 ;
    public final static int VERDICT = 21 ;
    //%% Additional extensions from ES 202 785
    //%% The numbers are different from this document
    //%% because "ARRAY" was introduced.
    public final static int ALTSTEP = 22 ;
    public final static int FUNCTION = 23 ;
    public final static int TESTCASE = 24 ;
}

