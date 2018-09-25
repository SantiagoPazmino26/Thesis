package sernet.verinice.model.dataprotection;

import sernet.verinice.model.iso27k.IISO27KModelListener;

/**
 * @author Santiago Pazmino
 *
 */
public interface IDpModelListener extends IISO27KModelListener {
    
    void modelReload(DataProtectionModel newModel);

}
