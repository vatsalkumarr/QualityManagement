package com.rest.services;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.rest.dao.TestCaseDao;
import com.rest.dao.TestExecutionDao;
import com.rest.dao.TestExecutionDaoImpl;
import com.rest.dao.TestPlanDao;
import com.rest.dao.TestCaseDaoImpl;
import com.rest.dao.TestPlanDaoImpl;
import com.rest.dao.TestScriptDao;
import com.rest.dao.TestScriptDaoImpl;
import com.rest.dao.UserDao;
import com.rest.dao.UserDaoImpl;
 
@Configuration
@ComponentScan(basePackages="com.rest")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{
 
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/qualitymanagement");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        
        return dataSource;
    }
     
    @Bean
    public TestPlanDao getPlanDAO() {
        return new TestPlanDaoImpl(getDataSource());
    }
        
    @Bean
    public TestPlanService getPlanServices() {
        return new TestPlanService();
    }
    
    @Bean
    public TestCaseDao getCaseDAO() {
        return new TestCaseDaoImpl(getDataSource());
    }
        
    @Bean
    public TestCaseService getCaseServices() {
        return new TestCaseService();
    }
    @Bean
    public TestScriptDao getScriptDAO() {
        return new TestScriptDaoImpl(getDataSource());
    }
        
    @Bean
    public TestScriptService getScriptServices() {
        return new TestScriptService();
    }
    @Bean
    public TestExecutionDao getExecutionDAO() {
        return new TestExecutionDaoImpl(getDataSource());
    }
        
    @Bean
    public TestExecutionService getTestExecutionServices() {
        return new TestExecutionService();
    }
    
    @Bean
    public UserDao getUserDAO() {
        return new UserDaoImpl(getDataSource());
    }
        
    @Bean
    public UserService getUserServices() {
        return new UserService();
    }
}