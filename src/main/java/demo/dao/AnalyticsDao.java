package demo.dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Sorts.orderBy;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import demo.ApplicationContext;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.bson.Document;

/**
 * This class interacts with the analytics collection in the siteData db.
 *
 * Note: DAO stands for Data Access Object. The purpose of this type of class is to provide separation between database
 * interactions and the business logic of an application, and provide a reliable interface for interacting with the
 * database.
 */
public class AnalyticsDao {

    /**
     * Note: It's good practice to define things like db/collection names and field names
     * in one place like this. It helps us guard against typos, makes it easier to see what
     * fields we've defined, and makes naming updates easier since they only have to happen in one place.
     */

    private static final String DATABASE_NAME = "siteData";
    private static final String COLLECTION_NAME = "analytics";

    public static final String DATE_FIELD = "date";
    public static final String REQUEST_FIELD = "request";

    private static final MongoDatabase _db = ApplicationContext.getMongoClient().getDatabase(DATABASE_NAME);
    private static final MongoCollection<Document> _collection = _db.getCollection(COLLECTION_NAME);

    public static void saveHttpRequest(final Date date, final String method, final String endpoint) {
        final Document doc = new Document(DATE_FIELD, date)
                .append(REQUEST_FIELD, String.format("%s %s", method, endpoint));

        _collection.insertOne(doc);
    }

    public static Map<String, Integer> getNumRequestsPerType() {
        final Map<String, Integer> results = new HashMap<>();

        final Iterator<String> requestTypes = _collection.distinct(REQUEST_FIELD, String.class).iterator();
        while (requestTypes.hasNext()) {
            final String ns = requestTypes.next();
            final int num = (int) _collection.countDocuments(eq(REQUEST_FIELD, ns));
            results.put(ns, num);
        }

        return results;
    }
}
