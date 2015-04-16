package org.gearfalcone.erp.db.dao;

import org.gearfalcone.erp.db.entities.Employee;

/**
 * Created by andy on 13.04.15.
 */
public abstract class AbstractEmployeeDAO {

    public abstract void save(Employee emp);
    public abstract Employee findByLogin(String login);
}
