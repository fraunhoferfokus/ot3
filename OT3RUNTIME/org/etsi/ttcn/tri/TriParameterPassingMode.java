// TRI IDL TriParameterPassingModeType
package org.etsi.ttcn.tri;
/**
 * A value of type TriParameterPassingMode is either in, inout, or out. This
 * abstract type is used in procedure based TRI communication operations and for
 * external function calls.
 *
 */
public interface TriParameterPassingMode {
    public final static int TRI_IN = 0;
    public final static int TRI_INOUT = 1;
    public final static int TRI_OUT = 2;
}

