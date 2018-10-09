/*******************************************************************************
 * Copyright (c) 2018 Santiago Pazmino <santiago.d.pinto[at]student.fh-kiel[dot]de>
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *     This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *     You should have received a copy of the GNU Lesser General Public
 * License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors:
 *     Santiago Pazmino <santiago.d.pinto[at]student.fh-kiel[dot]de> - initial API and implementation
 ******************************************************************************/
package sernet.verinice.model.dataprotection;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import sernet.hui.common.connect.EntityType;
import sernet.hui.common.connect.HUITypeFactory;
import sernet.hui.common.connect.PropertyList;
import sernet.verinice.interfaces.IReevaluator;
import sernet.verinice.interfaces.IReevaluatorProtectionGoals;
import sernet.verinice.model.bsi.Schutzbedarf;
import sernet.verinice.model.bsi.Messages;
import sernet.verinice.model.common.CascadingTransaction;
import sernet.verinice.model.common.CnALink;
import sernet.verinice.model.common.CnATreeElement;
import sernet.verinice.model.common.TransactionAbortedException;

/**
 * Adapter for elements that provide or receive protection levels.
 *
 * @author santiago.d.pinto[at]student.fh-kiel[dot]de
 * @version $Rev$ $LastChangedDate$ $LastChangedBy$
 *
 */
@SuppressWarnings("serial")
public class ProtectionGoalsAdapter implements IReevaluatorProtectionGoals, Serializable {


    private transient Logger log = Logger.getLogger(ProtectionGoalsAdapter.class);

    public Logger getLog() {
        if (log == null) {
            log = Logger.getLogger(ProtectionGoalsAdapter.class);
        }
        return log;
    }

    private CnATreeElement cnaTreeElement;

    public ProtectionGoalsAdapter(CnATreeElement parent) {
        this.cnaTreeElement = parent;
    }

    @Override
    public int getIntegrity() {
        PropertyList properties = cnaTreeElement.getEntity().getProperties(cnaTreeElement.getTypeId() + Schutzbedarf.INTEGRITAET);
        if (hasValue(properties)){
            return Schutzbedarf.toInt(properties.getProperty(0).getPropertyValue());
        } else {
            return Schutzbedarf.UNDEF;
        }
    }

    @Override
    public int getAvailability() {
        PropertyList properties = cnaTreeElement.getEntity().getProperties(cnaTreeElement.getTypeId() + Schutzbedarf.VERFUEGBARKEIT);
        if (hasValue(properties)){
            return Schutzbedarf.toInt(properties.getProperty(0).getPropertyValue());
        } else {
            return Schutzbedarf.UNDEF;
        }
    }

    @Override
    public int getConfidentiality() {
        PropertyList properties = cnaTreeElement.getEntity().getProperties(cnaTreeElement.getTypeId() + Schutzbedarf.VERTRAULICHKEIT);
        if (hasValue(properties)){
            return Schutzbedarf.toInt(properties.getProperty(0).getPropertyValue());
        } else {
            return Schutzbedarf.UNDEF;
        }
    }
    
    @Override
    public int getTransparency() {
        PropertyList properties = cnaTreeElement.getEntity().getProperties(cnaTreeElement.getTypeId() + Schutzbedarf.TRANSPARENCY);
        if (hasValue(properties)){
            return Schutzbedarf.toInt(properties.getProperty(0).getPropertyValue());
        } else {
            return Schutzbedarf.UNDEF;
        }
    }
    
    @Override
    public int getInterveanibility() {
        PropertyList properties = cnaTreeElement.getEntity().getProperties(cnaTreeElement.getTypeId() + Schutzbedarf.INTERVENEABILITY);
        if (hasValue(properties)){
            return Schutzbedarf.toInt(properties.getProperty(0).getPropertyValue());
        } else {
            return Schutzbedarf.UNDEF;
        }
    }
    
