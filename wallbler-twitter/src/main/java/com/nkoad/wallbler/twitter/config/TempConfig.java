package com.nkoad.wallbler.twitter.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({TempProperties.class})
public class TempConfig {
}
