// TCI IDL TciTestComponentKindType
package org.etsi.ttcn.tci;
/**
 * The following constants shall be used for component handling
 *
 */
public interface TciTestComponentKind {
    public final static int TCI_CTRL_COMP = 0;
    public final static int TCI_MTC_COMP = 1;
    public final static int TCI_PTC_COMP = 2;
    public final static int TCI_SYSTEM_COMP = 3;
    public final static int TCI_ALIVE_COMP = 4;
    //%% Extensions ES 202 781 Configuration and Deployment Support
    public final static int TCI_MTC_STATIC_COMP = 5;
    public final static int TCI_PTC_STATIC_COMP = 6;
    public final static int TCI_SYSTEM_STATIC_COMP = 7;
}

