package org.zero.mybatis.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.io.IOException;

@Configuration
@AutoConfigureAfter({DataBaseConfiguration.class})
@Slf4j
public class MybatisConfiguration {

    @Bean(name = "sqlSessionFactory")
    @Autowired
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dynamicDataSource) throws IOException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        // 设置查找器
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath*:org/zero/mybatis/dao/*.xml"));
        bean.setTypeAliasesPackage("org.zero.mybatis.po");
        try {

            SqlSessionFactory session = bean.getObject();
            MapperHelper mapperHelper = new MapperHelper();
            //特殊配置
            Config config = new Config();
            //具体支持的参数看后面的文档
            config.setNotEmpty(true);
            //设置配置
            mapperHelper.setConfig(config);
            // 注册自己项目中使用的通用Mapper接口，这里没有默认值，必须手动注册
            mapperHelper.registerMapper(Mapper.class);
            //配置完成后，执行下面的操作
            mapperHelper.processConfiguration(session.getConfiguration());
            return session;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean(name = "sqlSessionTemplate")
    @Autowired
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public MapperScannerConfigurer scannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        configurer.setSqlSessionTemplateBeanName("sqlSessionTemplate");
        configurer.setBasePackage("org.zero.mybatis.dao");
        configurer.setMarkerInterface(Mapper.class);
        return configurer;
    }
}