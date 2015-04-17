package org.gearfalcone.erp.db.mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.gearfalcone.erp.db.entities.Employee;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import javax.validation.ValidationException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by andy on 14.04.15.
 */
public class MongoDelegate <T> {

    private MongoTemplate mongoTemplate;
    private Validator validator;

    public MongoDelegate(MongoTemplate mt, Validator validator){
        if(mt==null)
            throw new IllegalArgumentException("MongoTemplate cannot be null");
        mongoTemplate=mt;

        this.validator=validator;
    }

    public void save(T entity){

        validate(entity);

        String colName=extractCollectionName(entity);
        String json=toJson(entity);

        DBObject dbo=(DBObject)JSON.parse(json);

        mongoTemplate.save(dbo, colName);
    }

    public DBObject toDBObject(String json){
        return (DBObject)JSON.parse(json);
    }

    public T toEntity(DBObject obj, Class entityClass){
        String json=JSON.serialize(obj);
        ObjectMapper om=new ObjectMapper();
        T val=null;

        try {
            val=(T)om.readValue(json, entityClass);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return val;
    }

    public String toJson(T entity){
        String value=null;

        try{
            ObjectMapper mapper=new ObjectMapper();
            value=mapper.writeValueAsString(entity);
        }catch (Exception ex){
            throw  new RuntimeException(ex);
        }

        return value;
    }

    public String extractCollectionName(T entity){

       if(entity==null)
           throw new IllegalArgumentException("Entity cannot be null");

       String colName=extractCollectionName(entity.getClass());

       return colName;
    }

    public String extractCollectionName(Class entityClass){
        String colName=mongoTemplate.getCollectionName(entityClass);

        return colName;
    }

    protected void validate(T entity){
        DataBinder binder=new DataBinder(entity);
        binder.setValidator(validator);

        binder.validate();

        BindingResult result=binder.getBindingResult();

        if(result.hasFieldErrors()){
            List<FieldError> fe=result.getFieldErrors();
            StringBuilder sb=new StringBuilder("The following problem has been found in entity ");
            sb.append(entity.getClass().getSimpleName())
                    .append(":\n");

            for(FieldError err: fe){
                sb.append("Field ")
                  .append(err.getField())
                  .append(" ")
                  .append(err.getDefaultMessage());
            }

            throw new ValidationException(sb.toString());
        }
    }


        public T findOne(String jsonCriteria, Class entityClass){
            T entity=null;

            String colName=mongoTemplate.getCollectionName(entityClass);

            DBCollection col=mongoTemplate.getCollection(colName);

            DBCursor cur=col.find(toDBObject(jsonCriteria));

            if(cur.hasNext()){
                DBObject obj=cur.next();
                entity=toEntity(obj, entityClass);
            }

            return entity;
        }

}
