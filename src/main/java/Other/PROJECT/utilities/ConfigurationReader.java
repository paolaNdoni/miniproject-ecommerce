package Other.PROJECT.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationReader {
    private static final Properties properties = new Properties();
    static {
        try (InputStream input = ConfigurationReader.class.getClassLoader()
                .getResourceAsStream("configuration.properties")) {
            if (input == null) {
                throw new RuntimeException("configuration.properties file not found");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration.properties file", e);
        }
    }
    public static String getProperty(String keyword){
        return properties.getProperty(keyword);
    }
}
