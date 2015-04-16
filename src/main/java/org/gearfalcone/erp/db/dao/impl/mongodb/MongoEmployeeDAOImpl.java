package org.gearfalcone.erp.db.dao.impl.mongodb;

import org.gearfalcone.erp.db.dao.AbstractEmployeeDAO;
import org.gearfalcone.erp.db.entities.Employee;
import org.gearfalcone.erp.db.mongo.MongoDelegate;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by andy on 13.04.15.
 */
public class MongoEmployeeDAOImpl extends AbstractEmployeeDAO {

    private MongoTemplate mongoTemplate;
    private MongoDelegate<Employee> mDelegate;

    public MongoEmployeeDAOImpl(MongoTemplate mt){
        mongoTemplate=mt;
        mDelegate=new MongoDelegate<Employee>(mt);
    }

    @Override
    public void save(Employee emp) {
        mDelegate.save(emp);
    }

    @Override
    public Employee findByLogin(String login) {
        return null;
    }
}