    @Override
    public int getUnlinkability() {
        PropertyList properties = cnaTreeElement.getEntity().getProperties(cnaTreeElement.getTypeId() + Schutzbedarf.UNLINKABILITY);
        if (hasValue(properties)){
            return Schutzbedarf.toInt(properties.getProperty(0).getPropertyValue());
        } else {
            return Schutzbedarf.UNDEF;
        }
    }
    
    @Override
    public int getDataMinimization() {
        PropertyList properties = cnaTreeElement.getEntity().getProperties(cnaTreeElement.getTypeId() + Schutzbedarf.DATA_MINIMIZATION);
        if (hasValue(properties)){
            return Schutzbedarf.toInt(properties.getProperty(0).getPropertyValue());
        } else {
            return Schutzbedarf.UNDEF;
        }
    }

    public boolean hasValue(PropertyList properties) {
        return properties != null &&
               !properties.getProperties().isEmpty() &&
               properties.getProperty(0).getPropertyValue() !=null;
    }

    @Override
    public void setIntegrity(int i) {
        EntityType entityType = HUITypeFactory.getInstance().getEntityType(cnaTreeElement.getEntity().getEntityType());
        String option = Schutzbedarf.toOption(cnaTreeElement.getTypeId(), Schutzbedarf.INTEGRITAET, i);

        cnaTreeElement.getEntity().setSimpleValue(entityType.getPropertyType(cnaTreeElement.getTypeId() + Schutzbedarf.INTEGRITAET), option);
    }

    @Override
    public void setAvailability(int i) {
        EntityType entityType = HUITypeFactory.getInstance().getEntityType(cnaTreeElement.getEntity().getEntityType());
        String option = Schutzbedarf.toOption(cnaTreeElement.getTypeId(), Schutzbedarf.VERFUEGBARKEIT, i);
        cnaTreeElement.getEntity().setSimpleValue(entityType.getPropertyType(cnaTreeElement.getTypeId() + Schutzbedarf.VERFUEGBARKEIT), option);
    }

    @Override
    public void setConfidentiality(int i) {
        EntityType entityType = HUITypeFactory.getInstance().getEntityType(cnaTreeElement.getEntity().getEntityType());
        String option = Schutzbedarf.toOption(cnaTreeElement.getTypeId(), Schutzbedarf.VERTRAULICHKEIT, i);
        cnaTreeElement.getEntity().setSimpleValue(entityType.getPropertyType(cnaTreeElement.getTypeId() + Schutzbedarf.VERTRAULICHKEIT), option);
    }
    
    @Override
    public void setTransparency(int i) {
        EntityType entityType = HUITypeFactory.getInstance().getEntityType(cnaTreeElement.getEntity().getEntityType());
        String option = Schutzbedarf.toOption(cnaTreeElement.getTypeId(), Schutzbedarf.TRANSPARENCY, i);
        cnaTreeElement.getEntity().setSimpleValue(entityType.getPropertyType(cnaTreeElement.getTypeId() + Schutzbedarf.TRANSPARENCY), option);
    }
    
    @Override
    public void setInterveanibility(int i) {
        EntityType entityType = HUITypeFactory.getInstance().getEntityType(cnaTreeElement.getEntity().getEntityType());
        String option = Schutzbedarf.toOption(cnaTreeElement.getTypeId(), Schutzbedarf.INTERVENEABILITY, i);
        cnaTreeElement.getEntity().setSimpleValue(entityType.getPropertyType(cnaTreeElement.getTypeId() + Schutzbedarf.INTERVENEABILITY), option);
    }
    
    @Override
    public void setUnlinkability(int i) {
        EntityType entityType = HUITypeFactory.getInstance().getEntityType(cnaTreeElement.getEntity().getEntityType());
        String option = Schutzbedarf.toOption(cnaTreeElement.getTypeId(), Schutzbedarf.UNLINKABILITY, i);
        cnaTreeElement.getEntity().setSimpleValue(entityType.getPropertyType(cnaTreeElement.getTypeId() + Schutzbedarf.UNLINKABILITY), option);
    }
    
