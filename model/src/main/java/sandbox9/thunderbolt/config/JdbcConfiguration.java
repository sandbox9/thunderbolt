package sandbox9.thunderbolt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

/**
 * Created by chanwook on 2014. 12. 19..
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "sandbox9.thunderbolt",
        includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*JpaRepository")
)
public class JdbcConfiguration {
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(H2).build();
        return db;
    }
}
