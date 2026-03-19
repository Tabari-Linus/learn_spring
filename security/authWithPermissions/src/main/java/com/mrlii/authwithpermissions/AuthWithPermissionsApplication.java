package com.mrlii.authwithpermissions;

import com.mrlii.authwithpermissions.configuration.propertiesConfig.EmailPropertiesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(EmailPropertiesConfig.class)
public class AuthWithPermissionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthWithPermissionsApplication.class, args);
    }

}
