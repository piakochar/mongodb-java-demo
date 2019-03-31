package demo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContext implements InitializingBean, DisposableBean {
    private static MongoClient _client;

    @Override
    public void afterPropertiesSet() throws Exception {
        _client = MongoClients.create(Application.MONGODB_CONNECTION_STRING);
        System.out.println("Just connected to the db!");
    }

    @Override
    public void destroy() throws Exception {
        _client.close();
        System.out.println("Closed connection to db!");
    }

    public static MongoClient getMongoClient() {
        return _client;
    }
}
