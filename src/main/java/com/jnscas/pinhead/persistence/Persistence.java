package com.jnscas.pinhead.persistence;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public abstract class Persistence<T> {
    private final Class<T> typeClass;

    private String tableName;
    private MongoDatabase database;


    public Persistence(MongoDatabase database,
                       String tableName,
                       Class<T> typeClass) {
        this.database = database;
        this.tableName = tableName;
        this.typeClass = typeClass;
    }

    public void store(T obj) {
        retrieveCollection().insertOne(obj);
    }

    protected MongoCollection<T> retrieveCollection() {
        return database.getCollection(tableName, typeClass);
    }

    protected Optional<T> findByColumn(String columnName, String filterValue) {
        T obj = retrieveCollection()
                .find(eq(columnName, filterValue))
                .first();
        if (obj != null) {
            return Optional.of(obj);
        } else {
            return Optional.empty();
        }
    }

    protected void updateColumn(String userName, String nameColumn, String value) {
        retrieveCollection().updateOne(eq("userName", userName), set(nameColumn, value));
    }
}
