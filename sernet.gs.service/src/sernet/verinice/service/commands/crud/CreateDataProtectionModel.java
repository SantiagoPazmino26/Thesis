package sernet.verinice.service.commands.crud;

import sernet.gs.service.RuntimeCommandException;
import sernet.verinice.interfaces.CommandException;
import sernet.verinice.model.common.ChangeLogEntry;
import sernet.verinice.model.dataprotection.DataProtectionModel;
import sernet.verinice.service.commands.SaveElement;
import sernet.verinice.service.model.LoadModel;

public class CreateDataProtectionModel extends SaveElement<DataProtectionModel> {

    private static final long serialVersionUID = 0L;
    private transient Logger log = Logger.getLogger(CreateDataProtectionModel.class);
    
    public CreateDataProtectionModel() {
        this.element = new DataProtectionModel();
        this.stationId = ChangeLogEntry.STATION_ID;
    }

    @Override
    public void execute() {
        LoadModel<DataProtectionModel> loadModel = new LoadModel<>(DataProtectionModel.class);
        try {
            loadModel = getCommandService().executeCommand(loadModel);
        } catch (CommandException e) {
            getLog().error("Error while loading data protection model", e);
            throw new RuntimeCommandException("Error while loading data protection model.", e);
        }
        final DataProtectionModel model = loadModel.getModel();
        if(model==null) {
            super.execute();
        } else {
            getLog().warn("Data protection model exists. Will NOT create another model. Returning existing model.");
            element = model;
        }
    }
    
    public Logger getLog() {
        if (log == null) {
            log = Logger.getLogger(CreateDataProtectionModel.class);
        }
        return log;
    }
   
}
