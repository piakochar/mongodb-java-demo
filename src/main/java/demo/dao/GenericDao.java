package demo.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import demo.ApplicationContext;
import org.bson.Document;

/**
 * This class can be used to interact with any db and collection passed in.
 *
 * Note: DAO stands for Data Access Object. The purpose of this type of class is to provide separation between database
 * interactions and the business logic of an application, and provide a reliable interface for interacting with the
 * database.
 */
public class GenericDao {
    private final MongoDatabase _db;
    private final MongoCollection<Document> _collection;

    public GenericDao(final String dbName, final String collName) {
        _db = ApplicationContext.getMongoClient().getDatabase(dbName);
        _collection = _db.getCollection(collName);
    }

    public long countDocuments() {
        return _collection.countDocuments();
    }
}
