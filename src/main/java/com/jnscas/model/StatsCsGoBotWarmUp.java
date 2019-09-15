package com.jnscas.model;

import com.google.common.collect.Lists;
import com.jnscas.StatsCsGoBot;
import com.jnscas.commands.impl.StartCommand;
import com.jnscas.persistence.UserTelegramDAO;
import com.jnscas.statscsgo.persistence.UserDAO;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class StatsCsGoBotWarmUp {

    private static final Config config = ConfigFactory.defaultApplication();

    private static MongoClient mongoClient;

    public static StatsCsGoBot init() {
        mongoClient = MongoClients.create();
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoDatabase database = mongoClient.getDatabase(config.getString("db.mongo.name")).withCodecRegistry(pojoCodecRegistry);
        UserDAO userDAO = new UserDAO(database, "users");
        return new StatsCsGoBot(
                userDAO,
                Lists.newArrayList(new StartCommand(userDAO))
        );
    }

    public static void close() {
        mongoClient.close();
    }
}
