package org.gearfalcone.erp.rest.personnel;

import org.gearfalcone.erp.db.dao.AbstractEmployeeDAO;
import org.gearfalcone.erp.db.dao.factory.AbstractDAOFactory;
import org.gearfalcone.erp.db.entities.Employee;
import org.gearfalcone.erp.rest.responses.GenericResponse;
import org.gearfalcone.erp.rest.responses.helpers.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * Created by andy on 14.04.15.
 */

@RestController
@RequestMapping("/personnel")
public class PersonnelService {

    @Autowired
    @Qualifier("daoFactory")
    private AbstractDAOFactory daoFactory;

    @RequestMapping(value="/create", method= RequestMethod.POST, produces = {"application/json; charset=UTF-8"}, consumes = {"application/json; charset=UTF-8"})
    @ResponseBody
    public GenericResponse createEmployee(@RequestBody Employee emp){
        GenericResponse resp=null;

        try{
            AbstractEmployeeDAO empDAO=daoFactory.getEmployeeDAO();
            empDAO.save(emp);
        }catch(Exception ex){
            return ResponseHelper.composeFailedResponse(ex);
        }

        resp=ResponseHelper.composeSuccessResponse();

        return resp;
    }


}
