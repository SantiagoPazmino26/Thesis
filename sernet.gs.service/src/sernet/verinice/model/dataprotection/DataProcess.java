package sernet.verinice.model.dataprotection;

import java.util.Collection;

import sernet.hui.common.connect.Entity;
import sernet.hui.common.connect.HUITypeFactory;
import sernet.hui.common.connect.Property;
import sernet.hui.common.connect.PropertyList;
import sernet.hui.common.connect.PropertyType;
import sernet.hui.common.multiselectionlist.IMLPropertyOption;
import sernet.verinice.interfaces.IReevaluatorProtectionGoals;
import sernet.verinice.model.bsi.CnaStructureHelper;
import sernet.verinice.model.bsi.IBSIStrukturElement;
import sernet.verinice.model.bsi.MaximumProtectionRequirementsListener;
import sernet.verinice.model.bsi.TagHelper;
import sernet.verinice.model.common.CnATreeElement;
import sernet.verinice.model.common.ILinkChangeListener;
import sernet.verinice.model.ds.IDatenschutzElement;

import org.apache.log4j.Logger;

public class DataProcess extends CnATreeElement
implements IDpElement {
    
    private transient Logger log = Logger.getLogger(Process.class);

    public Logger getLog() {
        if (log == null) {
            log = Logger.getLogger(Data.class);
        }
        return log;
    }
	
    private static final long serialVersionUID = 1L;
    
	public static final String PROP_TAG			= "data_process_tag"; //$NON-NLS-1$
	public static final String PROP_NAME = "data_process_name"; //$NON-NLS-1$
	public static final String PROP_KUERZEL = "data_process_kuerzel"; //$NON-NLS-1$

	// ID must correspond to entity definition in entitytype XML description:
	public static final String TYPE_ID = "data_process"; //$NON-NLS-1$
	public static final String PROP_ERLAEUTERUNG = "data_process_erlaeuterung"; //$NON-NLS-1$
	public static final String PROP_ANZAHL = "data_process_anzahl"; //$NON-NLS-1$
	
	private final IReevaluatorProtectionGoals schutzbedarfProvider 
	= new ProtectionGoalsAdapter(this);
	
	private final ILinkChangeListener linkChangeListener
	= new MaximumProtectionRequirementsListener(this);
	
	public DataProcess(CnATreeElement parent) {
		super(parent);
		setEntity(new Entity(TYPE_ID));
		getEntity().initDefaultValues(getTypeFactory());
        // sets the localized title via HUITypeFactory from message bundle
        setTitel(getTypeFactory().getMessage(TYPE_ID));
    }	
	
	protected DataProcess() {
		
	}	
	
	@Override
	public String getTitle() {
		if (getEntity() == null){
			return ""; //$NON-NLS-1$
		}
		return getTitel(getEntity());
	}
	
	public static String getTitel(Entity entity) {
		if (entity == null){
			return ""; //$NON-NLS-1$
		}
		StringBuffer buff = new StringBuffer();
		buff.append( entity.getSimpleValue(PROP_NAME));
		
		return buff.toString();
	}
	
	@Override
    public void setTitel(String name) {
		getEntity().setSimpleValue(getEntityType().getPropertyType(PROP_NAME), name);
	}
	
	public String getName() {
	    return getEntity().getSimpleValue(PROP_NAME);
	}

	@Override
	public String getTypeId() {
		return TYPE_ID;
	}
	
	@Override
	public void addChild(CnATreeElement child) {
		// Person doesn't have children
	}
	
	@Override
	public boolean canContain(Object obj) {
		return CnaStructureHelper.canContain(obj);
	}

	public IReevaluatorProtectionGoals getProtectionGoalsProvider() {
		return schutzbedarfProvider;
	}
	
	@Override
	public ILinkChangeListener getLinkChangeListener() {
		return linkChangeListener;
	}
	
	public void setErlaeuterung(String name) {
		getEntity().setSimpleValue(getEntityType().getPropertyType(PROP_ERLAEUTERUNG), name);
	}
	
	public String getErlaeuterung() {
		return getEntity().getSimpleValue(PROP_ERLAEUTERUNG);
	}
	
	public void setKuerzel(String name) {
		getEntity().setSimpleValue(getEntityType().getPropertyType(PROP_KUERZEL), name);
	}

	public void setAnzahl(int anzahl) {
		getEntity().setSimpleValue(getEntityType().getPropertyType(PROP_ANZAHL), Integer.toString(anzahl));
	}

    @Override
    public String toString() {
        return "Person [getFullName()=" + getName()
                + ", getId()=" + getId() + "]";
    }
}
