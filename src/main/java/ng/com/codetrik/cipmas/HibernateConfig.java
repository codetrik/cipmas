package ng.com.codetrik.cipmas;
/*
 @Author Hamzat Habibllahi
 */
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("ng.com.codetrik.cipmas")
public class HibernateConfig {
    @Bean
    public DataSource dataSource(){
        HikariConfig hConfig = new HikariConfig();

        hConfig.setDriverClassName("org.postgresql.Driver");
/*      hConfig.setJdbcUrl("jdbc:postgresql://ec2-54-162-119-125.compute-1.amazonaws.com:5432/da77nb60ojktvr?password=c8e37ad3f42931e26c2bc855da26806b30f8c12423f399b9ee9554c7f438f0b5&sslmode=require&user=fknihocnordwve");
        hConfig.setUsername("fknihocnordwve");
        hConfig.setPassword("c8e37ad3f42931e26c2bc855da26806b30f8c12423f399b9ee9554c7f438f0b5");
*/
        hConfig.setSchema("codetrik_server");
        hConfig.setAutoCommit(true);
        hConfig.setConnectionTimeout(30000L);
        hConfig.setIdleTimeout(60000L);
        hConfig.setMaxLifetime(1800000L);
        hConfig.setMinimumIdle(10);
        hConfig.setMaximumPoolSize(10);
        hConfig.setInitializationFailTimeout(1L);

        return new HikariDataSource(hConfig);
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource);

        em.setPackagesToScan("ng.com.codetrik.cipmas");

        em.setPersistenceUnitName("punit");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        em.setJpaVendorAdapter(vendorAdapter);

        em.setJpaProperties(additionalProperties());

        return em;
    }
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {

        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){

        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor(){

        return new PersistenceAnnotationBeanPostProcessor();
    }

    public Properties additionalProperties() {

        Properties properties = new Properties();

        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        properties.setProperty("hibernate.format_sql", "true");

        return properties;
    }
}