    @Override
    public void setDataMinimization(int i) {
        EntityType entityType = HUITypeFactory.getInstance().getEntityType(cnaTreeElement.getEntity().getEntityType());
        String option = Schutzbedarf.toOption(cnaTreeElement.getTypeId(), Schutzbedarf.DATA_MINIMIZATION, i);
        cnaTreeElement.getEntity().setSimpleValue(entityType.getPropertyType(cnaTreeElement.getTypeId() + Schutzbedarf.DATA_MINIMIZATION), option);
    }

    @Override
    public String getIntegrityDescription() {
        return cnaTreeElement.getEntity().getSimpleValue(cnaTreeElement.getTypeId() + Schutzbedarf.INTEGRITAET_BEGRUENDUNG);
    }

    @Override
    public String getAvailabilityDescription() {
        return cnaTreeElement.getEntity().getSimpleValue(cnaTreeElement.getTypeId() + Schutzbedarf.VERFUEGBARKEIT_BEGRUENDUNG);
    }

    @Override
    public String getConfidentialityDescription() {
        return cnaTreeElement.getEntity().getSimpleValue(cnaTreeElement.getTypeId() + Schutzbedarf.VERTRAULICHKEIT_BEGRUENDUNG);
    }
    
    @Override
    public String getTransparencyDescription() {
        return cnaTreeElement.getEntity().getSimpleValue(cnaTreeElement.getTypeId() + Schutzbedarf.TRANSPARENCY_BEGRUENDUNG);
    }
    
    @Override
    public String getInterveanibilityDescription() {
        return cnaTreeElement.getEntity().getSimpleValue(cnaTreeElement.getTypeId() + Schutzbedarf.INTERVENEABILITY_BEGRUENDUNG);
    }
    
    @Override
    public String getUnlinkabilityDescription() {
        return cnaTreeElement.getEntity().getSimpleValue(cnaTreeElement.getTypeId() + Schutzbedarf.UNLINKABILITY_BEGRUENDUNG);
    }
    
    @Override
    public String getDataMinimizationDescription() {
        return cnaTreeElement.getEntity().getSimpleValue(cnaTreeElement.getTypeId() + Schutzbedarf.DATA_MINIMIZATION_BEGRUENDUNG);
    }

    @Override
    public void setIntegrityDescription(String text) {
        EntityType entityType = HUITypeFactory.getInstance().getEntityType(cnaTreeElement.getEntity().getEntityType());
        cnaTreeElement.getEntity().setSimpleValue(entityType.getPropertyType(cnaTreeElement.getTypeId() + Schutzbedarf.INTEGRITAET_BEGRUENDUNG), text);
    }

    @Override
    public void setAvailabilityDescription(String text) {
        EntityType entityType = HUITypeFactory.getInstance().getEntityType(cnaTreeElement.getEntity().getEntityType());
        cnaTreeElement.getEntity().setSimpleValue(entityType.getPropertyType(cnaTreeElement.getTypeId() + Schutzbedarf.VERFUEGBARKEIT_BEGRUENDUNG), text);
    }

    @Override
    public void setConfidentialityDescription(String text) {
        EntityType entityType = HUITypeFactory.getInstance().getEntityType(cnaTreeElement.getEntity().getEntityType());
        cnaTreeElement.getEntity().setSimpleValue(entityType.getPropertyType(cnaTreeElement.getTypeId() + Schutzbedarf.VERTRAULICHKEIT_BEGRUENDUNG), text);
    }
    
    @Override
    public void setTransparencyDescription(String text) {
        EntityType entityType = HUITypeFactory.getInstance().getEntityType(cnaTreeElement.getEntity().getEntityType());
        cnaTreeElement.getEntity().setSimpleValue(entityType.getPropertyType(cnaTreeElement.getTypeId() + Schutzbedarf.TRANSPARENCY_BEGRUENDUNG), text);
    }

    @Override
    public void setInterveanibilityDescription(String text) {
        EntityType entityType = HUITypeFactory.getInstance().getEntityType(cnaTreeElement.getEntity().getEntityType());
        cnaTreeElement.getEntity().setSimpleValue(entityType.getPropertyType(cnaTreeElement.getTypeId() + Schutzbedarf.INTERVENEABILITY_BEGRUENDUNG), text);
    }

