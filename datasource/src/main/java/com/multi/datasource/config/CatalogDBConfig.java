package com.multi.datasource.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Data
@Component
public class CatalogDBConfig {

    private@Value("${database_url_1}") String dbUrl;
    private@Value("${database_url}") String dbUrlOrm;
    private@Value("${database_username_1}") String dbUsername;
    private@Value("${database_password_1}") String dbPassword;
}
