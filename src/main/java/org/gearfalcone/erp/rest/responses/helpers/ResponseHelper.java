package org.gearfalcone.erp.rest.responses.helpers;

import org.gearfalcone.erp.rest.responses.GenericResponse;

/**
 * Created by andy on 15.04.15.
 */
public class ResponseHelper {

    public static GenericResponse composeFailedResponse(Exception ex){
        GenericResponse resp=new GenericResponse();

        resp.setStatus(GenericResponse.FAILED_STATUS);
        resp.setDetails(ex.getMessage());

        return resp;
    }

    public static GenericResponse composeSuccessResponse(){
        GenericResponse resp=new GenericResponse();

        resp.setStatus(GenericResponse.SUCCESS_STATUS);

        return resp;
    }
}
