package org.gearfalcone.erp.db.validation.validators;

import org.apache.log4j.Logger;
import org.gearfalcone.erp.db.dao.AbstractEmployeeDAO;
import org.gearfalcone.erp.db.dao.factory.AbstractDAOFactory;
import org.gearfalcone.erp.db.entities.Employee;
import org.gearfalcone.erp.db.validation.annotations.UniqueLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by andy on 16.04.15.
 */

@Component
public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

    private static final Logger LOG=Logger.getLogger(UniqueLoginValidator.class);

    @Autowired
    private AbstractDAOFactory daoFactory;

    @Override
    public void initialize(UniqueLogin uniqueLogin) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Employee emp=null;

        try{
            AbstractEmployeeDAO dao=daoFactory.getEmployeeDAO();
            emp=dao.findByLogin(s);
        }catch(Exception ex){
            LOG.error(ex.getMessage(), ex);
            return false;
        }

        if(emp==null)
            return true;

        return false;
    }

}
