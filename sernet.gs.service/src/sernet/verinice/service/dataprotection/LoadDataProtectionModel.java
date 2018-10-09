package sernet.verinice.service.dataprotection;

import java.util.List;

import sernet.gs.service.RetrieveInfo;
import sernet.gs.service.RuntimeCommandException;
import sernet.verinice.interfaces.CommandException;
import sernet.verinice.interfaces.GenericCommand;
import sernet.verinice.interfaces.INoAccessControl;
import sernet.verinice.model.dataprotection.DataProtectionModel;
import org.apache.log4j.Logger;
import sernet.verinice.service.commands.crud.CreateDataProtectionModel;

public class LoadDataProtectionModel extends GenericCommand implements INoAccessControl {

    private static final long serialVersionUID = 0L;

    private transient Logger log = Logger.getLogger(LoadDataProtectionModel.class);

	private DataProtectionModel model;

	public LoadDataProtectionModel() {
	    // nothing to do
	}
	
	@SuppressWarnings("unchecked")
    public void execute() {
		List<DataProtectionModel> modelList = getDaoFactory().getDAO(DataProtectionModel.class).findAll(RetrieveInfo.getChildrenInstance());
		if(modelList==null || modelList.isEmpty()) {
		    if (getLog().isInfoEnabled()) {
		        getLog().info("No data protection model found. Creating a new one...");
            }
		    createDataProtectionModel();
		} else if(modelList.size()>1) {
				throw new RuntimeCommandException("More than one data protection model found.");
		} else if(modelList.size()==1) {			
			model = modelList.get(0);
		}
	}
	
	private void createDataProtectionModel() {
        try {
        	CreateDataProtectionModel modelCreationCommand = new CreateDataProtectionModel();
            modelCreationCommand = getCommandService()
                    .executeCommand(modelCreationCommand);
            model = modelCreationCommand.getElement();
        } catch (CommandException e) {
            throw new RuntimeCommandException("Error while creating base protection model.", e);
        }      
    }

	public DataProtectionModel getModel() {
		return model;
	}
	
    public Logger getLog() {
        if (log == null) {
            log = Logger.getLogger(LoadDataProtectionModel.class);
        }
        return log;
    }
	
}