    @Override
    public void setUnlinkabilityDescription(String text) {
        EntityType entityType = HUITypeFactory.getInstance().getEntityType(cnaTreeElement.getEntity().getEntityType());
        cnaTreeElement.getEntity().setSimpleValue(entityType.getPropertyType(cnaTreeElement.getTypeId() + Schutzbedarf.UNLINKABILITY_BEGRUENDUNG), text);
    }
    
    @Override
    public void setDataMinimizationDescription(String text) {
        EntityType entityType = HUITypeFactory.getInstance().getEntityType(cnaTreeElement.getEntity().getEntityType());
        cnaTreeElement.getEntity().setSimpleValue(entityType.getPropertyType(cnaTreeElement.getTypeId() + Schutzbedarf.DATA_MINIMIZATION_BEGRUENDUNG), text);
    }

    private void fireVerfuegbarkeitChanged(CascadingTransaction ta) {

        try {
            // 1st step: traverse down:
            // find bottom nodes from which to start:
            CascadingTransaction downwardsTA = new CascadingTransaction();
            Set<CnATreeElement> bottomNodes = new HashSet<>();
            findBottomNodes(cnaTreeElement, bottomNodes, downwardsTA);

            // 2nd step: traverse up:
            for (CnATreeElement bottomNode : bottomNodes) {
                // determine protection level from parents (or keep own
                // depending on description):
                bottomNode.getLinkChangeListener().determineAvailability(ta);

            }

        } catch (TransactionAbortedException tae) {
            getLog().debug("Reeavluation of availability aborted."); //$NON-NLS-1$
            throw new RuntimeException(tae);
        } catch (RuntimeException e) {
            ta.abort();
            throw e;
        } catch (Exception e) {
            ta.abort();
            throw new RuntimeException(e);
        }
    }

    private void fireVertraulichkeitChanged(CascadingTransaction ta) {

        try {
            // 1st step: traverse down:
            // find bottom nodes from which to start:
            CascadingTransaction downwardsTA = new CascadingTransaction();
            Set<CnATreeElement> bottomNodes = new HashSet<>();
            findBottomNodes(cnaTreeElement, bottomNodes, downwardsTA);

            // 2nd step: traverse up:
            for (CnATreeElement bottomNode : bottomNodes) {
                // determine protection level from parents (or keep own
                // depending on description):
                bottomNode.getLinkChangeListener().determineConfidentiality(ta);

            }

        } catch (TransactionAbortedException tae) {
            getLog().debug("Reeavluation of confidentiality aborted."); //$NON-NLS-1$
            throw new RuntimeException(tae);
        } catch (RuntimeException e) {
            ta.abort();
            throw e;
        } catch (Exception e) {
            ta.abort();
            throw new RuntimeException(e);
        }
    }

    private void fireIntegritaetChanged(CascadingTransaction ta) {

        try {
            // 1st step: traverse down:
            // find bottom nodes from which to start:
            CascadingTransaction downwardsTA = new CascadingTransaction();
            Set<CnATreeElement> bottomNodes = new HashSet<>();
            findBottomNodes(cnaTreeElement, bottomNodes, downwardsTA);

            // 2nd step: traverse up:
            for (CnATreeElement bottomNode : bottomNodes) {
                // determine protection level from parents (or keep own
                // depending on description):
                bottomNode.getLinkChangeListener().determineIntegrity(ta);

            }

        } catch (TransactionAbortedException tae) {
            getLog().debug("Reeavluation of integrity aborted."); //$NON-NLS-1$
            throw new RuntimeException(tae);
        } catch (RuntimeException e) {
            ta.abort();
            throw e;
        } catch (Exception e) {
            ta.abort();
            throw new RuntimeException(e);
        }
    }    

