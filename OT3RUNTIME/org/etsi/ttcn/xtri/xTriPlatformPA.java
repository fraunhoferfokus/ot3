// TriPlatform
// TE -> PA
package org.etsi.ttcn.xtri;

import org.etsi.ttcn.tci.*;
import org.etsi.ttcn.tri.*;

public interface xTriPlatformPA {
    // Ref: TRI-Definition 5.6.3.1
    public TriStatus xtriExternalFunction(TriFunctionId functionId,
            TciParameterList parameterList, Value returnValue);
}