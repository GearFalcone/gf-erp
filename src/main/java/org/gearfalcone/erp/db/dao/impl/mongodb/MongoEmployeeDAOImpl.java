package org.gearfalcone.erp.db.dao.impl.mongodb;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.gearfalcone.erp.db.dao.AbstractEmployeeDAO;
import org.gearfalcone.erp.db.entities.Employee;
import org.gearfalcone.erp.db.mongo.MongoDelegate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.validation.Validator;

/**
 * Created by andy on 13.04.15.
 */
public class MongoEmployeeDAOImpl extends AbstractEmployeeDAO {

    private MongoTemplate mongoTemplate;
    private MongoDelegate<Employee> mDelegate;
    private Validator validator;

    public MongoEmployeeDAOImpl(MongoTemplate mt, Validator validator){
        super();

        mongoTemplate=mt;
        mDelegate=new MongoDelegate<>(mt, validator);
        this.validator=validator;
    }

    @Override
    public void save(Employee emp) {
        mDelegate.save(emp);
    }

    @Override
    public Employee findByLogin(String login) {
        StringBuilder b=new StringBuilder("{login: \"");
        b.append(login)
                .append("\"}");

        Employee emp=mDelegate.findOne(b.toString(), Employee.class);

        return emp;
    }
}
