package sernet.verinice.model.dataprotection;

import java.util.Collection;

import sernet.verinice.model.common.CnATreeElement;
import sernet.verinice.model.common.ILinkChangeListener;

public class DataProcess extends CnATreeElement
implements IBSIStrukturElement {
    
    private transient Logger log = Logger.getLogger(Process.class);

    public Logger getLog() {
        if (log == null) {
            log = Logger.getLogger(Data.class);
        }
        return log;
    }
	
	public static final String PROP_TAG			= "process_tag"; //$NON-NLS-1$
	public static final String PROP_NAME = "process_name"; //$NON-NLS-1$
	public static final String PROP_KUERZEL = "process_kuerzel"; //$NON-NLS-1$

	// ID must correspond to entity definition in entitytype XML description:
	public static final String TYPE_ID = "process"; //$NON-NLS-1$
	public static final String PROP_ERLAEUTERUNG = "process_erlaeuterung"; //$NON-NLS-1$
	public static final String PROP_ANZAHL = "process_anzahl"; //$NON-NLS-1$
	
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
	
	@Override
    public String getKuerzel() {
		return getEntity().getSimpleValue(PROP_KUERZEL);
	}
	@Override
    public Collection<? extends String> getTags() {
		return TagHelper.getTags(getEntity().getSimpleValue(PROP_TAG));
	}
	
	protected DataProcess() {
		
	}
	
	@Override
    public int getSchicht() {
		return 0;
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
        return "Person [getFullName()=" + getName() + ", getKuerzel()=" + getKuerzel()
                + ", getId()=" + getId() + "]";
    }
