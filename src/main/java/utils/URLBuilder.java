package utils;

public class URLBuilder {
    public static String getProdBaseUrl() {
        String env = ConfigReader.get(URLConstants.Env, "prod");
        if ("prod".equalsIgnoreCase(env)) {
            return ConfigReader.get(URLConstants.PROD_BASE_URL, "https://m.twitch.tv/?desktop-redirect=true");
        }

        throw new RuntimeException("Invalid environment Value in config.properties");
    }


}
