package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.example.demo")
public class MybatisConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setDataSource(dataSource);
//        bean.setConfigLocation(resolver.getResource("classpath:mybatis/mybatis-config.xml"));
        bean.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*.xml"));
        bean.setTypeAliasesPackage("com.example.demo");

        Properties properties = new Properties();
        // 카멜 케이스 VO 매핑
        properties.setProperty("mapUnderscoreToCamelCase", "true");
        // 쿼리 결과 필드가 null인 경우, 누락이 되서 나오지 않게 설정
        properties.setProperty("callSettersOnNulls", "true");
        // 쿼리에 보내는 파라미터가 null인 경우, 오류가 발생하는 것 방지
        properties.setProperty("jdbcTypeForNull", "NULL");
        bean.setConfigurationProperties(properties);

        return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        sqlSessionFactory.getConfiguration().setCallSettersOnNulls(true);
        sqlSessionFactory.getConfiguration().setJdbcTypeForNull(null);
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
