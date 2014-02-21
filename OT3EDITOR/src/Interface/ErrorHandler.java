package Interface;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import Gently.Coordinate;

public class ErrorHandler {
	
	
	public static void ClearErrorMarkers(EDITOR gEditor)
	{
	   try {
		   gEditor.getResource().deleteMarkers(IMarker.PROBLEM, true,
			   	IResource.DEPTH_INFINITE);
	   } catch (Exception e) {	  
		  System.out.println("+++++ Exception in ClearErrorMarkers");
       }
	}

	public static void Error(String str, Coordinate p,
			EDITOR gEditor) {
		
		int line = p.getLine();
		int offset = p.getOffset();
		int length = p.getLength();
		
		try {
			
			ClearErrorMarkers(gEditor);

			IMarker m = gEditor.getResource().createMarker(IMarker.PROBLEM);

			m.setAttribute(IMarker.LINE_NUMBER, line);
			m.setAttribute(IMarker.CHAR_START, offset);
			m.setAttribute(IMarker.CHAR_END, offset + length);
			m.setAttribute(IMarker.MESSAGE, str);
			m.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
			m.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);

		} catch (CoreException e) {
			System.out.println("+++++ Exception in Error");
		}
	}
}