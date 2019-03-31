package demo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

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
