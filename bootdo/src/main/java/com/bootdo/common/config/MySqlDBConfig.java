package com.bootdo.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
@SuppressWarnings("AlibabaRemoveCommentedCode")
@Configuration
@MapperScan(basePackages = {"com.bootdo.activiti.dao","com.bootdo.blog.dao","com.bootdo.common.dao","com.bootdo.constant.dao","com.bootdo.oa.dao","com.bootdo.system.dao"}, sqlSessionFactoryRef = "adsSqlSessionFactory")
public class MySqlDBConfig {
    static final String PACKAGE = "com.bootdo.*.dao";  //扫描的mapper包结构
    private Logger logger = LoggerFactory.getLogger(MySqlDBConfig.class);
    @Value("${spring.datasource.first.url}")
    private String dbUrl;

    @Value("${spring.datasource.first.username}")
    private String username;

    @Value("${spring.datasource.first.password}")
    private String password;

    @Value("${spring.datasource.first.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("{spring.datasource.connectionProperties}")
    private String connectionProperties;

    //@Bean(name = "adsDataSource")
    @Bean(name = "adsDataSource",initMethod = "init", destroyMethod = "close")
    @Primary   //此注解表示在默认的数据源配置，即在默认配置时用到的数据源配置
    @ConfigurationProperties("spring.datasource.first")
    public DataSource adsDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(connectionProperties);

        return datasource;
    }

    @Bean(name = "adsTransactionManager")
    @Primary
    public DataSourceTransactionManager adsTransactionManager(@Qualifier("adsDataSource") DataSource adsDataSource) {
        return new DataSourceTransactionManager(adsDataSource);
    }

    @Bean(name = "adsSqlSessionFactory")
    @Primary
    public SqlSessionFactory adsSqlSessionFactory(@Qualifier("adsDataSource") DataSource adsDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(adsDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver ().getResources("classpath:/mybatis/*/*.xml"));
        //sessionFactory.setTypeAliasesPackage("com.bootdo.*.domain.*");
        return sessionFactory.getObject();
    }
    @Bean(name = "adsSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("adsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate (sqlSessionFactory);
    }



}