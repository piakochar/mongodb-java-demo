package demo;

import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Sorts.orderBy;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Date;
import java.util.Optional;
import org.bson.Document;

/**
 * This class interacts with the userViews collection in the siteData db.
 *
 * Note: DAO stands for Data Access Object. The purpose of this type of class is to provide separation between database
 * interactions and the business logic of an application, and provide a reliable interface for interacting with the
 * database.
 */
public class UserViewsDao {

    /**
     * Note: It's good practice to define things like db/collection names and field names
     * in one place like this. It helps us guard against typos, makes it easier to see what
     * fields we've defined, and makes naming updates easier since they only have to happen in one place.
     */

    private static final String DATABASE_NAME = "siteData";
    private static final String COLLECTION_NAME = "userViews";

    public static final String USER_FIELD = "user";
    public static final String DATE_FIELD = "date";
    public static final String NAMESPACE_FIELD = "namespace";

    private static final MongoDatabase _db = ApplicationContext.getMongoClient().getDatabase(DATABASE_NAME);
    private static final MongoCollection<Document> _collection = _db.getCollection(COLLECTION_NAME);

    public static void saveUserView(final String userName, final Date date, final String dbViewed, final String collViewed) {
        final Document doc = new Document(USER_FIELD, userName)
                .append(DATE_FIELD, date)
                .append(NAMESPACE_FIELD, String.format("%s.%s", DATABASE_NAME, COLLECTION_NAME));
        _collection.insertOne(doc);
    }

    public static Optional<Document> getMostRecentViewer() {
        final Document firstDoc = _collection.find().sort(orderBy(descending(DATE_FIELD))).first();
        return Optional.ofNullable(firstDoc);
    }
}
