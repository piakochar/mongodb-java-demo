package demo;


import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Sorts.orderBy;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;

/**
 * This class interacts with the userViews collection in the siteData db.
 *
 * Note: DAO stands for Data Access Object. The purpose of this type of class is to provide separation between database
 * interactions and the business logic of an application, and provide a reliable interface for interacting with the
 * database.
 */
public class ReviewsDao {
    private static final String DATABASE_NAME = "siteData";
    private static final String COLLECTION_NAME = "reviews";

    public static final String USERNAME_FIELD = "username";
    public static final String CONTENT_FIELD = "content";
    public static final String DATE_FIELD = "date";

    private static final MongoDatabase _db = ApplicationContext.getMongoClient().getDatabase(DATABASE_NAME);
    private static final MongoCollection<Document> _collection = _db.getCollection(COLLECTION_NAME);

    public static void saveReview(final Review review) {
        final Document doc = new Document(USERNAME_FIELD, review.getUsername())
                .append(CONTENT_FIELD, review.getContent())
                .append(DATE_FIELD, review.getDate());
        _collection.insertOne(doc);
    }
    /**
     * Get a list of the most recent reviews
     * @param numReviews the number of reviews to return
     * @return
     */
    public static List<Review> findReviews(final int numReviews) {
        final List<Review> reviews = new LinkedList<>();
        final Iterable<Review> findResults =
                _collection.find().sort(orderBy(descending(DATE_FIELD))).limit(numReviews).map(Review::new);
        findResults.forEach(reviews::add);
        return reviews;
    }
}
