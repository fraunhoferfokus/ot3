package Extensions;

import Gently.JavaNode;
import CyanTools.PARSER;
import UserPackage.OutlineTupel;

import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

import UserPackage.UserCode;

class XOL_SyntaxContentProvider implements ITreeContentProvider {

	private static final Object[] EMPTY = {};
	private TreeViewer viewer;
	private IDocument document;
	JavaNode syntax = null;

	private IDocumentListener documentListener = new IDocumentListener() {

		@Override
		public void documentChanged(DocumentEvent event) {
			//System.out.println("documentChanged in XOL_SyntaxContentProvider");
			if (!XOL_SyntaxContentProvider.this.viewer.getControl()
					.isDisposed()) {
				//System.out.println("(isDisposed, AbstractSyntax set to null)");
			   PARSER.ClearAbstractSyntax();
				//Parser.AbstractSyntax = null;
				XOL_SyntaxContentProvider.this.viewer.refresh();
			}
		}

		@Override
		public void documentAboutToBeChanged(DocumentEvent event) {
		}
	};

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		this.viewer = (TreeViewer) viewer;

		if (oldInput instanceof IDocument) {
			document.removeDocumentListener(documentListener);
		}

		if (newInput instanceof IDocument) {
			document = (IDocument) newInput;
			document.addDocumentListener(documentListener);
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {
		// called for root element
		//System.out.println("Outline: !!!!!!! getElements for root");
		
		JavaNode n = PARSER.getAbstractSyntax();
		if (n == null) {
			return EMPTY;
		}

		
		JavaNode parseresult = PARSER.getAbstractSyntax();
		Object[] result = UserCode.XOL_UserData(parseresult);
		
		return result; 
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		// called for non-root elements
		//System.out.println("Outline: getChildren");

		if (parentElement instanceof OutlineTupel) {
			OutlineTupel p = (OutlineTupel) parentElement;
			return p.kinder;
		}

		return null;
	}
	
	
	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof OutlineTupel){
			OutlineTupel region = (OutlineTupel) element;
			
			boolean res = region.kinder.length>0;
			return res;
		}

		return false;
	}

}