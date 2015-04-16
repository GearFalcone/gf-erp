package org.gearfalcone.erp.db.dao.factory;

import org.gearfalcone.erp.db.dao.AbstractEmployeeDAO;

/**
 * Created by andy on 14.04.15.
 */
public abstract class AbstractDAOFactory {

    public abstract AbstractEmployeeDAO getEmployeeDAO();
}
