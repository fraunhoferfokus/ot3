package Extensions;

import Gently.JavaNode;
import CyanTools.PARSER;
import UserPackage.OutlineTupel;

import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.custom.CaretEvent;
import org.eclipse.swt.custom.CaretListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

//ANNOATED
public class XOL_ContentOutlinePage extends ContentOutlinePage implements
		CaretListener {

	private IDocument input;
	private XOL_SyntaxContentProvider contentProvider;

	// CONSTRUCTOR
	public XOL_ContentOutlinePage(final TextEditor eddy) {
	   
	   System.out.println("+++ XOL_ContentOutlinePage constructor called");
	   
		IDocument doc = null;
		if (eddy.getEditorInput() != null) {
			doc = eddy.getDocumentProvider().getDocument(eddy.getEditorInput());
		}
		input = doc;

		addSelectionChangedListener(new ISelectionChangedListener() {

		   // CALLBACK
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
			   System.out.println("+++ XOL_ContentOutlinePage/ISelectionChangedListener selectionChanged called");

				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();
				if (!selection.isEmpty()) {
					ITypedRegion region = (ITypedRegion) selection
							.getFirstElement();
					eddy.setHighlightRange(region.getOffset(),
							region.getLength(), true);
				}
				refresh();
			}

		});

	}

	public
	void refresh() {
		final TreeViewer treeViewer = getTreeViewer();
		if (treeViewer != null && treeViewer.getTree() != null
				&& !treeViewer.getTree().isDisposed()) {

			Control control = treeViewer.getControl();
			control.setRedraw(false);
			treeViewer.setInput(this.input);
			treeViewer.refresh();
			treeViewer.expandAll();
			control.setRedraw(true);
			control.redraw();

		}
	}

	// CALLBACK
	@Override
	// ContentOutlinePage
	public void createControl(Composite parent) {
	   
	   System.out.println("+++ XOL_ContentOutlinePage createControl called");

		PARSER.ClearAbstractSyntax();
		super.createControl(parent);
		TreeViewer treeViewer = getTreeViewer();
		contentProvider = new XOL_SyntaxContentProvider();
		treeViewer.setContentProvider(contentProvider);
		treeViewer.setLabelProvider(new XOL_SimpleLabelProvider());
		treeViewer.addSelectionChangedListener(this);

		if (this.input != null) {
			treeViewer.setInput(this.input);
		} else {

		}
		getSite().setSelectionProvider(treeViewer);
	}

	// CALLBACK
	@Override
	// CaretListener
	public void caretMoved(CaretEvent event) {

		// fuer outline muss lediglich die Ast-Wurzel geliefert werden
		// OutLineNode ist JavaNode plus ITypedRegion

		System.out.println("+++ XOL_ContentOutlinePage caretMoved called");

		JavaNode parseresult = PARSER.getAbstractSyntax();

		if (parseresult == null) {
		   System.out.println("+++ parseresult == null");

		} else {

			int offset = event.caretOffset;

			JavaNode JavaNodeAtCaret = JavaNode.getChildNearOffset(parseresult,
					offset);

			OutlineTupel OutlineNodeForCaretPosition = new OutlineTupel(
					JavaNodeAtCaret.getType(), // getType 1
					"peng",
					JavaNodeAtCaret.getCoordinate().getOffset(),
					JavaNodeAtCaret.getCoordinate().getLength(),
					new OutlineTupel[] {}, true);

			TreeViewer tv = getTreeViewer();
			if (tv != null) {

				tv.setSelection(new StructuredSelection(
						OutlineNodeForCaretPosition));
				tv.reveal(OutlineNodeForCaretPosition);
				tv.expandToLevel(

				OutlineNodeForCaretPosition, TreeViewer.ALL_LEVELS);
			}

		}


	}

}
