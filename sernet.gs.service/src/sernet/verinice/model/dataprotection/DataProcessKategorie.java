package sernet.verinice.model.dataprotection;

import sernet.verinice.model.common.CnATreeElement;

public class DataProcessKategorie extends CnATreeElement 
implements IDpElement {
	
    private static final long serialVersionUID = 1L;
    public static final String TYPE_ID = "processkategorie"; //$NON-NLS-1$
	public static final String TYPE_ID_HIBERNATE = "process-kategorie"; //$NON-NLS-1$

	public DataProcessKategorie(CnATreeElement model) {
		super(model);
	}
	
	protected DataProcessKategorie() {
		
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
		if (obj instanceof DataProcess){
			return true;
		}
		return false;
	}
}

