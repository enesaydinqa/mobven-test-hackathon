package com.hackathon.H2db;

import com.hackathon.properties.H2DBProp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class H2DBConfiguration
{
    @Autowired
    public H2DBProp h2DBProp;

    public static JdbcTemplate embedDB;

    @Bean()
    public DataSource dataSource()
    {
        var builder = new EmbeddedDatabaseBuilder();

        return builder
                .setName(h2DBProp.getDbName())
                .setType(EmbeddedDatabaseType.H2)
                .addScript(h2DBProp.getDbSchemaPath())
                .addScript(h2DBProp.getDbDeviceSqlPath())
                .build();
    }

    @Bean()
    public JdbcTemplate createJdbcTemplate()
    {
        embedDB = new JdbcTemplate();
        embedDB.setQueryTimeout(60);
        embedDB.setDataSource(dataSource());
        return embedDB;
    }
}