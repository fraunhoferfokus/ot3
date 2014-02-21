package CyanTools;

import java.io.StringReader;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.reconciler.DirtyRegion;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension;

import Gently.GrammarLib;
import Gently.JavaNode;
import Gently.TokenStream;

import Interface.*;


public class PARSER implements IReconcilingStrategy,
		IReconcilingStrategyExtension {

	public PARSER(Interface.EDITOR e, String haha) { // called from Configuration getReconciler 1/1
		gEditor = e;
	}

	EDITOR gEditor;
	
	private IDocument fDocument;

	// IReconcilingStrategy 1
	//void setDocument(IDocument document)
	//Tells this reconciling strategy on which document it will work. This method will be called before any other method and can be called multiple times. The regions passed to the other methods always refer to the most recent document passed into this method.
	//Parameters:
	//document - the document on which this strategy will work
	
	@Override
	public void setDocument(IDocument d) {
		fDocument = d;
	}

	// IReconcilingStrategy 2
	//void reconcile(DirtyRegion dirtyRegion,
          //IRegion subRegion)
    //Activates incremental reconciling of the specified dirty region. As a dirty region might span multiple content types, the segment of the dirty region which should be investigated is also provided to this reconciling strategy. The given regions refer to the document passed into the most recent call of setDocument(IDocument).
    //Parameters:
    //dirtyRegion - the document region which has been changed
    //subRegion - the sub region in the dirty region which should be reconciled
	
	@Override
	public void reconcile(DirtyRegion dirtyRegion, IRegion subRegion) {
	   System.out.println("+++ Reconciler reconcile 1 called");
		DoReconcile(fDocument);
	}

	// IReconcilingStrategy 2
	//void reconcile(IRegion partition)
	//Activates non-incremental reconciling. The reconciling strategy is just told that there are changes and that it should reconcile the given partition of the document most recently passed into setDocument(IDocument).
	//Parameters:
	//partition - the document partition to be reconciled
	
	@Override
	public void reconcile(IRegion partition) {
	   System.out.println("+++ Reconciler reconcile 2 called");
		DoReconcile(fDocument);
	}

	// IReconcilingStrategyExtension 1
	//void setProgressMonitor(IProgressMonitor monitor)
	//Tells this reconciling strategy with which progress monitor it will work. This method will be called before any other method and can be called multiple times.
	//Parameters:
	//monitor - the progress monitor with which this strategy will work
	
	@Override
	public void setProgressMonitor(IProgressMonitor monitor) {
	}

	// IReconcilingStrategyExtension 2
	//void initialReconcile()
	//Called only once in the life time of this reconciling strategy.
	
	@Override
	public void initialReconcile() {
	   System.out.println("+++ Reconciler initialReconcile called");
		DoReconcile(fDocument);
	}
	
	//----------------------------------------------------------------------------------------------------------------------------

	private void DoReconcile(IDocument doc) {
	   
	   // PARSER CONNECTION

		XParseDocument(doc, gEditor); // <= PARSER 1/1
		
		Gently.JavaNode ast = getAbstractSyntax();

		if (ast != null) {
			Folder.PrepareFolding(gEditor);
		}

	}
	//----------------
	private //public 
    static JavaNode AbstractSyntax;
    
    public static JavaNode getAbstractSyntax()
    {
       return AbstractSyntax;
    }
    public static void ClearAbstractSyntax()
    {
       AbstractSyntax = null;
    }

    // used in Umbra.Reconceiler PARSER CONNECTION
    //public 
    private static void XParseDocument(IDocument document, EDITOR gEditor) {
       
       System.out.println("+++ Parser.ParseDocument called");
        
        String loc = gEditor.getLoc();

        String docstr = document.get();
        StringReader rdr = new StringReader(docstr);
        
        String name = loc;

        AbstractSyntax = GrammarLib.CyanParser(name, rdr, docstr);

        if (AbstractSyntax == null) {

            // PARSE ERROR

            ErrorHandler.ClearErrorMarkers(gEditor);
            
            Gently.Coordinate p = TokenStream.ReportedErrorPos;
            ErrorHandler.Error("syntax error", p, gEditor);

        } else {

            // PARSE OK
           
            boolean flag = GrammarLib.CyanSemantics(loc, AbstractSyntax);
            if (!flag) {

                // SEMANTICS ERROR

                ErrorHandler.ClearErrorMarkers(gEditor);
                
                String SemErr_msg = TokenStream.ReportedErrorMsg;
                
                Gently.Coordinate p = TokenStream.ReportedErrorPos;
                ErrorHandler.Error(SemErr_msg, p, gEditor);

                AbstractSyntax = null;
            } else {

                // SEMANTICS OK

                ErrorHandler.ClearErrorMarkers(gEditor);
            }
        }
    }
	//----------------

}
