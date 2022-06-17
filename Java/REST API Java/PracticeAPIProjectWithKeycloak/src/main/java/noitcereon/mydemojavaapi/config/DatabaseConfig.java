package noitcereon.mydemojavaapi.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DatabaseConfig {
    private final Logger _logger = LoggerFactory.getLogger(getClass());
    @Value("${spring.datasource.url}")
    private String DATABASE_URI_FROM_ENVIRONMENT;
    /**
     * Configuration for deploying to Heroku, so the connection string is in the correct format.
     * @return A BasicDataSource with jdbc url to postgres database based on DATABASE_URL env variable.
     * @throws URISyntaxException Happens when URI syntax is wrong, I suppose
     */
    @Bean
    @Profile("production")
    public BasicDataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(DATABASE_URI_FROM_ENVIRONMENT);
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        _logger.debug("dataSource after modification: " + dbUri);
        return basicDataSource;
    }
}
