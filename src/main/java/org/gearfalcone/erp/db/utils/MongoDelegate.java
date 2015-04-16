package org.gearfalcone.erp.db.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.gearfalcone.erp.db.annotations.MongoCollectionName;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.lang.annotation.Annotation;

/**
 * Created by andy on 14.04.15.
 */
public class MongoDelegate <T> {

    private MongoTemplate mongoTemplate;

    public MongoDelegate(MongoTemplate mt){
        if(mt==null)
            throw new IllegalArgumentException("MongoTemplate cannot be null");
        mongoTemplate=mt;
    }

    public void save(T entity){
        String colName=extractCollectionName(entity);
        String json=toJson(entity);

        DBObject dbo=(DBObject)JSON.parse(json);

        mongoTemplate.save(dbo, colName);
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

        String colName;

        if(entity.getClass().isAnnotationPresent(MongoCollectionName.class)){
            Annotation m=entity.getClass().getAnnotation(MongoCollectionName.class);
            MongoCollectionName mcn=(MongoCollectionName)m;

            colName=mcn.name();
            if(colName==null || colName.isEmpty())
                throw new IllegalArgumentException("Mongo DB collection name has not been specified");
        }else
            throw new IllegalArgumentException("Cannot determine Mongo DB collection name. Invalid entity object");

       return colName;
    }

}
