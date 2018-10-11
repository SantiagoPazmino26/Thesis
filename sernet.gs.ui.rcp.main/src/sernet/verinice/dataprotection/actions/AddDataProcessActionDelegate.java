/*******************************************************************************
 * Copyright (c) 2018 Santiago Pazmino
 *
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors:
 *     Santiago Pazmino
 ******************************************************************************/
package sernet.verinice.dataprotection.actions;

import sernet.verinice.model.dataprotection.DataProcess;
import sernet.verinice.model.dataprotection.DataProcessKategorie;

/**
 * @author Santiago Pazmino
 *
 */
public class AddDataProcessActionDelegate extends AbstractAddDataProtectionElementActionDelegate<DataProcess> {

    public AddDataProcessActionDelegate() {
        super(DataProcessKategorie.class, DataProcess.TYPE_ID, Messages.AddRoomDelegate_0);
    }
}
