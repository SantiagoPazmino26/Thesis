package sernet.verinice.model.dataprotection;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;

import sernet.verinice.model.bp.IBpModelListener;
import sernet.verinice.model.common.ChangeLogEntry;
import sernet.verinice.model.common.CnALink;
import sernet.verinice.model.common.CnATreeElement;

/**
 * 
 * @author Santiago Pazmino
 */
public class DataProtectionModel extends CnATreeElement implements IDpRoot {
    
    private static final long serialVersionUID = 0L;
    
    public static final String TYPE_ID = "dataprotection_model"; //$NON-NLS-1$    
    public static final String TITLE = "Data Protection Model"; //$NON-NLS-1$
    
    private transient Logger log;
    
    private transient List<IDpModelListener> listeners;
    
    
    /* (non-Javadoc)
     * @see sernet.verinice.model.common.CnATreeElement#getTitle()
     */
    @Override
    public String getTitle() {
        return TITLE;
    }

    /* (non-Javadoc)
     * @see sernet.verinice.model.common.CnATreeElement#getTypeId()
     */
    @Override
    public String getTypeId() {
        return TYPE_ID;
    }
    
    @Override
    public void refreshAllListeners(Object source) {
        Logger.getLogger(this.getClass()).debug("Model refresh to all listeners."); //$NON-NLS-1$
        for (IDpModelListener listener : getListeners()) {
            listener.modelRefresh(source);
        }
    }
    
    private synchronized List<IDpModelListener> getListeners() {
        if (listeners == null){
            listeners = new CopyOnWriteArrayList<>();
        }
        return listeners;
    }
    
    @Override
    public boolean canContain(Object obj) {
        return (obj instanceof DataNetwork);
    }

    @Override
    public void childAdded(CnATreeElement category, CnATreeElement child) {
        for (IDpModelListener listener : getListeners()) {
            listener.childAdded(category, child);
            if (child instanceof DataNetwork) {
                listener.modelRefresh(null);
            }
        }
    }
    
    @Override
    public void databaseChildAdded(CnATreeElement child) {
        if (child == null){
            return;
        }
        for (IDpModelListener listener : getListeners()) {
            listener.databaseChildAdded(child);
        }
    }
    
    @Override
    public void childRemoved(CnATreeElement category, CnATreeElement child) {
        for (IDpModelListener listener : getListeners()) {
            listener.childRemoved(category, child);
        }
    }
    
    @Override
    public void removeChild(CnATreeElement child) {
        if (getChildren().remove(child)) {
            this.childRemoved(this, child);
        }
    }
    
    @Override
    public void databaseChildRemoved(CnATreeElement child) {
        for (IDpModelListener listener : getListeners()) {
            listener.databaseChildRemoved(child);
        }   
    }
    
    @Override
    public void databaseChildRemoved(ChangeLogEntry entry) {
        for (IDpModelListener listener : getListeners()) {
            listener.databaseChildRemoved(entry);
        }
    }

    @Override
    public void childChanged(CnATreeElement child) {
        for (IDpModelListener listener : getListeners()) {
            listener.childChanged(child);
        }
    }
    
    @Override
    public void databaseChildChanged(CnATreeElement child) {
        for (IDpModelListener listener : getListeners()) {
            listener.databaseChildChanged(child);
        }
    }
    
    @Override
    public void linkChanged(CnALink old, CnALink link, Object source) {
        for (IDpModelListener listener : getListeners()) {
            listener.linkChanged(old, link, source);
        }
    }
    
    @Override
    public void linkRemoved(CnALink link) {
        for (IDpModelListener listener : getListeners()) {
            listener.linkRemoved(link);
        }
    }
    
    @Override
    public void linkAdded(CnALink link) {
        for (IDpModelListener listener : getListeners()) {
            listener.linkAdded(link);
        }
    }
    
    public void modelReload(DataProtectionModel newModel) {
        for (IDpModelListener listener : getListeners()) {
            listener.modelReload(newModel);
            if (getLog().isDebugEnabled()) {
                getLog().debug("modelReload, listener: " + listener); //$NON-NLS-1$
            }
        }
    }
    
    public void addModITBOModelListener(IDpModelListener listener) {
        if (getLog().isDebugEnabled()) {
            getLog().debug("Adding ISO model listener.");
        }
        if (!getListeners().contains(listener)){
            getListeners().add(listener);
        }
    }
    
    public void removeBpModelListener(IDpModelListener listener) {
        if (getListeners().contains(listener)){
            getListeners().remove(listener);
        }
    }
    
    /**
     * Moves all {@link IBpModelListener} from this model
     * to newModel.
     * 
     * @param newModel 
     */
    public void moveListener(DataProtectionModel newModel) {
        for (IDpModelListener listener : getListeners()) {
            newModel.addModITBOModelListener(listener);
        }
        for (IDpModelListener listener : getListeners()) {
            removeBpModelListener(listener);
        }      
    }    
    
    private Logger getLog() {
        if(log==null) {
            log = Logger.getLogger(DataProtectionModel.class);
        }
        return log;
    }

}
