
package sernet.verinice.model.dataprotection;

import sernet.verinice.model.common.CnATreeElement;

public class DataStakeholderKategorie extends CnATreeElement 
implements IDpKategorie {
	
    private static final long serialVersionUID = 1L;
    public static final String TYPE_ID = "data_stakeholder_kategorie"; //$NON-NLS-1$
	public static final String TYPE_ID_HIBERNATE = "data-stakeholder-kategorie"; //$NON-NLS-1$

	public DataStakeholderKategorie(CnATreeElement model) {
		super(model);
	}
	
	protected DataStakeholderKategorie() {
		
	}
	@Override
	public String getTitle() {
        return getTypeFactory().getMessage(TYPE_ID);
	}
	
	
	
	@Override
	public String getTypeId() {
		return TYPE_ID;
	}
	
	@Override
	public boolean canContain(Object obj) {
		if (obj instanceof DataStakeholder){
			return true;
		}
		return false;
	}
}
