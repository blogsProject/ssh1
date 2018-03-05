package com.luotianyi.test1.config;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.support.OpenSessionInViewInterceptor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @title: HibernateConfig.java
 * @package com.shuyuntu.report.config
 * @description:
 * @copyright: shuyuntu.com
 * @author 毕泗平
 * @date 2017年3月19日 下午3:19:15
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:hibernateconfig.properties", "classpath:config.properties" })
public class HibernateConfig {

	@Autowired
	private Environment dataConfig;

	/**
	 * 1. 配置数据源
	 * 
	 * @author 毕泗平
	 * @date 2017年3月18日 下午3:06:33
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@Bean(name = { "dataSource" }, initMethod = "init", destroyMethod = "close")
	public DataSource druidDataSource() throws SQLException, IOException {
		DruidDataSource dataSource = new DruidDataSource();
		dataConfig.getProperty("dirver");
		dataSource.setDriverClassName(dataConfig.getProperty("dirver"));
		dataSource.setUrl(dataConfig.getProperty("url"));
		dataSource.setUsername(dataConfig.getProperty("dbusername"));
		dataSource.setPassword(dataConfig.getProperty("dbpassword"));
		dataSource.setInitialSize(Integer.parseInt(dataConfig.getProperty("initialSize")));
		dataSource.setMaxActive(Integer.parseInt(dataConfig.getProperty("maxActive")));
		dataSource.setMaxIdle(Integer.parseInt(dataConfig.getProperty("maxIdle")));
		dataSource.setMinIdle(Integer.parseInt(dataConfig.getProperty("minIdle")));
		dataSource.setMaxWait(Long.parseLong(dataConfig.getProperty("maxWait")));
		dataSource.setRemoveAbandoned(Boolean.parseBoolean(dataConfig.getProperty("removeAbandoned")));
		dataSource.setRemoveAbandonedTimeout(Integer.parseInt(dataConfig.getProperty("removeAbandonedTimeout")));
		dataSource.setTimeBetweenEvictionRunsMillis(
				Long.parseLong(dataConfig.getProperty("timeBetweenEvictionRunsMillis")));
		dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(dataConfig.getProperty("minEvictableIdleTimeMillis")));
		dataSource.setValidationQuery(dataConfig.getProperty("validationQuery"));
		dataSource.setTestWhileIdle(Boolean.parseBoolean(dataConfig.getProperty("testWhileIdle")));
		dataSource.setTestOnBorrow(Boolean.parseBoolean(dataConfig.getProperty("testOnBorrow")));
		dataSource.setTestOnReturn(Boolean.parseBoolean(dataConfig.getProperty("testOnReturn")));
		dataSource.setPoolPreparedStatements(Boolean.parseBoolean(dataConfig.getProperty("poolPreparedStatements")));
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(
				Integer.parseInt(dataConfig.getProperty("maxPoolPreparedStatementPerConnectionSize")));
		dataSource.setFilters(dataConfig.getProperty("filters"));
		return dataSource;
	}

	/**
	 * 2. 配置sessionFactory
	 * 
	 * @author 毕泗平
	 * @date 2017年3月18日 下午3:09:18
	 * @param dataSource
	 * @return
	 */
	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource); // 引入数据源
		sessionFactoryBean.setPackagesToScan(new String[] { "com.luotianyi.test1.pojo" }); // 实体所在包
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", dataConfig.getProperty("dialect")); // 方言
																						// 使用MySQL
		properties.setProperty("hibernate.hbm2ddl.auto", dataConfig.getProperty("hbm2ddl_auto")); // update：加载hibernate自动更新数据库结构
		properties.setProperty("hibernate.show_sql", dataConfig.getProperty("show_sql")); // 控制台是否显示SQL语句
		properties.setProperty("hibernate.transaction.coordinator_class",
				dataConfig.getProperty("transaction_coordinator_class")); // 支持事务类型
		sessionFactoryBean.setHibernateProperties(properties);

		return sessionFactoryBean;
	}

	/**
	 * 3. 事务管理器
	 * 
	 * @author 毕泗平
	 * @date 2017年3月18日 下午3:59:57
	 * @param localSessionFactory
	 * @return
	 */
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}

	/**
	 * 4. OSIV模式（解决hibernate延迟加载问题）
	 * 
	 * @author 毕泗平
	 * @date 2017年3月18日 下午4:15:24
	 * @param sessionFactory
	 * @return
	 */
	@Autowired
	@Bean
	public OpenSessionInViewInterceptor openSessionInViewInterceptor(SessionFactory sessionFactory) {
		OpenSessionInViewInterceptor viewInterceptor = new OpenSessionInViewInterceptor();
		viewInterceptor.setSessionFactory(sessionFactory);
		return viewInterceptor;
	}

}
