package com.jnscas.pinhead.factories;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDatabaseSingleton {

    private static MongoDatabase database;

    private static void newInstance() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        Config config = ConfigFactory.defaultApplication();
        MongoClient mongoClient = MongoClients.create(config.getString("db.mongo.host"));
        database = mongoClient.getDatabase(config.getString("db.mongo.name")).withCodecRegistry(pojoCodecRegistry);
    }

    public static MongoDatabase getInstance() {
        if (database == null) {
            newInstance();
        }
        return database;
    }

}
