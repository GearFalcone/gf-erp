package org.gearfalcone.erp.boot;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.gearfalcone.erp.db.dao.factory.AbstractDAOFactory;
import org.gearfalcone.erp.db.dao.factory.impl.MongoDAOFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoDataAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.text.SimpleDateFormat;

/**
 * Created by andy on 13.04.15.
 */

@Configuration
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan(basePackages = "org.gearfalcone.erp.rest, org.gearfalcone.erp.db.validation.validators")
public class BootRunner {

    @Value("${gf.erp.db.vendorname}")
    private String dbVendorName;
    @Value("${gf.erp.db.host}")
    private String dbHost;
    @Value("${gf.erp.db.port}")
    private int dbPort;
    @Value("${gf.erp.db.database}")
    private String dbName;
    @Value("${gf.erp.db.username}")
    private String dbUsername;
    @Value("${gf.erp.db.password}")
    private String dbPassword;

    private static final String MONGO_DB_VENDOR_NAME="mongodb";


    public static void main(String[] args){
        SpringApplication.run(BootRunner.class, args);
    }

    @Bean
    public AbstractDAOFactory daoFactory(){
        AbstractDAOFactory df=null;
        switch(dbVendorName){
            case MONGO_DB_VENDOR_NAME: MongoTemplate mt=instantiateMongo();
                                       df=new MongoDAOFactory(mt);
                                       break;
            default:
                throw new UnsupportedOperationException("DB vendor: "+dbVendorName+"has not supported");
        }

        return df;
    }

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return builder;
    }

    @Bean
    public LocalValidatorFactoryBean validator(){
        return new LocalValidatorFactoryBean();
    }

    private MongoTemplate instantiateMongo(){
        MongoTemplate templ;
        try{
            Mongo mon=new MongoClient(dbHost, dbPort);
            templ=new MongoTemplate(mon, dbName, new UserCredentials(dbUsername, dbPassword));
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return templ;
    }
}
