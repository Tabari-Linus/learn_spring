package com.mrlii.authwithpermissions.configuration.propertiesconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.mail")
public record EmailPropertiesConfig(
        String username,
        String from
) {
}
