package org.gearfalcone.erp.db.dao.factory;

import org.gearfalcone.erp.db.dao.AbstractEmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

/**
 * Created by andy on 14.04.15.
 */

@Component
public abstract class AbstractDAOFactory {

    @Autowired
    protected Validator validator;

    public abstract AbstractEmployeeDAO getEmployeeDAO();
}
