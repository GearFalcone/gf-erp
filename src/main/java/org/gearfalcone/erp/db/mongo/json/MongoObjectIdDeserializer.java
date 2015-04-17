package org.gearfalcone.erp.db.mongo.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by andy on 16.04.15.
 */
public class MongoObjectIdDeserializer extends JsonDeserializer<String> {

    private static final Logger LOG=Logger.getLogger(MongoObjectIdDeserializer.class);

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        String id=node.get("$oid").asText();
        LOG.debug(id);

        return id;
    }
}