    /**
     * @param downwardElement
     * @param downwardsTA
     * @param bottomNodes
     * @return
     */
    private void findBottomNodes(CnATreeElement downwardElement, Set<CnATreeElement> bottomNodes, CascadingTransaction downwardsTA) {
        if (downwardsTA.hasBeenVisited(downwardElement)){
            return;
        }

        try {
            downwardsTA.enter(downwardElement);
        } catch (TransactionAbortedException e) {
            Logger.getLogger(this.getClass()).error(Messages.SchutzbedarfAdapter_3 + downwardElement.getTitle(), e);
            return;
        }

        int countLinks = 0;
        for (CnALink link : downwardElement.getLinksDown()) {
            if (link.getDependency().isProtectionRequirementsProvider()) {
                countLinks++;
                findBottomNodes(link.getDependency(), bottomNodes, downwardsTA);
            }
        }

        // could not go further down, so add this node:
        if (countLinks == 0){
            bottomNodes.add(downwardElement);
        }
    }

    public CnATreeElement getParent() {
        return cnaTreeElement;
    }

    public void setParent(CnATreeElement parent) {
        this.cnaTreeElement = parent;
    }

    @Override
    public void updateIntegrity(CascadingTransaction ta) {
        fireIntegritaetChanged(ta);
    }

    @Override
    public void updateAvailability(CascadingTransaction ta) {
        fireVerfuegbarkeitChanged(ta);
    }

    @Override
    public void updateConfidentiality(CascadingTransaction ta) {
        fireVertraulichkeitChanged(ta);
    }
    
    @Override
    public void updateTransparency(CascadingTransaction ta) {
        //TODO fireIntegritaetChanged(ta);
    }

    @Override
    public void updateInterveanibility(CascadingTransaction ta) {
    	//TODO fireIntegritaetChanged(ta);
    }

    @Override
    public void updateUnlinkability(CascadingTransaction ta) {
    	//TODO fireIntegritaetChanged(ta);
    }
    
    @Override
    public void updateDataMinimization(CascadingTransaction ta) {
    	//TODO fireIntegritaetChanged(ta);
    }

    /*
     * (non-Javadoc)
     *
     * @seesernet.gs.ui.rcp.main.bsi.model.ISchutzbedarfProvider#
     * isCalculatedAvailability()
     */
    @Override
    public boolean isCalculatedAvailability() {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @seesernet.gs.ui.rcp.main.bsi.model.ISchutzbedarfProvider#
     * isCalculatedConfidentiality()
     */
    @Override
    public boolean isCalculatedConfidentiality() {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * sernet.gs.ui.rcp.main.bsi.model.ISchutzbedarfProvider#isCalculatedIntegrity
     * ()
     */
    @Override
    public boolean isCalculatedIntegrity() {
        return false;
    }
    
    /*
     * (non-Javadoc)
     *
     * @see
     * sernet.gs.ui.rcp.main.bsi.model.ISchutzbedarfProvider#isCalculatedIntegrity
     * ()
     */
    @Override
    public boolean isCalculatedTransparency() {
        return false;
    }
    
    /*
     * (non-Javadoc)
     *
     * @see
     * sernet.gs.ui.rcp.main.bsi.model.ISchutzbedarfProvider#isCalculatedIntegrity
     * ()
     */
    @Override
    public boolean isCalculatedInterveanibility() {
        return false;
    }
    
    /*
     * (non-Javadoc)
     *
     * @see
     * sernet.gs.ui.rcp.main.bsi.model.ISchutzbedarfProvider#isCalculatedIntegrity
     * ()
     */
    @Override
    public boolean isCalculatedUnlinkability() {
        return false;
    }
    
    /*
     * (non-Javadoc)
     *
     * @see
     * sernet.gs.ui.rcp.main.bsi.model.ISchutzbedarfProvider#isCalculatedIntegrity
     * ()
     */
    @Override
    public boolean isCalculatedDataMinimization() {
        return false;
    }

    @Override
    public void updateValue(CascadingTransaction ta) {
        // do nothing
    }

    @Override
    public void setValue(CascadingTransaction ta, String properyName, Object value) {
        // do nothing
    }
}
