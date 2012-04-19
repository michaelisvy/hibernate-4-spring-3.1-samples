package hibernate.config.java;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {
	
	
	@Bean
	public SessionFactory sessionFactory() {
	 return new LocalSessionFactoryBuilder(datasource())
	 .addAnnotatedClasses(Account.class)
	 .buildSessionFactory();
	}
	
	@Bean
    public PlatformTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory());
    }
	
	@Bean
	public DataSource datasource() {
		EmbeddedDatabaseFactoryBean bean = new EmbeddedDatabaseFactoryBean();
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(new ClassPathResource("hibernate/config/java/schema.sql"));
		bean.setDatabasePopulator(databasePopulator);
		bean.afterPropertiesSet(); // necessary because EmbeddedDatabaseFactoryBean is a FactoryBean
		return bean.getObject();
	}


}
