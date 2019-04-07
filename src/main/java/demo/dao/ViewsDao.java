package demo.dao;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import demo.ApplicationContext;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.bson.Document;

/**
 * This class interacts with the views collection in the siteData db.
 *
 * Note: DAO stands for Data Access Object. The purpose of this type of class is to provide separation between database
 * interactions and the business logic of an application, and provide a reliable interface for interacting with the
 * database.
 */
public class ViewsDao {

    /**
     * Note: It's good practice to define things like db/collection names and field names
     * in one place like this. It helps us guard against typos, makes it easier to see what
     * fields we've defined, and makes naming updates easier since they only have to happen in one place.
     */

    private static final String DATABASE_NAME = "siteData";
    private static final String COLLECTION_NAME = "views";

    public static final String DATE_FIELD = "date";
    public static final String NAMESPACE_FIELD = "namespace";

    private static final MongoDatabase _db = ApplicationContext.getMongoClient().getDatabase(DATABASE_NAME);
    private static final MongoCollection<Document> _collection = _db.getCollection(COLLECTION_NAME);

    public static void saveCollectionView(final Date date, final String dbViewed, final String collViewed) {
        final Document doc = new Document(DATE_FIELD, date)
                .append(NAMESPACE_FIELD, String.format("%s.%s", dbViewed, collViewed));
        _collection.insertOne(doc);
    }


    public static Map<String, Integer> getViewsByNamespace() {
        final Map<String, Integer> results = new HashMap<>();

        final Iterator<String> viewedNamespaces = _collection.distinct(NAMESPACE_FIELD, String.class).iterator();
        while (viewedNamespaces.hasNext()) {
            final String ns = viewedNamespaces.next();
            final int num = (int) _collection.countDocuments(eq(NAMESPACE_FIELD, ns));
            results.put(ns, num);
        }

        return results;
    }
}
