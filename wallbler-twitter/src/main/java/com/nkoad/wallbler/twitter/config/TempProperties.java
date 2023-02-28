package com.nkoad.wallbler.twitter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "temp-properties")
@Data
public class TempProperties {

    Integer aaa;

    Integer bbb;

    Boolean w;

    Boolean x;

    boolean y;

    boolean z;

}
