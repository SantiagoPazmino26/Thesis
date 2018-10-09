
package sernet.verinice.model.dataprotection;

import sernet.verinice.model.bsi.IBSIStrukturKategorie;
import sernet.verinice.model.common.CnATreeElement;

public class DataKategorie extends CnATreeElement 
implements IBSIStrukturKategorie {
	
    private static final long serialVersionUID = 1L;
    public static final String TYPE_ID = "datakategorie"; //$NON-NLS-1$
	public static final String TYPE_ID_HIBERNATE = "data-kategorie"; //$NON-NLS-1$

	public DataKategorie(CnATreeElement model) {
		super(model);
	}
	
	protected DataKategorie() {
		
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
		if (obj instanceof Data){
			return true;
		}
		return false;
	}
}
