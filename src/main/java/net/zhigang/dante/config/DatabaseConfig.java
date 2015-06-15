package net.zhigang.dante.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

//import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "net.zhigang.dante.repositories")
@EnableTransactionManagement
public class DatabaseConfig {

	@Bean
	public DataSource dataSource() {
		// final HikariDataSource ds = new HikariDataSource();
		// ds.setMaximumPoolSize(50);
		// ds.setIdleTimeout(20000);
		// ds.setMaxLifetime(25000);
		// ds.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
		// //BAE
		// //ds.addDataSourceProperty("url",
		// "jdbc:mysql://sqld.duapp.com:4050/ukfyCIihIvaeZgflfjNf");
		// //ds.addDataSourceProperty("user", "z3OU7Ba5tNrigFjwoYbIRod0");
		// //ds.addDataSourceProperty("password",
		// "msPhzjLcy7WQ6VZfYqLZhEjNqo0aIgDV");
		// //ACE
		// //ds.addDataSourceProperty("url",
		// "jdbc:mysql://rdsfbyyurnmyqzq.mysql.rds.aliyuncs.com:3306/rs6834r4d4gni170");
		// //ds.addDataSourceProperty("user", "rs6834r4d4gni170");
		// //ds.addDataSourceProperty("password", "mozisuper1414");
		// //SOHU
		// //ds.addDataSourceProperty("url",
		// "jdbc:mysql://10.121.42.208:3306/msports");
		// //ds.addDataSourceProperty("user", "msports");
		// //ds.addDataSourceProperty("password", "6b14adda32a2a31");
		// //Local
		// ds.addDataSourceProperty("url",
		// "jdbc:mysql://127.0.0.1:3306/rs6834r4d4gni170");
		// ds.addDataSourceProperty("user", "rs6834r4d4gni170");
		// ds.addDataSourceProperty("password", "mozisuper1414");

		// ds.addDataSourceProperty("cachePrepStmts", true);
		// ds.addDataSourceProperty("prepStmtCacheSize", 250);
		// ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
		// ds.addDataSourceProperty("useServerPrepStmts", true);
		// return ds;


//		final DruidDataSource ds = new DruidDataSource();
//		ds.setUrl("jdbc:mysql://localhost:3306/rs6834r4d4gni170");
//		ds.setUsername("root");
//		ds.setPassword("1234");


	    
	    final DruidDataSource ds = new DruidDataSource();
	    ds.setUrl("jdbc:mysql://192.168.1.8:3306/dante?characterEncoding=UTF-8");
	    ds.setUsername("dante");
	    ds.setPassword("dantemysql");


		ds.setInitialSize(20);
		ds.setMinIdle(20);
		ds.setMaxActive(100);
		ds.setMaxWait(60000);
		ds.setTimeBetweenEvictionRunsMillis(60000);
		ds.setMinEvictableIdleTimeMillis(300000);
		ds.setValidationQuery("SELECT 1");
		ds.setTestWhileIdle(true);
		ds.setTestOnBorrow(false);
		ds.setTestOnReturn(false);
		ds.setPoolPreparedStatements(false);
		ds.setMaxPoolPreparedStatementPerConnectionSize(20);
		// try {
		// ds.setFilters("stat,slf4j");
		// } catch(Exception e){
		// e.printStackTrace();
		// }

		return ds;
	}

	@Bean
	@Autowired
	public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(false);
		vendorAdapter
				.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
		vendorAdapter.setDatabase(Database.MYSQL);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("net.zhigang.dante.domain");
		factory.setDataSource(dataSource);

		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect",
				"org.hibernate.dialect.MySQL5InnoDBDialect");
		// properties.setProperty("hibernate.cache.use_second_level_cache",
		// "true");
		// properties.setProperty("hibernate.cache.region.factory_class",
		// "org.hibernate.cache.ehcache.EhCacheRegionFactory");
		// properties.setProperty("hibernate.cache.use_query_cache", "true");
		// properties.setProperty("hibernate.generate_statistics", "true");

		factory.setJpaProperties(properties);
		factory.afterPropertiesSet();

		return factory.getObject();
	}

	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(
			EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
	}
}
