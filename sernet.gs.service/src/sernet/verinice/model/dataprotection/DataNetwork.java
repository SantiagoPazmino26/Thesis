package sernet.verinice.model.dataprotection;

import java.util.Collection;

import sernet.hui.common.connect.ITaggableElement;
import sernet.verinice.model.bsi.TagHelper;
import sernet.verinice.model.common.CnATreeElement;


/**
 * 
 * @author Santiago Pazmino
 */

public class DataNetwork extends CnATreeElement implements IDpElement, ITaggableElement  {
    
    private static final long serialVersionUID = 0L;
       
    public static final String TYPE_ID = "data_network"; //$NON-NLS-1$
    public static final String PROP_NAME = "data_network_name"; //$NON-NLS-1$
    public static final String PROP_TAG = "data_network_tag"; //$NON-NLS-1$
    public static final String PROP_QUALIFIER = "data_network_qualifier"; //$NON-NLS-1$
    public static final String PROP_QUALIFIER_BASIC = "data_network_qualifier_basic"; //$NON-NLS-1$
    public static final String PROP_QUALIFIER_STANDARD = "data_network_qualifier_standard"; //$NON-NLS-1$
    public static final String PROP_QUALIFIER_HIGH = "data_network_qualifier_high"; //$NON-NLS-1$
    
    protected DataNetwork() {}
    
    public DataNetwork(CnATreeElement parent) {
        this(parent, false);
    }

    public DataNetwork(CnATreeElement parent, boolean createChildren) {
        super(parent);
        init();
        if (createChildren) {
            createNewCategories();
        }
    }     
    
    public void createNewCategories() {
        addChild(new DataKategorie(this));
        addChild(new DataProcessKategorie(this));
        addChild(new DataStakeholderKategorie(this));
    }
    
    @Override
    public boolean canContain(Object object) {
        return object instanceof DataKategorie || object instanceof DataProcessKategorie
                ;
    }

    @Override
    public String getTitle() {
        return getEntity().getPropertyValue(PROP_NAME);
    }
    
    @Override
    public void setTitel(String name) {
        getEntity().setSimpleValue(getEntityType().getPropertyType(PROP_NAME), name);
    }

    @Override
    public String getTypeId() {
        return TYPE_ID;
    }
    
    public String getQualifier() {
        return getEntity().getPropertyValue(PROP_QUALIFIER);
    }

    public void setQualifier(String qualifier) {
        getEntity().setSimpleValue(getEntityType().getPropertyType(PROP_QUALIFIER), qualifier);
    }

    /**
     * @return The proceeding of securing. The proceeding is stored in the
     *         property PROP_QUALIFIER.
     */
    public String getProceeding() {
        return getQualifier();
    }

    /**
     * Sets the proceeding of securing. The proceeding is stored in the property
     * PROP_QUALIFIER.
     * 
     * @param proceeding
     *            The proceeding of securing or qualifier
     */
    public void setProceeding(String proceeding) {
        setQualifier(proceeding);
    }
    
    public static boolean isItNetwork(CnATreeElement element) {
        if (element == null) {
            return false;
        }
        return TYPE_ID.equals(element.getTypeId());
    }

    @Override
    public Collection<String> getTags() {
        return TagHelper.getTags(getEntity().getPropertyValue(PROP_TAG));
    }

}
