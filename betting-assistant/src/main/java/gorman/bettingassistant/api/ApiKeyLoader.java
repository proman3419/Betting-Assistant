package gorman.bettingassistant.api;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class ApiKeyLoader {
    public static String loadFromFile(String path) {
        URL resource = ApiKeyLoader.class.getClassLoader().getResource(path);
        if (resource == null) {
            throw new IllegalArgumentException("File not found");
        } else {
            try {
                Path resourcePath = new File(resource.toURI()).toPath();
                String resourceContent = Files.readString(resourcePath);
                System.out.printf("Loaded API key: %s\n", resourceContent);
                return resourceContent;
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
