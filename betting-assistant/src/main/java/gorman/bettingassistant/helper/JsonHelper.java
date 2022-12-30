package gorman.bettingassistant.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonHelper {
    public static JsonNode loadFromFile(String path) {
        URL resource = JsonHelper.class.getClassLoader().getResource(path);
        if (resource == null) {
            throw new IllegalArgumentException("File not found");
        } else {
            try {
                Path resourcePath = new File(resource.toURI()).toPath();
                String resourceContent = Files.readString(resourcePath);
                return JsonHelper.loadTree(resourceContent);
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static JsonNode loadTree(String jsonStr) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = mapper.readTree(jsonStr);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return rootNode;
    }
}
