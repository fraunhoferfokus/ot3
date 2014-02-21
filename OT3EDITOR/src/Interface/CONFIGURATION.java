package Interface;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.reconciler.MonoReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;

import CyanTools.PARSER;
import CyanTools.SCANNER;


//public class SourceViewerConfiguration
//extends Object
//This class bundles the configuration space of a source viewer. Instances of this class are passed to the configure method of ISourceViewer.
//Each method in this class get as argument the source viewer for which it should provide a particular configuration setting such as a presentation reconciler. Based on its specific knowledge about the returned object, the configuration might share such objects or compute them according to some rules.
//
//Clients should subclass and override just those methods which must be specific to their needs.

public class CONFIGURATION extends SourceViewerConfiguration
{

	private EDITOR editor;
	//-------------------------------------------------------------------------------------------------

	// Constructor
	public CONFIGURATION(EDITOR ed, String hallo) {  // used in Umbra.Editor 1/1
		editor = ed;
	}
	
	//public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer)
	//Returns the presentation reconciler ready to be used with the given source viewer.
	//Parameters:
	//sourceViewer - the source viewer
	//Returns:
	//the presentation reconciler or null if presentation reconciling should not be supported

	@Override
	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr;

		// SCANNER DEFINITION xxx
		SCANNER sc = new SCANNER();   //<== SCANNER 1/1
		
		dr = new DefaultDamagerRepairer(sc);
		
		
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		return reconciler;
	}
	
	private static String[] DocumentTypes = new String[] {
       IDocument.DEFAULT_CONTENT_TYPE
       // , CONTENT_TYPE_WHITESPACE
   };

	//public String[] getConfiguredContentTypes(ISourceViewer sourceViewer)
	//Returns all configured content types for the given source viewer. This list tells the caller which content types must be configured for the given source viewer, i.e. for which content types the given source viewer's functionalities must be specified. This implementation always returns new String[] { IDocument.DEFAULT_CONTENT_TYPE }.
	//Parameters:
	//sourceViewer - the source viewer to be configured by this configuration
	//Returns:
	//the configured content types for the given viewer
	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return 
		      //DocumentProvider.
		      DocumentTypes;
	}

	//public IReconciler getReconciler(ISourceViewer sourceViewer)
	//Returns the reconciler ready to be used with the given source viewer. This implementation always returns null.
	//Parameters:
	//sourceViewer - the source viewer to be configured by this configuration
	//Returns:
	//a reconciler or null if reconciling should not be supported
	@Override()
	public IReconciler getReconciler(ISourceViewer sourceViewer) {
	   
		PARSER strategy = new PARSER(editor, "haha"); // // <= PARSER 1/1
		
		//strategy.setEditor(editor);
		MonoReconciler reconciler = new MonoReconciler(strategy, false);
		return reconciler;
	}
	
	//public ITextHover getTextHover(ISourceViewer sourceViewer,
          //String contentType,
          //int stateMask)
    //Returns the text hover which will provide the information to be shown in a text hover popup window when requested for the given source viewer and the given content type. This implementation always returns null.
    //Parameters:
    //sourceViewer - the source viewer to be configured by this configuration
    //contentType - the content type
    //stateMask - the SWT event state mask
    //Returns:
    //a text hover or null if no hover support should be installed	

	@Override()
	public ITextHover getTextHover(ISourceViewer sourceViewer,
			String contentType) {

		/* if (XTH_.activated)
			return new Extensions.XTH_TextHover();
		else*/
			return null;
	}
	
	//public IContentAssistant getContentAssistant(ISourceViewer sourceViewer)
	//Returns the content assistant ready to be used with the given source viewer. This implementation always returns null.
	//Parameters:
	//sourceViewer - the source viewer to be configured by this configuration
	//Returns:
	//a content assistant or null if content assist should not be supported

	@Override()
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {

		if (false) { //(XCA_.activated) {
			ContentAssistant assistant = new ContentAssistant();
			/*
			assistant.setContentAssistProcessor(
					new XCA_ContentAssistProcessor(),
					IDocument.DEFAULT_CONTENT_TYPE);
					*/

			return assistant;
		} else {
			return null;
		}
	}

}