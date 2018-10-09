package sernet.verinice.bpm.rcp;

import org.eclipse.ui.IWorkbenchWindow;

import sernet.gs.ui.rcp.main.ImageCache;
import sernet.gs.ui.rcp.main.actions.OpenViewAction;
import sernet.gs.ui.rcp.main.common.model.CnAElementFactory;
import sernet.gs.ui.rcp.main.common.model.IModelLoadListener;
import sernet.gs.ui.rcp.main.service.ServiceFactory;
import sernet.verinice.interfaces.ActionRightIDs;
import sernet.verinice.model.bp.elements.BpModel;
import sernet.verinice.model.bsi.BSIModel;
import sernet.verinice.model.catalog.CatalogModel;
import sernet.verinice.model.dataprotection.DataProtectionModel;
import sernet.verinice.model.iso27k.ISO27KModel;

public class OpenTaskViewAction extends OpenViewAction {

    Boolean isActive = null;
    
    public OpenTaskViewAction(IWorkbenchWindow window) {
        super(window, Messages.OpenTaskViewAction_0, TaskView.ID, ImageCache.VIEW_TASK, ActionRightIDs.TASKVIEW);
        CnAElementFactory.getInstance().addLoadListener(new IModelLoadListener() {
            public void closed(BSIModel model) {                
            }
            public void loaded(BSIModel model) {             
            }
            public void loaded(ISO27KModel model) {
                if(checkRights()){
                    setEnabled(isActive());
                }
            }
            @Override
            public void loaded(BpModel model) {
                // nothing to do
                
            }
            @Override
            public void loaded(CatalogModel model) {
                // nothing to do
            }
			@Override
			public void loaded(DataProtectionModel model) {
				// do nothing
				
			}
        });
    }
    
    public OpenTaskViewAction(IWorkbenchWindow window, String rightID){
        super(window, Messages.OpenTaskViewAction_0, TaskView.ID, ImageCache.VIEW_TASK, rightID);
        CnAElementFactory.getInstance().addLoadListener(new IModelLoadListener() {
            public void closed(BSIModel model) {                
            }
            public void loaded(BSIModel model) {             
            }
            public void loaded(ISO27KModel model) {
                if(checkRights()){
                    setEnabled(isActive());
                }
            }
            @Override
            public void loaded(BpModel model) {
                // nothing to do
            }

            @Override
            public void loaded(CatalogModel model) {
                // nothing to do
            }
			@Override
			public void loaded(DataProtectionModel model) {
				// do nothing
				
			}
        });        
    }
    
    private boolean isActive() {
        if(isActive==null) {
            isActive = ServiceFactory.lookupProcessServiceIsa().isActive();
        }
        return isActive.booleanValue();
    }

}
