package com.hackathon.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource(ignoreResourceNotFound = true, value = "classpath:h2db.properties")
public class H2DBProp
{
    @Value("${H2DB.name}")
    public String dbName;

    @Value("${H2DB.schema.path}")
    public String dbSchemaPath;

    @Value("${H2DB.device.sql.path}")
    public String dbDeviceSqlPath;

}