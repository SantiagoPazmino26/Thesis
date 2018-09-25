package sernet.verinice.dataprotection.actions;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

import sernet.gs.ui.rcp.main.actions.ImportXMLAction;
import sernet.gs.ui.rcp.main.bsi.dialogs.XMLImportDialog;
import sernet.verinice.interfaces.ActionRightIDs;
/**
 * This action opens the import dialog for VNA files as catalogs.
 * This action is executed when the import button is pressed in the tool bar of
 * the Catalog View. The action is configured in the plugin.xml file.
 * 
 * @author Sebastian Hagedorn
 * @author Daniel Murygin
 */
public class ImportCatalogAction extends ImportXMLAction {
    
    public static final String ID = "sernet.verinice.dataprotection.importcatalogaction";

    public void run() {
        final XMLImportDialog dialog = new XMLImportDialog(Display.getCurrent().getActiveShell(), true);  
        if (dialog.open() != Window.OK) {
            return;
        }
    }

    @Override
    public String getRightID() {
        return ActionRightIDs.CATALOGIMPORT;
    }

}
