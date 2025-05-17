package MiniProject.Utilities;

import java.util.Properties;

public class ConfigurationReader {
    private static final Properties properties = new Properties();
    static {
        try {
            properties.load(ConfigurationReader.class.getClassLoader()
                    .getResourceAsStream("configuration.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration.properties file", e);
        }

    }
    public static String getProperty(String keyword){
        return properties.getProperty(keyword);
    }
}
