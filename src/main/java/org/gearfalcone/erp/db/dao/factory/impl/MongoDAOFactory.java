package org.gearfalcone.erp.db.dao.factory.impl;

import org.gearfalcone.erp.db.dao.AbstractEmployeeDAO;
import org.gearfalcone.erp.db.dao.factory.AbstractDAOFactory;
import org.gearfalcone.erp.db.dao.impl.mongodb.MongoEmployeeDAOImpl;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoDAOFactory extends AbstractDAOFactory {

    private MongoEmployeeDAOImpl employeeDAO;
    private MongoTemplate mongoTemplate;

    public MongoDAOFactory(MongoTemplate mt){
        mongoTemplate=mt;
    }

    @Override
    public AbstractEmployeeDAO getEmployeeDAO() {
        if(employeeDAO==null)
            employeeDAO=new MongoEmployeeDAOImpl(mongoTemplate, validator);

        return employeeDAO;
    }
}
