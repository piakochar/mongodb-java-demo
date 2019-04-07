package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static String MONGODB_CONNECTION_STRING = "mongodb+srv://user:pass@bechdelcluster-1y9u3.mongodb-dev.net/test";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
