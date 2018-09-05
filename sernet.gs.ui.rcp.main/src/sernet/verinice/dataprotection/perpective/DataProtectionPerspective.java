package sernet.verinice.dataprotection.perpective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import sernet.gs.ui.rcp.main.bsi.views.BSIMassnahmenView;
import sernet.gs.ui.rcp.main.bsi.views.BrowserView;
import sernet.verinice.bp.rcp.BaseProtectionView;
import sernet.verinice.iso27k.rcp.CatalogView;

public class DataProtectionPerspective implements IPerspectiveFactory {
	public static final String ID = "sernet.verinice.dataprotection.perspective.DataProtectionPerspective";
		
	private static final float RATIO_CONTROL_FOLDER = 0.3f;
	private static final float RATIO_MODEL_FOLDER = 0.4f;
//	private static final float RATIO_DETAILS_FOLDER = 0.5f;

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		
		IFolderLayout controlFolder = layout.createFolder("control", IPageLayout.LEFT, RATIO_CONTROL_FOLDER, editorArea);
		controlFolder.addView(CatalogView.ID);
		controlFolder.addPlaceholder(CatalogView.ID + ":*");
		layout.getViewLayout(CatalogView.ID).setCloseable(true);
		
		IFolderLayout modelFolder = layout.createFolder("model", IPageLayout.LEFT, RATIO_MODEL_FOLDER, editorArea);
		modelFolder.addView(BaseProtectionView.ID);
		modelFolder.addPlaceholder(BaseProtectionView.ID + ":*");
		layout.getViewLayout(BSIMassnahmenView.ID).setCloseable(true);
		
//		IFolderLayout folder = layout.createFolder("datails",IPageLayout.BOTTOM, RATIO_DETAILS_FOLDER, editorArea);
//		folder.addView(BrowserView.ID);
//		layout.getViewLayout(BSIMassnahmenView.ID).setCloseable(true);	
	}
}

