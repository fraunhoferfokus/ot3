package Extensions;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import Interface.Activator;
import UserPackage.OutlineTupel;

class XOL_SimpleLabelProvider extends LabelProvider
{
	@Override
	public Image getImage(Object element)
	{
		OutlineTupel node = (OutlineTupel) element;
		
		String str = node.bildname;

		if (str.equals("peng")) {
			ImageDescriptor idesc = Activator.getImageDescriptor("icons/type.gif");
			return idesc.createImage();
		}	
		else if (str.equals("type")) {
			ImageDescriptor idesc = Activator.getImageDescriptor("icons/type.gif");
			return idesc.createImage();
		} else if (str.equals("module")) {
			ImageDescriptor idesc = Activator.getImageDescriptor("icons/module.gif");
			return idesc.createImage();
		} else if (str.equals("function")) {
			ImageDescriptor idesc = Activator.getImageDescriptor("icons/function.gif");
			return idesc.createImage();	
		} else if (str.equals("testcase")) {
			//System.out.println("try to load image testcase %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			ImageDescriptor idesc = Activator.getImageDescriptor("icons/testcase.gif");
			Image img = idesc.createImage();
			//System.out.println("img=" + img);
			return img;
			
		}
		return null;
	}

	@Override // LabelProvider
	public String getText(Object element)
	{

		OutlineTupel node = (OutlineTupel) element;
		String str = node.aufschrift;
		return str;
	}

}