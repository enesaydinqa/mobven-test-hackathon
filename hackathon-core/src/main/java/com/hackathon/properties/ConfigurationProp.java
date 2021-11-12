package com.hackathon.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource(ignoreResourceNotFound = true, value = "classpath:configuration.properties")
public class ConfigurationProp
{
    public static ConfigurationProp configurationProp;

    @Autowired
    public void ConfigurationProp(ConfigurationProp configurationProp)
    {
        ConfigurationProp.configurationProp = configurationProp;
    }

    @Value("${H2DB.name}")
    public String dbName;

    @Value("${H2DB.schema.path}")
    public String dbSchemaPath;

    @Value("${H2DB.device.sql.path}")
    public String dbDeviceSqlPath;

    @Value("#{systemProperties['jira.open.issue'] ?:false}")
    public boolean isJiraOpenIssue;
}
