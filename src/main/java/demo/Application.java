package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static String MONGODB_CONNECTION_STRING = "mongodb+srv://user:pass@cluster0-1y9u3.mongodb-dev.net/test?retryWrites=true";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
