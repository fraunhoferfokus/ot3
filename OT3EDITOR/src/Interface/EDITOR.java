// C:\Users\friwi\Dropbox\Spaces\UmbraSpace\Sterntaler\src\Umbra\Editor.java

// Wed Aug 14 18:36:12     2013
// /cygdrive/c/Users/friwi/Dropbox/Spaces/UmbraSpace/Sterntaler/src/Umbra

// Mi 14 Aug 2013 18:49:53 CEST
// /Users/friwi/Dropbox/Spaces/UmbraSpace/Sterntaler/src/Umbra

package Interface;



import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.swt.widgets.Composite;

import Extensions.XOL_;
import Extensions.XOL_ContentOutlinePage;

//public class TextEditor
//extends AbstractDecoratedTextEditor
//The standard/default text editor.
//This editor has id "org.eclipse.ui.DefaultTextEditor". The editor's context menu has id #TextEditorContext. The editor's ruler context menu has id #TextRulerContext.
//
//The workbench will automatically instantiate this class when the default editor is needed for a workbench window.



public class EDITOR extends TextEditor {
   
	private String qqloc;
	public String getLoc() { return qqloc; }
	
	//private int qqEdId;
	//private static int EdCount = 0;
	private XOL_ContentOutlinePage outlinePage;

	private ProjectionSupport projectionSupport;
	private IResource qqresource;
	public IResource getResource() { 
	qqresource = ((IFileEditorInput) this.getEditorInput())
          .getFile();
	return qqresource;
	}

	public
	static ColorManager CM_for_Scanner;
	//----------------------------------------------------------

	// CONSTRUCTOR
	
	public EDITOR() { // called via reflection ???
	   
		super();
		
		CM_for_Scanner = new ColorManager(); // simple xxxxxxxxxxxxxxxxx
		
		//setDocumentProvider(new DocumentProvider()); // empty
		setDocumentProvider(new TextFileDocumentProvider()); // empty
		
		setSourceViewerConfiguration(new CONFIGURATION(this, "hallo")); // <== Configuration 1/1
		
		
	
	}

	

	//public void dispose()
	//Description copied from class: AbstractTextEditor
	//The AbstractTextEditor implementation of this IWorkbenchPart method may be extended by subclasses. Subclasses must call super.dispose().
	//Note that many methods may return null after the editor is disposed.
    //
	//Specified by:
	//dispose in interface IWorkbenchPart
	//Overrides:
	//dispose in class AbstractDecoratedTextEditor
	@Override()
	public void dispose() {
		//CM_for_Scanner.dispose();
		if (outlinePage != null)
			outlinePage.dispose();
		super.dispose();
	}

	// from Class WorkbenchPart
	//public abstract void createPartControl(Composite parent)
	//Description copied from interface: IWorkbenchPart
	//Creates the SWT controls for this workbench part.
	//Clients should not call this method (the workbench calls this method when it needs to, which may be never).
    //
	//For implementors this is a multi-step process:
    //
	//Create one or more controls within the parent.
	//Set the parent layout as needed.
	//Register any global actions with the site's IActionBars.
	//Register any context menus with the site.
	//Register a selection provider with the site, to make it available to the workbench's ISelectionService (optional).
	//Specified by:
	//createPartControl in interface IWorkbenchPart
	//Parameters:
	//parent - the parent control
	@Override
	public void createPartControl(Composite parent) {
		try {
		super.createPartControl(parent);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		   System.out.println("Exception in super.createPartControl");
		}

		ProjectionViewer viewer = (ProjectionViewer) getSourceViewer();

		projectionSupport = new ProjectionSupport(viewer,
				getAnnotationAccess(), getSharedColors());
		projectionSupport.install();

		// turn projection mode on
		viewer.doOperation(ProjectionViewer.TOGGLE);

		Folder.setAnnotationModel( viewer.getProjectionAnnotationModel() );
		
		System.out.println("+++ end createPartControl");

	}
	
	// from Class AbstractDecoratedTextEditor
    //protected ISourceViewer createSourceViewer(Composite parent,
         // IVerticalRuler ruler,
          //int styles)
//Description copied from class: AbstractTextEditor
//Creates the source viewer to be used by this editor. Subclasses may re-implement this method.
//Overrides:
//createSourceViewer in class AbstractTextEditor
//Parameters:
//parent - the parent control
//ruler - the vertical ruler
//styles - style bits, SWT.WRAP is currently not supported
//Returns:
//the source viewer


	@Override
	protected 
	ISourceViewer createSourceViewer(Composite parent,
			IVerticalRuler ruler, int styles) {

		ISourceViewer viewer = new ProjectionViewer(parent, ruler,
				getOverviewRuler(), isOverviewRulerVisible(), styles);

		// ensure decoration support has been created and configured.
		getSourceViewerDecorationSupport(viewer);

		// custom stuff
		//resource = ((IFileEditorInput) getEditorInput()).getFile();

		IPath lok =getResource().getLocation();
		qqloc = lok.toString();
		//System.out.println("Editor: resource location ********** : " + loc);
		
		try {
			getResource().deleteMarkers(IMarker.PROBLEM, true,
					IResource.DEPTH_INFINITE);
		} catch (Exception e) {
			System.out.println("Exception in createSourceViewer");
		}
		// end custom stuff

		return viewer;
	}
	
	//public Object getAdapter(Class adapter)
	//Description copied from class: WorkbenchPart
	//Returns an object which is an instance of the given class associated with this object. Returns null if no such object can be found. Subclasses may override this method (however, if they do so, they should invoke the method on their superclass to ensure that the Platform's adapter manager is consulted).
	//Specified by:
	//getAdapter in interface IAdaptable
	//Overrides:
	//getAdapter in class AbstractDecoratedTextEditor
	//Parameters:
	//adapter - the adapter class to look up
	//Returns:
	//a object castable to the given class, or null if this object does not have an adapter for the given class
	
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class required) {
	   //qq("Editor getAdapter");
		// XOL_
		if (!XOL_.activated)
			return null;
		if (IContentOutlinePage.class.equals(required)) {
			if (outlinePage == null) {
				outlinePage = new XOL_ContentOutlinePage(this);
				getSourceViewer().getTextWidget().addCaretListener(outlinePage);
			}
			return outlinePage;
		}
		return super.getAdapter(required);
	}
}
