/*******************************************************************************
 * Copyright (c) 2017 Daniel Murygin <dm[a]sernet.de>.
 * This program is free software: you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License 
 * as published by the Free Software Foundation, either version 3 
 * of the License, or (at your option) any later version.
 *     This program is distributed in the hope that it will be useful,    
 * but WITHOUT ANY WARRANTY; without even the implied warranty 
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU General Public License for more details.
 *     You should have received a copy of the GNU General Public 
 * License along with this program. 
 * If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contributors:
 *     Daniel Murygin <dm[a]sernet.de> - initial API and implementation
 ******************************************************************************/
package sernet.verinice.dataprotection.dnd.transfer;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.swt.dnd.TransferData;

import sernet.gs.model.IGSModel;
import sernet.gs.ui.rcp.main.bsi.dnd.transfer.VeriniceElementTransfer;
import sernet.verinice.model.bsi.BausteinUmsetzung;
import sernet.verinice.model.bsi.IBSIStrukturElement;
import sernet.verinice.model.bsi.IMassnahmeUmsetzung;

/**
 *
 */
public final class DataProtectionModelingTransfer extends VeriniceElementTransfer {

    private static final Logger log = Logger.getLogger(DataProtectionModelingTransfer.class);
    
    private static final String TYPENAME_DPMODELELEMENT = "dpModelElement";
    private static final int TYPEID_DPMODELELEMENT = registerType(TYPENAME_DPMODELELEMENT);
    
    private static DataProtectionModelingTransfer instance = new DataProtectionModelingTransfer();
    
    public static DataProtectionModelingTransfer getInstance(){
        return instance;
    }
    
    private DataProtectionModelingTransfer(){}
    
    /* (non-Javadoc)
     * @see org.eclipse.swt.dnd.Transfer#getTypeNames()
     */
    @Override
    protected String[] getTypeNames() {
        return new String[]{TYPENAME_DPMODELELEMENT};
    }

    /* (non-Javadoc)
     * @see org.eclipse.swt.dnd.Transfer#getTypeIds()
     */
    @Override
    protected int[] getTypeIds() {
        return new int[]{TYPEID_DPMODELELEMENT};
    }
    
    public void javaToNative (Object data, TransferData transferData){      
        
        if (data == null || !(validateData(data))) {
            return;
        }
        if (isSupportedType(transferData)) {
            ArrayList<IMassnahmeUmsetzung> massnahmen = new ArrayList<>();
            if (data instanceof IMassnahmeUmsetzung[]) {
                massnahmen.addAll(Arrays.asList((IMassnahmeUmsetzung[]) data));
                write(getInstance(), transferData, massnahmen);
            } else if (data instanceof IMassnahmeUmsetzung) {
                massnahmen.add((IMassnahmeUmsetzung) data);
                write(getInstance(), transferData, massnahmen);
            }

            ArrayList<BausteinUmsetzung> bausteine = new ArrayList<>();
            if(data instanceof BausteinUmsetzung[]){
                bausteine.addAll(Arrays.asList((BausteinUmsetzung[]) data));
                write(getInstance(), transferData, bausteine);
            }else if(data instanceof BausteinUmsetzung){
                bausteine.add((BausteinUmsetzung) data);
                write(getInstance(), transferData, bausteine);
            }

        }
    }
    
    private static void write(VeriniceElementTransfer transfer, TransferData transferData,
            List<?> elements) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream objectOut = new ObjectOutputStream(out);

            objectOut.writeObject(elements.toArray(new Object[elements.size()]));

            transfer.doJavaToNative(out.toByteArray(), transferData);
        } catch (IOException e) {
        	log.error("Error while serializing object for dnd", e);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see sernet.gs.ui.rcp.main.bsi.dnd.transfer.VeriniceElementTransfer#
     * validateData(java.lang.Object)
     */
    @Override
    public boolean validateData(Object data){
        return (data instanceof IMassnahmeUmsetzung || data instanceof IMassnahmeUmsetzung[]
                || data instanceof BausteinUmsetzung || data instanceof BausteinUmsetzung[]);
    }

}
