package org.gearfalcone.erp.db.mongo.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.bson.types.ObjectId;

import java.io.IOException;

/**
 * Created by andy on 16.04.15.
 */
public class MongoObjectIdSerializer extends JsonSerializer<ObjectId> {


    @Override
    public void serialize(ObjectId objectId, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if(objectId == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(objectId.toString());
        }
    }
}
