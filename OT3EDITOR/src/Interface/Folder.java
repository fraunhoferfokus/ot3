package Interface;

import java.util.ArrayList;
import java.util.HashMap;



import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.jface.text.source.projection.ProjectionAnnotationModel;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import Extensions.XOL_ContentOutlinePage;
import Gently.JavaNode;
import CyanTools.PARSER;

public class Folder
{

   // called by EDITOR
   public static void setAnnotationModel(ProjectionAnnotationModel model)
   {
      annotationModel = model;
   }

   // called by PARSER after each Reconcile

   public static void PrepareFolding(final EDITOR editor)
   {
      //fPositions.clear();
      fNodes.clear();
      JavaNode ast = PARSER.getAbstractSyntax();
      
      TraverseAst(ast);
      
      Display.getDefault().asyncExec(new Runnable()
      {
         public void run()
         {
            // notify outline
            IContentOutlinePage outline = (IContentOutlinePage) editor
                  .getAdapter(IContentOutlinePage.class);
            if (outline != null && outline instanceof XOL_ContentOutlinePage) {
               ((XOL_ContentOutlinePage) outline).refresh();
            }

            updateFoldingStructure(editor);
         }
      });

   }

   // -------------------
   
   private static void TraverseAst(JavaNode node)
   {
      if (node.Child1.length > 0) {

         int offset = node.getCoordinate().getOffset();
         //int length = node.getCoordinate().getLength();

         if (offset == -1) {
            node.Log();
            return; // artificial node
         }

         if (node.folding == 1) { // !!! FOLDING !!!
            
            

            try {
               //fPositions.add(new Position(offset, length));
               fNodes.add(node);
            } catch (Exception e) {
               // ignore
            }

         }

         for (JavaNode Ch : node.Child1) {
            TraverseAst(Ch);
         }
      }
   }

   
   private static final ArrayList<JavaNode> fNodes = new ArrayList<JavaNode>();
   private static Annotation[] oldAnnotations;
   private static ProjectionAnnotationModel annotationModel;

   private static void updateFoldingStructure(EDITOR editor)
   {
      //Annotation[] annotations = new Annotation[fPositions.size()];
      Annotation[] annotations = new Annotation[fNodes.size()];
      HashMap<ProjectionAnnotation, Position> newAnnotations = new HashMap<ProjectionAnnotation, Position>();


      for (int i = 0; i < fNodes.size(); i++) {
         JavaNode node = fNodes.get(i);
         
         int offset = node.getCoordinate().getOffset();
         int length = node.getCoordinate().getLength();
         
         Position pos = new Position(offset, length);
         
         ProjectionAnnotation annotation = new ProjectionAnnotation();
         newAnnotations.put(annotation, pos);
         annotations[i] = annotation;
      }
      annotationModel.modifyAnnotations(oldAnnotations, newAnnotations, null);
      oldAnnotations = annotations;
   }

}
