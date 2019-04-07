package demo.model;

import demo.dao.ReviewsDao;
import java.util.Date;
import org.bson.Document;

public class Review {

    private String username;
    private String content;
    private Date date;

    public Review() {}

    public Review(final Document dbDocument) {
        username = dbDocument.getString(ReviewsDao.USERNAME_FIELD);
        content = dbDocument.getString(ReviewsDao.CONTENT_FIELD);
        date = dbDocument.getDate(ReviewsDao.DATE_FIELD);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
